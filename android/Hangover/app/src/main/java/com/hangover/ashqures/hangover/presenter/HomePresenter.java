package com.hangover.ashqures.hangover.presenter;

import android.content.Context;

import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.exception.ErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorMessageFactory;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.task.CommonRXTask;
import com.hangover.ashqures.hangover.view.HomeView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/21/16.
 */
public class HomePresenter implements Presenter {


    private HomeView view;

    CommonRXTask authTask;
    private DataMapper dataMapper;

    @Inject
    public HomePresenter(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public void setView(HomeView view){
        this.view = view;
    }

    public void initialize(){

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.view = null;
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

    private final class HomeSubscriber extends DefaultSubscriber<List<ItemEntity>> {

        @Override
        public Context getContext() {
            return HomePresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            HomePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            HomePresenter.this.hideViewLoading();
            HomePresenter.this.showErrorMessage((ErrorBundle) e);
            HomePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ItemEntity> items) {
            //HomePresenter.this.showStoreView(HomePresenter.this.dataMapper.transform(items));
        }
    }

}
