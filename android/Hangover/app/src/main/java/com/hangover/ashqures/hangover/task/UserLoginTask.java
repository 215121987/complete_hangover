package com.hangover.ashqures.hangover.task;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import com.hangover.ashqures.hangover.activity.R;
import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.service.imp.RestResponse;
import com.hangover.ashqures.hangover.util.CommonUtil;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.util.RestConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;
import dmax.dialog.SpotsDialog;

/**
 * Created by ashqures on 8/3/16.
 */
public class UserLoginTask extends AsyncTask<String, Void, JSONObject> implements RestConstants{

    private SessionManager session;
    private  View mView;
    private  Context context;

    private AlertDialog dialog ;


    UserLoginTask(View view, Context context) {
        this.mView = view;
        this.context = context;
        this.session = new SessionManager(context);
        this.dialog = new SpotsDialog(context, R.style.CustomDialog);
    }


    @Override
    protected void onPreExecute(){
        this.dialog.setMessage("Authenticating...");
        this.dialog.show();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject userInfo = null;
        try{
            String url = RestConstants.LOGIN_URL;
            ApiConnection apiConnection = new ApiConnection(url);
            apiConnection.addParam(USER_EMAIL, params[0]);
            apiConnection.addParam(PASSWORD, params[1]);
            apiConnection.addParam(DEVICE_ID, CommonUtil.getDeviceInfo(context).get("deviceId"));
            RestResponse restResponse = apiConnection.execute(ApiConnection.RequestMethod.POST);
            JSONObject obj = null;
            if(restResponse.getCode()== HttpStatus.SC_OK){
                obj =  new JSONObject(restResponse.getResponse());
                userInfo = obj.getJSONObject("login_status");
            }
        }catch (RepositoryErrorBundle re){

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfo;

        // TODO: register the new account here.
    }

    @Override
    protected void onPostExecute(final JSONObject loginInfo) {
        try {
            Map<String,String> sessionParamMap = new HashMap<String,String>();
            sessionParamMap.put("token",loginInfo.getString("token"));
            JSONObject userInfo = loginInfo.getJSONObject("user");
            sessionParamMap.put("email",userInfo.getString("email"));
            sessionParamMap.put("name",userInfo.getString("name"));
            sessionParamMap.put("userId", userInfo.getString("id"));
            session.createLoginSession(sessionParamMap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    @Override
    protected void onCancelled() {

    }
}