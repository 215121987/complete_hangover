package com.hangover.java.task;

import com.hangover.java.model.type.MessageType;
import com.hangover.java.notification.*;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class AsyncTask {


    public NotificationTask getPasswordNotificationTask(String username){
       return new NotificationTask(username, NotificationType.RESET_PASSWORD);
    }

    public NotificationTask getRegisterNotificationTask(Map<String,String> map){
        return new NotificationTask(map, NotificationType.REGISTRATION);
    }

    public NotificationTask getOTPNotificationTask(Map<String,String> map){
        return new NotificationTask(map, NotificationType.OTP);
    }

    public NotificationTask getOrderNotificationTask(Map<String,String> map){
        return new NotificationTask(map, NotificationType.ORDER);
    }
}
