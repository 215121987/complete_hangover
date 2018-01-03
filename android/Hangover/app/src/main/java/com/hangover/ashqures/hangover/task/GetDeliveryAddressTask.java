package com.hangover.ashqures.hangover.task;

import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.entity.AddressEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.NetworkConnectionException;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.presenter.Presenter;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.service.imp.RestResponse;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 8/29/16.
 */
public class GetDeliveryAddressTask extends BaseTask<Map<String,String>, JSONObject> {

    private DefaultSubscriber<JSONObject> subscriber;
    private JsonMapper jsonMapper;
    private DataMapper dataMapper;
    private Presenter.Method method;

    public GetDeliveryAddressTask(DefaultSubscriber<JSONObject> subscriber) {
        super(subscriber.getContext());
        this.subscriber = subscriber;
        this.jsonMapper = new JsonMapper();
        this.dataMapper = new DataMapper();
        this.method = Presenter.Method.GET;
    }

    @Override
    public DefaultSubscriber<JSONObject> getSubscriber() {
        return this.subscriber;
    }

    public void setMethod(Presenter.Method method){
        this.method = method;
    }

    @Override
    protected AsyncTaskResult<JSONObject> doInBackground(Map<String, String>... params) {
        AsyncTaskResult<JSONObject> asyncTaskResult;
        if (isThereInternetConnection()) {
            try {
                RestResponse restResponse;
                if(null!= params && params.length>0){
                    restResponse = getDataFromApi(params[0]);
                }else {
                    restResponse = getDataFromApi(null);
                }
                if (null != restResponse && restResponse.getCode().equals(HttpStatus.SC_OK)) {
                        asyncTaskResult = new AsyncTaskResult<JSONObject>(transform(restResponse.getResponse()));
                } else {
                    StatusEntity statusEntity = this.jsonMapper.transformStatus(transform(restResponse.getResponse()));
                    asyncTaskResult = new AsyncTaskResult<JSONObject>(new DefaultErrorBundle(dataMapper.transform(statusEntity)));
                }
            }catch (RepositoryErrorBundle re){
                asyncTaskResult = new AsyncTaskResult<>(re);
            }catch (Exception e) {
                e.printStackTrace();
                asyncTaskResult = new AsyncTaskResult<>(new DefaultErrorBundle(e));
            }
        } else {
            asyncTaskResult = new AsyncTaskResult<>(new NetworkConnectionException());
        }
        return asyncTaskResult;
    }


    @Override
    protected void onPostExecute(AsyncTaskResult<JSONObject> asyncTaskResult) {
        if(!isCancelled()){
            if(!asyncTaskResult.hasError()){
                this.subscriber.onNext(asyncTaskResult.getResult());
            }else{
                this.subscriber.onError(asyncTaskResult.getErrorBundle().getException());
            }
        }
        super.onPostExecute(asyncTaskResult);
    }

    public RestResponse getDataFromApi(Map<String,String> paramMap)throws RepositoryErrorBundle {
        ApiConnection apiConnection = new ApiConnection(RestApi.USER_ADDRESS_URL);
        apiConnection.addHeader("token", sessionManager.getAuthToken());
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(getConnectionMethod(this.method));
    }
}
