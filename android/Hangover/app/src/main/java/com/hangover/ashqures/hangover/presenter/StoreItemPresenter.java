package com.hangover.ashqures.hangover.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.hangover.ashqures.hangover.component.PerActivity;
import com.hangover.ashqures.hangover.db.CartItemDBHelper;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.dto.ItemDetailDTO;
import com.hangover.ashqures.hangover.entity.CartItemEntity;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.exception.ErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorMessageFactory;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.task.ApiBuilder;
import com.hangover.ashqures.hangover.task.CommonTask;
import com.hangover.ashqures.hangover.task.TaskConstant;
import com.hangover.ashqures.hangover.view.CommonView;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/16/16.
 */
@PerActivity
public class StoreItemPresenter implements Presenter {

    private CommonTask<ItemEntity> mTask;
    private CommonView<ItemDTO> view;

    @Inject
    CartItemDBHelper cartItemDBHelper;

    private final DataMapper dataMapper;

    @Inject
    public StoreItemPresenter(DataMapper dataMapper){
        this.dataMapper = dataMapper;
    }


    public void setView(@NonNull CommonView<ItemDTO> view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        StoreItemPresenter.this.mTask.cancel(true);
        StoreItemPresenter.this.view = null;
    }

    public void addToCart(ItemDTO itemDTO, ItemDetailDTO itemDetailDTO, int quantity){
        CartItemEntity cart = new CartItemEntity();
        cart.setItemId(itemDTO.getId());
        cart.setItemName(itemDTO.getName());
        cart.setImageUrl(itemDTO.getDescription());
        cart.setItemDetailId(itemDetailDTO.getId());
        cart.setItemSize(itemDetailDTO.getSize());
        cart.setItemPrice(itemDetailDTO.getPrice());
        cart.setItemQuantity(quantity);
        this.cartItemDBHelper.insert(cart);
        System.out.println("DB Row Count:- "+ cartItemDBHelper.numberOfRows());
    }

    public void initialize(Long itemId) {
        this.loadItem(itemId);
    }

    private void loadItem(Long itemId){
        String url = new ApiBuilder(TaskConstant.STORE_ITEM_URL)
                .addPath(itemId+"").build();
        this.mTask = new CommonTask<>(new StoreItemSubscriber(),url);
        this.mTask.execute();
    }

    private void showItemDetailsInView(ItemDTO itemDTO) {
        this.view.renderView(itemDTO);
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


    private final class StoreItemSubscriber extends DefaultSubscriber<ItemEntity> {

        @Override
        public Context getContext() {
            return StoreItemPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            StoreItemPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            StoreItemPresenter.this.hideViewLoading();
            StoreItemPresenter.this.showErrorMessage((ErrorBundle) e);
            StoreItemPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(ItemEntity itemEntity) {
            StoreItemPresenter.this.showItemDetailsInView(StoreItemPresenter.this.dataMapper.transform(itemEntity));
        }
    }
}
