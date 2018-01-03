package com.hangover.java.dto;

import com.hangover.java.model.type.OrderFrom;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "order_placed")
public class PlaceOrderDTO {
    
    private Long userId;
    private String cartHash;
    private Long addressId;
    private OrderFrom orderFrom;
    private String[] couponCode;
    private Double amount;
    private String orderNumber;
    private PaymentModeType paymentMode;
    private PaymentDetailDTO paymentDetail;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCartHash() {
        return cartHash;
    }

    public void setCartHash(String cartHash) {
        this.cartHash = cartHash;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public OrderFrom getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(OrderFrom orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String[] getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String [] couponCode) {
        this.couponCode = couponCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public PaymentModeType getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeType paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentDetailDTO getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(PaymentDetailDTO paymentDetail) {
        this.paymentDetail = paymentDetail;
    }
}
