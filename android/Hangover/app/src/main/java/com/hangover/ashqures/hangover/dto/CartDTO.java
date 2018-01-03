package com.hangover.ashqures.hangover.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ashqures on 8/22/16.
 */
public class CartDTO extends BaseDTO{

    private Double netAmount =0.0;
    private Double grossAmount =0.0;
    private Double taxAbleAmount = 0.0;
    private Double nonTaxAbleAmount = 0.0;
    private Double discountedAmount = 0.0;
    private Double tax =0.0;
    private Double deliveryCharge = 0.0;
    private List<CartItemDTO> cartItems;
    private Map<String,Double> serviceCharges;

    public Double getNetAmount() {
        this.netAmount = this.taxAbleAmount+this.nonTaxAbleAmount+this.tax+deliveryCharge-this.discountedAmount;
        return netAmount;
    }

    public Double getGrossAmount() {
        this.grossAmount = this.taxAbleAmount+this.nonTaxAbleAmount;
        return grossAmount;
    }


    public Double getTaxAbleAmount() {
        return taxAbleAmount;
    }

    public void setTaxAbleAmount(Double taxAbleAmount) {
        this.taxAbleAmount = taxAbleAmount;
    }

    public Double getNonTaxAbleAmount() {
        return nonTaxAbleAmount;
    }

    public void setNonTaxAbleAmount(Double nonTaxAbleAmount) {
        this.nonTaxAbleAmount = nonTaxAbleAmount;
    }

    public Double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public void addCartItem(CartItemDTO cartItem){
        if(null==getCartItems())
            setCartItems(new ArrayList<CartItemDTO>());
        getCartItems().add(cartItem);
    }

    public Map<String, Double> getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Map<String, Double> serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public void addServiceCharge(String key, Double value){
        if(null== getServiceCharges())
            setServiceCharges(new HashMap<String, Double>());
        getServiceCharges().put(key,value);
    }
}
