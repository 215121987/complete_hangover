package com.hangover.java.controller;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.bl.UserBL;
import com.hangover.java.dto.*;
import com.hangover.java.exception.NoRecordFoundException;
import com.hangover.java.model.*;
import com.hangover.java.model.type.OfferFor;
import com.hangover.java.model.type.OrderFrom;
import com.hangover.java.util.*;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 22/06/15
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/comm")
public class CommonController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonBL commonBL;

    @Autowired
    private ShoppingBL shoppingBL;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private UserBL userBL;


    @RequestMapping("/home")
    public String register(HttpServletRequest request) {
        HomeDTO homeDTO = commonBL.getHome(OfferFor.WEB);
        request.setAttribute("home", homeDTO);
        return "home";
    }


    @RequestMapping(value = {"/shop"})
    public String shop(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException, ClassNotFoundException {
        //logger.info("Path Variable Category "+category + " and brand "+ brand);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put(ITEM_CATEGORY, "1");
        /*CustomClassMapperUtil customClassMapperUtil = CustomClassMapperUtil.getInstance();
        Map<String, Class<? extends BaseEntity>> classNameMap = customClassMapperUtil.getClassNameMap();
        for(String key : classNameMap.keySet()){
            System.out.println("Key:- "+key +"  Value:- "+classNameMap.get(key));
        }*/
        /*request.getParameterMap();
        List<ItemEntity> itemList = shoppingBL.getItem(paramMap);
        if (!isAjaxRequest(request)) {
            request.setAttribute("items", itemList);
            request.setAttribute("categories", shoppingBL.getCategories());
            request.setAttribute("brands", shoppingBL.getBrands(1L, 1));
            return "shop/shop";
        } else {
            JSONObject object = new JSONObject();
            object.put("items", getItemsAsJSONArray(itemList));
            responseAsJSON(response, object);
            return null;
        }*/
        return shopDefault(request, response, paramMap);
    }

    /*@RequestMapping(value = {"/{category_brand}/shop"})
    public String shopByCategory(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("category_brand") String catBrand) throws JSONException, IOException {
        Map<String, String> paramMap = new HashMap<String, String>();
        if (catBrand.startsWith(ITEM_CATEGORY)) {
            String category = catBrand.replace(ITEM_CATEGORY + "-", "");
            paramMap.put(ITEM_CATEGORY, category.replace("-", ","));
        } else {
            String brand = catBrand.replace(BRAND_NAME + "-", "");
            paramMap.put(ITEM_CATEGORY, brand.replace("-", ","));
        }
        return shopDefault(request, response, paramMap);
    }*/

    @RequestMapping(value = {"/{category}/shop"})
    public String shopByCategoryAndBrand(HttpServletRequest request, HttpServletResponse response,
                                         @PathVariable("category") String category) throws JSONException, IOException {
        Map<String, String> paramMap = new HashMap<String, String>();
        category = category.replace(ITEM_CATEGORY + "-", "");
        paramMap.put(ITEM_CATEGORY, category.replace("-", ","));
        // brand = brand.replace(BRAND_NAME + "-", "");
        // paramMap.put(BRAND_NAME, brand.replace("-", ","));
        return shopDefault(request, response, paramMap);
    }


    private String shopDefault(HttpServletRequest request, HttpServletResponse response, Map<String, String> paramMap) throws JSONException, IOException {
        if (StringUtil.isNotNullOrEmpty(request.getParameter(PAGE_NUMBER)))
            paramMap.put(PAGE_NUMBER, request.getParameter(PAGE_NUMBER));
        String brandName = request.getParameter(BRAND_NAME);
        if (StringUtil.isNotNullOrEmpty(brandName)) {
            paramMap.put(BRAND_NAME, "'" + brandName.replace("--", "','") + "'");
        }
        String itemSize = request.getParameter(ITEM_SIZE);
        if (StringUtil.isNotNullOrEmpty(itemSize)) {
            paramMap.put(ITEM_SIZE, itemSize.replace("--", ","));
        }

        String discount = request.getParameter(ITEM_DISCOUNT);
        if (StringUtil.isNotNullOrEmpty(discount)) {
            paramMap.put(ITEM_DISCOUNT, discount.replace("--", ","));
        }

        String orderBy = request.getParameter(ORDER_BY);
        if (StringUtil.isNotNullOrEmpty(orderBy)) {
            paramMap.put(ORDER_BY, orderBy);
        }
        String minPrice = request.getParameter(ITEM_MIN_PRICE);
        if (StringUtil.isNotNullOrEmpty(minPrice)) {
            paramMap.put(ITEM_MIN_PRICE, minPrice);
        }
        String maxPrice = request.getParameter(ITEM_MAX_PRICE);
        if (StringUtil.isNotNullOrEmpty(maxPrice)) {
            paramMap.put(ITEM_MAX_PRICE, maxPrice);
        }
        Cookie cookie = HangoverUtil.getCookie(request.getCookies(), SUPPLIER_ZIP_CODE);
        if (null != cookie && StringUtil.isNotNullOrEmpty(cookie.getValue())) {
            paramMap.put(SUPPLIER_ZIP_CODE, cookie.getValue());
        }
        logger.info("Shop Filter Param:- " + paramMap.toString());
        List<ItemEntity> itemList = shoppingBL.getItem(paramMap);
        String RETURN_PATH = "shop/shop";
        if (isAjaxRequest(request)) {
            String view = request.getParameter("view");
            if (StringUtil.isNotNullOrEmpty(view) && "json".equalsIgnoreCase(view)) {
                JSONObject object = new JSONObject();
                object.put("items", getItemsAsJSONArray(itemList));
                responseAsJSON(response, object);
                RETURN_PATH = null;
            } else {
                request.setAttribute("items", itemList);
                return "shop/shopItem";
            }
        } else {
            request.setAttribute("items", itemList);
            request.setAttribute("categories", shoppingBL.getChildCategories());
            Long categoryId = 1L;
            if (paramMap.containsKey(ITEM_CATEGORY)) {
                categoryId = Long.parseLong(paramMap.get(ITEM_CATEGORY));
            }
            request.setAttribute("brands", shoppingBL.getBrands(categoryId, 1));
        }
        /*if (!isAjaxRequest(request)) {
            request.setAttribute("items", itemList);
            request.setAttribute("categories", shoppingBL.getChildCategories());
            Long categoryId = 1L;
            if(paramMap.containsKey(ITEM_CATEGORY)){
                categoryId = Long.parseLong(paramMap.get(ITEM_CATEGORY));
            }
            request.setAttribute("brands", shoppingBL.getBrands(categoryId, 1));
        } else {
            String view = request.getParameter("view");
            if(StringUtil.isNotNullOrEmpty(view) && "json".equalsIgnoreCase(view)){
                JSONObject object = new JSONObject();
                object.put("items", getItemsAsJSONArray(itemList));
                responseAsJSON(response, object);
                return null;
            }else {
                request.setAttribute("items", itemList);
                return "shop/shopItem";
            }
        }*/
        return RETURN_PATH;
    }


    private JSONArray getItemsAsJSONArray(List<ItemEntity> items) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ItemEntity item : items) {
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("id", item.getId());
            itemJSON.put("name", item.getName());
            itemJSON.put("brand", item.getBrand().getName());
            itemJSON.put("status", item.getStatus());
            JSONObject itemDetailJSON = new JSONObject();
            ItemDetailEntity itemDetail = item.getItemDetailList().get(0);
            itemDetailJSON.put("size", itemDetail.getItemSize());
            itemDetailJSON.put("price", itemDetail.getSellingPrice());
            itemDetailJSON.put("quantity", itemDetail.getQuantity());
            itemDetailJSON.put("minPrice", itemDetail.getMinSellingPrice());
            itemJSON.put("detail", itemDetailJSON);
            jsonArray.put(itemJSON);
        }
        return jsonArray;
    }


    @RequestMapping("/shop/{itemId}")
    public String item(HttpServletRequest request, @PathVariable("itemId") Long itemId) throws NoRecordFoundException {
        ItemEntity item = shoppingBL.getItem(itemId);
        request.setAttribute("item", item);
        Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_CUSTOMER_LOCATION);
        if (null != cookie && StringUtils.isNotEmpty(cookie.getValue()) && null!= item) {
            request.setAttribute("items", shoppingBL.search(cookie.getValue(), item.getCategory().getName()));
        }
        return "/shop/itemDetail";
    }


    private JSONObject getCartItemAsJson(List<CartDTO> cartDTOs) throws JSONException {
        JSONObject mainObject = new JSONObject();
        JSONArray array = new JSONArray();
        if (null != cartDTOs && cartDTOs.size() > 0) {
            for (CartDTO cartDTO : cartDTOs) {
                JSONObject cartItem = new JSONObject();
                cartItem.put("id", cartDTO.getId());
                cartItem.put("quantity", cartDTO.getQuantity());
                JSONObject item = new JSONObject();
                item.put("id", cartDTO.getItemId());
                item.put("price", cartDTO.getPrice());
                item.put("name", cartDTO.getName());
                item.put("size", cartDTO.getSize());
                item.put("imageURL", cartDTO.getImageURL());
                item.put("description", cartDTO.getDescription());
                cartItem.put("item", item);
                array.put(cartItem);
            }
            mainObject.put("cartItem", array);
        } else {
            mainObject.put("message", "No Items in the cart");
        }
        mainObject.put("cart_hash", HangoverUtil.getAlphaNumeric(30));
        return mainObject;
    }


    @RequestMapping(value = "/cart")
    public String cart(HttpServletRequest request, @RequestParam(value = "action", required = false) String action) {
        String RETURN_PATH = "/shop/cart";
        List<CartDTO> cartDTOs;
        if (isUserLoggedIn(request)) {
            List<ShoppingCartItemEntity> shoppingCartItems = shoppingBL.getCartItem(getCurrentUsers().getId());
            cartDTOs = HangoverUtil.getCartDTOFromShoppingCartItems(shoppingCartItems);
        } else {
            cartDTOs = HangoverUtil.getCartDTO(HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH), shoppingBL);
        }
        request.setAttribute(Constants.SESSION_CART, cartDTOs);
        request.setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(cartDTOs));
        if (null == cartDTOs || cartDTOs.size() <= 0) {
            RETURN_PATH = "/shop/emptyCart";
        } else if (isEditCartItemAction(action)) {
            Long itemId = Long.parseLong(request.getParameter("itemId"));
            Long itemDetailId = Long.parseLong(request.getParameter("itemDetailId"));
            request.setAttribute("item", commonBL.getEntity(ItemEntity.class, itemId));
            CartDTO cartItem = new CartDTO();
            cartItem.setItemId(itemId);
            cartItem.setItemDetailId(itemDetailId);
            request.setAttribute("cartItem", cartDTOs.get(cartDTOs.indexOf(cartItem)));
            RETURN_PATH = "/shop/cartForm";
        }
        return RETURN_PATH;
    }


    /*private void processRequestCart(HttpServletRequest request){
Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH);
if(null!=cookie && StringUtils.isNotEmpty(cookie.getValue())){
    String cartHash = cookie.getValue();
    cartHash = HangoverUtil.base64Decode(cartHash);
    *//*List<CartDTO> cartDTOs = (List<CartDTO>) request.getSession().getAttribute(SESSION_CART);
            if(null==cartDTOs){
                cartDTOs = new ArrayList<CartDTO>();
            }*//*
            List<CartDTO> cartDTOs =  new ArrayList<CartDTO>();
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
            if(isUserLoggedIn(request))
                request.setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(getCurrentUsers().getId()));
            else
                request.setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(cartDTOs));
        }
    }*/

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String addToCart(HttpServletRequest request, HttpServletResponse response,
                            ShoppingDTO shoppingDTO, @RequestParam(value = "action", required = false) String action) {
        logger.info(shoppingDTO.toString());
        List<CartDTO> cartDTOs;
        StatusDTO statusDTO = new StatusDTO();
        if (isUserLoggedIn(request)) {
            List<ShoppingCartItemEntity> shoppingCartItems;
            if (isAddToCartAction(action) || isEditCartItemAction(action)) {
                shoppingDTO.setUserId(getCurrentUsers().getId());
                shoppingCartItems = shoppingBL.addItemToCart(shoppingDTO, statusDTO);
            } else {
                shoppingCartItems = shoppingBL.getCartItem(getCurrentUsers().getId());
            }
            cartDTOs = HangoverUtil.getCartDTOFromShoppingCartItems(shoppingCartItems);
        } else {
            cartDTOs = HangoverUtil.getCartDTO(HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH), shoppingBL);
            if (null == cartDTOs)
                cartDTOs = new ArrayList<CartDTO>();
            if (isAddToCartAction(action) || isEditCartItemAction(action)) {
                CartDTO cartDTO = new CartDTO();
                cartDTO.setItemId(shoppingDTO.getItemId());
                cartDTO.setItemDetailId(shoppingDTO.getItemDetailId());
                cartDTO.setQuantity(shoppingDTO.getQuantity());
                if (cartDTOs.contains(cartDTO)) {
                    cartDTO = cartDTOs.get(cartDTOs.indexOf(cartDTO));
                    if (isAddToCartAction(action))
                        cartDTO.addQuantity(shoppingDTO.getQuantity());
                    else
                        cartDTO.setQuantity(shoppingDTO.getQuantity());
                } else {
                    ItemDetailEntity itemDetail = shoppingBL.getItemDetailWithItem(shoppingDTO.getItemDetailId());
                    cartDTO.setId(cartDTO.getItemId() * cartDTO.getItemDetailId() * -1);
                    cartDTO.setName(itemDetail.getItem().getName());
                    cartDTO.setDescription(itemDetail.getItem().getDescription());
                    cartDTO.setSize(itemDetail.getItemSize());
                    cartDTO.setPrice(itemDetail.getSellingPrice());
                    cartDTO.setImageURL(itemDetail.getItem().getImageURL().get(0));
                }
                cartDTOs.remove(cartDTO);
                for (CartDTO cDTO : cartDTOs) {
                    if (cDTO.getId().equals(shoppingDTO.getId())) {
                        cartDTOs.remove(cDTO);
                        break;
                    }
                }
                cartDTOs.add(cartDTO);
            }
        }
        try {
            //request.getSession().setAttribute(SESSION_CART, cartDTOs);
            //request.getSession().setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(cartDTOs));
            String cartHash = HangoverUtil.getCartHash(cartDTOs);
            response.addCookie(HangoverUtil.getCartHashCookie(request.getContextPath(), cartHash));
            if (isAjaxRequest(request)) {
                JSONObject cartItem = getCartItemAsJson(cartDTOs);
                cartItem.put("cart_hash", cartHash);
                responseAsJSON(response, cartItem);
                return null;
            }
        } catch (JSONException e) {
            logger.error("Unable to create json for cart " + e.getMessage());
        } catch (IOException e) {
            logger.error("Unable to write json in response" + e.getMessage());
        }
        return sendRedirect("/comm/cart.html");
    }


    private boolean isAddToCartAction(String action) {
        return StringUtil.isNotNullOrEmpty(action) && action.equalsIgnoreCase(ACTION_ADD_TO_CART);
    }

    private boolean isEditCartItemAction(String action) {
        return StringUtil.isNotNullOrEmpty(action) && action.equalsIgnoreCase(ACTION_EDIT_CART_ITEM);
    }

    @RequestMapping("/cart/delete/{itemId}/{itemDetailId}")
    public String deleteFromCart(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("itemId") Long itemId,
                                 @PathVariable("itemDetailId") Long itemDetailId) {
        StatusDTO statusDTO = new StatusDTO();
        List<CartDTO> cartDTOs = HangoverUtil.getCartDTO(HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH), shoppingBL);
        CartDTO cartDTO = new CartDTO();
        cartDTO.setItemId(itemId);
        cartDTO.setItemDetailId(itemDetailId);
        /*cartDTO.setUserId(getCurrentUsers().getId());*/
        if (cartDTOs.contains(cartDTO)) {
            cartDTOs.remove(cartDTO);
            //request.getSession().setAttribute(SESSION_CART, cartDTOs);
            //request.setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(cartDTOs));
            String cartHash = HangoverUtil.getCartHash(cartDTOs);
            response.addCookie(HangoverUtil.getCartHashCookie(request.getContextPath(), cartHash));
            if (isUserLoggedIn(request)) {
                ShoppingDTO shoppingDTO = new ShoppingDTO();
                shoppingDTO.setUserId(getCurrentUsers().getId());
                shoppingDTO.setItemId(itemId);
                shoppingDTO.setItemDetailId(itemDetailId);
                shoppingBL.deleteItemFromCart(shoppingDTO, statusDTO);
            }
        }
        return sendRedirect("/comm/cart.html");
    }

    @RequestMapping("/shortList")
    public String shortList(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        List<WishListEntity> wishLists;
        if (isUserLoggedIn(request)) {
            wishLists = shoppingBL.getWishList(getCurrentUsers().getId());
            if(null == wishLists || wishLists.size()<=0 ){
                wishLists = new ArrayList<WishListEntity>();
                WishListEntity wishList = new WishListEntity();
                wishList.setName(SHOTLIST_DEFAULT_NAME);
                wishLists.add(wishList);
            }
        } else {
            wishLists = new ArrayList<WishListEntity>();
            WishListEntity wishList = new WishListEntity();
            wishList.setName(SHOTLIST_DEFAULT_NAME);
            List<Long> itemIds = (List<Long>) request.getSession().getAttribute(SESSION_SHORTLIST);
            if (null != itemIds && itemIds.size() > 0) {
                List<ItemEntity> items = shoppingBL.getItems(itemIds);
                for (ItemEntity item : items) {
                    WishListItemEntity wishListItem = new WishListItemEntity();
                    wishListItem.setItem(item);
                    wishListItem.setWishList(wishList);
                    wishList.addWishListItem(wishListItem);
                }
            }
            wishLists.add(wishList);
        }
        request.setAttribute("wishLists", wishLists);
        return "/shop/shortListView";
    }

    @RequestMapping("/shortList/add")
    public String addToShortList(HttpServletRequest request, HttpServletResponse response,
                                 Long itemId) throws JSONException {
        StatusDTO statusDTO = new StatusDTO();
        if (isUserLoggedIn(request)) {
            ShoppingDTO shoppingDTO = new ShoppingDTO();
            shoppingDTO.setUserId(getCurrentUsers().getId());
            shoppingDTO.setItemId(itemId);
            shoppingBL.addItemToWishList(shoppingDTO, statusDTO);
        } else {
            List<Long> itemIds = (List<Long>) request.getSession().getAttribute(SESSION_SHORTLIST);
            if (null == itemIds) {
                itemIds = new ArrayList<Long>();
            }
            if (!itemIds.contains(itemId)) {
                itemIds.add(itemId);
                request.getSession().setAttribute(SESSION_SHORTLIST, itemIds);
                statusDTO.setCode(HttpStatus.OK.value());
                statusDTO.setMessage(commonUtil.getText("success.added", statusDTO.getLocale()));
            } else {
                statusDTO.setCode(HttpStatus.CONFLICT.value());
                statusDTO.setMessage(commonUtil.getText("error.item.already.exist", statusDTO.getLocale()));
            }
        }
        try {
            responseAsJSON(response, getStatusAsJSON(statusDTO));
        } catch (JSONException e) {
            logger.error("Unable to create json for cart " + e.getMessage());
        } catch (IOException e) {
            logger.error("Unable to write json in response" + e.getMessage());
        }
        return null;
    }

    @RequestMapping("/shortList/delete/{id}")
    public String deleteFromShortList(HttpServletRequest request, @PathVariable("id") Long id) {
        WishListItemEntity wishListItem = new WishListItemEntity();
        wishListItem.setId(id);
        commonBL.deleteEntity(wishListItem);
        return "home";
    }


    /*Checkout*/
    @RequestMapping(value = "/checkout")
    public String checkout(HttpServletRequest request) {
        String RETURN_PATH = "/customer/checkout";
        List<CartDTO> cartDTOs = new ArrayList<CartDTO>();
        request.setAttribute(ACTION_PLACE_ORDER, true);
        if (isUserLoggedIn(request)) {
            if(getCurrentUsers().isAgeVerified()){
                cartDTOs = HangoverUtil.getCartDTOFromShoppingCartItems(shoppingBL.getCartItem(getCurrentUsers().getId()));
                Map<String, Object> filterParam = new HashMap<String, Object>();
                Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_CUSTOMER_LOCATION);
                if (null != cookie) {
                    filterParam.put("zipCode", cookie.getValue());
                }
                List<AddressEntity> addressList = userBL.getUserAddress(getCurrentUsers().getId(), filterParam);
                request.setAttribute("addressList", addressList);
            }else{
                request.setAttribute(SER_AGE_VERIFIED, false);
                RETURN_PATH = "/shop/emptyCart";
            }
        } else {
            cartDTOs = HangoverUtil.getCartDTO(HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH), shoppingBL);
        }
        if (null == cartDTOs || cartDTOs.size() <= 0) {
            RETURN_PATH = "/shop/emptyCart";
        }
        request.setAttribute(Constants.SESSION_CART, cartDTOs);
        request.setAttribute(SESSION_CART_SUMMARY, shoppingBL.getCartSummary(cartDTOs));
        return RETURN_PATH;
    }

    @RequestMapping(value = "/delivery/address")
    public String updateDeliveryAddress(HttpServletRequest request, HttpServletResponse response,
                                        @QueryParam("addressId") Long addressId) throws JSONException, IOException {
        StatusDTO statusDTO = new StatusDTO();
        if (null != addressId) {
            request.getSession().setAttribute(SESSION_DELIVERY_ADDRESS_ID, addressId);
            statusDTO.setCode(HttpStatus.OK.value());
        } else {
            statusDTO.setCode(HttpStatus.BAD_REQUEST.value());
        }
        responseAsJSON(response, getStatusAsJSON(statusDTO));
        return null;
    }

    @RequestMapping(value = "/address/save", method = RequestMethod.POST)
    public String saveAddress(HttpServletRequest request, HttpServletResponse response,
                              AddressEntity address) throws JSONException, IOException {
        StatusDTO statusDTO = new StatusDTO();
        if (isUserLoggedIn(request)) {
            address.setUser(getCurrentUsers());
            userBL.save(address, statusDTO);
            request.getSession().setAttribute(SESSION_DELIVERY_ADDRESS_ID, address.getId());
        } else {
            statusDTO.setCode(HttpStatus.FORBIDDEN.value());
            statusDTO.setMessage("Please login");
        }
        responseAsJSON(response, getStatusAsJSON(statusDTO));
        return null;
    }


    @RequestMapping(value = "/search")
    public String search(HttpServletRequest request, HttpServletResponse response,
                         @QueryParam("query") String query,
                         @QueryParam("view") String view) throws JSONException, IOException {
        Cookie cookie = HangoverUtil.getCookie(request.getCookies(), COOKIES_CUSTOMER_LOCATION);
        String RETURN_PATH = "shop/shop";
        if (null != cookie && StringUtils.isNotEmpty(cookie.getValue())) {
            List<ItemEntity> itemList = shoppingBL.search(cookie.getValue(), query);
            /*JSONObject object = new JSONObject();
            object.put("items", getItemsAsJSONArray(itemList));
            responseAsJSON(response, object);*/
            request.setAttribute("items", itemList);
        }
        if (isAjaxRequest(request))
            RETURN_PATH = view;
        else {

            request.setAttribute("categories", shoppingBL.getChildCategories());
            Long categoryId = 1L;
            request.setAttribute("brands", shoppingBL.getBrands(categoryId, 1));
        }
        return RETURN_PATH;
    }


    @RequestMapping(value = "/order/place")
    public String placeOrder(HttpServletRequest request, HttpServletResponse response,
                             @QueryParam("amount") Double amount) throws JSONException, IOException {
        StatusDTO statusDTO = new StatusDTO();
        PlaceOrderDTO placeOrderDTO = new PlaceOrderDTO();
        placeOrderDTO.setOrderFrom(OrderFrom.WEB);
        placeOrderDTO.setUserId(getCurrentUsers().getId());
        placeOrderDTO.setCartHash(HangoverUtil.getCookie(request.getCookies(), COOKIES_CART_HASH).getValue());
        placeOrderDTO.setAddressId((Long) request.getSession().getAttribute(SESSION_DELIVERY_ADDRESS_ID));
        placeOrderDTO.setAmount(amount);
        placeOrderDTO = shoppingBL.placeOrder(placeOrderDTO, statusDTO);
        responseAsJSON(response, getPlaceOrderJSON(placeOrderDTO));
        return null;
    }

    private JSONObject getPlaceOrderJSON(PlaceOrderDTO placeOrderDTO) throws JSONException {
        JSONObject mainObject = new JSONObject();
        mainObject.put("orderNumber", placeOrderDTO.getOrderNumber());
        mainObject.put("amount", placeOrderDTO.getAmount());
        mainObject.put("cart_hash", placeOrderDTO.getCartHash());
        return mainObject;
    }
}
