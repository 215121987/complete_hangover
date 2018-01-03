package com.hangover.ashqures.hangover.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashqures on 8/27/16.
 */
public class CartEntity extends BaseEntity {

    private List<CartItemEntity> cartItems;
    private List<ServiceChargeEntity> serviceCharges;


    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItems(CartItemEntity cartItem){
        if(null == getCartItems())
            setCartItems(new ArrayList<CartItemEntity>());
        getCartItems().add(cartItem);
    }

    public List<ServiceChargeEntity> getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(List<ServiceChargeEntity> serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public void addServiceCharge(ServiceChargeEntity serviceCharge){
        if(null == getServiceCharges())
            setServiceCharges(new ArrayList<ServiceChargeEntity>());
        getServiceCharges().add(serviceCharge);
    }
}
