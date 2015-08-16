package ir.dayasoft.steelpars.Service;

import android.app.IntentService;
import android.content.Intent;

import java.util.List;

import ir.dayasoft.steelpars.Class.Branch;
import ir.dayasoft.steelpars.Class.Category;
import ir.dayasoft.steelpars.Class.CategoryColor;
import ir.dayasoft.steelpars.Class.City;
import ir.dayasoft.steelpars.Class.Color;
import ir.dayasoft.steelpars.Class.Image;
import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Class.ProductImage;
import ir.dayasoft.steelpars.Communication.ImageDownloader;
import ir.dayasoft.steelpars.Core.Core;

/**
 * Created by navid on 03/28/2015.
 */
public class IntentServicesGetAllData  extends IntentService {

    public IntentServicesGetAllData() {

        super("IntentServicesGetAllData");

    }


    @Override
    protected void onHandleIntent(Intent workIntent) {

        if (workIntent.equals(null))
            return;

        if (!Core.Communication.isConnectingToInternet(this))
            return;

        Color.SyncWithServer(this);

        Category.SyncWithServer(this);

        CategoryColor.SyncWithServer(this);

        Product.SyncWithServer(this,"-1","-1");

        ProductImage.SyncWithServer(this,"-1");

        City.SyncWithServer(this);

        Branch.SyncWithServer(this);

        List<String> urls= Image.GetAllImagesUrls(this);
        ImageDownloader imageDownloader=new ImageDownloader(null ,false,this);
        imageDownloader.execute(urls.toArray(new String[urls.size()]));

    }

    }
