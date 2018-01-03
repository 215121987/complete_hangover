package com.hangover.java.notification;

import com.hangover.java.model.type.MessageType;
import com.hangover.java.util.ApplicationContextUtil;
import com.hangover.java.util.ValidatorUtil;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationTask implements Runnable {

    private NotificationType notificationType = null;
    private Message message;
    private String to;
    private Map<String,String> map;

    private MessageSender smsSender;

    private MessageSender emailSender;

    private EmailFactory emailFactory;

    private SMSFactory smsFactory;

    public NotificationTask(String username, NotificationType type) {
        this.to = username;
        this.notificationType = type;
    }

    public NotificationTask(String[] usernames, NotificationType type) {
        this.to = usernames[0];
        this.message = new Message();
        this.message.setTo(usernames);
        this.notificationType = type;
    }

    public NotificationTask(Map<String,String> map, NotificationType type) {
        this.to = map.get("username");
        this.notificationType = type;
        this.map = map;
    }

    public NotificationTask(Message message, NotificationType type) {
        this.to = message.getTo()[0];
        this.message = message;
        this.notificationType = type;
    }

    public NotificationTask(Message message) {
        this.to = message.getTo()[0];
        this.message = message;
    }

    private void prepareTask() {
        this.smsSender = (SMSSender) ApplicationContextUtil.getApplicationContext().getBean("smsSender");
        this.emailSender = (EmailSender) ApplicationContextUtil.getApplicationContext().getBean("emailSender");
        this.emailFactory = (EmailFactory) ApplicationContextUtil.getApplicationContext().getBean("emailFactory");
        this.smsFactory = (SMSFactory) ApplicationContextUtil.getApplicationContext().getBean("smsFactory");
    }

    @Override
    public void run() {
        prepareTask();
        this.execute();
    }

    private void execute() {
        Message email = null;
        Message sms = null;
        switch (this.notificationType){
            case ORDER:
                sms = this.smsFactory.getOrderCompleteMessage(this.map);
                email = this.emailFactory.getOrderCompleteMessage(this.map);
                break;
            case ORDER_DISPATCHED:break;
            case ORDER_DELIVERED:break;
            case RESET_PASSWORD:
                sms = this.smsFactory.getResetPasswordMessage(this.to);
                email = this.emailFactory.getResetPasswordMessage(this.to);
                break;
            case REGISTRATION:
                sms = this.smsFactory.getRegistrationMessage(this.map);
                email = this.emailFactory.getRegistrationMessage(this.map);
                break;
            case OTP:
                if(isEmail())
                    this.emailFactory.getOTPMessage(this.map);
                else if(isMobile())
                    sms= this.smsFactory.getOTPMessage(this.map);
                break;
            default:
                if(isEmail())
                    email = this.message;
                else if(isMobile())
                    sms = this.message;
                break;
        }
        if(null!= email)
            this.emailSender.send(email);
        if(null!=sms)
            this.smsSender.send(sms);
    }

    private Message prepareSMS() {
        Message resultMessage;
        switch (notificationType) {
            case REGISTRATION:  resultMessage = this.smsFactory.getRegistrationMessage(this.map);
                break;
            case RESET_PASSWORD: resultMessage = this.smsFactory.getResetPasswordMessage(this.to);
                break;
            case OTP:resultMessage = this.smsFactory.getOTPMessage(this.map);
                break;
            case ORDER:resultMessage = this.smsFactory.getOrderCompleteMessage(this.map);
                break;
            default:
                resultMessage = this.message;
        }
        return resultMessage;
    }

    private Message prepareMail() {
        Message resultMessage;
        switch (notificationType) {
            case REGISTRATION: resultMessage = this.emailFactory.getRegistrationMessage(this.map);
                break;
            case RESET_PASSWORD:
                resultMessage = this.emailFactory.getResetPasswordMessage(this.to);
                break;
            case OTP:
                resultMessage = this.emailFactory.getOTPMessage(this.map);
                break;
            case ORDER:this.emailFactory.getOrderCompleteMessage(this.map);
            default:
                resultMessage = this.message;
        }
        return resultMessage;
    }

    public boolean isEmail() {
        return ValidatorUtil.isValidEmail(this.to);
    }

    public boolean isMobile() {
        return ValidatorUtil.isValidMobile(this.to);
    }
}
