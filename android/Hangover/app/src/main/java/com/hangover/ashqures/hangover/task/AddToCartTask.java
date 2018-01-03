package com.hangover.ashqures.hangover.task;

import com.hangover.ashqures.hangover.entity.CartItemEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.NetworkConnectionException;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.service.imp.RestResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 8/22/16.
 */
public class AddToCartTask extends BaseTask<CartItemEntity, StatusEntity> {

    private JsonMapper jsonMapper;

    private DataMapper dataMapper;

    private DefaultSubscriber<StatusEntity> subscriber;

    public AddToCartTask(DefaultSubscriber<StatusEntity> subscriber) {
        super(subscriber.getContext());
        this.jsonMapper = new JsonMapper();
        this.dataMapper = new DataMapper();
    }


    @Override
    public DefaultSubscriber<StatusEntity> getSubscriber() {
        return this.subscriber;
    }

    @Override
    protected AsyncTaskResult<StatusEntity> doInBackground(CartItemEntity... params) {
        AsyncTaskResult<StatusEntity> asyncTaskResult;
        if (isThereInternetConnection()) {
            try {
                RestResponse restResponse;
                if(null!= params && params.length>0){
                    Map<String,String> paramMap = new HashMap<>();
                    restResponse = getDataFromApi(paramMap);
                }else {
                    restResponse = getDataFromApi(null);
                }
                if (null != restResponse && restResponse.getCode().equals(HttpStatus.SC_OK)) {
                    System.out.println(restResponse.getResponse());
                    Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                    asyncTaskResult = new AsyncTaskResult<StatusEntity>(jsonMapper.<StatusEntity>transform(transform(restResponse.getResponse())));
                } else {
                    StatusEntity statusEntity = this.jsonMapper.transformStatus(transform(restResponse.getResponse()));
                    asyncTaskResult = new AsyncTaskResult<StatusEntity>(new DefaultErrorBundle(dataMapper.transform(statusEntity)));
                }
            }catch (RepositoryErrorBundle re){
                asyncTaskResult = new AsyncTaskResult<>(re);
            }catch (Exception e) {
                asyncTaskResult = new AsyncTaskResult<>(new DefaultErrorBundle(e));
            }
        } else {
            asyncTaskResult = new AsyncTaskResult<>(new NetworkConnectionException());
        }
        return asyncTaskResult;
    }


    @Override
    protected void onPostExecute(AsyncTaskResult<StatusEntity> asyncTaskResult) {
        if(!isCancelled()){
            if(!asyncTaskResult.hasError()){
                this.subscriber.onNext(asyncTaskResult.getResult());
            }else{
                this.subscriber.onError(asyncTaskResult.getErrorBundle().getException());
                //showToastMessage(asyncTaskResult.getErrorBundle().getCode()+"");
            }
        }
        super.onPostExecute(asyncTaskResult);
    }

    public RestResponse getDataFromApi(Map<String,String> paramMap)throws RepositoryErrorBundle {
       // System.out.println("Rest Url:- "+ this.url);
        ApiConnection apiConnection = new ApiConnection(ADD_TO_CART_URL);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.POST);
    }
}
