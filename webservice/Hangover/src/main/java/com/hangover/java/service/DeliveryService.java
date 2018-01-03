package com.hangover.java.service;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.UserBL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/11/16
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/deliver")
@Transactional
public class DeliveryService extends BaseService{

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private CommonBL commonBL;

    @Autowired
    private UserBL userBL;
}
