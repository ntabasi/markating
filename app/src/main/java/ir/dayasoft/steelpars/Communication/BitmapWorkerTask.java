package ir.dayasoft.steelpars.Communication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.File;
import java.lang.ref.WeakReference;

import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;


public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
	
	private final WeakReference<ImageView> imageViewReference;
    Integer defaultImageResourceId;

	public BitmapWorkerTask(ImageView imageView,Integer defaultImageResourceId, Context context) {
		// Use a WeakReference to ensure the ImageView can be garbage collected
		imageViewReference = new WeakReference<ImageView>(imageView);
        this.defaultImageResourceId=defaultImageResourceId;

	}

	// Decode image in background.
	@Override
	protected Bitmap doInBackground(String... params) {

		File file = new File(Constants.ImageDirectoryPath + File.separator
				+ params[0]);
		Bitmap bmp = null;
		if (file.exists()) {
			bmp = Core.Images.decodeSampledBitmapFromFile(file.getAbsolutePath(),
                    350, 350); // ;

			file.exists();

		}

		return bmp;
	}

	// Once complete, see if ImageView is still around and set bitmap.
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		if (imageViewReference != null)
        {
            ImageView imageView = imageViewReference.get();

        if ( bitmap != null) {

			if (imageView != null) {
				imageView.setImageBitmap(bitmap);
			}
		}
        else
        {
         //   Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.);
            imageView.setImageResource (defaultImageResourceId);
        }
        }
	}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
