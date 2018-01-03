package com.hangover.java.bl.impl;

import com.hangover.java.bl.PaymentBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.dao.CommonDao;
import com.hangover.java.dao.ShoppingDao;
import com.hangover.java.dao.StoreDao;
import com.hangover.java.dao.UserDao;
import com.hangover.java.dto.*;
import com.hangover.java.model.*;
import com.hangover.java.model.type.OrderState;
import com.hangover.java.model.type.PaymentStatus;
import com.hangover.java.notification.Message;
import com.hangover.java.notification.PostPaymentSuccessTask;
import com.hangover.java.notification.PushNotificationService;
import com.hangover.java.task.AsyncTask;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.Constants;
import com.hangover.java.util.HangoverUtil;
import com.paytm.merchant.CheckSumServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/4/16
 * Time: 7:37 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository("paymentBL")
@Transactional
public class PaymentBLImpl extends BaseBL implements PaymentBL, Constants {


    private Logger logger = LoggerFactory.getLogger(PaymentBLImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private PushNotificationService fcmPush;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private TaskExecutor taskExecutor;


    @Autowired
    private ShoppingBL shoppingBL;


    @Override
    public PaymentGatewayDetail placeOrder(PlaceOrderDTO placeOrder, StatusDTO status) {
        PaymentGatewayDetail paymentGatewayDetail =null;
        List<ShoppingCartItemEntity> cartItems = shoppingDao.getCartItems(placeOrder.getUserId());
        if(null!= cartItems  && cartItems.size()>0){
            CartSummaryDTO cartSummary = shoppingBL.prepareSummary(cartItems);
            if(!placeOrder.getAmount().equals(cartSummary.getNetAmount())){
                status.setMessage(commonUtil.getText("error.mismatch.amount", status.getLocale()));
                saveErrorMessage(status, HttpStatus.CONFLICT.ordinal());
                return paymentGatewayDetail;
            }
            OrderEntity order = new OrderEntity();
            order.setOrderNumber(HangoverUtil.generateOrderNumber(placeOrder.getUserId(), cartItems.get(0).getShoppingCart().getId()));
            order.setCustomer(userDao.getUser(placeOrder.getUserId()));
            order.setAddress(AddressEntity.getAddress(placeOrder.getAddressId()));
            order.setOrderFrom(placeOrder.getOrderFrom());
            order.setTotalAmount(cartSummary.getNetAmount());
            for(ShoppingCartItemEntity cartItem :  cartItems){
                OrderItemEntity orderItem = new OrderItemEntity();
                orderItem.setItem(cartItem.getItem());
                orderItem.setItemSize(cartItem.getItemDetail().getItemSize());
                orderItem.setPrice(cartItem.getPrice());
                orderItem.setOrder(order);
                orderItem.setQuantity(cartItem.getQuantity());
                order.addOrderItem(orderItem);
            }
            shoppingDao.save(order);
            placeOrder.setOrderNumber(order.getOrderNumber());
            saveSuccessMessage(status,"");
            paymentGatewayDetail = initiatePayment(placeOrder.getPaymentDetail(), order);
        }
        return paymentGatewayDetail;
    }


    public PaymentGatewayDetail initiatePayment(PaymentDetailDTO paymentDetail, OrderEntity order){
        PaymentGatewayDetail paymentGatewayDetail = new PaymentGatewayDetail();
        switch (PaymentModeType.valueOf(paymentDetail.getMode())){
            case WALLET:
                paymentGatewayDetail.setActionURL(CommonUtil.getProperty("PAYTM.URL"));
                paymentGatewayDetail.addParam("ORDER_ID", order.getOrderNumber());
                paymentGatewayDetail.addParam("CUST_ID", order.getCustomer().getId() + "");
                paymentGatewayDetail.addParam("INDUSTRY_TYPE_ID", CommonUtil.getProperty("PAYTM.INDUSTRY.TYPE.ID"));
                switch (order.getOrderFrom()){
                    case APP:
                        paymentGatewayDetail.addParam("CHANNEL_ID", CommonUtil.getProperty("PAYTM.CHANNEL.WAP.ID"));
                        break;
                    default:
                        paymentGatewayDetail.addParam("CHANNEL_ID", CommonUtil.getProperty("PAYTM.CHANNEL.WEB.ID"));
                        break;
                }
                paymentGatewayDetail.addParam("TXN_AMOUNT", order.getTotalAmount() + "");
                paymentGatewayDetail.addParam("MID", CommonUtil.getProperty("PAYTM.MID"));
                paymentGatewayDetail.addParam("WEBSITE", CommonUtil.getProperty("PAYTM.WEBSITE"));
                paymentGatewayDetail.addParam("MOBILE_NO", order.getCustomer().getMobile());
                paymentGatewayDetail.addParam("EMAIL", order.getCustomer().getEmail());
                paymentGatewayDetail.addParam("CALLBACK_URL", CommonUtil.getProperty("PAYMENT.CALLBACK.URL"));
                try {
                    paymentGatewayDetail.addParam("CHECKSUMHASH", CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(CommonUtil.getProperty("PAYTM.MERCHANT.KEY"), paymentGatewayDetail.gerParamAsTreeMap()));
                } catch (Exception e) {
                    logger.error("Unable to generate PAYTM Checksum"+ e);
                }
                break;
        }
        return paymentGatewayDetail;
    }

    
    public PaymentCompleteDTO paymentDone(String checkSumHash, TreeMap<String,String> parameters){
        PaymentCompleteDTO paymentComplete = new PaymentCompleteDTO();
        boolean isValidChecksum = false;
        try{
            isValidChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(CommonUtil.getProperty("PAYTM.MERCHANT.KEY"), parameters,checkSumHash);
        }catch (Exception e){
            logger.error("Invalid Checksum"+ e);
        }
        String status = "FAILED";
        if(isValidChecksum && parameters.containsKey("STATUS")){
            if(parameters.get("STATUS").equals("TXN_SUCCESS")){
                status = "SUCCESS";
            }else{
                status = "FAILED";
            }
        }else{
            logger.error("Checksum mismatch");
        }
        payment(parameters.get("ORDERID"), parameters.get("TXNID"), Double.parseDouble(parameters.get("TXNAMOUNT")), "PAYTM", status);
        paymentComplete.setOrderNumber(parameters.get("ORDERID"));
        paymentComplete.setStatus(status);
        paymentComplete.setTransactionId(parameters.get("TXNID"));
        return paymentComplete;
    }
    
    
    public void payment(String orderId, String transactionId, Double amount, String paymentThough, String status){
        OrderEntity order  = shoppingDao.getOrder(orderId);
        PaymentEntity payment = new PaymentEntity();
        payment.setOrder(order);
        payment.setAmount(amount);
        logger.info("Payment:- transaction id:-"+ transactionId+" , order id:-"+orderId+", status:-"+ status);
        if("SUCCESS".equals(status)){
            order.setState(OrderState.PAYMENT_SUCCESS);
            payment.setStatus(PaymentStatus.SUCCESS);
        }else if("FAILED".equals(status)){
            order.setState(OrderState.PAYMENT_FAILED);
            payment.setStatus(PaymentStatus.FAILED);
        }
        payment.setTransactionId(transactionId);
        payment.setPaymentThrough(paymentThough);
        shoppingDao.save(order);
        shoppingDao.save(payment);
        if("SUCCESS".equals(status)){
            shoppingDao.emptyUserCart(order.getCustomer().getId());
        }
        Map<String,String> map = new HashMap<String, String>();
        map.put("email", order.getCustomer().getEmail());
        map.put("mobile", order.getCustomer().getMobile());
        map.put("orderNumber", orderId);
        map.put("transactionId", transactionId);
        map.put("amount", amount+"");
        map.put("status", status);
        this.taskExecutor.execute(this.asyncTask.getOrderNotificationTask(map));
        this.taskExecutor.execute(new PostPaymentSuccessTask(orderId));
    }
    
    public void postPaymentSuccess(String orderNumber){
        OrderEntity order = commonDao.getOrder(orderNumber);
        AddressEntity address = order.getAddress();
        List<SupplierStoreEntity> stores = storeDao.getStoreByLocation(address.getZipCode());
        SupplierStoreEntity store  = stores.get(0);
        order.setStore(store);
        storeDao.save(order);
        notifyOrderToStore(order);
    }
    
    public void notifyOrderToStore(OrderEntity order){
        logger.info("Notifying order to store...");
        List<LoginStatusEntity> loggedInStaffs = storeDao.getAvailableStoreStaff(order.getStore().getId());
        if(null!= loggedInStaffs && loggedInStaffs.size()>0){
            Message message = new Message();
            String []to = new String[loggedInStaffs.size()];
            for(int i=0;i<loggedInStaffs.size();i++){
                to[i] = loggedInStaffs.get(i).getPushToken();
            }
            message.setTo(to);
            message.setSubject("New Order");
            message.setContent(commonUtil.getText("push.notification.order.placed.content"));
            message.putContext("name", order.getCustomer().getName());
            message.putContext("mobile", order.getCustomer().getMobile());
            message.putContext("orderId", order.getId()+"");
            message.putContext("orderNumber", order.getOrderNumber());
            logger.info("Sending push to store devices...");
            fcmPush.transactional(message);
        }
    }
    
    
}
