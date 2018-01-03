package com.hangover.java.notification;

import com.hangover.java.dao.ShoppingDao;
import com.hangover.java.dao.UserDao;
import com.hangover.java.model.OTPEntity;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.SMSTemplateEntity;
import com.hangover.java.model.type.MessageType;
import com.hangover.java.model.type.PasswordType;
import com.hangover.java.security.PasswordEncoder;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.DateUtil;
import com.hangover.java.util.HangoverUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("smsFactory")
@Transactional
public class SMSFactory {

    private static final String SMS_DYNAMIC_TEXT = "PLACEHOLDER";

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommonUtil commonUtil;

    public Message getResetPasswordMessage(String username){
        Message message = new Message();
        UserEntity user = userDao.getUserByUsername(username);
        message.setTo(user.getMobile());
        String temPassword ="Welcome123";//HangoverUtil.getAlphaNumeric(6);
        user.setPassword(passwordEncoder.encode(temPassword));
        user.setPasswordType(PasswordType.TEMPORARY);
        userDao.save(user);
        SMSTemplateEntity smsTemplate = userDao.getMasterData(SMSTemplateEntity.class, "type", MessageType.RESET_PASSWORD);
        String sms = smsTemplate.getMessage();
        sms = sms.replace(SMS_DYNAMIC_TEXT, temPassword);
        message.setContent(sms);
        return message;
    }

    public Message getRegistrationMessage(Map<String,String> map){
        Message message = new Message();
        UserEntity user = userDao.getUserByUsername(map.get("username"));
        String temPassword ="Welcome123";//HangoverUtil.getAlphaNumeric(6);
        user.setPassword(passwordEncoder.encode(temPassword));
        user.setPasswordType(PasswordType.TEMPORARY);
        message.setTo(user.getMobile());
        SMSTemplateEntity smsTemplate = userDao.getMasterData(SMSTemplateEntity.class, "type", MessageType.REGISTRATION_NOTIFICATION);
        String sms = smsTemplate.getMessage();
        sms = sms.replaceFirst(SMS_DYNAMIC_TEXT, user.getName());
        sms = sms.replaceFirst(SMS_DYNAMIC_TEXT, map.get("username"));
        sms = sms.replaceFirst(SMS_DYNAMIC_TEXT, temPassword);
        message.setContent(sms);
        return message;
    }

    public Message getOTPMessage(Map<String,String> map){
        Message message = new Message();
        UserEntity user = userDao.getUserByUsername(map.get("username"));
        message.setTo(user.getMobile());
        String otp = HangoverUtil.getAlphaNumeric(4);
        OTPEntity otpEntity = new OTPEntity();
        otpEntity.setUser(user);
        otpEntity.setOtp(otp);
        otpEntity.setSendTo(map.get("username"));
        otpEntity.setRequestedFrom(map.get("requestedFrom"));
        userDao.save(otpEntity);
        SMSTemplateEntity smsTemplate = userDao.getMasterData(SMSTemplateEntity.class, "type", MessageType.OTP);
        String sms = smsTemplate.getMessage();
        sms = sms.replace(SMS_DYNAMIC_TEXT, otp);
        message.setContent(sms);
        return message;
    }
    
    public Message getOrderCompleteMessage(Map<String,String> map){
        Message message = new Message();
        OrderEntity order = shoppingDao.getOrder(map.get("orderNumber"));
        message.setTo(order.getCustomer().getMobile());
        MessageType type = null;
        List<String> placeHolders = new ArrayList<String>();
        placeHolders.add(order.getCustomer().getName());
        placeHolders.add(order.getOrderNumber());
        switch (order.getState()){
            case PAYMENT_SUCCESS:
                type = MessageType.ORDER_PAYMENT_SUCCESS;
                break;
            case PAYMENT_FAILED:
                type = MessageType.ORDER_PAYMENT_FAILED;
                break;
            case PAYMENT_CANCELED:
                type = MessageType.ORDER_PAYMENT_CANCELED;
                break;
            case ORDER_ACCEPTED:
                return null;
            case ORDER_REJECTED:
                type = MessageType.ORDER_REJECTED;
                break;
            case ORDER_INVOICE_GENERATED:
                return null;
            case ORDER_PACKED:
                return null;
            case ORDER_DISPATCHED:
                type = MessageType.ORDER_DISPATCHED;
                break;
            case ORDER_DELIVERED:
                type = MessageType.ORDER_DELIVERED;
                placeHolders.add(DateUtil.convertDateToString(order.getUpdatedAt()));
                break;
            case ORDER_DELIVERY_FAILED:
                type = MessageType.ORDER_DELIVERY_FAILED;
                break;
            default: return null;
        }
        SMSTemplateEntity smsTemplate = userDao.getMasterData(SMSTemplateEntity.class, "type", type);
        String sms = smsTemplate.getMessage();
        for(String placeHolder : placeHolders){
            sms = sms.replaceFirst(SMS_DYNAMIC_TEXT, placeHolder);
        }
        message.setContent(sms);
        return message;
    }

}
