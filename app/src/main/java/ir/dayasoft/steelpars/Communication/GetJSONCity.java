package ir.dayasoft.steelpars.Communication;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ir.dayasoft.steelpars.Class.City;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.Constants;


public class GetJSONCity extends AsyncTask<String, Integer, Integer> {


    Context context;
    String Location;
    List<City> cityList;
    ProgressDialog progressDialog;

    String res = "";
    String phrase;
    Boolean flag;



    public GetJSONCity(Context contextMain) {
        this.context = contextMain;
        this.Location = "http://naseri.dayasoft.ir/adminitration/city.aspx";
        //this.Location = "http://api.openweathermap.org/data/2.5/weather?q=tehran";
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    protected Integer doInBackground(String... urls) {

        phrase = Constants.Phrase;
        String dec = AppConfig.GetDec(context);
        flag = false;

        try {

            URL url = new URL(Location);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();

            String data = ConvertStreamToString(stream);

            cityList = City.ReadAndParseJSON(data);

            if(cityList.size()>0) {
                City.ClearCity(context);
                City.InsertCity(context, cityList);
            }

            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setCancelable(false);
        progressDialog.setMessage("درحال برقراری ارتباط");
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(Integer result) {
        progressDialog.dismiss();
    }

    static String ConvertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    protected void onProgressUpdate(Integer... progress) {
    }

}