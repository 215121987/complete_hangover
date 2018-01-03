package com.hangover.ashqures.hangover.task;

import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.entity.BaseEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorMessageFactory;
import com.hangover.ashqures.hangover.exception.NetworkConnectionException;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.presenter.Presenter;
import com.hangover.ashqures.hangover.service.imp.ApiConnection;
import com.hangover.ashqures.hangover.service.imp.RestResponse;
import com.hangover.ashqures.hangover.view.LoadDataView;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import cz.msebera.android.httpclient.HttpStatus;

/**
 * Created by ashqures on 8/19/16.
 */
public class CommonRXTask extends BaseTask<Map<String,String>, JSONObject> {


    private DefaultSubscriber<JSONObject> subscriber;

    private String url;

    private Presenter.Method method = Presenter.Method.GET;

    public CommonRXTask(DefaultSubscriber<JSONObject> subscriber){
        super(subscriber.getContext());
        this.subscriber = subscriber;
    }

    public void setURL(String url){
        this.url = url;
    }

    public void setMethod(Presenter.Method method){
        this.method = method;
    }

    @Override
    public DefaultSubscriber<JSONObject> getSubscriber() {
        return subscriber;
    }

    @Override
    protected AsyncTaskResult<JSONObject> doInBackground(Map<String, String>... params) {
        AsyncTaskResult<JSONObject> asyncTaskResult;
        if(null!= params && params.length>0){
            asyncTaskResult = handle(params[0]);
        }else {
            asyncTaskResult = handle(null);
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
        System.out.println("Rest Url:- "+ this.url);
        ApiConnection apiConnection = new ApiConnection(this.url);
        if(null!=paramMap && paramMap.size()>0)
            apiConnection.addParams(paramMap);
        return apiConnection.execute(getConnectionMethod(this.method));
    }
}
