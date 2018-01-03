package com.hangover.java.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/19/16
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class CartSummaryDTO {
    
    private Long id;
    private Double netAmount =0.0;
    private Double grossAmount =0.0;
    private Double taxAbleAmount = 0.0;
    private Double nonTaxAbleAmount = 0.0;
    private Double discountedAmount = 0.0;
    private Double tax =0.0;
    private Double deliveryCharge = 0.0;
    private List<CartDTO>  cartDTOs;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Double getNetAmount() {
        this.netAmount = this.taxAbleAmount+this.nonTaxAbleAmount+this.tax+deliveryCharge-this.discountedAmount;
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public Double getGrossAmount() {
        this.grossAmount = this.taxAbleAmount+this.nonTaxAbleAmount;
        return grossAmount;
    }

    public void setGrossAmount(Double grossAmount) {
        this.grossAmount = grossAmount;
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

    public List<CartDTO> getCartDTOs() {
        return cartDTOs;
    }

    public void setCartDTOs(List<CartDTO> cartDTOs) {
        this.cartDTOs = cartDTOs;
    }


    public void addCartDTO(CartDTO cartDTO){
        if(null==getCartDTOs()){
            setCartDTOs(new ArrayList<CartDTO>());
        }
        getCartDTOs().add(cartDTO);

    }


}
