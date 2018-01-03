package com.hangover.ashqures.hangover.service.imp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.task.ApiBuilder;
import com.hangover.ashqures.hangover.task.TaskConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by ashqures on 8/4/16.
 */
public class RestApiImpl implements RestApi {


    private final Context context;
    private final JsonMapper jsonMapper;


    public RestApiImpl(Context context, JsonMapper jsonMapper) {
        this.context = context;
        this.jsonMapper = jsonMapper;
    }

    protected JSONObject transform(String response) throws JSONException {
        return new JSONObject(response);
    }

    @Override
    public RestResponse get(Map<String, String> paramMap, String url) throws RepositoryErrorBundle {
        ApiConnection apiConnection = new ApiConnection(STORE_URL);
        apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }

    @Override
    public List<ItemEntity> getItems(Map<String, String> paramMap) throws RepositoryErrorBundle{
        ApiConnection apiConnection = new ApiConnection(STORE_URL);
        apiConnection.addParams(paramMap);
        RestResponse restResponse = apiConnection.execute(ApiConnection.RequestMethod.GET);
        return null;
    }

    @Override
    public RestResponse getItem(Long itemId, Map<String,String> paramMap) throws RepositoryErrorBundle{
        String url = new ApiBuilder(TaskConstant.STORE_ITEM_URL)
                .addPath(itemId+"").build();
        ApiConnection apiConnection = new ApiConnection(url);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }

    @Override
    public RestResponse getUser(Long userId, Map<String,String> paramMap) throws RepositoryErrorBundle{
        String url = new ApiBuilder(TaskConstant.USER_URL)
                .addPath(userId+"").build();
        ApiConnection apiConnection = new ApiConnection(url);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }

    @Override
    public RestResponse login(Map<String,String> paramMap) throws RepositoryErrorBundle{
        ApiConnection apiConnection = new ApiConnection(LOGIN_URL);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.POST);
    }

    @Override
    public RestResponse logout(Map<String,String> paramMap)throws RepositoryErrorBundle {
        ApiConnection apiConnection = new ApiConnection(LOGOUT_URL);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }

    protected boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }
}
