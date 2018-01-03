package com.hangover.java.bl;

import com.hangover.java.dto.PaymentCompleteDTO;
import com.hangover.java.dto.PaymentGatewayDetail;
import com.hangover.java.dto.PlaceOrderDTO;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.OrderEntity;

import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/4/16
 * Time: 7:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentBL {

    PaymentGatewayDetail placeOrder(PlaceOrderDTO placeOrderDTO, StatusDTO status);

    PaymentCompleteDTO paymentDone(String checkSumHash, TreeMap<String,String> parameters);

    void payment(String orderId, String transactionId, Double amount, String paymentThough, String status);

    void postPaymentSuccess(String orderNumber);

    void notifyOrderToStore(OrderEntity order);
}
