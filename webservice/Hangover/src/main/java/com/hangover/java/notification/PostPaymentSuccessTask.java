package com.hangover.java.notification;

import com.hangover.java.bl.PaymentBL;
import com.hangover.java.dao.CommonDao;
import com.hangover.java.dao.StoreDao;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.util.ApplicationContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/6/16
 * Time: 12:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class PostPaymentSuccessTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(PostPaymentSuccessTask.class);

    private String orderNumber;

    private PaymentBL paymentBL;

    public PostPaymentSuccessTask(final String orderNumber){
        this.orderNumber = orderNumber;
        this.paymentBL = (PaymentBL)ApplicationContextUtil.getApplicationContext().getBean("paymentBL");
        logger.info("Post payment success task created");
    }


    @Override
    public void run() {
        logger.info("Executing Post payment success task");
           execute();
    }


    private void execute(){
        this.paymentBL.postPaymentSuccess(this.orderNumber);
    }
}
