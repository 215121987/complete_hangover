package com.hangover.ashqures.hangover.task;

import android.content.Context;

import com.hangover.ashqures.hangover.entity.UserEntity;
import com.hangover.ashqures.hangover.exception.RepositoryErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.service.imp.RestResponse;

import java.util.List;
import java.util.Map;

/**
 * Created by ashqures on 9/3/16.
 */
public class UserTask extends BaseTask<Map<String,String>, UserEntity> {


  private DefaultSubscriber<UserEntity> subscriber;

    public UserTask(DefaultSubscriber<UserEntity> subscriber) {
        super(subscriber.getContext());
        this.subscriber = subscriber;
    }

    @Override
    public DefaultSubscriber<UserEntity> getSubscriber() {
        return this.subscriber;
    }

    @Override
    public RestResponse getDataFromApi(Map<String, String> paramMap) throws RepositoryErrorBundle {
        return null;
    }

    @Override
    protected AsyncTaskResult<UserEntity> doInBackground(Map<String, String>... params) {
        return null;
    }

    @Override
    protected void onPostExecute(final AsyncTaskResult<UserEntity> asyncTaskResult) {
        if(!isCancelled()){
            if(!asyncTaskResult.hasError()){
                subscriber.onNext(asyncTaskResult.getResult());
            }else{
                this.subscriber.onError(asyncTaskResult.getErrorBundle().getException());
            }
        }
        super.onPostExecute(asyncTaskResult);
    }


}
