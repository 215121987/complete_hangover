package com.hangover.ashqures.hangover.presenter;

import android.content.Context;
import android.provider.ContactsContract;

import com.hangover.ashqures.hangover.component.PerActivity;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.dto.UserDTO;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.UserEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorMessageFactory;
import com.hangover.ashqures.hangover.fragments.AuthFragment;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.service.RestApi;
import com.hangover.ashqures.hangover.task.AsyncTaskHandler;
import com.hangover.ashqures.hangover.task.CommonRXTask;
import com.hangover.ashqures.hangover.view.AuthView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/18/16.
 */
@PerActivity
public class AuthPresenter implements Presenter {

    private enum AuthTye{
        SIGNIN, SIGNUP, USERNAME_VERIFICATION, IDENTIFY, SEND_OTP
    }


    AuthView view;
    DataMapper dataMapper;
    private final JsonMapper jsonMapper;
    CommonRXTask authTask;
    /*CommonRXTask signUpTask;
    CommonRXTask identifyUserTask;*/

    @Inject
    public AuthPresenter(DataMapper dataMapper, JsonMapper jsonMapper) {
        this.dataMapper = dataMapper;
        this.jsonMapper = jsonMapper;
    }

    public void setView(AuthView authView){
        this.view = authView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if(null!= this.authTask){
            this.authTask.cancel(true);
        }
       /* if(null != signUpTask){
            this.signUpTask.cancel(true);
        }
        if(null != identifyUserTask){
            this.identifyUserTask.cancel(true);
        }*/
        this.view=null;
    }

    public void attemptSignIn(Map<String,String> requestParam){
        this.authTask = AsyncTaskHandler.getSignInTask(new AuthSubscriber(AuthTye.SIGNIN));
        this.authTask.execute(requestParam);
    }

    public void attemptSignUp(Map<String,String> requestParam){
        this.authTask = AsyncTaskHandler.getSignUpTask(new AuthSubscriber(AuthTye.SIGNUP));
        this.authTask.execute(requestParam);
    }

    public void attemptIdentifyUser(Map<String,String> requestParam){
        this.authTask = AsyncTaskHandler.getIdentifyTask(new AuthSubscriber(AuthTye.IDENTIFY));
        this.authTask.execute(requestParam);
    }

    public void attemptSendOTP(Map<String,String> requestParam){
        this.authTask = AsyncTaskHandler.getSendOTPTask(new AuthSubscriber(AuthTye.SEND_OTP));
        this.authTask.execute(requestParam);
    }

    public void attemptVerifyUsername(Map<String,String> requestParam){
        this.authTask = AsyncTaskHandler.getVerifyUsernameTask(new AuthSubscriber(AuthTye.USERNAME_VERIFICATION));
        this.authTask.execute(requestParam);
    }

    private void onSignInSuccess(UserDTO userDTO){
        this.view.handleSignIn(userDTO);
    }

    private void onSignUpSuccess(UserDTO userDTO){
        this.view.handleSignUp(userDTO);
    }

    private void onIdentifyUserSuccess(StatusDTO statusDTO){
        this.view.handleIdentifyUser(statusDTO);
    }

    private void onSendOTPSuccess(StatusDTO statusDTO){
        this.view.handleIdentifyUser(statusDTO);
    }

    private void onVerifyUsernameSuccess(StatusDTO statusDTO){
        this.view.handleIdentifyUser(statusDTO);
    }

    private void showViewLoading() {
        this.view.showLoading();
    }

    private void hideViewLoading() {
        this.view.hideLoading();
    }

    private void showViewRetry() {
        this.view.showRetry();
    }

    private void hideViewRetry() {
        this.view.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.view.context(), errorBundle);
        this.view.showError(errorMessage);
    }

    private final class AuthSubscriber extends DefaultSubscriber<JSONObject> {

        private AuthTye authTye;

        public AuthSubscriber(AuthTye authTye){
            this.authTye = authTye;
        }

        @Override
        public Context getContext() {
            return AuthPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            AuthPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            AuthPresenter.this.hideViewLoading();
            AuthPresenter.this.showErrorMessage((ErrorBundle) e);
            AuthPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(JSONObject response) {
            try{
                switch (authTye){
                    case SIGNIN:
                        UserEntity signInUser = AuthPresenter.this.jsonMapper.transformUser(response);
                        AuthPresenter.this.onSignInSuccess(AuthPresenter.this.dataMapper.transform(signInUser));
                        break;
                    case SIGNUP:
                        UserEntity signUpUser = AuthPresenter.this.jsonMapper.transformUser(response);
                        AuthPresenter.this.onSignInSuccess(AuthPresenter.this.dataMapper.transform(signUpUser));
                        break;
                    case IDENTIFY:
                        StatusEntity identifyStatus = AuthPresenter.this.jsonMapper.transformStatus(response);
                        AuthPresenter.this.onIdentifyUserSuccess(AuthPresenter.this.dataMapper.transform(identifyStatus));
                        break;
                    case USERNAME_VERIFICATION:
                        StatusEntity usernameVrfStatus = AuthPresenter.this.jsonMapper.transformStatus(response);
                        AuthPresenter.this.onVerifyUsernameSuccess(AuthPresenter.this.dataMapper.transform(usernameVrfStatus));
                        break;
                    case SEND_OTP:
                        StatusEntity sendOTPStatus = AuthPresenter.this.jsonMapper.transformStatus(response);
                        AuthPresenter.this.onSendOTPSuccess(AuthPresenter.this.dataMapper.transform(sendOTPStatus));
                        break;
                }
            }catch (JSONException je){
                onError(new DefaultErrorBundle(je));
            }
        }
    }

    private final class SignUpSubscriber extends DefaultSubscriber<UserEntity> {

        @Override
        public Context getContext() {
            return AuthPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            AuthPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            AuthPresenter.this.hideViewLoading();
            AuthPresenter.this.showErrorMessage((ErrorBundle) e);
            AuthPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(UserEntity userEntity) {
            AuthPresenter.this.onSignUpSuccess(AuthPresenter.this.dataMapper.transform(userEntity));
        }
    }

    private final class IdentifyUserSubscriber extends DefaultSubscriber<StatusEntity> {

        @Override
        public Context getContext() {
            return AuthPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            AuthPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            AuthPresenter.this.hideViewLoading();
            AuthPresenter.this.showErrorMessage((ErrorBundle) e);
            AuthPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(StatusEntity statusEntity) {
            AuthPresenter.this.onIdentifyUserSuccess(AuthPresenter.this.dataMapper.transform(statusEntity));
        }
    }
}
