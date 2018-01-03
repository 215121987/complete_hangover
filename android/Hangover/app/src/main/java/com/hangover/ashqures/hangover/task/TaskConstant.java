package com.hangover.ashqures.hangover.task;

/**
 * Created by ashqures on 8/12/16.
 */
public interface TaskConstant {

    final static String RESPONSE_TYPE_JSON = "application/json";

    final static String BASE_URL = "http://192.168.1.100:8080/hangover/services";
    /*final static String BASE_URL = "http://10.0.2.2:8080/hangover/services";*/

    final static String API_BASE_URL = BASE_URL+ "/api";
    final static String STORE_ITEM_URL = API_BASE_URL+"/ItemEntity/PATH_PARAM";
    final static String USER_URL = API_BASE_URL+"/UserEntity/PATH_PARAM";
    final static String REMOVE_FROM_CART_URL = API_BASE_URL+"/ShoppingCartItemEntity/PATH_PARAM";




    final static String STORE_BASE_URL = BASE_URL+ "/store";
    final static String STORE_URL = STORE_BASE_URL+"/item";
    final static String CART_URL = STORE_BASE_URL+"/cart";
    final static String ADD_TO_CART_URL = STORE_BASE_URL+"/cart";



    final static String USER_BASE_URL = BASE_URL+ "/user-1";
    final static String IDENTIFY_USER_URL = USER_BASE_URL+ "/help/identify";
    final static String USER_ADDRESS_URL = API_BASE_URL+"/address";



    final static String PAYMENT_BASE_URL = BASE_URL+ "/payment";


    final static String LOGIN_URL = BASE_URL+"/ann/login";

    final static String LOGOUT_URL = BASE_URL+"/ann/logout";

    final static String REGISTER_URL = BASE_URL+"/ann/register";












    /*rest param name*/
    final static String USER_EMAIL = "email";
    final static String PASSWORD = "password";
    final static String DEVICE_ID = "deviceId";

}
