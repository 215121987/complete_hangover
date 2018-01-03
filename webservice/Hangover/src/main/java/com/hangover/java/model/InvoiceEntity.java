package com.hangover.java.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/1/16
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceEntity extends BaseEntity{
    
    private Long invoiceNumber;
    private OrderEntity order;
    private ShippingEntity shipping;


    @Column(name = "invoice_number", nullable = false, unique = true, updatable = false)
    public Long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @ManyToOne
    @JoinColumn(name = "order__id", nullable = false)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "shipping_id", nullable = false)
    public ShippingEntity getShipping() {
        return shipping;
    }

    public void setShipping(ShippingEntity shipping) {
        this.shipping = shipping;
    }
}
