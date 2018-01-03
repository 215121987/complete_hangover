package com.hangover.ashqures.hangover.service;

import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.service.imp.RestResponse;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * Created by ashqures on 8/4/16.
 */
public interface RestApi {


    /*rest api URL*/
    final static String RESPONSE_TYPE_JSON = "application/json";

    final static String BASE_URL = "http://192.168.1.100:8080/hangover/services";
    //final static String BASE_URL = "http://10.0.2.2:8080/hangover/services";

    final static String API_BASE_URL = BASE_URL+ "/api";
    final static String STORE_ITEM_URL = API_BASE_URL+"/ItemEntity/PATH_PARAM";
    final static String USER_URL = API_BASE_URL+"/UserEntity/PATH_PARAM";
    final static String REMOVE_FROM_CART_URL = API_BASE_URL+"/ShoppingCartItemEntity/PATH_PARAM";
    //final static String USER_ADDRESS_URL = API_BASE_URL+"/AddressEntity/PATH_PARAM/user";



    final static String STORE_BASE_URL = BASE_URL+ "/store";
    final static String STORE_URL = STORE_BASE_URL+"/item";
    final static String CART_URL = STORE_BASE_URL+"/cart";
    final static String ADD_TO_CART_URL = STORE_BASE_URL+"/cart";



    final static String USER_BASE_URL = BASE_URL+ "/user";
    final static String USER_ADDRESS_URL = USER_BASE_URL+ "/address";

    final static String PAYMENT_BASE_URL = BASE_URL+ "/payment";


    final static String BASE_ANN_URL = BASE_URL+"/ann";
    final static String LOGIN_URL = BASE_ANN_URL+"/login";
    final static String LOGOUT_URL = BASE_ANN_URL+"/logout";
    final static String REGISTER_URL = BASE_ANN_URL+"/register";
    final static String IDENTIFY_USER_URL = BASE_ANN_URL+"/help/identify";
    final static String SEND_OTP_URL = BASE_ANN_URL+"/otp/send";
    final static String VERIFY_USERNAME_URL = BASE_ANN_URL+"/verify/username";


    /*rest param name*/
    final static String NAME = "name";
    final static String MOBILE_NUMBER = "mobile";
    final static String USER_EMAIL = "email";
    final static String USERNAME = "username";
    final static String PASSWORD = "password";
    final static String CONFIRM_PASSWORD = "confirmPassword";
    final static String DEVICE_ID = "deviceId";
    final static String ATTR_ADDRESS = "address";
    final static String ATTR_STREET = "street";
    final static String ATTR_CITY = "city";
    final static String ATTR_STATE = "state";
    final static String ATTR_COUNTRY = "country";
    final static String ATTR_ZIPCODE = "zipCode";



    RestResponse get(Map<String,String> paramMap, String url)throws RepositoryErrorBundle;

    List<ItemEntity> getItems(Map<String,String> paramMap)throws RepositoryErrorBundle;

    RestResponse getItem(Long itemId, Map<String,String> paramMap)throws RepositoryErrorBundle;

    RestResponse getUser(Long userId, Map<String,String> paramMap) throws RepositoryErrorBundle;

    RestResponse login(Map<String,String> paramMap) throws RepositoryErrorBundle;

    RestResponse logout(Map<String,String> paramMap)throws RepositoryErrorBundle;

}
