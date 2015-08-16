package ir.dayasoft.steelpars.Core.DayaImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Handler;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    // default image show in list (Before online image download)
  //  final int stub_id = R.drawable.default_image_group;
    // Initialize MemoryCache
    MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    ExecutorService executorService;
    Boolean maskImage;
    Boolean maskImageBackGround;
    Context MyContext;

    int Border;
    int Mask;


    //handler to display images in UI thread
    Handler handler = new Handler();
    private HashMap<Integer,Integer> defaultImageType=new HashMap<>();
    //Create Map (collection) to store image and image url in key value pair
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());


    public ImageLoader(Context context,
                       HashMap <Integer,Integer> defaultImageType,
                       Boolean maskImage,
                       Boolean maskImageBackGround,
                       int border,
                       int mask) {

        fileCache = new FileCache(context);
        this.defaultImageType=defaultImageType;
        this.maskImage=maskImage;
        this.maskImageBackGround=maskImageBackGround;
        MyContext=context;
        // Creates a thread pool that reuses a fixed number of
        // threads operating off a shared unbounded queue.
        executorService = Executors.newFixedThreadPool(5);
        Border = border;
        Mask = mask;

    }

    public ImageLoader(Context context,
                       HashMap <Integer,Integer> defaultImageType,
                       Boolean maskImage,
                       Boolean maskImageBackGround) {

        fileCache = new FileCache(context);
        this.defaultImageType=defaultImageType;
        this.maskImage=maskImage;
        this.maskImageBackGround=maskImageBackGround;
        MyContext=context;
        // Creates a thread pool that reuses a fixed number of
        // threads operating off a shared unbounded queue.
        executorService = Executors.newFixedThreadPool(5);
    }

    public void     DisplayImage(String url, ImageView imageView,Integer defaultImage) {
        //Store image and url in Map
        imageViews.put(imageView, url);

        //Check image is stored in MemoryCache Map or not (see MemoryCache.java)
        Bitmap bitmap = memoryCache.get(url);

        if (bitmap != null) {
            // if image is stored in MemoryCache Map then
            // Show image in listview row
            if (maskImage)
            makeMaskImage(imageView,bitmap);
            else
            imageView.setImageBitmap(bitmap);

        } else {
            //queue Photo to download from url
            queuePhoto(url, imageView);

            //Before downloading image show default image

            if (maskImage)
                makeMaskImage(imageView,defaultImage);
            else
                imageView.setImageResource(defaultImage);
          }
    }

    private void queuePhoto(String url, ImageView imageView) {
        // Store image and url in PhotoToLoad object
        PhotoToLoad p = new PhotoToLoad(url, imageView);

        // pass PhotoToLoad object to PhotosLoader runnable class
        // and submit PhotosLoader runnable to executers to run runnable
        // Submits a PhotosLoader runnable task for execution  

        executorService.submit(new PhotosLoader(p));
    }

    private Bitmap getBitmap(String url) {
        File f = fileCache.getFile(url);

        //from SD cache
        //CHECK : if trying to decode file which not exist in cache return null
        Bitmap b = decodeFile(f);
        if (b != null)
            return b;

        // Download image file from web
        try {

            Bitmap bitmap = null;
            URL imageUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setInstanceFollowRedirects(true);
            InputStream is = conn.getInputStream();

            // Constructs a new FileOutputStream that writes to file
            // if file not exist then it will create file
            OutputStream os = new FileOutputStream(f);

            // See Utils class CopyStream method
            // It will each pixel from input stream and
            // write pixels to output stream (file)
            Utils.CopyStream(is, os);

            os.close();
            conn.disconnect();

            //Now file created and going to resize file with defined height
            // Decodes image and scales it to reduce memory consumption
            bitmap = decodeFile(f);

            return bitmap;

        } catch (Throwable ex) {
            ex.printStackTrace();
            if (ex instanceof OutOfMemoryError)
                memoryCache.clear();
            return null;
        }
    }

    //Decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {

        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1 = new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();

            //Find the correct scale value. It should be the power of 2.

            // Set width/height of recreated image
            final int REQUIRED_SIZE = 1000000;//todo change qaulity

            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            //decode with current scale values
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean imageViewReused(PhotoToLoad photoToLoad) {

        String tag = imageViews.get(photoToLoad.imageView);
        //Check url is already exist in imageViews MAP
        if (tag == null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }

    public void clearCache() {
        //Clear cache directory downloaded images and stored data in maps
        memoryCache.clear();
        fileCache.clear();
    }

    //Task for the queue
    private class PhotoToLoad {
        public String url;
        public ImageView imageView;

        public PhotoToLoad(String u, ImageView i) {
            url = u;
            imageView = i;
        }
    }

    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            try {
                //Check if image already downloaded
                if (imageViewReused(photoToLoad))
                    return;
                // download image from web url
                Bitmap bmp = getBitmap(photoToLoad.url);

                // set image data in Memory Cache
                memoryCache.put(photoToLoad.url, bmp);

                if (imageViewReused(photoToLoad))
                    return;

                // Get bitmap to display
                BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);

                // Causes the Runnable bd (BitmapDisplayer) to be added to the message queue.
                // The runnable will be run on the thread to which this handler is attached.
                // BitmapDisplayer run method will call
                handler.post(bd);

            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    //Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;

        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
            bitmap = b;
            photoToLoad = p;
        }

        public void run() {
            if (imageViewReused(photoToLoad))
                return;

            // Show bitmap on UI
            if (bitmap != null)



            if (maskImage)
            {

                makeMaskImage( photoToLoad.imageView,bitmap);
            }
            else
            {
                photoToLoad.imageView.setImageBitmap(bitmap);
            }
            //else
             //   photoToLoad.imageView.setImageResource(stub_id);
        }
    }


    public void makeMaskImage(ImageView mImageView, int mContent)
    {
        Bitmap original = BitmapFactory.decodeResource(MyContext.getResources(), mContent);
        Bitmap mask = BitmapFactory.decodeResource(MyContext.getResources(), Mask);
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, 0, 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        mImageView.setImageBitmap(result);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (maskImageBackGround)
            mImageView.setBackgroundResource(Border);
    }

    public void makeMaskImage(ImageView mImageView, Bitmap original1) {
        // Bitmap original = BitmapFactory.decodeResource(context.getResources(), mContent);

        Bitmap mask = BitmapFactory.decodeResource(MyContext.getResources(), Mask);

        Bitmap original=Bitmap.createScaledBitmap   (original1,mask.getWidth(), mask.getHeight(),true);

        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, 0, 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        mImageView.setImageBitmap(result);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
       if (maskImageBackGround)
        mImageView.setBackgroundResource(Border);
    }



}
