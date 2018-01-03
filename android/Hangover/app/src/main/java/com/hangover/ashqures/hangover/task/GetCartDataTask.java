package com.hangover.ashqures.hangover.task;


import com.hangover.ashqures.hangover.entity.CartEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.NetworkConnectionException;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.service.imp.RestResponse;

import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 8/27/16.
 */
public class GetCartDataTask extends BaseTask<Map<String,String>, CartEntity> {

    private DefaultSubscriber<CartEntity> subscriber;
    private JsonMapper jsonMapper;
    private DataMapper dataMapper;

    public GetCartDataTask(DefaultSubscriber<CartEntity> subscriber) {
        super(subscriber.getContext());
        this.subscriber = subscriber;
        this.jsonMapper = new JsonMapper();
        this.dataMapper = new DataMapper();
    }

    @Override
    public DefaultSubscriber<CartEntity> getSubscriber() {
        return this.subscriber;
    }

    @Override
    protected AsyncTaskResult<CartEntity> doInBackground(Map<String, String>... params) {
        AsyncTaskResult<CartEntity> asyncTaskResult;
        if (isThereInternetConnection()) {
            try {
                RestResponse restResponse;
                if(null!= params && params.length>0){
                    restResponse = getDataFromApi(params[0]);
                }else {
                    restResponse = getDataFromApi(null);
                }
                if (null != restResponse && restResponse.getCode().equals(HttpStatus.SC_OK)) {
                    asyncTaskResult = new AsyncTaskResult<CartEntity>(jsonMapper.transformCart(transform(restResponse.getResponse())));
                } else {
                    StatusEntity statusEntity = this.jsonMapper.transformStatus(transform(restResponse.getResponse()));
                    asyncTaskResult = new AsyncTaskResult<CartEntity>(new DefaultErrorBundle(dataMapper.transform(statusEntity)));
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
    protected void onPostExecute(AsyncTaskResult<CartEntity> asyncTaskResult) {
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
        ApiConnection apiConnection = new ApiConnection(CART_URL);
        apiConnection.addHeader("token", sessionManager.getAuthToken());
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }
}
