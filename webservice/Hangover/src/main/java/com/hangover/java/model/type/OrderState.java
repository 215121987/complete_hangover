package com.hangover.java.model.type;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/1/16
 * Time: 11:40 PM
 * To change this template use File | Settings | File Templates.
 */
public enum OrderState {

    ORDER_CREATED,
    PAYMENT_SUCCESS,
    PAYMENT_FAILED,
    PAYMENT_CANCELED,
    ORDER_CANCELED,
    ORDER_ACCEPTED,
    ORDER_REJECTED,
    ORDER_IN_PROCESS,
    ORDER_INVOICE_GENERATED,
    ORDER_PACKED,
    ORDER_DISPATCHED,
    ORDER_DELIVERED,
    ORDER_DELIVERY_FAILED
}
