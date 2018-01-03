package com.hangover.ashqures.hangover.task;

import com.hangover.ashqures.hangover.entity.BaseEntity;
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
import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 6/29/16.
 */
public class CommonTask<T extends BaseEntity> extends BaseTask<Map<String,String>, T>{

    private JsonMapper jsonMapper;

    private DataMapper dataMapper;

    private DefaultSubscriber<T> subscriber;

    private String url;

    public CommonTask(DefaultSubscriber<T> subscriber, String url) {
        super(subscriber.getContext());
        this.jsonMapper = new JsonMapper();
        this.dataMapper = new DataMapper();
        this.url = url;
        this.subscriber = subscriber;
    }

    @Override
    public DefaultSubscriber<T> getSubscriber() {
        return this.subscriber;
    }

    @Override
    protected AsyncTaskResult<T> doInBackground(Map<String,String>... params) {
        AsyncTaskResult<T> asyncTaskResult = null;// new AsyncTaskResult<T>();
        if (isThereInternetConnection()) {
            try {
                RestResponse restResponse;
                if(null!= params && params.length>0){
                    restResponse = getDataFromApi(params[0]);
                }else {
                    restResponse = getDataFromApi(null);
                }
                if (null != restResponse && restResponse.getCode().equals(HttpStatus.SC_OK)) {
                    Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                    asyncTaskResult = new AsyncTaskResult<T>(jsonMapper.<T>transform(transform(restResponse.getResponse()),type));
                } else {
                    StatusEntity statusEntity = this.jsonMapper.transformStatus(transform(restResponse.getResponse()));
                    asyncTaskResult = new AsyncTaskResult<T>(new DefaultErrorBundle(this.dataMapper.transform(statusEntity)));
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
    protected void onPostExecute(AsyncTaskResult<T> asyncTaskResult) {
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
        System.out.println("Rest Url:- "+ this.url);
        ApiConnection apiConnection = new ApiConnection(this.url);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }

}
