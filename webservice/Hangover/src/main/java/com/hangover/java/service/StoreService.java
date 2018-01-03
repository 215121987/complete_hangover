package com.hangover.java.service;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.bl.StoreBL;
import com.hangover.java.dto.PlaceOrderDTO;
import com.hangover.java.dto.ShoppingDTO;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.ShoppingCartItemEntity;
import com.hangover.java.model.SupplierStoreEntity;
import com.hangover.java.model.master.ServiceChargeEntity;
import com.hangover.java.model.type.OrderFrom;
import com.hangover.java.model.type.OrderState;
import com.hangover.java.service.wso.CartWSO;
import com.hangover.java.service.wso.OrderWSO;
import com.hangover.java.util.CommonUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/26/16
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/store")
@Transactional
public class StoreService extends BaseService {

    private Logger logger = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    private CommonBL commonBL;

     @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private ShoppingBL shoppingBL;

    @Autowired
    private StoreBL storeBL;

    @GET
    @Path("/item")
    public Response getShop(@Context UriInfo uriInfo) {
        Map<String, String> queryParam = getQueryParamAsStringMap(uriInfo);
        List entities  = shoppingBL.getItem(queryParam);
        return sendResponse(entities);
    }

    @GET
    @Path("/cart")
    @PermitAll
    public Response cart(@Context UriInfo uriInfo, @Context SecurityContext context){
        List<ShoppingCartItemEntity> shoppingCartItems = shoppingBL.getCartItem(getUser(context).getId());
        List<ServiceChargeEntity> serviceCharges = commonBL.getEntities(ServiceChargeEntity.class, null);
        CartWSO cartWSO = new CartWSO();
        cartWSO.setCartItems(shoppingCartItems);
        cartWSO.setServiceCharges(serviceCharges);
        if(null != shoppingCartItems)
            cartWSO.setCount(shoppingCartItems.size());
        return sendResponse(cartWSO);
    }

    @POST
    @Path("/cart")
    @PermitAll
    public Response addToCart(@FormParam("itemId")Long itemId, @FormParam("itemDetailId")Long itemDetailId,
                              @FormParam("quantity")Integer quantity, @Context SecurityContext context) {
        StatusDTO statusDTO = new StatusDTO();
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        shoppingDTO.setItemId(itemId);
        shoppingDTO.setItemDetailId(itemDetailId);
        shoppingDTO.setUserId(getUser(context).getId());
        shoppingDTO.setQuantity(quantity);
        List<ShoppingCartItemEntity> shoppingCartItems = shoppingBL.addItemToCart(shoppingDTO, statusDTO);
        CartWSO cartWSO = new CartWSO();
        cartWSO.setCount(shoppingCartItems.size());
        return sendResponse(cartWSO);
    }

    @GET
    @Path("/service/{zipCode}")
    public Response checkService(@PathParam("zipCode")String zipCode){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("zipCode", zipCode);
        List<SupplierStoreEntity> stores  = commonBL.getEntities(SupplierStoreEntity.class, param);
        StatusDTO statusDTO = new StatusDTO();
        if(null== stores || stores.size()<=0){
            statusDTO.setCode(405);
            statusDTO.setMessage(commonUtil.getText("error.dont.have.service"));
        }
        return sendResponse(statusDTO);
    }

    @POST
    @Path("/order/place")
    @PermitAll
    public Response placeOrder(@FormParam("cartHash")String cartHas, @FormParam("amount")Double amount,
                               @FormParam("addressId")Long addressId, @Context SecurityContext context) {
        StatusDTO statusDTO = new StatusDTO();
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setUserId(getUser(context).getId());
        placeOrderDTO.setCartHash(cartHas);
        placeOrderDTO.setAddressId(addressId);
        placeOrderDTO.setOrderFrom(OrderFrom.APP);
        PlaceOrderDTO order = shoppingBL.placeOrder(placeOrderDTO, statusDTO);
        /*OrderWSO orderWSO = new OrderWSO();
        orderWSO.setOrderNumber(order.getOrderNumber());*/
        return sendResponse(order);
    }


    @GET
    @Path("/order")
    @PermitAll
    public Response getStoreOrder(@Context SecurityContext context){
        List<OrderEntity> orders = storeBL.getStoreOpenOrder(getUser(context).getId());
        return sendResponse(orders);
    }


    @GET
    @Path("/order/archive")
    @PermitAll
    public Response getStoreOrderArchive(@Context SecurityContext context, @QueryParam("pageNumber")int pageNumber){
        List<OrderEntity> orders = storeBL.getStoreStaffArchiveOrder(getUser(context).getId(), pageNumber);
        return sendResponse(orders);
    }

    @GET
    @Path("/order/{id}")
    @PermitAll
    public Response getOrder(@PathParam("id") Long id){
        OrderEntity order = commonBL.getOrder(id);
        return sendResponse(order);
    }


    @POST
    @Path("/order/process")
    @PermitAll
    public Response updateOrderState(@FormParam("orderId")Long orderId, @FormParam("state")String state, @Context SecurityContext context){
       StatusDTO status = new StatusDTO();
        storeBL.updateState(orderId, getUser(context).getId(), state, status);
        return sendResponse(status);
    }
    
}
