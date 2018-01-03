package com.hangover.java.security;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.controller.BaseController;
import com.hangover.java.dto.CartDTO;
import com.hangover.java.dto.ShoppingDTO;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.ItemDetailEntity;
import com.hangover.java.model.ShoppingCartItemEntity;
import com.hangover.java.model.SupplierStaffEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.Constants;
import com.hangover.java.util.HangoverUtil;
import com.hangover.java.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 20/8/14
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class Authentication extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationFailureHandler, Constants {

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private CommonBL commonBL;


    @Autowired
    private ShoppingBL shoppingBL;


    private UserEntity user;



    public void setLogoutUrl(String logoutUrl) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        System.out.print("Loggedin");
         this.user = (UserEntity) authentication.getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.LOGGED_IN_USER_ID, user.getId());
        session.setAttribute(Constants.LOGGED_IN_USERNAME, user.getUsername());
        session.setAttribute(Constants.LOGGED_IN_FULL_NAME, user.getName());
        List<SupplierStaffEntity> supplierStaffs = commonBL.getEntities(SupplierStaffEntity.class, "user", user.getId());
        if(null!=supplierStaffs && supplierStaffs.size()>0){
            SupplierStaffEntity supplierStaff =  supplierStaffs.get(0);
            session.setAttribute(Constants.LOGGED_IN_USER_SUPPLIER_ID, supplierStaff.getSupplier().getId());
            if(null!=supplierStaff.getStore())
                session.setAttribute(Constants.LOGGED_IN_USER_STORE_ID, supplierStaff.getStore().getId());
        }
        HangoverUtil.updateUserCart(request, response,this.shoppingBL, this.user.getId());
        updateUserWishList(request);
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(HttpStatus.OK.value());
        statusDTO.setMessage("Logged in successfully");
        if(isAjaxRequest(request)){
            try {
                responseAsJSON(response, HangoverUtil.getStatusAsJSON(statusDTO));
            } catch (JSONException e) {
                logger.error("Unable to send response as json "+ e);
            }
        }else{
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.print("Logg-in failed"+ e.getMessage());
        StatusDTO status = new StatusDTO();
        status.setCode(HttpStatus.FORBIDDEN.value());
        status.setMessage(commonUtil.getText("error.credential.invalid", request.getLocale()));
        request.getSession().setAttribute(Constants.RESPONSE_STATUS, status);
        response.sendRedirect(request.getContextPath() + "/login.html");
    }


    private void updateUserCart(HttpServletRequest request, HttpServletResponse response){
        //List<CartDTO> cartDTOs =  new ArrayList<CartDTO>();
        Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH);
        if(null!=cookie && StringUtils.isNotEmpty(cookie.getValue())){
            String cartHash = cookie.getValue();
            cartHash = HangoverUtil.base64Decode(cartHash);
            List<CartDTO> cartDTOs = HangoverUtil.getCartDTOFromCartHash(cartHash);
            List<ShoppingDTO> shoppingDTOs = HangoverUtil.getShoppingDTOFromCart(cartDTOs);
            List<ShoppingCartItemEntity>  shoppingCartItems =  shoppingBL.updateCart(shoppingDTOs, user.getId(), null);
            if(null!= shoppingCartItems && shoppingCartItems.size()>0){
                cartDTOs = HangoverUtil.getCartDTOFromShoppingCartItems(shoppingCartItems);
                request.getSession().setAttribute(SESSION_CART, cartDTOs);
                cartHash = HangoverUtil.getCartHash(cartDTOs);
                response.addCookie(HangoverUtil.getCartHashCookie(request.getContextPath(),cartHash));
            }
        }
        //List<CartDTO> cartDTOs = (List<CartDTO>) request.getSession().getAttribute(SESSION_CART);
        /*List<ShoppingDTO> shoppingDTOs = HangoverUtil.getShoppingDTOFromCart(cartDTOs);*/
        /*if(null!=cartDTOs && cartDTOs.size()>0){
            String cartHash = HangoverUtil.getCartHash(cartDTOs);
            response.addCookie(HangoverUtil.getCartHashCookie(cartHash));
        }*/
        /*List<ShoppingCartItemEntity>  shoppingCartItems =  shoppingBL.updateCart(shoppingDTOs, user.getId(), null);
        if(null!= shoppingCartItems && shoppingCartItems.size()>0){
            cartDTOs = HangoverUtil.getCartDTOFromShoppingCartItems(shoppingCartItems);
            request.getSession().setAttribute(SESSION_CART, cartDTOs);
            String cartHash = HangoverUtil.getCartHash(cartDTOs);
            response.addCookie(HangoverUtil.getCartHashCookie(request.getContextPath(),cartHash));
        }*/
    }

    private void updateUserWishList(HttpServletRequest request){
        List<Long> itemIds = (List<Long>) request.getSession().getAttribute(SESSION_SHORTLIST);
        if(null!=itemIds && itemIds.size()>0){
            shoppingBL.updateWishList(itemIds, user.getId(), null);
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(ajaxHeader);
    }

    protected void responseAsJSON(HttpServletResponse response, JSONObject object) throws IOException {
        response.setContentType("application/x-json");
        response.getWriter().print(object);
    }

}
