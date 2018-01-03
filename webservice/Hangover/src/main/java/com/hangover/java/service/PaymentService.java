package com.hangover.java.service;

import com.hangover.java.bl.PaymentBL;
import com.hangover.java.dto.PaymentDetailDTO;
import com.hangover.java.dto.PaymentGatewayDetail;
import com.hangover.java.dto.PlaceOrderDTO;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.type.OrderFrom;
import com.hangover.java.util.HangoverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 9:02 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/payment")
@Transactional
public class PaymentService extends BaseService{

    private Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PaymentBL paymentBL;


    @POST
    @PermitAll
    public Response payment(PaymentDetailDTO paymentDetail, @FormParam("addressId")Long addressId,
                            @Context SecurityContext context){
        StatusDTO statusDTO = new StatusDTO();
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setOrderFrom(OrderFrom.APP);
        placeOrderDTO.setUserId(getUser(context).getId());
        placeOrderDTO.setAddressId(addressId);
        placeOrderDTO.setAmount(paymentDetail.getAmount());
        placeOrderDTO.setPaymentDetail(paymentDetail);
        PaymentGatewayDetail paymentGatewayDetail = paymentBL.placeOrder(placeOrderDTO, statusDTO);
        return sendResponse(paymentGatewayDetail);
    }


    @POST
    @Path("/done")
    public Response paymentDone(@Context UriInfo uriInfo){
        return Response.ok().build();
    }

}
