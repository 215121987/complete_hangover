package com.hangover.ashqures.hangover.task;

import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.presenter.Presenter;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.service.imp.RestResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ashqures on 8/12/16.
 */
public class AsyncTaskHandler {



    public static CommonRXTask getSignInTask(DefaultSubscriber<JSONObject> subscriber){
        CommonRXTask loginTask = new CommonRXTask(subscriber);
        loginTask.setURL(RestApi.LOGIN_URL);
        loginTask.setMethod(Presenter.Method.POST);
        return loginTask;
    }

    public static CommonRXTask getSignUpTask(DefaultSubscriber<JSONObject> subscriber){
        CommonRXTask registerTask = new CommonRXTask(subscriber);
        registerTask.setURL(RestApi.REGISTER_URL);
        registerTask.setMethod(Presenter.Method.POST);
        return registerTask;
    }

    public static CommonRXTask getIdentifyTask(DefaultSubscriber<JSONObject> subscriber){
        CommonRXTask identifyTask = new CommonRXTask(subscriber);
        identifyTask.setURL(RestApi.IDENTIFY_USER_URL);
        identifyTask.setMethod(Presenter.Method.POST);
        return identifyTask;
    }

    public static CommonRXTask getSendOTPTask(DefaultSubscriber<JSONObject> subscriber){
        CommonRXTask identifyTask = new CommonRXTask(subscriber);
        identifyTask.setURL(RestApi.SEND_OTP_URL);
        identifyTask.setMethod(Presenter.Method.GET);
        return identifyTask;
    }

    public static CommonRXTask getVerifyUsernameTask(DefaultSubscriber<JSONObject> subscriber){
        CommonRXTask identifyTask = new CommonRXTask(subscriber);
        identifyTask.setURL(RestApi.VERIFY_USERNAME_URL);
        identifyTask.setMethod(Presenter.Method.POST);
        return identifyTask;
    }

}
