package com.hangover.java.controller;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.model.AddressEntity;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.UserCardInfoEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.type.OrderItemState;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 5/21/16
 * Time: 7:50 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private CommonBL commonBL;

    @Autowired
    private ShoppingBL shoppingBL;


    @RequestMapping("")
    public String account(HttpServletRequest request) {
        return "/customer/account";
    }


    @RequestMapping("/order")
    public String orders(HttpServletRequest request, @QueryParam("tab")String tab ) {
        String RETURN_PATH = "";
        OrderItemState  orderItemState = OrderItemState.ORDERED;
        if(isAjaxRequest(request) && StringUtil.isNotNullOrEmpty(tab)){
            /*if(tab.equalsIgnoreCase(TAB_ORDER) ){
                orderItemState =  OrderItemState.ORDERED;
            }else if(tab.equalsIgnoreCase(TAB_ORDER_HISTORY) ){
                orderItemState =  OrderItemState.DELIVERED;;
            }else */if(tab.equalsIgnoreCase(TAB_RETURN_ORDER) ){
                orderItemState =  OrderItemState.RETURNED;
            } else if(tab.equalsIgnoreCase(TAB_DELIVERED_ORDER) ){
                orderItemState =  OrderItemState.DELIVERED;
            }
            RETURN_PATH =  "/customer/orders";
        } else{
            RETURN_PATH =  "/customer/orderLayout";
        }
        List<OrderEntity> orders = commonBL.getEntities(OrderEntity.class, "customer", getCurrentUsers().getId());
        request.setAttribute("orders", orders);
        return RETURN_PATH;
    }


    @RequestMapping("/profile")
    public String profiles(HttpServletRequest request, @QueryParam("tab")String tab) {
        String RETURN_PATH = "/customer/profile";
        if(StringUtil.isNotNullOrEmpty(tab)){
            if(tab.equalsIgnoreCase(TAB_PERSONAL_INFO) ){
                UserEntity user = getCurrentUsers();
                request.setAttribute("user", user);
                if(isAjaxRequest(request))
                    RETURN_PATH =  "/customer/personalInfo";
            }else if(tab.equalsIgnoreCase(TAB_SAVED_ADDRESSES) ){
                List<AddressEntity> addresses = commonBL.getEntities(AddressEntity.class,
                        "user", getCurrentUsers().getId());
                request.setAttribute("addresses", addresses);
                if(isAjaxRequest(request))
                    RETURN_PATH =  "/customer/deliveryAddress";
            }else if(tab.equalsIgnoreCase(TAB_SAVED_CARDS) ){
                List<UserCardInfoEntity> userCardsInfo = commonBL.getEntities(UserCardInfoEntity.class,
                        "user", getCurrentUsers().getId());
                request.setAttribute("cardsInfo", userCardsInfo);
                if(isAjaxRequest(request))
                    RETURN_PATH =  "/customer/savedCards";
            }
        } else{
            UserEntity user = getCurrentUsers();
            request.setAttribute("user", user);
        }
        request.setAttribute("tab", tab);
        return RETURN_PATH;
    }

    @RequestMapping("/credit")
    public String credit(HttpServletRequest request) {
        return "home";
    }


}
