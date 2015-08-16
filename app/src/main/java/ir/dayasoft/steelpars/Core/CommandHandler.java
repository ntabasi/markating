package ir.dayasoft.steelpars.Core;


import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.dayasoft.steelpars.Class.Branch;
import ir.dayasoft.steelpars.Class.Cart;
import ir.dayasoft.steelpars.Class.Category;
import ir.dayasoft.steelpars.Class.CategoryColor;
import ir.dayasoft.steelpars.Class.City;
import ir.dayasoft.steelpars.Class.Color;
import ir.dayasoft.steelpars.Class.Customer;
import ir.dayasoft.steelpars.Class.Invoice;
import ir.dayasoft.steelpars.Class.InvoiceProduct;
import ir.dayasoft.steelpars.Class.Message;
import ir.dayasoft.steelpars.Class.Product;
import ir.dayasoft.steelpars.Class.ProductImage;


public class CommandHandler {


    final static public int errorType_ServerAccess = 1;
    final static public int errorType_DataBaseAccess = 2;
    final static public int errorType_NoError = 3;
    public static final int GetAssignment = 1;



    static public String RowSeparator = "_,_";
    static public String ColumnSeparator = "_!_";
    static public String CommandSeparator = "_#_";
    static public String invoiceItemSpilter = "_@_";

    static public String FilterNull(String input) {
        if (TextUtils.isEmpty(input) || input == null || input.equals(null))
            return "-1";
        return input;
    }
    static public Boolean CommandValidation(String command) {

        if (command.length() > 2) {

            String CodeString = command.substring(0, 2);
            try {

                Integer.parseInt(CodeString);
                return true;
            } catch (Exception exception) {

                return false;
            }

        }

        return false;
    }
    static public String GetCommandCode(int CodeNumber) {
        String Res = "";

        Locale loc = new Locale("en_US");

        return String.format(loc, "%02d", CodeNumber);

    }
    static private String ConvertArrayToStringColumns(String[] input) {

        String Res = "";
        for (String temp : input) {

            Res += FilterNull(temp) + ColumnSeparator;
        }

        Res = Res.substring(0, Res.length() - ColumnSeparator.length());

        return Res;
    }


    static public class DecodeCommand {

        static public List<City> GetCity (String command) {
            command = command.substring(2);
            List<City> objList = new ArrayList<City>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                City temp = new City();
                temp.setCityId(Long.valueOf(AllColumns[inx_city_cityId]));
                temp.setName(String.valueOf(AllColumns[inx_city_name]));
                temp.setCost(Long.valueOf(AllColumns[inx_city_cost]));


                objList.add(temp);
            }

            return objList;
        }
        static public List<ProductImage> GetProductImage (String command) {
            command = command.substring(2);
            List<ProductImage> objList = new ArrayList<ProductImage>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                ProductImage temp = new ProductImage();
                temp.setProductImageId(Long.valueOf(AllColumns[inx_ProductImage_productImageId]));
                temp.setFK_productId(Long.valueOf(AllColumns[inx_ProductImage_Fk_productId]));
                temp.setFK_imageId(Long.valueOf(AllColumns[inx_ProductImage_FK_imageId]));
                temp.setImageUrl(String.valueOf(AllColumns[inx_ProductImage_imageUrl]));


                objList.add(temp);
            }

            return objList;
        }
        static public List<Branch> GetBranch (String command) {
            command = command.substring(2);
            List<Branch> objList = new ArrayList<Branch>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Branch temp = new Branch();
                temp.setBranchId(Long.valueOf(AllColumns[inx_Branch_branchId]));
                temp.setName(String.valueOf(AllColumns[inx_Branch_name]));
                temp.setStatus(Integer.valueOf(AllColumns[inx_Branch_status]));
                temp.setAddress(String.valueOf(AllColumns[inx_Branch_address]));
                temp.setPhone(String.valueOf(AllColumns[inx_Branch_phone]));
                temp.setWebSite(String.valueOf(AllColumns[inx_Branch_webSite]));
                temp.setCellphone(String.valueOf(AllColumns[inx_Branch_cellPhone]));
                temp.setOpenTime1(String.valueOf(AllColumns[inx_Branch_openTime1]));
                temp.setOpenTime2(String.valueOf(AllColumns[inx_Branch_openTime2]));
                temp.setCloseTime1(String.valueOf(AllColumns[inx_Branch_closeTime1]));
                temp.setCloseTime2(String.valueOf(AllColumns[inx_Branch_closeTime2]));
                temp.setDescription(String.valueOf(AllColumns[inx_Branch_description]));
                temp.setRate(Double.valueOf(AllColumns[inx_Branch_rate]));
                temp.setVotes(Integer.valueOf(AllColumns[inx_Branch_votes]));
                temp.setVotesCount(Integer.valueOf(AllColumns[inx_Branch_votesCount]));
                temp.setOutOfService(Integer.valueOf(AllColumns[inx_Branch_outOfService]));
                temp.setOutOfServiceCause(String.valueOf(AllColumns[inx_Branch_outOfServiceCause]));
                temp.setUpdateDate(String.valueOf(AllColumns[inx_Branch_updateDate]));



                objList.add(temp);
            }

            return objList;
        }
        static public List<Category> GetCategory (String command) {
            command = command.substring(2);
            List<Category> objList = new ArrayList<Category>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Category temp = new Category();
                temp.setCategoryId(Long.valueOf(AllColumns[inx_Category_categoryId]));
                temp.setParentId(Long.valueOf(AllColumns[inx_Category_parentId]));
                temp.setLevel(Integer.valueOf(AllColumns[inx_Category_level]));
                temp.setName(String.valueOf(AllColumns[inx_Category_name]));
                temp.setImageUrl(String.valueOf(AllColumns[inx_Category_imageUrl]));
                temp.setStatus(Integer.valueOf(AllColumns[inx_Category_status]));
                temp.setUpdateDate(String.valueOf(AllColumns[inx_Category_updateDate]));
                temp.setOrder(i);




                objList.add(temp);
            }

            return objList;
        }
        static public List<Product> GetProduct (String command) {
            command = command.substring(2);
            List<Product> objList = new ArrayList<Product>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Product temp = new Product();
                temp.setProductId(Long.valueOf(AllColumns[inx_Product_productId]));
                temp.setName(String.valueOf(AllColumns[inx_Product_name]));
                temp.setFK_categoryId(Integer.valueOf(AllColumns[inx_Product_FK_categoryId]));
                temp.setImageUrl(String.valueOf(AllColumns[inx_Product_imageUrl]));
                temp.setPrice(Double.valueOf(AllColumns[inx_Product_price]));
                temp.setPriceAgent(String.valueOf(AllColumns[inx_Product_priceAgent]));
                temp.setDiscountPrice(Double.valueOf(AllColumns[inx_Product_discountPrice]));
                temp.setDescription(String.valueOf(AllColumns[inx_Product_description]));
                temp.setUnitCountability(Integer.valueOf(AllColumns[inx_Product_unitCountability]));
                temp.setUnitName(String.valueOf(AllColumns[inx_Product_unitName]));
                temp.setUpdateDate(String.valueOf(AllColumns[inx_Product_updateDate]));


                objList.add(temp);
            }

            return objList;
        }
        static public List<Cart> GetCart (String command) {
            command = command.substring(2);
            List<Cart> objList = new ArrayList<Cart>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Cart temp = new Cart();
                temp.setCartId(Long.valueOf(AllColumns[inx_Cart_cartId]));
                temp.setFK_productId(Long.valueOf(AllColumns[inx_Cart_FK_productId]));
                temp.setCount(Double.valueOf(AllColumns[inx_Cart_count]));
                temp.setOrder(Integer.valueOf(AllColumns[inx_Cart_order]));
                temp.setStatus(Integer.valueOf(AllColumns[inx_Cart_status]));
                temp.setCreateDate(String.valueOf(AllColumns[inx_Cart_createDate]));

                temp.setProductName(String.valueOf(AllColumns[inx_Cart_productName]));
                temp.setProductUnit(String.valueOf(AllColumns[inx_Cart_productUnit]));
                temp.setProductPrice(String.valueOf(AllColumns[inx_Cart_productPrice]));

                objList.add(temp);
            }

            return objList;
        }
        static public List<Color> GetColor (String command) {
            command = command.substring(2);
            List<Color> objList = new ArrayList<Color>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Color temp = new Color();
                temp.setColorId(Long.valueOf(AllColumns[inx_Color_colorId]));
                temp.setCode(String.valueOf(AllColumns[inx_Color_code]));

                objList.add(temp);
            }

            return objList;
        }
        static public List<CategoryColor> GetCategoryColor (String command) {
            command = command.substring(2);
            List<CategoryColor> objList = new ArrayList<CategoryColor>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                CategoryColor temp = new CategoryColor();
                temp.setCategoryColorId(Long.valueOf(AllColumns[inx_CategoryColor_categoryColorId]));
                temp.setFK_categoryId(Integer.valueOf(AllColumns[inx_CategoryColor_Fk_categoryId]));
                temp.setFK_colorId(Integer.valueOf(AllColumns[inx_CategoryColor_Fk_colorId]));
                temp.setOrder(Integer.valueOf(AllColumns[inx_CategoryColor_order]));

                objList.add(temp);
            }

            return objList;
        }
        static public Customer GetCustomer (String command,Context context) {

            command = command.substring(2);
            Customer customer = new Customer();
            String[] AllColumns = command.split(ColumnSeparator);

            int colCount = AllColumns.length;

            if(colCount>0) {



                 customer.setCustomerId(Long.valueOf(AllColumns[0]));
                 AppConfig.SetSecurityCode(context,AllColumns[1]);
             //   customer.setGroups(Integer.valueOf(AllColumns[inx_GetCustomer_Fk_groupId]));
            //    customer.setMinDeposit(Double.valueOf(AllColumns[inx_GetCustomer_MinDeposit]));
              //  customer.setPaymentType(Integer.valueOf(AllColumns[inx_GetCustomer_PaymentType]));

            }

            return customer;
        }
        static public String GetLogin(String command, Context context) {
            command = command.substring(2);
            try {
                if (command.equals(String.valueOf(Customer.Register_DuplicateCellPhone)))
                    return String.valueOf(Customer.Register_DuplicateCellPhone);
                String[] AllColumns = command.split(ColumnSeparator);

                if (AllColumns.length != 2)
                    return "0";

                AppConfig.SetUserId(context, AllColumns[0]);
                AppConfig.SetSecurityCode(context, AllColumns[1]);
                AppConfig.SetIsLogin(context, true);
                Customer.UpdateCustomerId(context, Long.valueOf(AllColumns[0]));
                AppConfig.StartCheckConfirmCodeReceiver(context);

                return "1";
            } catch (Exception e) {
                return "-1";
            }
        }
        static public Customer GetSignInCustomer (String command) {
            command = command.substring(2);
            Customer customer = new Customer();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            if(rowCount>0) {
                String[] AllColumns = AllRows[0].split(ColumnSeparator);

                customer.setCustomerId(Long.valueOf(AllColumns[inx_GetCustomer_id]));
             //   customer.setGroups(Integer.valueOf(AllColumns[inx_GetCustomer_Fk_groupId]));
                customer.setMinDeposit(Double.valueOf(AllColumns[inx_GetCustomer_MinDeposit]));
                customer.setPaymentType(Integer.valueOf(AllColumns[inx_GetCustomer_PaymentType]));

            }

            return customer;
        }
        static public List<Cart> GetAvailableProduct (String command) {
            command = command.substring(2);
            List<Cart> cartList = new ArrayList<>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i <rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Cart temp = new Cart();
                temp.setFK_productId(Long.valueOf(AllColumns[0]));
                temp.setCount(Double.valueOf(AllColumns[1]));

                cartList.add(temp);
            }

            return cartList;
        }


        static public List<Message> GetMessage(String command) {
            command = command.substring(2);
            List<Message> objList = new ArrayList<Message>();
            String[] AllRows = command.split(RowSeparator);
            int rowCount = AllRows.length;

            for (int i = 0; i < rowCount; i++) {
                String[] AllColumns = AllRows[i].split(ColumnSeparator);

                Message temp = new Message();
                temp.setFK_senderUserId(Long.valueOf(AllColumns[inx_Message_FK_formUserId]));
                temp.setStatus(Integer.valueOf(AllColumns[inx_Message_status]));
                temp.setDate(String.valueOf(AllColumns[inx_Message_createDate]));
                temp.setFK_receiverUserId(Long.valueOf(AllColumns[inx_Message_FK_toUserId]));
                temp.setType(Integer.valueOf(AllColumns[inx_Message_type]));
                temp.setContent(String.valueOf(AllColumns[inx_Message_content]));
                temp.setMessageId(Long.valueOf(AllColumns[inx_Message_messageId]));

                objList.add(temp);
            }

            return objList;
        }

        static public Integer CheckInvoiceStatus(String command) {

            command = command.substring(2);
            if (command.length() > 0) {
                try {


                    Integer status = Integer.parseInt(command.substring(0, 1));
                    return status;
                } catch (Exception e) {
                    return -1;
                }
            }
            return -1;
        }
        static public List<InvoiceProduct> GetInvoiceProduct(String command) {
            try {
                List<InvoiceProduct> products = new ArrayList<>();
                InvoiceProduct product = new InvoiceProduct();

                if (command.length() > 3) {
                    command = command.substring(command.lastIndexOf(ColumnSeparator) + ColumnSeparator.length());

                    String[] AllRow = command.split(CommandHandler.RowSeparator);
                    if (AllRow.length > 0) {
                        for (String itemString : AllRow) {
                            String[] itemCol = itemString.split(CommandHandler.invoiceItemSpilter);
                            product = new InvoiceProduct();
                            product.setFK_productId(Long.valueOf(itemCol[0]));
                            product.setCount(Double.valueOf(itemCol[1]));
                            product.setPrice(Double.valueOf(itemCol[2]));

                            products.add(product);

                        }
                        return products;
                    }

                }
                return null;

            } catch (Exception e) {
                return null;
            }
        }

        static public Double GetInvoiceTotalCost(String command) {
            try {
                if (command.length() > 3) {
                    command = command.substring(3);
                    String[] allColumn = command.split(ColumnSeparator);

                    if (allColumn.length == 3) {
                        return Double.valueOf(allColumn[0]);
                    }
                }
                return 0d;
            } catch (Exception e) {
                return 0d;
            }

        }


        static public Double GetInvoiceExtraCost(String command) {
            try {
                if (command.length() > 3) {
                    command = command.substring(0, 3);
                    String[] allColumn = command.split(ColumnSeparator);

                    if (allColumn.length == 3) {
                        return Double.valueOf(allColumn[1]);

                    }
                }
                return 0d;
            } catch (Exception e) {
                return 0d;
            }
        }

    }

    static public class EncodeCommand {

        static public String EncodeAvailableProduct(List<Cart> cartList) {

            String returnValue = GetCommandCode(GetAvailableFood);

            String[] column = new String[1];

            for(Cart cart: cartList) {
                column[0] = String.valueOf(cart.getFK_productId());
                returnValue += ConvertArrayToStringColumns(column) + RowSeparator;
            }

            returnValue = returnValue.substring(0,returnValue.length()-CommandHandler.RowSeparator.length());



            return returnValue;
        }
        static public String EncodeCart (List<Cart> cartList) {

            String returnValue= "22";

            for(Cart cart: cartList) {
                returnValue += String.valueOf(cart.getCartId())+
                        CommandHandler.ColumnSeparator +
                        cart.getFK_productId() +
                        CommandHandler.ColumnSeparator +
                        cart.getCount() +
                        CommandHandler.ColumnSeparator +
                        cart.getStatus() +
                        CommandHandler.ColumnSeparator +
                        cart.getCreateDate() +
                        CommandHandler.RowSeparator;
            }
            returnValue = returnValue.substring(0,returnValue.length()-CommandHandler.RowSeparator.length());

            return returnValue;
        }




        static public String InvoiceProduct(List<InvoiceProduct> invoiceProductList) {

            String returnValue = "22";

            for (InvoiceProduct invoiceProduct : invoiceProductList) {
                returnValue +=
                        invoiceProduct.getFK_productId() +
                                CommandHandler.ColumnSeparator +
                                invoiceProduct.getCount()
                                + RowSeparator;


            }
            returnValue = returnValue.substring(0, returnValue.length() - CommandHandler.RowSeparator.length());

            return returnValue;
        }

      /*  static public String EncodeSignUp (Context mContext, Customer resCustomer) {

            String returnValue = GetCommandCode(CustomerRegister);
            String simSN = Core.PhoneInfo.GetSimSerialNumber(mContext);
            Customer customer = resCustomer;

            String[] column = new String[17];

            column[inx_Customer_customerId] = customer.getCustomerId().toString();
            column[inx_Customer_name] = customer.getName();
            column[inx_Customer_family] = customer.getFamily();
            column[inx_Customer_userName] = customer.getUsername();
            column[inx_Customer_password] = customer.getPassword();
            column[inx_Customer_phone] = customer.getPhone();
            column[inx_Customer_cellPhone] = customer.getCellPhone();
            column[inx_Customer_address] = customer.getAddress();
            column[inx_Customer_email] = customer.getEmail();
            column[inx_Customer_createDate] = customer.getCreateDate();
            column[inx_Customer_paymentType] = customer.getPaymentType().toString();
            column[inx_Customer_minDeposit] = customer.getMinDeposit().toString();
            column[inx_Customer_status] = customer.getStatus().toString();
            column[inx_Customer_simSN] = simSN;
          //  column[inx_Customer_appVersion] = Constants.AppVersion;
          //  column[inx_Customer_deviceId] = Core.PhoneInfo.GetDeviceId(mContext);



            returnValue += ConvertArrayToStringColumns(column) + RowSeparator;

            returnValue = returnValue.substring(0,returnValue.length()-CommandHandler.RowSeparator.length());



            return returnValue;
        }*/
        static public String EncodeLogin(String userName, String password) {

            String returnValue = GetCommandCode(CustomerRegister);

            String[] column = new String[2];


            column[inx_SignIn_userName] = userName;
            column[inx_SignIn_password] = password;


            returnValue += ConvertArrayToStringColumns(column) + RowSeparator;


            returnValue = returnValue.substring(0,returnValue.length()-CommandHandler.RowSeparator.length());



            return returnValue;
        }


        static public String  Customer(List<Customer> customerList, Context mContext) {

            String returnValue = GetCommandCode(CustomerRegister);
            String simSN = Core.PhoneInfo.GetSimSerialNumber(mContext);

            String[] column = new String[14];

            for (Customer customer : customerList) {
                column[inx_Customer_customerId] = customer.getCustomerId().toString();
                column[inx_Customer_name] = customer.getName();
                column[inx_Customer_family] = customer.getFamily();
                column[inx_Customer_userName] = customer.getUsername();
                column[inx_Customer_password] = customer.getPassword();
                column[inx_Customer_phone] = customer.getPhone();
                column[inx_Customer_cellPhone] = customer.getCellPhone();
                column[inx_Customer_address] = customer.getAddress();
                column[inx_Customer_email] = customer.getEmail();
                column[inx_Customer_createDate] = customer.getCreateDate();
                column[inx_Customer_paymentType] = customer.getPaymentType().toString();
                column[inx_Customer_minDeposit] = customer.getMinDeposit().toString();
                column[inx_Customer_status] = customer.getStatus().toString();
                column[inx_Customer_simSN] = simSN;

                returnValue += ConvertArrayToStringColumns(column) + RowSeparator;
            }

            returnValue = returnValue.substring(0, returnValue.length() - CommandHandler.RowSeparator.length());


            return returnValue;
        }


        /**
         * InvoiceProduct *
         * InvoiceProductId
         * FK_invoiceId
         * FK_productId
         * Price
         * Count
         * Order
         * ProductName
         * ProductUnitName


         * SignUp *
         * name
         * phone
         * email
         * address
         * cellphone
         * appVersion
         * cellPhoneSN
         * simSN


         * invoice *
         * invoiceId
         * serverInvoiceId
         * FK_customerId
         * price
         * deliveryCost
         * address
         * port
         * status
         * paymentType
         * deposit
         * primaryInvoiceId
         * createDate
         * input


         * cart *
         * cartId
         * FK_productId
         * count
         * status
         * createDate
         */

    }


    static public String GetCommand(String command) {

        if (command.length() > 2) {

            String returnValue = command.substring(2);
            try {
                return returnValue;
            } catch (Exception exception) {

                return "0";
            }

        }
        return "0";
    }

    // inx Branch
    final static public Integer inx_Branch_branchId = 0;
    final static public Integer inx_Branch_name = 1;
    final static public Integer inx_Branch_status = 2;
    final static public Integer inx_Branch_address = 3;
    final static public Integer inx_Branch_phone = 4;
    final static public Integer inx_Branch_webSite = 5;
    final static public Integer inx_Branch_cellPhone = 6;
    final static public Integer inx_Branch_openTime1 = 7;
    final static public Integer inx_Branch_openTime2 = 8;
    final static public Integer inx_Branch_closeTime1 = 9;
    final static public Integer inx_Branch_closeTime2 = 10;
    final static public Integer inx_Branch_description = 11;
    final static public Integer inx_Branch_rate = 12;
    final static public Integer inx_Branch_votes = 13;
    final static public Integer inx_Branch_votesCount = 14;
    final static public Integer inx_Branch_outOfService = 15;
    final static public Integer inx_Branch_outOfServiceCause = 16;
    final static public Integer inx_Branch_updateDate = 17;


    // inx Category
    final static public Integer inx_Category_categoryId = 0;
    final static public Integer inx_Category_parentId = 1;
    final static public Integer inx_Category_level = 2;
    final static public Integer inx_Category_name = 3;
    final static public Integer inx_Category_imageUrl = 4;
    final static public Integer inx_Category_status = 5;
    final static public Integer inx_Category_updateDate = 6;


    // inx Product
    final static public Integer inx_Product_productId = 0;
    final static public Integer inx_Product_name = 1;
    final static public Integer inx_Product_FK_categoryId = 2;
    final static public Integer inx_Product_imageUrl = 3;
    final static public Integer inx_Product_price = 4;
    final static public Integer inx_Product_priceAgent = 5;
    final static public Integer inx_Product_discountPrice = 6;
    final static public Integer inx_Product_description = 7;
    final static public Integer inx_Product_unitCountability = 8;
    final static public Integer inx_Product_unitName = 9;
    final static public Integer inx_Product_updateDate = 10;


    // inx Invoice
    final static public Integer inx_Invoice_invoiceId = 0;
    final static public Integer inx_Invoice_FK_customerId = 1;
    final static public Integer inx_Invoice_address = 2;




    // inx Cart
    final static public Integer inx_Cart_cartId = 0;
    final static public Integer inx_Cart_FK_productId = 1;
    final static public Integer inx_Cart_count = 2;
    final static public Integer inx_Cart_order = 3;
    final static public Integer inx_Cart_status = 4;
    final static public Integer inx_Cart_createDate = 5;

    final static public Integer inx_Cart_productName = 6;
    final static public Integer inx_Cart_productUnit = 7;
    final static public Integer inx_Cart_productPrice = 8;


    // inx Customer
    final static public Integer inx_Customer_customerId = 0;
    final static public Integer inx_Customer_name = 1;
    final static public Integer inx_Customer_family = 2;
    final static public Integer inx_Customer_userName = 3;
    final static public Integer inx_Customer_password = 4;
    final static public Integer inx_Customer_phone = 5;
    final static public Integer inx_Customer_cellPhone = 6;
    final static public Integer inx_Customer_address = 7;
    final static public Integer inx_Customer_email = 8;
    final static public Integer inx_Customer_createDate = 9;
    final static public Integer inx_Customer_paymentType = 10;
    final static public Integer inx_Customer_minDeposit = 11;
    final static public Integer inx_Customer_status = 12;
    final static public Integer inx_Customer_simSN = 13;


    // inx Color
    final static public Integer inx_Color_colorId = 0;
    final static public Integer inx_Color_code = 1;

    // inx GetCustomer
    final static public Integer inx_GetCustomer_id= 0;
    final static public Integer inx_GetCustomer_Fk_groupId = 1;
    final static public Integer inx_GetCustomer_MinDeposit = 2;
    final static public Integer inx_GetCustomer_PaymentType = 3;

    // inx CategoryColor
    final static public Integer inx_CategoryColor_categoryColorId = 0;
    final static public Integer inx_CategoryColor_Fk_categoryId = 1;
    final static public Integer inx_CategoryColor_Fk_colorId = 2;
    final static public Integer inx_CategoryColor_order = 3;

    // inx ProductImage
    final static public Integer inx_ProductImage_productImageId = 0;
    final static public Integer inx_ProductImage_Fk_productId = 1;
    final static public Integer inx_ProductImage_FK_imageId = 2;
    final static public Integer inx_ProductImage_imageUrl = 3;

    // inx SignIn
    final static public Integer inx_SignIn_userName = 0;
    final static public Integer inx_SignIn_password = 1;

    // inx City
    final static public Integer inx_city_cityId = 0;
    final static public Integer inx_city_name = 1;
    final static public Integer inx_city_cost = 2;

    static public int CustomerRegister = 11;
    static public int GetCategory = 12;
    static public int GetCustomerSecurityCode = 13;
    static public int ConfirmCustomerCellPhoneNumber = 14;
    static public int GetSplashImage = 15;
    static public int GetProduct = 16;
    static public int CheckIsOpen = 17;
    static public int GetMessage = 18;
    static public int SubmitInvoice = 19;
    static public int CheckInvoiceStatus = 20;
    static public int SendMessage = 21;
    static public int GetProductImages = 22;
    static public int GetColor = 23;
    static public int GetCategoryColor = 24;
    static public int CustomerSignIn = 25;
    static public int GetBranch = 26;
    static public int GetAvailableFood =27;


    // inx Message
    final static public Integer inx_Message_FK_formUserId = 0;
    final static public Integer inx_Message_status = 1;
    final static public Integer inx_Message_createDate = 2;
    final static public Integer inx_Message_FK_toUserId = 3;
    final static public Integer inx_Message_type = 4;
    final static public Integer inx_Message_content = 5;
    final static public Integer inx_Message_title = 6;
    final static public Integer inx_Message_messageId = 7;




}