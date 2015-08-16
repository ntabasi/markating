package ir.dayasoft.steelpars.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DbSystem";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper (Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    ///// Start cart
    public static final String TABLE_cart = "cart";

    public static final String cart_cartId = "CartId";
    public static final String cart_FK_productId = "FK_productId";
    public static final String cart_count = "count";
    public static final String cart_order = "orders";
    public static final String cart_status = "status";
    public static final String cart_createDate = "createDate";

    public static final String cart_Create = " create table "
            + TABLE_cart + "("
            + cart_cartId + " integer primary key  autoincrement ,"
            + cart_FK_productId + " integer,"
            + cart_count + " real,"
            + cart_order + " integer,"
            + cart_status + " integer,"
            + cart_createDate + " text"
            + ");";
    ///// End cart
///// Start category
    public static final String TABLE_category = "category";

    public static final String category_categoryId = "CategoryId";
    public static final String category_parentId = "parentId";
    public static final String category_level = "level";
    public static final String category_order = "orders";
    public static final String category_name = "name";
    public static final String category_imageUrl = "imageUrl";
    public static final String category_status = "status";
    public static final String category_updateDate = "updateDate";
    public static final String category_FK_color = "FK_color";
    public static final String category_icon = "icon";

    public static final String category_Create = " create table "
            + TABLE_category + "("
            + category_categoryId + " integer primary key  ,"
            + category_parentId + " integer,"
            + category_level + " integer,"
            + category_order + " integer,"
            + category_name + " text,"
            + category_imageUrl + " text,"
            + category_status + " integer,"
            + category_updateDate + " text,"
            + category_FK_color + " integer,"
            + category_icon + " text"
            + ");";
    ///// End category
///// Start customer
    public static final String TABLE_customer = "customer";

    public static final String customer_customerId = "CustomerId";
    public static final String customer_name = "name";
    public static final String customer_family = "family";
    public static final String customer_username = "username";
    public static final String customer_password = "password";
    public static final String customer_phone = "phone";
    public static final String customer_cellPhone = "cellPhone";
    public static final String customer_address = "address";
    public static final String customer_email = "email";
    public static final String customer_createDate = "createDate";
    public static final String customer_paymentType = "paymentType";
    public static final String customer_minDeposit = "minDeposit";
    public static final String customer_status = "status";

    public static final String customer_Create = " create table "
            + TABLE_customer + "("
            + customer_customerId + " integer primary key  autoincrement ,"
            + customer_name + " text,"
            + customer_family + " text,"
            + customer_username + " text,"
            + customer_password + " text,"
            + customer_phone + " text,"
            + customer_cellPhone + " text,"
            + customer_address + " text,"
            + customer_email + " text,"
            + customer_createDate + " text,"
            + customer_paymentType + " integer,"
            + customer_minDeposit + " real,"
            + customer_status + " integer"
            + ");";
    ///// End customer
///// Start invoice
    public static final String TABLE_invoice = "invoice";

    public static final String invoice_invoiceId = "InvoiceId";
    public static final String invoice_serverInvoiceId = "serverInvoiceId";
    public static final String invoice_FK_customerId = "FK_customerId";
    public static final String invoice_price = "price";
    public static final String invoice_deliveryCost = "deliveryCost";
    public static final String invoice_address = "address";
    public static final String invoice_port = "port";
    public static final String invoice_status = "status";
    public static final String invoice_paymentType = "paymentType";
    public static final String invoice_deposit = "deposit";
    public static final String invoice_primaryInvoiceId = "primaryInvoiceId";
    public static final String invoice_createDate = "createDate";
    public static final String invoice_input = "input";
    public static final String invoice_paymentCode = "paymentCode";

    public static final String invoice_Create = " create table "
            + TABLE_invoice + "("
            + invoice_invoiceId + " integer primary key  autoincrement ,"
            + invoice_serverInvoiceId + " integer,"
            + invoice_FK_customerId + " integer,"
            + invoice_price + " real,"
            + invoice_deliveryCost + " real,"
            + invoice_address + " text,"
            + invoice_port + " integer,"
            + invoice_status + " integer,"
            + invoice_paymentType + " integer,"
            + invoice_deposit + " real,"
            + invoice_primaryInvoiceId + " integer,"
            + invoice_createDate + " text, "
            + invoice_input + " integer,"
            + invoice_paymentCode + " text"
            + ");";
    ///// End invoice

    ///// Start branch
    public static final String TABLE_branch = "branch";

    public static final String branch_branchId = "branch_branchId";
    public static final String branch_name = "branch_name";
    public static final String branch_status = "branch_status";
    public static final String branch_address = "branch_address";
    public static final String branch_phone = "branch_phone";
    public static final String branch_webSite = "branch_webSite";
    public static final String branch_cellPhone = "branch_cellPhone";
    public static final String branch_openTime1 = "branch_openTime1";
    public static final String branch_openTime2 = "branch_openTime2";
    public static final String branch_closeTime1 = "branch_closeTime1";
    public static final String branch_closeTime2 = "branch_closeTime2";
    public static final String branch_description = "branch_description";
    public static final String branch_rate = "branch_rate";
    public static final String branch_votes = "branch_votes";
    public static final String branch_votesCount = "branch_votesCount";
    public static final String branch_outOfService = "branch_outOfService";
    public static final String branch_outOfServiceCause = "branch_outOfServiceCause";
    public static final String branch_updateDate = "branch_updateDate";



    public static final String branch_Create = " create table "
            + TABLE_branch + "("
            + branch_branchId + " integer primary key  autoincrement ,"
            + branch_name + " text,"
            + branch_status + " integer,"
            + branch_address + " text,"
            + branch_phone + " text,"
            + branch_webSite + " text,"
            + branch_cellPhone + " text,"
            + branch_openTime1 + " text,"
            + branch_openTime2 + " text,"
            + branch_closeTime1 + " text,"
            + branch_closeTime2 + " text,"
            + branch_description + " text, "
            + branch_rate + " real, "
            + branch_votes + " int,"
            + branch_votesCount + " int,"
            + branch_outOfService + " int,"
            + branch_outOfServiceCause + " text,"
            + branch_updateDate + " date"
            + ");";
    ///// End branch

    ///// Start color
    public static final String TABLE_color = "color";

    public static final String color_colorId = "colorId";
    public static final String color_code= "code";

    public static final String color_create = " create table "
            + TABLE_color + "("
            + color_colorId + " integer primary key,"
            + color_code + " text"
            + ");";
    ///// End color

    ///// Start city
    public static final String TABLE_city= "city";

    public static final String city_cityId = "cityId";
    public static final String city_name= "name";
    public static final String city_cost= "cost";

    public static final String city_create = " create table "
            + TABLE_city + "("
            + city_cityId + " integer primary key,"
            + city_name + " text, "
            + city_cost + " text"
            + ");";
    ///// End city

///// Start categoryColor
    public static final String TABLE_categoryColor = "categoryColor";

    public static final String categoryColor_categoryColorId = "categoryColorId";
    public static final String categoryColor_FK_categoryId= "Fk_categoryId";
    public static final String categoryColor_FK_colorId= "FK_colorId";
    public static final String categoryColor_order= "categoryColorOrder";

    public static final String categoryColor_create = " create table "
            + TABLE_categoryColor + "("
            + categoryColor_categoryColorId + " integer primary key autoincrement,"
            + categoryColor_FK_categoryId + " integer,"
            + categoryColor_FK_colorId + " integer, "
            + categoryColor_order + " integer"
            + ");";
///// End categoryColor

///// Start invoiceProduct
    public static final String TABLE_invoiceProduct = "invoiceProduct";

    public static final String invoiceProduct_invoiceProductId = "InvoiceProductId";
    public static final String invoiceProduct_FK_productId = "FK_productId";
    public static final String invoiceProduct_FK_invoiceId = "FK_invoiceId";
    public static final String invoiceProduct_price = "price";
    public static final String invoiceProduct_count = "count";
    public static final String invoiceProduct_order = "orders";

    public static final String invoiceProduct_Create = " create table "
            + TABLE_invoiceProduct + "("
            + invoiceProduct_invoiceProductId + " integer primary key  autoincrement ,"
            + invoiceProduct_FK_productId + " integer,"
            + invoiceProduct_FK_invoiceId + " integer,"
            + invoiceProduct_price + " real,"
            + invoiceProduct_count + " real,"
            + invoiceProduct_order + " integer"
            + ");";
    ///// End invoiceProduct
///// Start message
    public static final String TABLE_message = "message";

    public static final String message_messageId = "MessageId";
    public static final String message_FK_senderUserId = "FK_senderUserId";
    public static final String message_FK_receiverUserId = "FK_receiverUserId";
    public static final String message_Content = "Content";
    public static final String message_contentType = "contentType";
    public static final String message_type = "type";
    public static final String message_date = "date";
    public static final String message_status = "status";
    public static final String message_input = "input";

    public static final String message_Create = " create table "
            + TABLE_message + "("
            + message_messageId + " integer primary key  autoincrement ,"
            + message_FK_senderUserId + " integer, "
            + message_FK_receiverUserId + " integer, "
            + message_Content + " text, "
            + message_contentType + " integer, "
            + message_type + " integer, "
            + message_date + " text, "
            + message_status + " integer, "
            + message_input + " integer "
            + ");";
    ///// End message
///// Start product
    public static final String TABLE_product = "product";

    public static final String product_productId = "productId";
    public static final String product_name = "name";
    public static final String product_FK_categoryId = "FK_categoryId";
    public static final String product_imageUrl = "imageUrl";
    public static final String product_price = "price";
    public static final String product_priceAgent = "priceAgent";
    public static final String product_discountPrice = "discountPrice";
    public static final String product_description = "description";
    public static final String product_unitCountability = "unitCountability";
    public static final String product_unitName = "unitName";
    public static final String product_updateDate = "updateDate";

    public static final String product_Create = " create table "
            + TABLE_product + "("
            + product_productId + " integer primary key  autoincrement ,"
            + product_name + " text,"
            + product_FK_categoryId + " integer,"
            + product_imageUrl + " text,"
            + product_price + " real,"
            + product_priceAgent + " text,"
            + product_discountPrice + " real,"
            + product_description + " text,"
            + product_unitCountability + " integer,"
            + product_unitName + " text,"
            + product_updateDate + " text"
            + ");";
    ///// End product
    ///// Start Log Error
    public static final String TABLE_LogError = "logError";
    public static final String logError_logErrorId = "LogErrorId";
    public static final String logError_applicationName = "applicationName";
    public static final String logError_errorMsg = "errorMsg";
    public static final String logError_errorLocation = "errorLocation";
    public static final String logError_createDate = "createDate";
    public static final String logError_deviceSn = "deviceSn";
    public static final String logError_senT = "senT";
    public static final String logError_Create = " create table "
            + TABLE_LogError + "("
            + logError_logErrorId + " integer primary key  autoincrement ,"
            + logError_applicationName + " text,"
            + logError_errorMsg + " text,"
            + logError_errorLocation + " text,"
            + logError_createDate + " text,"
            + logError_deviceSn + " text,"
            + logError_senT + " integer "
            + ");";
    ///// End Log Error
///// Start productImage
    public static final String TABLE_productImage = "productImage";

    public static final String productImage_productImageId = "ProductImageId";
    public static final String productImage_FK_productId = "FK_productId";
    public static final String productImage_imageUrl = "imageUrl";
    public static final String productImage_FK_imageId = "FK_imageId";

    public static final String productImage_Create = " create table "
            + TABLE_productImage + "("
            + productImage_productImageId + " integer primary key  autoincrement ,"
            + productImage_FK_productId + " integer,"
            + productImage_imageUrl + " text,"
            + productImage_FK_imageId + " integer"
            + ");";

    ///// End productImage

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(cart_Create);
        database.execSQL(category_Create);
        database.execSQL(customer_Create);
        database.execSQL(invoice_Create);
        database.execSQL(invoiceProduct_Create);
        database.execSQL(message_Create);
        database.execSQL(product_Create);
        database.execSQL(productImage_Create);
        database.execSQL(logError_Create);
        database.execSQL(branch_Create);
        database.execSQL(color_create);
        database.execSQL(categoryColor_create);
        database.execSQL(city_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        onCreate(db);
    }
}