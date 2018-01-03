package com.hangover.ashqures.hangover.util;

/**
 * Created by ashqures on 1/24/16.
 */
public interface RestConstants {


    public final static String RESPONSE_TYPE_JSON = "application/json";

    public final static String BASE_URL = "http://192.168.1.100:8080/hangover/services";
    //public final static String BASE_URL = "http://10.0.2.2:8080/hangover/services";

    public final static String LOGIN_URL = BASE_URL+"/ann/login.json";

    public final static String LOGOUT_URL = BASE_URL+"/ann/logout.json";

    public final static String REGISTER_URL = BASE_URL+"/ann/register.json";

    public final static String STORE_URL = BASE_URL+"/hangover/shop.json";

    public final static String STORE_ITEM_URL = BASE_URL+"/hangover/shop/PATH_PARAM.json";



    /*rest param name*/
    public final static String NAME = "name";
    public final static String OTP = "otp";
    public final static String MOBILE_NUMBER = "mobile";
    public final static String USER_EMAIL = "email";
    public final static String USERNAME = "username";
    public final static String PASSWORD = "password";
    public final static String CONFIRM_PASSWORD = "confirm_password";
    public final static String DOB = "dob";
    public final static String DEVICE_ID = "deviceId";
    public final static String ZIP_CODE = "zipCode";


}
