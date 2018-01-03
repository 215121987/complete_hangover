package com.hangover.java.util;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 20/8/14
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {

    static final String SECURITY_TOKEN = "SecurityToken";
    static final String AUTHORIZATION_PROPERTY = "Authorization";
    static final String AUTHENTICATION_SCHEME = "Basic";


    static final String TEMPLATE_BASE_LOCATION = "template";
    static final String FORGOT_PASSWORD_TEMPLATE = TEMPLATE_BASE_LOCATION+"/forgotPassword.vm";
    static final String NOTIFICATION_MAIL_TEMPLATE = TEMPLATE_BASE_LOCATION+"/notificationMail.vm";
    static final String OTP_TEMPLATE = TEMPLATE_BASE_LOCATION+"/otp.vm";
    static final String ORDER_CONFIRMATION_TEMPLATE = TEMPLATE_BASE_LOCATION+"/orderConfirmation.vm";

    public static final String RESPONSE_STATUS = "status";

    public static final String VIEW_JSON = "json";
    
    static final String LOGGED_IN_USER = "currentUser";
    static final String LOGGED_IN_USER_ID = "currentUserId";
    static final String LOGGED_IN_USERNAME = "username";
    static final String LOGGED_IN_FULL_NAME = "fullName";
    static final String LOGGED_IN_USER_SUPPLIER_ID = "supplierId";
    static final String LOGGED_IN_USER_STORE_ID = "storeId";
    static final String SER_AGE_VERIFIED = "ageVerified";

    static final String PARAM_USER_NAME = "name";
    static final String PARAM_USER_PROFILE_PICK = "profile_pick";

    static final String PATTERN_DOUBLE = "patternDouble";

    static final String FILE_CONTENT_TYPE_PDF = "application/pdf";


    static final String FILE_TYPE_PDF = ".pdf";
    static final String FILE_TYPE_DXF = ".dxf";
    
    static final String COMMA_SEPARATOR =", ";

    /*Excel column name*/

    static final String PARAM_USERNAME = "username";
    static final String PARAM_USER_EMAIL = "email";
    static final String PARAM_USER_PASSWORD = "password";
    static final String PARAM_USER_OLD_PASSWORD = "old_password";
    static final String PARAM_USER_CONFIRM_PASSWORD = "confirm_password";
    static final String PARAM_FIELD_NAME = "name";
    static final String PARAM_FIELD_VALUE = "value";
    static final String PARAM_START_DATE = "start_date";
    
    
    
    /*Item filter field name*/
    
    static final String  BRAND_NAME= "brand";
    static final String  ITEM_SIZE= "itemSize";
    static final String  SUPPLIER_ZIP_CODE= "zipCode";
    static final String  ITEM_CATEGORY= "category";
    static final String  PAGE_NUMBER= "page";
    static final String  ORDER_BY= "orderBy";
    static final String  START_INDEX= "startIndex";
    static final String  MAX_RESULT= "maxResult";
    static final String  ITEM_STATUS= "status";
    static final String  SORT_BY_PRICE_LOW_TO_HIGH= "price-asc";
    static final String  SORT_BY_PRICE_HIGH_TO_LOW= "price-dsc";
    static final String  SORT_BY_DEFAULT= "default";
    static final String  ITEM_DISCOUNT= "discount";
    static final String  ITEM_MIN_PRICE= "minPrice";
    static final String  ITEM_MAX_PRICE= "maxPrice";



    /*Action name*/

    static final String  ACTION_ADD_TO_CART= "add-to-cart";
    static final String  ACTION_EDIT_CART_ITEM= "edit-cart-item";
    static final String  ACTION_PLACE_ORDER= "placeOrder";


    /*cookies name*/
    static final String  COOKIES_CUSTOMER_LOCATION= "customer_location";
    static final String  COOKIES_CART_HASH= "hangover_cart_hash";
    static final String  COOKIES_SESSION_ID= "hangover.sid";
    static final String  SESSION_CART= "cart";
    static final String  SESSION_CART_SUMMARY= "cartSummary";
    static final String  SESSION_SHORTLIST= "shortList";
    static final String  SESSION_CHECKOUT_STATUS= "checkoutStatus";
    static final String  SESSION_CHECKOUT_ORDER_REVIEWED= "checkoutOrderReviewed";
    static final String  SESSION_DELIVERY_ADDRESS_ID= "deliveryAddressId";
    static final String  SESSION_DELIVERY_ADDRESS= "deliveryAddress";

    /*Shortlist Name*/
    static final String  SHOTLIST_DEFAULT_NAME= "My Wish List";

    /*CART ITEM DETAIL*/
     static final String CART_ITEM_SEPARATOR = "-";
    static final String CART_ITEM_DETAIL_SEPARATOR = "x";
    static final String  CART_ITEM_ID= "itemId";
    static final String  CART_ITEM_DETAIL_ID= "itemDetailId";
    static final String  CART_ITEM_QUANTITY= "quantity";

    static final String  TAB_PERSONAL_INFO= "personalInfo";
    static final String  TAB_SAVED_ADDRESSES= "savedAddresses";
    static final String  TAB_SAVED_CARDS= "savedCards";
    static final String  TAB_ORDER= "order";
    static final String  TAB_OPENED_ORDER= "openOrder";
    static final String  TAB_RETURN_ORDER= "returnOrder";
    static final String  TAB_EXCHANGED_ORDER= "exchangedOrder";
    static final String  TAB_ORDER_HISTORY= "orderHistory";
    static final String  TAB_DELIVERED_ORDER= "deliveredOrder";




    static final String  SERVICE_CHARGE_NAME_DELIVERY= "delivery charge";
    static final String  SERVICE_CHARGE_NAME_MIN_DELIVERY= "Min Delivery Value";
    static final String  SERVICE_CHARGE_NAME_TOTAL_TAX= "Total Tax";


}
