package com.hangover.java.dto;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/10/16
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PaymentCompleteDTO {
    
    
    private String status;
    private String orderNumber;
    private String transactionId;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
