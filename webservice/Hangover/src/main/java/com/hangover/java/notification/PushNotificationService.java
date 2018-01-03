package com.hangover.java.notification;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/11/16
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PushNotificationService {

    void promotional(Message message);


    void transactional(Message message);
}
