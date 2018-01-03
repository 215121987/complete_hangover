package com.hangover.ashqures.hangover.task;

import android.text.TextUtils;

import com.hangover.ashqures.hangover.auth.SessionManager;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.NetworkConnectionException;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.service.imp.RestResponse;
import com.hangover.ashqures.hangover.util.RestConstants;
import com.hangover.ashqures.hangover.util.ValidatorUtil;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 8/3/16.
 */
public class GetStoreDataTask extends BaseTask<Map<String,String>,List<ItemEntity>>{

    JsonMapper jsonMapper;

    DataMapper dataMapper;

    final DefaultSubscriber subscriber;

    @Inject
    public GetStoreDataTask(DefaultSubscriber<List<ItemEntity>> subscriber){
        super(subscriber.getContext());
        jsonMapper = new JsonMapper();
        dataMapper = new DataMapper();
        this.subscriber = subscriber;
    }


    @Override
    public DefaultSubscriber<List<ItemEntity>> getSubscriber() {
        return this.subscriber;
    }

    @Override
    protected AsyncTaskResult<List<ItemEntity>> doInBackground(Map<String,String>... paramMaps) {
        AsyncTaskResult<List<ItemEntity>> asyncTaskResult;
        if (isThereInternetConnection()) {
            try {
                RestResponse restResponse = getDataFromApi(paramMaps[0]);
                if (null != restResponse && restResponse.getCode().equals(HttpStatus.SC_OK)) {
                    asyncTaskResult = new AsyncTaskResult<>(this.jsonMapper.transformItemCollection(transform(restResponse.getResponse())));
                } else {
                    StatusEntity statusEntity = this.jsonMapper.transformStatus(transform(restResponse.getResponse()));
                    asyncTaskResult = new AsyncTaskResult<>(new DefaultErrorBundle(this.dataMapper.transform(statusEntity)));
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
    protected void onPostExecute(final AsyncTaskResult<List<ItemEntity>> asyncTaskResult) {
        if(!isCancelled()){
            if(!asyncTaskResult.hasError()){
                subscriber.onNext(asyncTaskResult.getResult());
            }else{
                this.subscriber.onError(asyncTaskResult.getErrorBundle().getException());
            }
        }
        super.onPostExecute(asyncTaskResult);
    }

    @Override
    protected void onCancelled() {

    }

    public RestResponse getDataFromApi(Map<String,String> paramMap)throws RepositoryErrorBundle{
        ApiConnection apiConnection = new ApiConnection(STORE_URL);
        String zipCode = sessionManager.getStringValue(SessionManager.KEY_ZIP_CODE);
        if(null != zipCode && !"".equals(zipCode))
            apiConnection.addParam(RestConstants.ZIP_CODE, zipCode);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(ApiConnection.RequestMethod.GET);
    }


}