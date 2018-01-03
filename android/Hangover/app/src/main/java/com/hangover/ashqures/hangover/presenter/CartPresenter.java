package com.hangover.ashqures.hangover.presenter;

import android.content.Context;

import com.hangover.ashqures.hangover.dto.AddressDTO;
import com.hangover.ashqures.hangover.dto.CartDTO;
import com.hangover.ashqures.hangover.dto.CartItemDTO;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.entity.AddressEntity;
import com.hangover.ashqures.hangover.entity.CartEntity;
import com.hangover.ashqures.hangover.entity.CartItemEntity;
import com.hangover.ashqures.hangover.entity.ServiceChargeEntity;
import com.hangover.ashqures.hangover.entity.mapper.DataMapper;
import com.hangover.ashqures.hangover.entity.mapper.JsonMapper;
import com.hangover.ashqures.hangover.exception.DefaultErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorBundle;
import com.hangover.ashqures.hangover.exception.ErrorMessageFactory;
import com.hangover.ashqures.hangover.interactor.DefaultSubscriber;
import com.hangover.ashqures.hangover.task.GetCartDataTask;
import com.hangover.ashqures.hangover.task.GetDeliveryAddressTask;
import com.hangover.ashqures.hangover.util.RestConstants;
import com.hangover.ashqures.hangover.view.CartView;
import com.hangover.ashqures.hangover.view.CommonView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/27/16.
 */
public class CartPresenter implements Presenter {

    static final String  SERVICE_CHARGE_NAME_DELIVERY= "delivery charge";
    static final String  SERVICE_CHARGE_NAME_MIN_DELIVERY= "Min Delivery Value";

    private GetCartDataTask mTask;

    private GetDeliveryAddressTask mAddressTask;

    private CartView view;

    private final DataMapper dataMapper;

    private final JsonMapper jsonMapper;

    private Method method = Method.GET;

    @Inject
    public CartPresenter(DataMapper dataMapper, JsonMapper jsonMapper){
        this.dataMapper = dataMapper;
        this.jsonMapper = jsonMapper;
    }

    public void setView(CartView view){
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
        if(null != this.mTask)
            this.mTask.cancel(true);
        if(null!= this.mAddressTask)
            this.mAddressTask.cancel(true);
        this.view = null;
    }


    public void initialize(){
        this.mTask = new GetCartDataTask(new CartSubscriber());
        this.mTask.execute();
    }

    public void loadUserDeliveryAddress(Map<String,String> param){
        this.method = Method.GET;
        this.mAddressTask = new GetDeliveryAddressTask(new AddressSubscriber());
        this.mAddressTask.setMethod(this.method);
        this.mAddressTask.execute(param);
    }

    public void attemptToSaveAddress(Map<String,String> formAttr){
        this.method = Method.POST;
        this.mAddressTask = new GetDeliveryAddressTask(new AddressSubscriber());
        this.mAddressTask.setMethod(this.method);
        this.mAddressTask.execute(formAttr);
    }

    public void deleteAddress(Long addressId){
        this.method = Method.DELETE;
        this.mAddressTask = new GetDeliveryAddressTask(new AddressSubscriber());
        this.mAddressTask.setMethod(this.method);
        this.mAddressTask.execute();
    }

    public void onItemClicked(Long itemId) {
        this.view.viewItem(itemId);
    }

    public void removeItemFromCart(CartItemDTO cartItem){
        //TODO remove item from cart.
    }

    private void showCartItemInView(CartDTO cartDTO){
        this.view.renderView(cartDTO);
    }

    private void showAddressInView(List<AddressDTO> addressDTOs){
        this.view.renderAddress(addressDTOs);
    }

    private void addAddressInView(AddressDTO addressDTO){
        this.view.addAddress(addressDTO);
    }

    private void deleteAddressFromView(){
        this.view.removeAddress();
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

    private CartDTO prepare(CartEntity cartEntity){
        CartDTO cartDTO = new CartDTO();
        for(CartItemEntity cartItemEntity : cartEntity.getCartItems()){
            CartItemDTO cartItemDTO = this.dataMapper.transform(cartItemEntity);
            if(null != cartItemDTO.getItemPrice()){
                if(cartItemDTO.isTaxable()){
                    cartDTO.setTaxAbleAmount(cartDTO.getTaxAbleAmount()+cartItemDTO.getItemPrice()*cartItemDTO.getItemQuantity());
                }else{
                    cartDTO.setNonTaxAbleAmount(cartDTO.getNonTaxAbleAmount()+cartItemDTO.getItemPrice()*cartItemDTO.getItemQuantity());
                }
            }
            cartDTO.addCartItem(cartItemDTO);
        }
        Double totalPercent = 0.0;
        Double minDeliveryValue = 0.0;
        Double deliveryCharge = 0.0;
        for(ServiceChargeEntity serviceCharge : cartEntity.getServiceCharges()){
            if(serviceCharge.isPercent()){
                totalPercent = totalPercent+serviceCharge.getValue();
                cartDTO.addServiceCharge(serviceCharge.getName(), cartDTO.getTaxAbleAmount()*serviceCharge.getValue()/100);
            }else{
                if(serviceCharge.getName().equalsIgnoreCase(SERVICE_CHARGE_NAME_MIN_DELIVERY))
                    minDeliveryValue = serviceCharge.getValue();
                if(serviceCharge.getName().equalsIgnoreCase(SERVICE_CHARGE_NAME_DELIVERY))
                    deliveryCharge = serviceCharge.getValue();
                cartDTO.addServiceCharge(serviceCharge.getName(), serviceCharge.getValue());
            }
        }
        cartDTO.setTax(cartDTO.getTaxAbleAmount()*totalPercent/100);
        if(cartDTO.getGrossAmount()<minDeliveryValue){
            cartDTO.setDeliveryCharge(deliveryCharge);
        }
        return cartDTO;
    }

    private final class CartSubscriber extends DefaultSubscriber<CartEntity> {

        @Override
        public Context getContext() {
            return CartPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            CartPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            CartPresenter.this.hideViewLoading();
            CartPresenter.this.showErrorMessage((ErrorBundle) e);
            CartPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(CartEntity cartEntity) {
            CartPresenter.this.showCartItemInView(CartPresenter.this.prepare(cartEntity));
        }
    }


    private final class AddressSubscriber extends DefaultSubscriber<JSONObject> {

        @Override
        public Context getContext() {
            return CartPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            CartPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            CartPresenter.this.hideViewLoading();
            CartPresenter.this.showErrorMessage((ErrorBundle) e);
            CartPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(JSONObject jsonObject) {
            try{
                switch (CartPresenter.this.method){
                    case GET: CartPresenter.this.showAddressInView(dataMapper.transformAddress(jsonMapper.transformAddressCollection(jsonObject)));
                        break;
                    case POST:CartPresenter.this.addAddressInView(dataMapper.transform(jsonMapper.transformAddress(jsonObject)));
                        break;
                    case DELETE:CartPresenter.this.deleteAddressFromView();
                        break;
                }
            }catch (JSONException je){
               onError(new DefaultErrorBundle(je));
            }
        }
    }



    /*private final class AddressSubscriber extends DefaultSubscriber<List<AddressEntity>> {

        @Override
        public Context getContext() {
            return CartPresenter.this.view.context();
        }

        @Override
        public void onCompleted() {
            CartPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            CartPresenter.this.hideViewLoading();
            CartPresenter.this.showErrorMessage((ErrorBundle) e);
            CartPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<AddressEntity> addressEntities) {
            CartPresenter.this.showAddressInView(dataMapper.transformAddress(addressEntities));
        }
    }*/
}
