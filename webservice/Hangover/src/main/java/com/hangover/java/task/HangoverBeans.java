package com.hangover.java.task;

import com.hangover.java.dao.CommonDao;
import com.hangover.java.dao.UserDao;
import com.hangover.java.security.PasswordEncoder;
import com.hangover.java.util.CommonUtil;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 06/11/15
 * Time: 11:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HangoverBeans {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private CommonDao commonDao ;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CommonUtil getCommonUtil() {
        return commonUtil;
    }

    public CommonDao getCommonDao() {
        return commonDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
