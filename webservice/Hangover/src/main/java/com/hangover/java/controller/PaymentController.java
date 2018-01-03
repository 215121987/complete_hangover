package com.hangover.java.controller;

import com.hangover.java.bl.PaymentBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.dto.*;
import com.hangover.java.model.type.OrderFrom;
import com.hangover.java.util.HangoverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/9/16
 * Time: 6:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(CommonController.class);


    @Autowired
    private PaymentBL paymentBL;

    @RequestMapping(value = "" , method = RequestMethod.POST)
    public String payment(HttpServletRequest request, HttpServletResponse response,
                          PaymentDetailDTO paymentDetail,
                          @RequestParam("amount")Double amount,
                          @RequestParam("mode")String paymentMode,
                          @RequestParam("walletId")Long walletId) throws Exception {
        StatusDTO statusDTO = new StatusDTO();
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setOrderFrom(OrderFrom.WEB);
        placeOrderDTO.setUserId(getCurrentUsers().getId());
        placeOrderDTO.setCartHash(HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH).getValue());
        placeOrderDTO.setAddressId((Long) request.getSession().getAttribute(SESSION_DELIVERY_ADDRESS_ID));
        placeOrderDTO.setAmount(paymentDetail.getAmount());
        placeOrderDTO.setPaymentDetail(paymentDetail);
        PaymentGatewayDetail paymentGatewayDetail = paymentBL.placeOrder(placeOrderDTO, statusDTO);
        request.setAttribute("gatewayDetail", paymentGatewayDetail);
        return "/payment/paymentProgress";
    }


    @RequestMapping(value = "/done" , method = RequestMethod.POST)
    public String paymentDone(HttpServletRequest request, HttpServletResponse response){
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, String[]> mapData = request.getParameterMap();
        TreeMap<String,String> parameters = new TreeMap<String,String>();
        String checkSumHash =  "";
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            if(paramName.equals("CHECKSUMHASH")){
                checkSumHash = mapData.get(paramName)[0];
            }else{
                parameters.put(paramName,mapData.get(paramName)[0]);
            }
        }
        PaymentCompleteDTO paymentComplete = paymentBL.paymentDone(checkSumHash, parameters);
        request.setAttribute("payment", paymentComplete);
        if(paymentComplete.getStatus().equals("SUCCESS")){
            removeCookie(response, request.getContextPath(),COOKIES_CART_HASH);
            request.removeAttribute(SESSION_DELIVERY_ADDRESS_ID);
        }
        return "/payment/paymentComplete";
    }





}
