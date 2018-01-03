package com.hangover.ashqures.hangover.presenter;

import android.content.Context;

import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.UserEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorBundle;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.task.AsyncTaskHandler;
import com.hangover.ashqures.hangover.task.CommonRXTask;
import com.hangover.ashqures.hangover.view.DialogView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ashqures on 9/4/16.
 */
public class DialogPresenter implements Presenter {


    private enum DialogAction{
        POSITIVE, NEGATIVE
    }

    private JsonMapper jsonMapper;
    DataMapper dataMapper;
    CommonRXTask mTask;

    private DialogView view;

    private DialogAction action;


    @Inject
    public DialogPresenter(DataMapper dataMapper, JsonMapper jsonMapper) {
        this.dataMapper = dataMapper;
        this.jsonMapper = jsonMapper;
    }

    public void setView(DialogView view){
        this.view = view;
    }

    public void initiatePositiveAction(Map<String, String> formAttr){
        action = DialogAction.POSITIVE;
        mTask = AsyncTaskHandler.getVerifyUsernameTask(new DialogSubscriber());
        mTask.execute(formAttr);
    }

    public void initiateNegativeAction(Map<String,String> formAttr){
        action = DialogAction.NEGATIVE;
        mTask = AsyncTaskHandler.getSendOTPTask(new DialogSubscriber());
        mTask.execute(formAttr);
    }

    public void onSuccess(StatusDTO statusDTO){
        switch (this.action){
            case POSITIVE:this.view.positiveAction(statusDTO);
                break;
            case NEGATIVE:this.view.negativeAction(statusDTO);
                break;
        }

    }

    public void onFailure(StatusDTO statusDTO){
       // this.view.negativeAction(statusDTO);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if(null!= this.mTask)
            this.mTask.cancel(true);
        this.view = null;
    }

    private final class DialogSubscriber extends DefaultSubscriber<JSONObject> {

        @Override
        public Context getContext() {
            return DialogPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(JSONObject response) {
            try {
                StatusEntity status = DialogPresenter.this.jsonMapper.transformStatus(response);
                DialogPresenter.this.onSuccess(DialogPresenter.this.dataMapper.transform(status));
            } catch (JSONException e) {
                onError(new DefaultErrorBundle(e));
            }
        }
    }
}
