package com.hangover.java.task;

import com.hangover.java.dao.UserDao;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.Constants;
import com.hangover.java.util.MessageSenderUtil;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 31/10/15
 * Time: 1:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotifyUserTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(NotifyUserTask.class);
    
    private UserDao userDao;
    private VelocityEngine velocityEngine;
    private CommonUtil commonUtil;

    public NotifyUserTask(HangoverBeans hangoverBeans) {
        //this.velocityEngine = hangoverBeans.getVelocityEngine();
        this.userDao = hangoverBeans.getUserDao();
        this.commonUtil = hangoverBeans.getCommonUtil();
    }



    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void execute(Map<String, Object> map) {
        String templateName = Constants.NOTIFICATION_MAIL_TEMPLATE;
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        List<UserEntity> userList = new ArrayList<UserEntity>();
        for (UserEntity user : userList) {
            mailMessage.setTo(user.getEmail());
            map.put("name", user.getName());
            try {
                MessageSenderUtil messageSenderUtil = new MessageSenderUtil(getProperties());
                messageSenderUtil.send(mailMessage, velocityEngine, templateName, map);
            } catch (MessagingException e) {
                logger.error("Unable to send mail to user " + e);
            }
        }
      
    }

    public void notifyAdmin(Map<String, Object> map){
        String templateName = Constants.NOTIFICATION_MAIL_TEMPLATE;
        List<UserEntity> userList = userDao.getUserByRole(Role.ROLE_ADMIN);
        map.put("content",commonUtil.getText("email.notification.content.floor.enrich", new Object[]{map.get("storeName")}));
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(commonUtil.getText("email.notification.subject.floor.enrich", new Object[]{map.get("storeName")}));
        for (UserEntity user : userList) {
            mailMessage.setTo(user.getEmail());
            map.put("name", user.getName());
            try {
                MessageSenderUtil messageSenderUtil = new MessageSenderUtil(getProperties());
                messageSenderUtil.send(mailMessage, velocityEngine, templateName, map);
            } catch (MessagingException e) {
                logger.error("Unable to send mail to user " + e);
            }
        }
    }
    
  
    
    
    

    private Properties getProperties(){
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", CommonUtil.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", CommonUtil.getProperty("mail.smtp.host"));
        props.put("mail.smtp.port", CommonUtil.getProperty("mail.smtp.port"));
        props.put("mail.smtp.auth", CommonUtil.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.from.email",CommonUtil.getProperty("mail.smtp.from.email"));
        props.put("mail.smtp.user",CommonUtil.getProperty("mail.smtp.user"));
        props.put("mail.smtp.password",CommonUtil.getProperty("mail.smtp.password"));
        return props;
    }
    
    
    public void notifyUserPassword(Map<String, Object> map, String email){
        String templateName = Constants.FORGOT_PASSWORD_TEMPLATE;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(commonUtil.getText("email.notification.subject.forgot.password"));
        mailMessage.setTo(email);
        try {
            MessageSenderUtil messageSenderUtil = new MessageSenderUtil(getProperties());
            messageSenderUtil.send(mailMessage, velocityEngine, templateName, map);
        } catch (MessagingException e) {
            logger.error("Unable to send mail to user " + e);
            //e.printStackTrace();
        }
    }


}
