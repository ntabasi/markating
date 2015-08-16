package ir.dayasoft.steelpars.Communication;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import ir.dayasoft.steelpars.Core.Constants;


public class WebService {


    public final String SOAP_ACTION_GetBranch = "http://tempuri.org/GetBranch";
    public final String SOAP_ACTION_GetCategory = "http://tempuri.org/GetCategory";
    public final String SOAP_ACTION_GetProduct = "http://tempuri.org/GetProduct";
    public final String SOAP_ACTION_GetInvoice = "http://tempuri.org/GetInvoice";
    public final String SOAP_ACTION_SendCart = "http://tempuri.org/SendCart";
    public final String SOAP_ACTION_SendInvoice = "http://tempuri.org/SendInvoice";
    public final String SOAP_ACTION_SendInvoiceProduct = "http://tempuri.org/SendInvoiceProduct";
    public final String SOAP_ACTION_CustomerRegister = "http://tempuri.org/CustomerRegister";
    public final String SOAP_ACTION_CustomerSignIn = "http://tempuri.org/CustomerSignIn";
    public final String SOAP_ACTION_GetColor = "http://tempuri.org/GetColor";
    public final String SOAP_ACTION_GetCategoryColor = "http://tempuri.org/GetCategoryColor";
    public final String SOAP_ACTION_GetProductImages = "http://tempuri.org/GetProductImages";
    public final String SOAP_ACTION_GetAvailableProduct = "http://tempuri.org/GetAvailableProduct";
    public final String SOAP_ACTION_GetCity = "http://tempuri.org/GetCity";
    public final String SOAP_ACTION_GetConfirm = "http://tempuri.org/GetConfirm";
    public final String SOAP_ACTION_SubmitInvoice = "http://tempuri.org/SubmitInvoice";
    public final String SOAP_ACTION_CheckInvoiceStatus = "http://tempuri.org/CheckInvoiceStatus";
    public final String SOAP_ACTION_GetMessage = "http://tempuri.org/GetMessage";


    public final String OPERATION_NAME_GetBranch = "GetBranch";
    public final String OPERATION_NAME_GetCategory = "GetCategory";
    public final String OPERATION_NAME_GetProduct = "GetProduct";
    public final String OPERATION_NAME_GetInvoice = "GetInvoice";
    public final String OPERATION_NAME_SendCart = "SendCart";
    public final String OPERATION_NAME_SendInvoice = "SendInvoice";
    public final String OPERATION_NAME_SendInvoiceProduct = "SendInvoiceProduct";
    public final String OPERATION_NAME_CustomerRegister = "CustomerRegister";
    public final String OPERATION_NAME_CustomerSignIn = "CustomerSignIn";
    public final String OPERATION_NAME_GetColor = "GetColor";
    public final String OPERATION_NAME_GetCategoryColor = "GetCategoryColor";
    public final String OPERATION_NAME_GetProductImages = "GetProductImages";
    public final String OPERATION_NAME_GetAvailableProduct = "GetAvailableProduct";
    public final String OPERATION_NAME_GetCity = "GetCity";
    public final String OPERATION_NAME_GetConfirm = "GetConfirm";
    public final String OPERATION_NAME_SubmitInvoice = "SubmitInvoice";
    public final String OPERATION_NAME_CheckInvoiceStatus = "CheckInvoiceStatus";
    public final String OPERATION_NAME_GetMessage = "GetMessage";

    public final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

 // public final String SOAP_ADDRESS = "http://test.omran118.com/webservice.asmx";
 //   public final String SOAP_ADDRESS = "http://parse-steel.ir//webservice.asmx";
 //   public final String SOAP_ADDRESS = "http://daya1.dayaapp.ir/webservice.asmx";
    public final String SOAP_ADDRESS = Constants.SOAP_ADDRESS;

    public WebService() {

    }


    public String GetBranch (String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetBranch);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetBranch, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetCategory (String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetCategory);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetCategory, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetProduct (String dec, String phrase, String updateDate, String categoryId) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetProduct);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("updateDate");
        pi.setValue(updateDate);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("categoryId");
        pi.setValue(categoryId);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetProduct, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetInvoice (String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetInvoice);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetInvoice, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetColor (String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetColor);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetColor, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetCategoryColor (String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetCategoryColor);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetCategoryColor, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetProductImages (String dec, String phrase, String productId) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetProductImages);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("productId");
        pi.setValue(productId);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetProductImages, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetAvailableProduct (String command, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetAvailableProduct);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("command");
        pi.setValue(command);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetAvailableProduct, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String GetCity (String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetCity);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetCity, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }

    public String SendCart (String userId, String cartString, String dec, String phrase) {
    SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
            OPERATION_NAME_SendCart);

    PropertyInfo pi = new PropertyInfo();

    pi.setName("userId");
    pi.setValue(userId);
    pi.setType(String.class);
    request.addProperty(pi);


     pi = new PropertyInfo();
     pi.setName("cartString");
     pi.setValue(cartString);
     pi.setType(String.class);
     request.addProperty(pi);


     pi = new PropertyInfo();
     pi.setName("dec");
     pi.setValue(dec);
     pi.setType(String.class);
     request.addProperty(pi);


     pi = new PropertyInfo();
    pi.setName("phrase");
    pi.setValue(phrase);
    pi.setType(String.class);
    request.addProperty(pi);

    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
            SoapEnvelope.VER11);
    envelope.dotNet = true;

    envelope.setOutputSoapObject(request);

    HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
    Object response = null;
    try {
        httpTransport.call(SOAP_ACTION_SendCart, envelope);
        response = envelope.getResponse().toString();
    } catch (Exception exception) {
        response = exception.toString();
    }
    return response.toString();
    }
    public String SendInvoice (String userId, String invoiceString, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_SendInvoice);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("userId");
        pi.setValue(userId);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("invoiceString");
        pi.setValue(invoiceString);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_SendInvoice, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String SendInvoiceProduct (String userId, String invoiceProductString, String code, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_SendInvoiceProduct);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("userId");
        pi.setValue(userId);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("invoiceProductString");
        pi.setValue(invoiceProductString);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("code");
        pi.setValue(code);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_SendInvoiceProduct, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String SignIn (String signInString, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_CustomerSignIn);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("signInString");
        pi.setValue(signInString);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_CustomerSignIn, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }
    public String SignUp (String signUpString, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_CustomerRegister);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("signUpString");
        pi.setValue(signUpString);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_CustomerRegister, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }


    public String GetConfirm(String userId, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetConfirm);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("userId");
        pi.setValue(userId);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetConfirm, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }


        public String  SubmitInvoice(String userId, String clientInvoiceId, String address, String command,String dec, String phrase ) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_SubmitInvoice);


        PropertyInfo pi = new PropertyInfo();

        pi.setName("userId");
        pi.setValue(userId);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("clientInvoiceId");
        pi.setValue(clientInvoiceId);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("address");
        pi.setValue(address);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("command");
        pi.setValue(command);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_SubmitInvoice, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }

        public String CheckInvoiceStatus( String userId,String invoiceId,String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_CheckInvoiceStatus);


        PropertyInfo pi = new PropertyInfo();

        pi.setName("userId");
        pi.setValue(userId);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("invoiceId");
        pi.setValue(invoiceId);
        pi.setType(String.class);
        request.addProperty(pi);

        pi = new PropertyInfo();
        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_CheckInvoiceStatus, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();

    }



    public String GetMessage(String dec, String phrase, String userId) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_GetMessage);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("userId");
        pi.setValue(userId);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_GetMessage, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }


    public String SendCustomer(String signUpString, String dec, String phrase) {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,
                OPERATION_NAME_CustomerRegister);

        PropertyInfo pi = new PropertyInfo();

        pi.setName("signUpString");
        pi.setValue(signUpString);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("dec");
        pi.setValue(dec);
        pi.setType(String.class);
        request.addProperty(pi);


        pi = new PropertyInfo();
        pi.setName("phrase");
        pi.setValue(phrase);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response = null;
        try {
            httpTransport.call(SOAP_ACTION_CustomerRegister, envelope);
            response = envelope.getResponse().toString();
        } catch (Exception exception) {
            response = exception.toString();
        }
        return response.toString();
    }


}
