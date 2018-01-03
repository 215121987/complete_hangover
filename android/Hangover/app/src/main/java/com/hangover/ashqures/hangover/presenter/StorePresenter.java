package com.hangover.ashqures.hangover.presenter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.hangover.ashqures.hangover.component.PerActivity;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorMessageFactory;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.task.BaseTask;
import com.hangover.ashqures.hangover.task.GetStoreDataTask;
import com.hangover.ashqures.hangover.view.StoreView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by ashqures on 8/11/16.
 */
@PerActivity
public class StorePresenter implements Presenter {

    private StoreView view;
    GetStoreDataTask mTask;
    DataMapper dataMapper;


    @Inject
    public StorePresenter(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }


    public void setView(@NonNull StoreView view) {
        this.view = view;
    }


    public void onItemClicked(ItemDTO itemDTO) {
        this.view.viewItem(itemDTO);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        StorePresenter.this.mTask.cancel(true);
        StorePresenter.this.view = null;
    }

    public void initialize() {
        this.loadStore();
    }

    private void loadStore() {
       /* this.hideViewRetry();
        this.showViewLoading();*/
        this.loadItems();
    }

    private void loadItems(){
        this.mTask = new GetStoreDataTask(new StoreSubscriber());
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("category","1");
        this.mTask.execute(paramMap);
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

    private void showStoreView(List<ItemDTO> itemDTOCollection) {
        this.view.renderStore(itemDTOCollection);
    }


    private final class StoreSubscriber extends DefaultSubscriber<List<ItemEntity>> {


        @Override
        public Context getContext() {
            return StorePresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            StorePresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            StorePresenter.this.hideViewLoading();
            StorePresenter.this.showErrorMessage((ErrorBundle) e);
            StorePresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ItemEntity> items) {
            StorePresenter.this.showStoreView(StorePresenter.this.dataMapper.transform(items));
        }
    }
}
