package ir.dayasoft.steelpars.Class;

import android.annotation.SuppressLint;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.CityDataSource;
import ir.dayasoft.steelpars.DataBase.DataBaseHelper;


public class City {

    private Long CityId;
    private String Name;
    private Long Cost;


    public static final String Json_City = "City";
    public static final String Tag = "database.Color";

    public Long getCityId() {
        return CityId;
    }
    public void setCityId(Long cityId) {
        CityId = cityId;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public Long getCost() {
        return Cost;
    }
    public void setCost(Long cost) {
        Cost = cost;
    }



    static public List<String> GetCityString (Context context) {
        List<String> stringList = new ArrayList<>();
        List<City> cityList = GetCity(context);
        for(City city: cityList) {
            stringList.add(city.getName());
        }
        return stringList;
    }
    static public City GetCustomerCity (Context context) {
        City returnValue =new City();
        CityDataSource cityDataSource= new CityDataSource(context);
        try {
            cityDataSource.open();
            returnValue = cityDataSource.GetSingleCity(AppConfig.GetCityId(context));
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCustomerCity");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cityDataSource.close();
        }
        return returnValue;
    }
    static public List<City> GetCity (Context context) {
        List<City> returnValue =new ArrayList<>();
        CityDataSource cityDataSource= new CityDataSource(context);
        try {
            cityDataSource.open();
            returnValue = cityDataSource.GetAllCity();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetCity");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cityDataSource.close();
        }
        return returnValue;
    }
    static public int InsertCity (Context context, List<City> cityList) {
        int returnValue = 0;
        CityDataSource cityDataSource= new CityDataSource(context);

        try {
            cityDataSource.open();
            returnValue = cityDataSource.InsertCity(cityList);
            if(returnValue != cityList.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertCity");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert City array. sizeOfCity:" + cityList.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertCity");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            cityDataSource.close();
            return returnValue;
        }
    }
    static public void ClearCity (Context context) {
        CityDataSource cityDataSource= new CityDataSource(context);
        try {
            cityDataSource.open();
            cityDataSource.ClearCity();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearCity");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            cityDataSource.close();
        }
    }
    static public void DeleteCity (Context context, long cityId) {
        CityDataSource cityDataSource= new CityDataSource(context);
        try {
            cityDataSource.open();
            cityDataSource.DeleteCity(cityId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteCity");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            cityDataSource.close();
        }
    }

    static public Integer SyncWithServer(Context context)
    {

        WebService ws=new WebService();


        String dec = AppConfig.GetDec(context);
        Boolean flag = false;

        String res = ws.GetCity(dec, Constants.Phrase );

        flag = CommandHandler.CommandValidation(res);
        if (flag) {
            List<City> resCity = CommandHandler.DecodeCommand.GetCity(res);
            City.ClearCity(context);
            City.InsertCity(context, resCity);
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;

    }



    @SuppressLint("NewApi")
    public static List<City> ReadAndParseJSON(String in) {
        List<City> returnValue = new ArrayList<>();

        try {

            JSONObject reader = new JSONObject(in);

            JSONArray myFunction = reader.getJSONArray(Json_City);

            for(int i=0; i<myFunction.length();i++) {
                City temp = new City();
                JSONObject jsonObject= myFunction.getJSONObject(i);
                temp.setCityId(jsonObject.getLong(DataBaseHelper.city_cityId));
                temp.setName(jsonObject.getString(DataBaseHelper.city_name));
                temp.setCost(jsonObject.getLong(DataBaseHelper.city_cost));
                returnValue.add(temp);
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return returnValue;
    }

    public static String toJSon(City city) {
        try {

            /*CityId;
            ng Name;
            Cost;*/

            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("CityId", city.getCityId()); // Set the first name/pair
            jsonObj.put("Name", city.getName());
            jsonObj.put("Cost", city.getCost());

            JSONObject jsonAdd = new JSONObject(); // we need another object to store the address
            jsonAdd.put("City",jsonObj);
            String d=jsonAdd.toString();
            return jsonAdd.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static String toJSon(List<City> cities) {
        try {

          JSONArray jsonArr = new JSONArray();

            for (City city : cities ) {
                JSONObject cityObj = new JSONObject();

                cityObj.put(DataBaseHelper.city_cityId, city.getCityId()); // Set the first name/pair
                cityObj.put(DataBaseHelper.city_name, city.getName());
                cityObj.put(DataBaseHelper.city_cost , city.getCost());


                jsonArr.put(cityObj);
            }

            JSONObject jsonAdd = new JSONObject(); // we need another object to store the address
            jsonAdd.put("City",jsonArr);
            String d=jsonAdd.toString();
            return jsonAdd.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }



    /*public static String toJSon(Person person) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("name", person.getName()); // Set the first name/pair
            jsonObj.put("surname", person.getSurname());

            JSONObject jsonAdd = new JSONObject(); // we need another object to store the address
            jsonAdd.put("address", person.getAddress().getAddress());
            jsonAdd.put("city", person.getAddress().getCity());
            jsonAdd.put("state", person.getAddress().getState());

            // We add the object to the main object
            jsonObj.put("address", jsonAdd);

            // and finally we add the phone number
            // In this case we need a json array to hold the java list
            JSONArray jsonArr = new JSONArray();

            for (PhoneNumber pn : person.getPhoneList() ) {
                JSONObject pnObj = new JSONObject();
                pnObj.put("num", pn.getNumber());
                pnObj.put("type", pn.getType());
                jsonArr.put(pnObj);
            }

            jsonObj.put("phoneNumber", jsonArr);

            return jsonObj.toString();

        }
        catch(JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

*/


}
