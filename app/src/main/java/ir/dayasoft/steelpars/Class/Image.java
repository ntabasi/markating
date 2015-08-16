package ir.dayasoft.steelpars.Class;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Core.Constants;


/**
 * Created by navid on 03/28/2015.
 */
public class Image {

    static private List<String> FilterExistsImges(List<String> images) {
        List<String> res = new ArrayList<String>();
        for (String image : images) {
            if (!image.equals("-1")) {
                File file = new File(Constants.ImageDirectoryPath
                        + File.separator + image);
                if (!file.exists()) {
                    res.add(image);
                }
            }

        }
        return res;

    }


    static public List<String> GetAllImagesUrls(Context context) {

        List<String> res = new ArrayList<String>();

        res.addAll(Product.GetAllImagesUrl(context));
        res.addAll(Category.GetAllImagesUrl(context));


        // /old images that not use in database delete from sdcard
      //  DeleteOldImages(res);

        List<String> filterRes = new ArrayList<String>();
        filterRes = FilterExistsImges(res);


        return filterRes;

    }

    static private Boolean DeleteOldImages(List<String> CurrentImages) {

        File folder = new File(Constants.ImageDirectoryPath);

        try {

            File[] allFiles = folder.listFiles();

            for (File file : allFiles) {

                String nameTemp = file.getName();
                if (!CurrentImages.contains(nameTemp)) {
                    file.delete();
                }
                Log.i("delete old Images", "complete" + file.getName());
            }

        } catch (Exception e) {
            Log.i("delete old Images",
                    Constants.ImageDirectoryPath + e.getMessage());
        }

        return true;

    }

    static private Boolean DeleteImages(List<String> imagesPath) {

        for (String image : imagesPath) {
            File file = new File(Constants.ImageDirectoryPath + File.separator
                    + image);

            file.deleteOnExit();

        }

        return true;

    }


}
