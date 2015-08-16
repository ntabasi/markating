package ir.dayasoft.steelpars.Core;

import android.content.Context;
import android.util.Log;

import ir.dayasoft.steelpars.Communication.LogErrorAsyncTask;


public class LogError {


    static final Integer Error = 0;
    static final Integer No_Error = 1;

    Long ID;
    String ApplicationName;
    String ErrorMsg;
    String ErrorLocation;
    String CreateDate;
    String DeviceSn;
    Integer SenT;

    public static Integer getNo_Error() {
        return No_Error;
    }
    public static Integer getError() {
        return Error;
    }
    public Long getID() {
        return ID;
    }
    public void setID(Long ID) {
        this.ID = ID;
    }
    public String getApplicationName() {
        return ApplicationName;
    }
    public void setApplicationName(String applicationName) {
        ApplicationName = applicationName;
    }
    public String getErrorMsg() {
        return ErrorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }
    public String getErrorLocation() {
        return ErrorLocation;
    }
    public void setErrorLocation(String errorLocation) {
        ErrorLocation = errorLocation;
    }
    public String getCreateDate() {
        return CreateDate;
    }
    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
    public String getDeviceSn() {
        return DeviceSn;
    }
    public void setDeviceSn(String cellphoneSn) {
        DeviceSn = cellphoneSn;
    }
    public Integer getSenT() {
        return SenT;
    }
    public void setSenT(Integer senT) {
        SenT = senT;
    }


    static public Integer LogError(LogError error) {
        return 1;
    }


    public void commite(Context context) {
        LogErrorDataSource logErrorDataSource = new LogErrorDataSource(context);
        LogErrorAsyncTask logErrorAsyncTask = new LogErrorAsyncTask(context,this);
        LogError l = new LogError();
        try {
            logErrorDataSource.open();
            l = logErrorDataSource.InsertLogError(this);
            logErrorAsyncTask.execute();

            Log.i("log.id", String.valueOf(l.getID()));

        } catch (Exception e) {

        } finally
        {
            logErrorDataSource.close();
        }
    }
    public void UpdateSent(Context context) {
        LogErrorDataSource logErrorDataSource = new LogErrorDataSource(context);
        try {
            logErrorDataSource.open();
            logErrorDataSource.DeleteLogError(this.ID);
            logErrorDataSource.InsertLogError(this);
        } catch (Exception e) {

        } finally
        {
            logErrorDataSource.close();
        }
    }


}
