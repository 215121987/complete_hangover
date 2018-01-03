package com.hangover.java.notification;

import com.hangover.java.dao.ShoppingDao;
import com.hangover.java.dao.UserDao;
import com.hangover.java.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/13/16
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
@Component("pushFactory")
@Transactional
public class PushNotificationFactory {


    @Autowired
    private UserDao userDao;

    @Autowired
    private CommonUtil commonUtil;
    
    @Autowired
    private ShoppingDao shoppingDao;
    
    public void ageConfirmationMessage(Map<String,String> param){
         Message message = new Message();

    }
    
}
