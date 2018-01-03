package com.hangover.java.model;

import com.hangover.java.model.type.PaymentStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/1/16
 * Time: 11:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "payment_detail")
@XmlRootElement(name = "payment_detail")
public class PaymentEntity extends BaseEntity{

    private OrderEntity order;
    private Double amount;
    private String transactionId;
    private PaymentStatus status;
    private String paymentThrough;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Column(name = "amount", nullable = false, updatable = false)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "transaction_id", nullable = false, unique = true, updatable = false)
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Enumerated(EnumType.STRING)
    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Column(name = "payment_through")
    public String getPaymentThrough() {
        return paymentThrough;
    }

    public void setPaymentThrough(String paymentThrough) {
        this.paymentThrough = paymentThrough;
    }
}
