package com.hangover.java.interceptor;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.dto.CartDTO;
import com.hangover.java.model.ItemDetailEntity;
import com.hangover.java.util.Constants;
import com.hangover.java.util.HangoverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/24/16
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class HangoverInterceptor implements HandlerInterceptor, Constants {

    @Autowired
    private ShoppingBL shoppingBL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_SESSION_ID);
        if(null==cookie){
            request.getSession().setMaxInactiveInterval(60*60*24*5);
            cookie = new Cookie(COOKIES_SESSION_ID, request.getSession().getId());
            cookie.setMaxAge(60*60*24*5);
            response.addCookie(cookie);
        }
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
       // e.printStackTrace();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    private void processRequestCart(HttpServletRequest request){
        Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH);
        if(null==request.getUserPrincipal() && null!=cookie){
            String cartHash = cookie.getValue();
            cartHash = HangoverUtil.base64Decode(cartHash);
            List<CartDTO> cartDTOs = (List<CartDTO>) request.getSession().getAttribute(Constants.SESSION_CART);
            if(null==cartDTOs){
                cartDTOs = new ArrayList<CartDTO>();
            }
            for(String item : cartHash.split(HangoverUtil.CART_HASH_SEPARATOR)){
                CartDTO cartDTO = new CartDTO();
                String its[] = item.split("X");
                cartDTO.setItemId(Long.parseLong(its[0]));
                cartDTO.setItemDetailId(Long.parseLong(its[1]));
                cartDTO.setQuantity(Integer.parseInt(its[2]));
                if(!cartDTOs.contains(cartDTO)){
                    ItemDetailEntity itemDetail = shoppingBL.getItemDetailWithItem(cartDTO.getItemDetailId());
                    cartDTO.setName(itemDetail.getItem().getName());
                    cartDTO.setDescription(itemDetail.getItem().getDescription());
                    cartDTO.setPrice(itemDetail.getSellingPrice());
                    cartDTO.setSize(itemDetail.getItemSize());
                    cartDTO.setImageURL(itemDetail.getItem().getImageURL().get(0));
                    cartDTOs.add(cartDTO);
                }
            }
            request.getSession().setAttribute(Constants.SESSION_CART, cartDTOs);
            request.getSession().setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(cartDTOs));
        }
    }
}
