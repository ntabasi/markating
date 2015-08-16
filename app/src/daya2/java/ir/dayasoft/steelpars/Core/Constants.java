package ir.dayasoft.steelpars.Core;

import android.os.Environment;

import java.io.File;

public final class Constants {

	public static final String ImageDirectoryPath = Environment
			.getExternalStorageDirectory()
			+ File.separator
			+ "naser"
			+ File.separator + ".Images";


    public static final String DateOfOrigin = "1/2/1900 12:00:00 AM";


    public static final String DecSep = "*-*-*";
    public static final String Phrase = "$%Fee*%##D#*^FSFFXS@!CCFSSFRGFREGDFVVRVEC%433533edd";

    public static final Integer Situation_input = 0;
    public static final Integer Situation_output = 1;

 //   public static final String WebImageDirectoryPath = "http://test.omran118.com/";
    public static final String WebImageDirectoryPath = "http://daya2.dayaapp.ir/";

	public static  final String SOAP_ADDRESS = "http://daya2.dayaapp.ir/webservice.asmx";

	public static final int TimeFreqPushData = 60000;
	public static final int TimeFreqPushInvoice = 60000;

	public static final int MaxNumberOfPushNotification = 1;
	public static final int MaxNumberOfPushInvoice = 6;

	public static final Boolean IsGarsonPackage = false;
	public static final String SchoolId = "4";


    public static final String ApplicationName = "ir.dayasoft.dayaShop";
    public static final String AppVersion = "1";

	public static final String BROADCAST_ACTION_CheckInvoiceStatusService = "com.garson24.parstaraaz.CheckInvoiceStatus";

}
