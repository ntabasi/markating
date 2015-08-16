package ir.dayasoft.steelpars.Core;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import ir.dayasoft.steelpars.R;


public class Core {

    static public class Converter {

        static public String IntToString(Integer Value, Integer MaxLength) {
            Integer ValueLenght = Value.toString().length();

            String temp = "";


            for (int i = 0; i < MaxLength - ValueLenght; i++) {
                temp += "0";

            }

            return temp + Value.toString();
        }

        static public String LongToString(Long Value, Integer MaxLength) {

            Integer ValueLenght = Value.toString().length();
            String temp = "";

            for (int i = 0; i < MaxLength - ValueLenght; i++) {
                temp += "0";

            }

            return temp + Value.toString();
        }

        static public String DoubleToString(double value) {
            String returnValue;

            NumberFormat format = NumberFormat.getInstance();
            format.setMaximumFractionDigits(2);
            java.util.Currency currency = java.util.Currency.getInstance("USD");
            format.setCurrency(currency);

            returnValue = format.format(value);
            returnValue = returnValue.replace(".","/");

            return returnValue;
        }

        static public Integer DoubleToInt (double value) {
            Integer returnValue;

            NumberFormat nf = new DecimalFormat("##");
            returnValue = Integer.valueOf(nf.format(value));

            return returnValue;
        }

        public static Boolean IsDouble(String value) {
            return true;
        }
    }

    static public class Decript {

        static public String Decrypt(String text, String key) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] keyBytes = new byte[16];
                byte[] b = key.getBytes("UTF-8");
                int len = b.length;
                if (len > keyBytes.length)
                    len = keyBytes.length;
                System.arraycopy(b, 0, keyBytes, 0, len);
                SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

                org.kobjects.base64.Base64 decoder = new org.kobjects.base64.Base64();
                byte[] results = cipher.doFinal(decoder.decode(text));
                return new String(results, "UTF-8");
            } catch (Exception exception) {
                return "0";
            }
        }

        static public String Encrypt(String text, String key) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] keyBytes = new byte[16];
                byte[] b = key.getBytes("UTF-8");
                int len = b.length;
                if (len > keyBytes.length)
                    len = keyBytes.length;
                System.arraycopy(b, 0, keyBytes, 0, len);
                SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
                IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

                byte[] results = cipher.doFinal(text.getBytes("UTF-8"));

                String result = Base64.encodeToString(results, Base64.DEFAULT);
                return result;
            } catch (Exception exception) {
                return "0";
            }
        }
    }

    static public class Files {

        static public Boolean Exists(String Path) {
            File file = new File(Path);

            return file.exists();

        }

        static public Boolean CreateDirectory(String DirName)

        {
            File direct = new File(DirName);

            if (!direct.exists()) {
                if (direct.mkdir()) {
                    return true;
                }

            }
            return false;
        }
    }

    static public class Images {
        private static String ImageDirectorypath = Environment
                .getExternalStorageDirectory() + File.separator + "sm24";

        static public Bitmap getImage(String ImageName, String DefaultImage,
                                      Context context)

        {
            // BitmapFactory.Options options = new BitmapFactory.Options();
            // options.inJustDecodeBounds = true;

            File file = new File(ImageDirectorypath + File.separator
                    + ImageName);
            if (file.exists()) {
                Bitmap bmp = Images.decodeSampledBitmapFromFile(
                        file.getAbsolutePath(), 50, 50); // ;
                // BitmapFactory.decodeFile(file.getAbsolutePath());
                file.exists();
                return bmp;
            } else {
                Files.CreateDirectory(ImageDirectorypath);
                int id = context.getResources().getIdentifier(
                        "drawable/" + DefaultImage, "drawable",
                        context.getPackageName());

                Bitmap bmp = Images.decodeSampledBitmapFromResource(
                        context.getResources(), id, 50, 50); // BitmapFactory.decodeResource(
                // context.getResources(), id);

                return bmp;
            }

        }

        public static int calculateInSampleSize(BitmapFactory.Options options,
                                                int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                // Calculate ratios of height and width to requested height and
                // width
                final int heightRatio = Math.round((float) height
                        / (float) reqHeight);
                final int widthRatio = Math.round((float) width
                        / (float) reqWidth);

                // Choose the smallest ratio as inSampleSize value, this will
                // guarantee
                // a final image with both dimensions larger than or equal to
                // the
                // requested height and width.
                inSampleSize = heightRatio < widthRatio ? heightRatio
                        : widthRatio;
            }

            return inSampleSize;
        }

        public static Bitmap decodeSampledBitmapFromFile(String path,
                                                         int reqWidth, int reqHeight) {

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(path, options);
        }

        public static Bitmap decodeSampledBitmapFromResource(Resources res,
                                                             int resId, int reqWidth, int reqHeight) {

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, resId, options);
        }

    }

    static public class Dates {

    /*
            String longV = "1343805819061";
            long millisecond = Long.parseLong(longV);
            String dateString= DateFormat.format("MM/dd/yyyy", new Date(millisecond)).toString();*/

        static final long ONE_MINUTE_IN_MILLIS = 60000;
        final static private int baseYear = 1392;
        private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
        static private String DateFormatString = "yyyy-MM-dd hh:mm";
        static private String DateFormatString2 = "MM/dd/yyyy kk:mm:ss";
        // static private String DateFormatString = "yyyy-MM-dd kk:mm:ss";
        // static private String LongTimeFormatString = "hh:mm:ss";
        static private String ShortTimeFormatString = "hh:mm";

        static public String GetEnDate(String str) {

            char[] chars = new char[str.length()];
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                int index = arabic.indexOf(ch);
                if (index >= 0)
                    ch = (char) (index + '0');
                chars[i] = ch;
            }
            return new String(chars);
        }

        static public String GetCurentDate() {

            GregorianCalendar calendar = new GregorianCalendar(Locale.ENGLISH);
            Date d = calendar.getTime();

            CharSequence s = DateFormat.format(DateFormatString, d.getTime());

            return s.toString();

        }

        static public String GetCurentDate2() {

            GregorianCalendar calendar = new GregorianCalendar(Locale.ENGLISH);
            Date d = calendar.getTime();

            CharSequence s = DateFormat.format(DateFormatString2, d.getTime());

            return s.toString();

        }

        static public String GetCurrentDate3() {

            GregorianCalendar calendar = new GregorianCalendar(Locale.ENGLISH);
            Date d = calendar.getTime();

            CharSequence s = DateFormat.format(DateFormatString, d.getTime());

            return GetEnDate(s.toString());

        }

        static public Boolean IsActive() {

            String t1 = Dates.GetCurrentDate3();
            String t2 = "01/20/2014 18:27:34";
            if (Dates.IsBefor(t1, t2))
                return true;
            else
                return false;
        }

        static public Boolean IsBefor(String date1Str, String date2Str) {

            Date d1 = new Date();
            Date d2 = new Date();

            date1Str= date1Str.substring(0,date1Str.length()-3);
            date2Str= date2Str.substring(0,date2Str.length()-3);


            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString, Locale.US);

            try {
                d1 = curFormater.parse(date1Str);
                d2 = curFormater.parse(date2Str);
            } catch (Exception e) {
            }

            return d1.before(d2);

        }

        static public Boolean IsAftar(String date1Str, String date2Str) {

            Date d1 = new Date();
            Date d2 = new Date();
            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString2, Locale.US);

            try {
                d1 = curFormater.parse(date1Str);
                d2 = curFormater.parse(date2Str);
            } catch (Exception e) {
                // TODO: handle exception
            }

            return d1.after(d2);

        }

        static public String GetShamsiCurentDate() {

            String dateStr = GetCurentDate();

            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString, Locale.US);
            try {
                Date dateObj = curFormater.parse(dateStr);
                Utilities util = new Utilities();
                Utilities.SolarCalendar sc = util.new SolarCalendar(dateObj);

                Locale loc = new Locale("en_US");
                return String.valueOf(sc.year) + "-"
                        + String.format(loc, "%02d", sc.month) + "-"
                        + String.format(loc, "%02d", sc.date) + " "
                        + String.format(loc, "%02d", dateObj.getHours()) + ":"
                        + String.format(loc, "%02d", dateObj.getMinutes())
                        + ":"
                        + String.format(loc, "%02d", dateObj.getSeconds());

            } catch (ParseException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "";
        }

        static public String ConvertMiladiToShamsi(Date MiladiDate,
                                                   Boolean ShortFormat) {

            CharSequence s = DateFormat.format(DateFormatString, MiladiDate);
            String dateStr = s.toString();

            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString, Locale.US);
            try {
                Date dateObj = curFormater.parse(dateStr);
                Utilities util = new Utilities();
                Utilities.SolarCalendar sc = util.new SolarCalendar(dateObj);

                Locale loc = new Locale("en_US");
                if (ShortFormat)
                    return String.valueOf(sc.year) + "-"
                            + String.format(loc, "%02d", sc.month) + "-"
                            + String.format(loc, "%02d", sc.date);

                else
                    return String.valueOf(sc.year) + "-"
                            + String.format(loc, "%02d", sc.month) + "-"
                            + String.format(loc, "%02d", sc.date) + " "
                            + String.format(loc, "%02d", dateObj.getHours())
                            + ":"
                            + String.format(loc, "%02d", dateObj.getMinutes())
                            + ":"
                            + String.format(loc, "%02d", dateObj.getSeconds());

            } catch (ParseException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "";
        }

        static public String ConvertMiladiToShamsi(String MiladiDate,
                                                   Boolean ShortFormat) {

            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString, Locale.US);
            try {
                Date dateObj = curFormater.parse(MiladiDate);

                Utilities util = new Utilities();
                Utilities.SolarCalendar sc = util.new SolarCalendar(dateObj);

                Locale loc = new Locale("en_US");
                if (ShortFormat)
                    return String.valueOf(sc.year) + "-"
                            + String.format(loc, "%02d", sc.month) + "-"
                            + String.format(loc, "%02d", sc.date);

                else
                    return String.valueOf(sc.year) + "-"
                            + String.format(loc, "%02d", sc.month) + "-"
                            + String.format(loc, "%02d", sc.date) + " "
                            + String.format(loc, "%02d", dateObj.getHours())
                            + ":"
                            + String.format(loc, "%02d", dateObj.getMinutes())
                            + ":"
                            + String.format(loc, "%02d", dateObj.getSeconds());

            } catch (ParseException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "";
        }

        static public String AddHoursToDate(String Date, Integer Hours) {
            String dt = GetCurentDate(); // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(DateFormatString,
                    Locale.US);
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(dt));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            c.add(Calendar.HOUR, Hours); // number of days to add
            dt = sdf.format(c.getTime());


            return dt;
        }

        static public String AddMinutesToDate(String Date, Integer minutes) {
            String dt = GetCurentDate2(); // Start date
            SimpleDateFormat sdf = new SimpleDateFormat(DateFormatString2,
                    Locale.US);


            Calendar c = Calendar.getInstance();
            Date date = new Date();
            try {
                c.setTime(sdf.parse(dt));
                date = sdf.parse(Date);
                long t = date.getTime();
                Date afterAddingTenMins = new Date(t + (10 * ONE_MINUTE_IN_MILLIS));

                dt = sdf.format(afterAddingTenMins);


            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            c.add(Calendar.MINUTE, minutes); // number of days to add
            //	dt = sdf.format(c.getTime());


            return dt;
        }

        static public String GetLongTime(String DateString) {

            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString, Locale.US);
            try {

                Date dateObj = curFormater.parse(DateString);

                Locale loc = new Locale("en_US");
                return String.format(loc, "%02d", dateObj.getHours()) + ":"
                        + String.format(loc, "%02d", dateObj.getMinutes())
                        + ":"
                        + String.format(loc, "%02d", dateObj.getSeconds());

            } catch (ParseException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "";
        }

        static public String GetShortTime(String DateString) {

            SimpleDateFormat curFormater = new SimpleDateFormat(
                    DateFormatString, Locale.US);
            try {

                Date dateObj = curFormater.parse(DateString);
                Locale loc = new Locale("en_US");

                return String.format(loc, "%02d", dateObj.getHours()) + ":"
                        + String.format(loc, "%02d", dateObj.getMinutes());

            } catch (ParseException e) {

                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return "";
        }

        static public String GetshamsiDate(int year, int month, int day) {
            int realyear = year + baseYear + 1;
            int realmonth = month + 1;
            int realday = day + 1;

            String res = realyear + "/" + realmonth + "/" + realday;

            return res;
        }

        static public Boolean CheckshamsiDateValid(int year, int month, int day) {
            // int realyear = year + baseYear;
            int realmonth = month + 1;
            int realday = day + 1;

            if (realmonth > 6 && realday > 30)
                return false;
            if (realmonth == 12 && realday > 29)
                return false;

            return true;
        }

        static public String buildDateString(int year, int month, int day) {
            int realyear = year + baseYear;
            int realmonth = month + 1;
            int realday = day + 1;

            Locale loc = new Locale("en_US");

            String res = String.format(loc, "%02d", realyear) + "/"
                    + String.format(loc, "%02d", realmonth) + "/"
                    + String.format(loc, "%02d", realday);

            return res;

        }

        static  public int GetDayOfWeek(String date)
        {


            Calendar calendar = new GregorianCalendar(2008, 01, 01); // Note that Month value is 0-based. e.g., 0 for January.

            Date dateTemp = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat(DateFormatString);
            try
            {
                dateTemp = date_format.parse(date);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            calendar.setTime(dateTemp);

            int reslut = calendar.get(Calendar.DAY_OF_WEEK);

            return reslut;
        }


        static  public int GetAshkanDayOfWeek(String date)
        {


            Calendar calendar = new GregorianCalendar(2008, 01, 01); // Note that Month value is 0-based. e.g., 0 for January.

            Date dateTemp = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat(DateFormatString);
            try
            {
                dateTemp = date_format.parse(date);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }

            calendar.setTime(dateTemp);

            int reslut = calendar.get(Calendar.DAY_OF_WEEK);


            if (reslut > 6) {
                reslut = reslut - 7;
            }

            return reslut;
        }
        static public int GetDay(String date) {
            Calendar calendar = new GregorianCalendar(2008, 01, 01); // Note that Month value is 0-based. e.g., 0 for January.

            Date dateTemp = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat(DateFormatString);

            try {
                dateTemp = date_format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            calendar.setTime(dateTemp);

            int reslut = calendar.get(Calendar.DAY_OF_MONTH);

            return reslut;
        }

        static public int GetMonthOfYear(String date) {
            Calendar calendar = new GregorianCalendar(2008, 01, 01); // Note that Month value is 0-based. e.g., 0 for January.

            Date dateTemp = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat(DateFormatString);

            try {
                dateTemp = date_format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            calendar.setTime(dateTemp);

            int reslut = calendar.get(Calendar.MONTH);

            return reslut;
        }

        static public int GetYear(String date) {
            Calendar calendar = new GregorianCalendar(2008, 01, 01); // Note that Month value is 0-based. e.g., 0 for January.

            Date dateTemp = new Date();
            SimpleDateFormat date_format = new SimpleDateFormat(DateFormatString);
            try {
                dateTemp = date_format.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            calendar.setTime(dateTemp);

            int reslut = calendar.get(Calendar.YEAR);

            return reslut;
        }

        static public List<String> GetShamsiDateSeparated(String date, Context context) {

            int dayWeekInx = GetDayOfWeek(date);
            if (dayWeekInx > 6) {
                dayWeekInx = dayWeekInx - 6;
            }

            int monthInx = 2;
            String ShamsiDate = ConvertMiladiToShamsi(date, false);
            String monthInt = ShamsiDate.substring(5, 7);
            try {
                monthInx = Integer.valueOf(monthInt);
            } catch (Exception e) {}

            int dayInx = 3;
            String dayInt = ShamsiDate.substring(8, 10);
            try {
                dayInx = Integer.valueOf(dayInt);
            } catch (Exception e) {}


            String day = context.getResources().getStringArray(R.array.day_array)[dayInx - 1];
            String dayWeek = context.getResources().getStringArray(R.array.week_array)[dayWeekInx];
            String month = context.getResources().getStringArray(R.array.month_array)[monthInx - 1];


            List<String> DateItem = new ArrayList<String>();
            DateItem.add(day);
            DateItem.add(dayWeek);
            DateItem.add(month);
            DateItem.add(String.valueOf(dayInx));

            return DateItem;
        }

    }

    static public class Fonts {
        static public String test() {

            List<String> sd = new ArrayList<String>();
            sd.add("12");
            sd.add("10");
            sd.add("1112");

            Set<String> inputtest = new HashSet<String>(sd);
            // inputtest.addAll(sd);

            // List<String> sd1=inputtest.iterator();

            List<String> list = new ArrayList<String>(inputtest);
            return list.get(0);
        }
    }

    static public class Currency {

        public static String AddComma(String value) {

            return value;
        }

        public static String AddPoint(String value) {

            return value;
        }

        public static String AddZero(String value) {
            if (value != "" && value != "0" && Converter.IsDouble(value)) {

                DecimalFormat numberFormat = new DecimalFormat("#");

                return numberFormat.format(Double.valueOf(value) * 1000);

            }
            return value;
        }

        public static String AddZero(Double value) {
            if (value != 0.0) {

                Locale loc = new Locale("en_US");
                NumberFormat numberFormat = NumberFormat
                        .getCurrencyInstance(loc);
                numberFormat = new DecimalFormat("#");

                return numberFormat.format(value * 1000);

            }
            return "0";
        }

        public static String RemoveZero(Double value) {
            if (value != 0.0)
                return String.valueOf(value / 1000);
            return "0";
        }

        public static String RemoveZero(String value) {
            if (value != "0")
                return String.valueOf(Double.valueOf(value) / 1000);
            return "0";
        }

        public static String AddCommaNoZero(String value) {

            return value;
        }

        public static String RemovePoint(String value) {

            return "0";
        }

        public static String RemoveComma(String value) {

            return "0";
        }

    }

    static public class StringOpration {
        static public Integer OccurrencesOfSubstring(String string,
                                                     String subString) {
            int lastIndex = 0;
            int count = 0;

            while (lastIndex != -1) {

                lastIndex = string.indexOf(subString, lastIndex);

                if (lastIndex != -1) {
                    count++;
                    lastIndex += subString.length();
                }

            }

            return count;
        }

    }

    static public class Communication {

        static public Boolean CheckWifiStatus(Context context) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            Boolean isWifi = manager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

            NetworkInfo info = (NetworkInfo) ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (isWifi && (info != null || info.isConnected())) {
                return true;
            }

            return false;

        }

        static public Boolean Check3gStatus(Context context) {

            return false;
        }

        static public Boolean CheckGprsStatus(Context context) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            Boolean isWifi = manager.getNetworkInfo(
                    ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
            NetworkInfo info = (NetworkInfo) ((ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();
            if (!isWifi && (info != null && info.isConnected())) {

                return true;
            }

            return false;
        }

        static public Boolean isConnectingToInternet(Context context) {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }
            return false;
        }


        static public Boolean isConnectingToInternet2(Context context) {

            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (!isInternetAvailable())
                return false;


            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }

            }


            return false;
        }



        static public boolean isInternetAvailable() {
            try {

                InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

                if (ipAddr.equals("") ) {
                    return false;
                } else {
                    return true;
                }

            } catch (Exception e) {
                return false;
            }

        }



    }

    static public class PhoneInfo {

        static public String GetDeviceId(Context context) {
            try {
                TelephonyManager mgr = (TelephonyManager) context
                        .getSystemService(context.TELEPHONY_SERVICE);
                String deviceId = mgr.getDeviceId();
                if (deviceId.equals(null) || "".equals(deviceId))
                    return "111";
                return deviceId;
            } catch (Exception exception) {
                return "111";
            }
        }

        static public String GetDeviceSoftwareVersion(Context context) {
            try {
                return "resid:" + Constants.SchoolId + "ver:1.0";
            } catch (Exception exception) {
                return "111";

            }

        }

        static public String GetSimSerialNumber(Context context) {

            try {
                TelephonyManager mgr = (TelephonyManager) context
                        .getSystemService(context.TELEPHONY_SERVICE);
                String SimSerialNumber = mgr.getSimSerialNumber();
                if (SimSerialNumber.equals(null) || "".equals(SimSerialNumber))
                    return "111";
                return SimSerialNumber;
            } catch (Exception exception) {
                return "111";
            }
        }

    }

    static public class Email {
        public static boolean isEmailValid(String email) {
            boolean isValid = false;

            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            CharSequence inputStr = email;

            Pattern pattern = Pattern.compile(expression,
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                isValid = true;
            }
            return isValid;
        }
    }

    static public class ApplicationLuncher {

        static void startAppFromPackageName(String packageName, Context context) {
            Intent mIntent = context.getPackageManager()
                    .getLaunchIntentForPackage(packageName);

            if (mIntent != null) {
                try {
                    context.startActivity(mIntent);
                } catch (ActivityNotFoundException err) {
                    Log.i("tag", "the App : " + packageName
                            + " is not installed");
                }
            } else {
                Log.i("tag", "the App : " + packageName + " is not installed");
            }
        }

        static void installAppFromPackageName(String APKName, Context context) {
            // copyAssets(APKName, context);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(
                    Uri.fromFile(new File(Constants.ImageDirectoryPath + "/"
                            + APKName)),
                    "application/vnd.android.package-archive");
            context.startActivity(intent);
        }

        static public boolean appInstalledOrNot(String uri, Context context) {
            PackageManager pm = context.getPackageManager();
            boolean app_installed = false;
            try {
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
                app_installed = true;
            } catch (PackageManager.NameNotFoundException e) {
                app_installed = false;
            }
            return app_installed;
        }

        static public void copyAssets(Context context) {
            AssetManager assetManager = context.getAssets();
            String[] files = null;
            try {
                files = assetManager.list("");
                File directory = new File(Constants.ImageDirectoryPath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
            } catch (IOException e) {
                Log.e("tag", "Failed to get asset file list.", e);
            }

            for (String filename : files) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")) {
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = assetManager.open(filename);
                        File outFile = new File(Constants.ImageDirectoryPath,
                                filename);
                        out = new FileOutputStream(outFile);
                        copyFile(in, out);
                        in.close();
                        in = null;
                        out.flush();
                        out.close();
                        out = null;
                    } catch (IOException e) {
                        Log.e("tag", "Failed to copy asset file: " + filename,
                                e);
                    }
                }
            }
        }

        static private void copyFile(InputStream in, OutputStream out)
                throws IOException {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }

    }

    static public class Share {

        static public Boolean SharePlainText(String subject, String body, Context context) {

            try {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, body);
                context.startActivity(Intent.createChooser(sharingIntent, "اشتراک با"));
            } catch (Exception exception) {
                return false;
            }

            return true;

        }


    }
}

