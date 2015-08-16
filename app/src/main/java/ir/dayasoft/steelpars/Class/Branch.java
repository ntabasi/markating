package ir.dayasoft.steelpars.Class;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ir.dayasoft.steelpars.Communication.WebService;
import ir.dayasoft.steelpars.Core.AppConfig;
import ir.dayasoft.steelpars.Core.CommandHandler;
import ir.dayasoft.steelpars.Core.Constants;
import ir.dayasoft.steelpars.Core.Core;
import ir.dayasoft.steelpars.Core.LogError;
import ir.dayasoft.steelpars.DataBase.BranchDataSource;


public class Branch {

    Long BranchId;
    String Name;
    Integer Status;
    String Address;
    String Phone;
    String WebSite;
    String Cellphone;
    String OpenTime1;
    String OpenTime2;
    String CloseTime1;
    String CloseTime2;
    String Description;
    Double Rate;
    Integer Votes;
    Integer VotesCount;
    Integer OutOfService;
    String OutOfServiceCause;
    String UpdateDate;//rfgerferfyyuy




    public static final String Tag = "database.Branch";

    public Long getBranchId() {
        return BranchId;
    }
    public void setBranchId(Long branchId) {
        BranchId = branchId;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getPhone() {
        return Phone;
    }
    public void setPhone(String phone) {
        Phone = phone;
    }
    public String getCellphone() {
        return Cellphone;
    }
    public void setCellphone(String cellphone) {
        Cellphone = cellphone;
    }
    public Integer getStatus() {
        return Status;
    }
    public void setStatus(Integer status) {
        Status = status;
    }
    public String getWebSite() {
        return WebSite;
    }
    public void setWebSite(String webSite) {
        WebSite = webSite;
    }
    public String getOpenTime1() {
        return OpenTime1;
    }
    public void setOpenTime1(String openTime1) {
        OpenTime1 = openTime1;
    }
    public String getOpenTime2() {
        return OpenTime2;
    }
    public void setOpenTime2(String openTime2) {
        OpenTime2 = openTime2;
    }
    public String getCloseTime1() {
        return CloseTime1;
    }
    public void setCloseTime1(String closeTime1) {
        CloseTime1 = closeTime1;
    }
    public String getCloseTime2() {
        return CloseTime2;
    }
    public void setCloseTime2(String closeTime2) {
        CloseTime2 = closeTime2;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public Double getRate() {
        return Rate;
    }
    public void setRate(Double rate) {
        Rate = rate;
    }
    public Integer getVotes() {
        return Votes;
    }
    public void setVotes(Integer votes) {
        Votes = votes;
    }
    public Integer getVotesCount() {
        return VotesCount;
    }
    public void setVotesCount(Integer votesCount) {
        VotesCount = votesCount;
    }
    public Integer getOutOfService() {
        return OutOfService;
    }
    public void setOutOfService(Integer outOfService) {
        OutOfService = outOfService;
    }
    public String getOutOfServiceCause() {
        return OutOfServiceCause;
    }
    public void setOutOfServiceCause(String outOfServiceCause) {
        OutOfServiceCause = outOfServiceCause;
    }
    public String getUpdateDate() {
        return UpdateDate;
    }
    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    static public List<Branch> FillSampleBranch () {
        List<Branch> returnValue= new ArrayList<>();

        Branch tmp = new Branch();
        tmp.setBranchId(Long.valueOf(1));
        tmp.setName("ورزنده");
        tmp.setPhone("0915551513333");
        tmp.setAddress("مشهد، احمد آباد");
        returnValue.add(tmp);

        tmp = new Branch();
        tmp.setBranchId(Long.valueOf(2));
        tmp.setName("قاسمی");
        tmp.setPhone("38554244");
        tmp.setAddress("بابل، خیابان جنگلبانی، جنب قنادی");
        returnValue.add(tmp);

        tmp=new Branch();
        tmp.setBranchId(Long.valueOf(3));
        tmp.setName("قائمی");
        tmp.setPhone("09399159900");
        tmp.setAddress("تهران، میدان پاستور، کنار گل فروشی ضعیم");
        returnValue.add(tmp);

        tmp=new Branch();
        tmp.setBranchId(Long.valueOf(4));
        tmp.setName("دلبری");
        tmp.setPhone("021555886688");
        tmp.setAddress("سمنان، کنار جاده اصلی");

        return returnValue;
    }

    static public List<String> GetBranchString (Context context) {
        List<String> returnValue  =new ArrayList<>();
        List<Branch> branchList = new ArrayList<>();
        BranchDataSource branchDataSource = new BranchDataSource(context);
        try {
            branchDataSource.open();
            branchList = branchDataSource.GetAllBranch();
            for(Branch branch : branchList) {
                returnValue.add(branch.getName());
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetBranchString");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            branchDataSource.close();
        }
        return returnValue;
    }
    static public Branch GetSingleBranch (Context context, long branchId) {
        Branch returnValue  =new Branch();
        BranchDataSource branchDataSource = new BranchDataSource(context);
        try {
            branchDataSource.open();
            returnValue = branchDataSource.GetSingleBranch(branchId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetSingleBranch");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            branchDataSource.close();
        }
        return returnValue;
    }
    static public List<Branch> GetBranch (Context context) {
        List<Branch> returnValue =new ArrayList<>();
        BranchDataSource branchDataSource = new BranchDataSource(context);
        try {
            branchDataSource.open();
            returnValue = branchDataSource.GetAllBranch();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "GetBranch");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            branchDataSource.close();
        }
        return returnValue;
    }
    static public int InsertBranch (Context context, List<Branch> branches) {
        int returnValue = 0;
        BranchDataSource branchDataSource = new BranchDataSource(context);

        try {
            branchDataSource.open();
            returnValue = branchDataSource.InsertBranch(branches);
            if(returnValue != branches.size())
            {
                LogError logError=new LogError();
                logError.setApplicationName( Constants.ApplicationName);
                logError.setDeviceSn(AppConfig.GetDeviceSN(context));
                logError.setCreateDate(Core.Dates.GetCurentDate());
                logError.setErrorLocation( Tag + "InsertBranch");
                logError.setSenT(0);
                logError.commite(context);
                logError.setErrorMsg("have problem in insert Category array. sizeOfBranches:" + branches.size()+" Size of sucsses insert:" + returnValue);
            }
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "InsertBranch");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);

        }
        finally {
            branchDataSource.close();
            return returnValue;
        }
    }
    static public void ClearBranche (Context context) {
        BranchDataSource branchDataSource = new BranchDataSource(context);
        try {
            branchDataSource.open();
            branchDataSource.ClearBranch();
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "ClearBranch");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            branchDataSource.close();
        }
    }
    static public void DeleteBranche (Context context, long branchId) {
        BranchDataSource branchDataSource = new BranchDataSource(context);
        try {
            branchDataSource.open();
            branchDataSource.DeleteBranch(branchId);
        }
        catch (Exception e )
        {
            LogError logError=new LogError();
            logError.setApplicationName( Constants.ApplicationName);
            logError.setDeviceSn(AppConfig.GetDeviceSN(context));
            logError.setCreateDate(Core.Dates.GetCurentDate());
            logError.setErrorLocation( Tag + "DeleteBranch");
            logError.setErrorMsg(e.getMessage());
            logError.setSenT(0);
            logError.commite(context);
        } finally {
            branchDataSource.close();
        }
    }


   public static Integer SyncWithServer(Context context )
    {
        WebService ws = new WebService();


        String  phrase = Constants.Phrase;
        String dec = AppConfig.GetDec(context);
        Boolean flag = false;

        String res = ws.GetBranch(dec, phrase);

        flag = CommandHandler.CommandValidation(res);
        if (flag) {
            List<Branch> branchList = CommandHandler.DecodeCommand.GetBranch(res);
            if(branchList.size() > 0) {

                Branch.ClearBranche(context);
                Branch.InsertBranch(context, branchList);

            }
            return CommandHandler.errorType_NoError;
        }
        else
            return CommandHandler.errorType_ServerAccess;

    }

}
