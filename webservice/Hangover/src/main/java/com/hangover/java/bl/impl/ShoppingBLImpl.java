package com.hangover.java.bl.impl;

import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.dao.CommonDao;
import com.hangover.java.dao.ShoppingDao;
import com.hangover.java.dto.*;
import com.hangover.java.exception.NoRecordFoundException;
import com.hangover.java.model.*;
import com.hangover.java.model.master.CategoryEntity;
import com.hangover.java.model.master.ServiceChargeEntity;
import com.hangover.java.model.type.ItemStatus;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.Constants;
import com.hangover.java.util.HangoverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/21/16
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("shoppingBL")
@Transactional
public class ShoppingBLImpl extends BaseBL implements ShoppingBL, Constants {

    private Logger logger = LoggerFactory.getLogger(ShoppingBLImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private CommonUtil commonUtil;
    
    
    public List<ShoppingCartItemEntity> updateCart(List<ShoppingDTO> shoppingDTOs,Long userId, StatusDTO statusDTO){
        List<ShoppingCartItemEntity> shoppingCartItems;
        ShoppingCartEntity shoppingCart = shoppingDao.getCart(userId);
        if(null!=shoppingDTOs && shoppingDTOs.size()>0){
            if(null == shoppingCart){
                shoppingCart = new ShoppingCartEntity();
                shoppingCart.setUser(UserEntity.getUser(userId));
            }
            for(ShoppingDTO shoppingDTO : shoppingDTOs){
                ShoppingCartItemEntity shoppingCartItem = new ShoppingCartItemEntity();
                shoppingCartItem.setItem(ItemEntity.getItem(shoppingDTO.getItemId()));
                shoppingCartItem.setItemDetail(ItemDetailEntity.getItemDetail(shoppingDTO.getItemDetailId()));
                shoppingCartItem.setQuantity(shoppingDTO.getQuantity());
                shoppingCartItem.setShoppingCart(shoppingCart);
                if(!shoppingCart.isItemExist(shoppingCartItem)){
                    shoppingCart.addShoppingCartItem(shoppingCartItem);
                }
            }
            shoppingDao.save(shoppingCart);
        }
        shoppingCartItems =  null!=shoppingCart?shoppingCart.getShoppingCartItems():null;
        logger.info("Shopping cart item " +shoppingCartItems+ "for user "+ userId + "");
        return shoppingCartItems;
    }

    @Override
    public List<ShoppingCartItemEntity> addItemToCart(ShoppingDTO shoppingDTO, StatusDTO statusDTO) {
        List<ShoppingCartItemEntity> shoppingCartItems;
        ShoppingCartItemEntity shoppingCartItem = shoppingDao.getCartItem(shoppingDTO.getUserId(),
                shoppingDTO.getItemId(), shoppingDTO.getItemDetailId());
        if(null!= shoppingCartItem){
            if(null!=shoppingDTO.getId()){
                if(!shoppingDTO.getId().equals(shoppingCartItem.getId())){
                    shoppingDao.delete(ShoppingCartItemEntity.class, shoppingDTO.getId());
                }
                shoppingCartItem.setQuantity(shoppingDTO.getQuantity());
            }else{
                shoppingCartItem.setQuantity(shoppingCartItem.getQuantity()+shoppingDTO.getQuantity());
            }
            shoppingDao.save(shoppingCartItem);
            shoppingCartItems= shoppingDao.getCartItems(shoppingDTO.getUserId());
        }else{
            if(null !=shoppingDTO.getId()){
                shoppingCartItem =  shoppingDao.get(ShoppingCartItemEntity.class, shoppingDTO.getId());
            }else{
                shoppingCartItem = new ShoppingCartItemEntity();
            }
            ItemEntity item = commonDao.get(ItemEntity.class, shoppingDTO.getItemId());
            ItemDetailEntity itemDetail = commonDao.get(ItemDetailEntity.class, shoppingDTO.getItemDetailId());
            shoppingCartItem.setItem(item);
            shoppingCartItem.setItemDetail(itemDetail);
            shoppingCartItem.setQuantity(shoppingDTO.getQuantity());
            ShoppingCartEntity shoppingCart = shoppingDao.getCart(shoppingDTO.getUserId());
            if(null==shoppingCart){
                shoppingCart = new ShoppingCartEntity();
                shoppingCart.setUser(UserEntity.getUser(shoppingDTO.getUserId()));
            }
            shoppingCartItem.setShoppingCart(shoppingCart);
            shoppingCart.addShoppingCartItem(shoppingCartItem);
            shoppingDao.save(shoppingCart);
            shoppingCartItems = shoppingCart.getShoppingCartItems();
        }
        saveSuccessMessage(statusDTO, "");
        return shoppingCartItems;
    }

    @Override
    public void deleteItemFromCart(ShoppingDTO shoppingDTO, StatusDTO statusDTO) {
        shoppingDao.deleteItemFromCart(shoppingDTO.getUserId(), shoppingDTO.getItemId(), shoppingDTO.getItemDetailId());
        saveSuccessMessage(statusDTO, "");
    }

    @Override
    public List<ShoppingCartItemEntity> getCartItem(Long userId) {
        return shoppingDao.getCartItems(userId);
    }

    @Override
    public void addItemToWishList(ShoppingDTO shoppingDTO, StatusDTO statusDTO) {
        WishListItemEntity wishListItem = shoppingDao.getWishListItem(shoppingDTO.getUserId(), shoppingDTO.getItemId());
        if(null==wishListItem){
            wishListItem = new WishListItemEntity();
            wishListItem.setItem(ItemEntity.getItem(shoppingDTO.getItemId()));
            WishListEntity wishList = shoppingDao.getWishList(shoppingDTO.getUserId());
            if(null==wishList){
                wishList = new WishListEntity();
                wishList.setName(SHOTLIST_DEFAULT_NAME);
                wishList.setUser(UserEntity.getUser(shoppingDTO.getUserId()));
            }
            wishListItem.setWishList(wishList);
            wishList.addWishListItem(wishListItem);
            shoppingDao.save(wishList);
            saveSuccessMessage(statusDTO, commonUtil.getText("success.added", statusDTO.getLocale()));
        }else{
            statusDTO.setMessage(commonUtil.getText("error.item.already.exist", statusDTO.getLocale()));
            saveErrorMessage(statusDTO, HttpStatus.CONFLICT.value());
        }
    }

    @Override
    public void updateWishList(List<Long> itemIds, Long userId, StatusDTO statusDTO) {
        if(null!= itemIds && itemIds.size()>0){
            WishListEntity wishList = shoppingDao.getWishList(userId);
            if(null==wishList){
                wishList = new WishListEntity();
                wishList.setUser(UserEntity.getUser(userId));
                wishList.setName(SHOTLIST_DEFAULT_NAME);
            }
            for(Long itemId : itemIds){
                WishListItemEntity wishListItem = new WishListItemEntity();
                wishListItem.setItem(ItemEntity.getItem(itemId));
                if(!wishList.isItemExist(wishListItem)){
                    wishList.addWishListItem(wishListItem);
                }
            }
            shoppingDao.save(wishList);
        }
    }

    @Override
    public List<WishListItemEntity> getWishListItem(Long userId) {
        return shoppingDao.getWishListItems(userId);
    }

    @Override
    public List<WishListEntity> getWishList(Long userId) {
        List<WishListEntity> wishLists = shoppingDao.gets(WishListEntity.class, "user", userId);
        return wishLists;
    }


    public List<ItemEntity> getItem(Map<String, String> paramMap){
        //paramMap = new HashMap<String, String>();
        //paramMap.put(ITEM_CATEGORY, "2,3,5");
        //paramMap.put(ITEM_SIZE, "300");
        //paramMap.put(SUPPLIER_ZIP_CODE, "560075");
        //paramMap.put(ITEM_STATUS, ItemStatus.AVAILABLE.toString());
        paramMap.put(ORDER_BY, SORT_BY_PRICE_LOW_TO_HIGH);
        Integer startIndex = 0;
        Integer maxResult = Integer.parseInt(CommonUtil.getProperty("max.record.per.page"));
        if(paramMap.containsKey(PAGE_NUMBER)){
            startIndex= maxResult*Integer.parseInt(paramMap.get(PAGE_NUMBER))-maxResult;
        }
        paramMap.put(START_INDEX, startIndex+"");
        paramMap.put(MAX_RESULT, maxResult+"");
        logger.info("Shop Filter Param:- "+paramMap.toString());
        List<ItemEntity> items = shoppingDao.getItem(paramMap);
        logger.info("Size:- "+ items.size());
        return items;
    }

    @Override
    public ItemEntity getItem(Long itemId)throws NoRecordFoundException{
        ItemEntity item = shoppingDao.get(ItemEntity.class, itemId);
        if(null==item){
            throw new NoRecordFoundException("Item not found");
        }
        return item;
    }

    @Override
    public List<ItemEntity> getItems(List<Long> itemIds){
        List<ItemEntity> items = shoppingDao.gets(ItemEntity.class, itemIds);
        return items;
    }

    @Override
    public ItemDetailEntity getItemDetailWithItem(Long itemDetailId) {
        ItemDetailEntity itemDetail = shoppingDao.get(ItemDetailEntity.class, itemDetailId);
        if(null!= itemDetail)
            HangoverUtil.initializeAssociatedEntity(itemDetail, "item");
        return itemDetail;
    }

    @Override
    public CategoryEntity getCategory() {
        //CategoryEntity category = shoppingDao.getCategory(1L);
        return shoppingDao.getCategory(1L);
    }

    @Override
    public List<CategoryEntity> getChildCategories() {
        CategoryEntity category = shoppingDao.getCategory(1L);
        return category.getChildCategories();
    }

    @Override
    public List<BrandEntity> getBrands(Long categoryId, int pageIndex) {
        int maxResult = 20;
        int startIndex = pageIndex*maxResult-maxResult;
        return shoppingDao.getBrands(categoryId, startIndex, maxResult);
    }




    public CartSummaryDTO prepareSummary(List<ShoppingCartItemEntity> cartItems) {
        CartSummaryDTO cartSummaryDTO = new CartSummaryDTO();
        if (null != cartItems && cartItems.size() > 0) {
            for(ShoppingCartItemEntity cartItem : cartItems){
                cartSummaryDTO.addCartDTO(HangoverUtil.getCartDTOFromShoppingCartItem(cartItem));
                if(cartItem.isTaxable()){
                    cartSummaryDTO.setTaxAbleAmount(cartSummaryDTO.getTaxAbleAmount()+cartItem.getItemDetail().getSellingPrice()*cartItem.getQuantity());
                }else{
                    cartSummaryDTO.setNonTaxAbleAmount(cartSummaryDTO.getNonTaxAbleAmount()+cartItem.getItemDetail().getSellingPrice()*cartItem.getQuantity());
                }
            }
            calculateServiceCharges(cartSummaryDTO);
        }
        return cartSummaryDTO;
    }

    private void calculateServiceCharges(CartSummaryDTO cartSummaryDTO){
        List<ServiceChargeEntity> serviceCharges = commonDao.gets(ServiceChargeEntity.class, "");
        Double totalPercent = 0.0;
        Double minDeliveryValue = 0.0;
        Double deliveryCharge = 0.0;
        for(ServiceChargeEntity serviceCharge : serviceCharges){
            if(serviceCharge.isPercent()){
                totalPercent = totalPercent+serviceCharge.getValue();
            }else{
                if(serviceCharge.getName().equalsIgnoreCase(SERVICE_CHARGE_NAME_MIN_DELIVERY))
                    minDeliveryValue = serviceCharge.getValue();
                if(serviceCharge.getName().equalsIgnoreCase(SERVICE_CHARGE_NAME_DELIVERY))
                    deliveryCharge = serviceCharge.getValue();
            }
        }
        cartSummaryDTO.setTax(cartSummaryDTO.getTaxAbleAmount()*totalPercent/100);
        if(cartSummaryDTO.getGrossAmount()<minDeliveryValue){
            cartSummaryDTO.setDeliveryCharge(deliveryCharge);
        }
    }

    public Map<String, Double> getServiceDelivery(Double taxableAmount) {
        Map<String, Double> detailMap = new HashMap<String, Double>();
        List<ServiceChargeEntity> serviceCharges = commonDao.gets(ServiceChargeEntity.class, "");
        Double totalPercent = 0.0;
        for (ServiceChargeEntity serviceCharge : serviceCharges) {
            if (serviceCharge.isPercent()) {
                totalPercent = totalPercent + serviceCharge.getValue();
                detailMap.put(serviceCharge.getName(), serviceCharge.getValue() * taxableAmount / 100);
            } else {
                detailMap.put(serviceCharge.getName(), serviceCharge.getValue());
            }
        }
        detailMap.put(SERVICE_CHARGE_NAME_TOTAL_TAX, taxableAmount * totalPercent / 100);
        return detailMap;
    }


    public CartSummaryDTO getCartSummary(Long userId) {
        List<ShoppingCartEntity> carts = shoppingDao.gets(ShoppingCartEntity.class, "user", userId);
        CartSummaryDTO cartSummaryDTO = new CartSummaryDTO();
        if (null != carts && carts.size() > 0) {
            cartSummaryDTO = prepareSummary(carts.get(0).getShoppingCartItems());
        }
        return cartSummaryDTO;
    }

    public CartSummaryDTO getCartSummary(List<CartDTO> cartDTOs){
        CartSummaryDTO cartSummaryDTO = new CartSummaryDTO();
        for(CartDTO cartDTO : cartDTOs){
            cartSummaryDTO.addCartDTO(cartDTO);
            if(cartDTO.isTaxable()){
                cartSummaryDTO.setTaxAbleAmount(cartSummaryDTO.getTaxAbleAmount()+cartDTO.getPrice()*cartDTO.getQuantity());
            }else{
                cartSummaryDTO.setNonTaxAbleAmount(cartSummaryDTO.getNonTaxAbleAmount()+cartDTO.getPrice()*cartDTO.getQuantity());
            }
        }
        calculateServiceCharges(cartSummaryDTO);
        return cartSummaryDTO;
    }

    @Override
    public List<ItemEntity> search(String zipCode, String query) {
        List<ItemEntity> itemEntities = shoppingDao.search(zipCode,query, 0, 20);
        return itemEntities;
    }

    @Override
    public PlaceOrderDTO placeOrder(PlaceOrderDTO placeOrder, StatusDTO status) {
        List<ShoppingCartItemEntity> cartItems = shoppingDao.getCartItems(placeOrder.getUserId());
        if(null!= cartItems  && cartItems.size()>0){
            CartSummaryDTO cartSummary = prepareSummary(cartItems);
            if(!placeOrder.getAmount().equals(cartSummary.getNetAmount())){
                status.setMessage(commonUtil.getText("error.mismatch.amount", status.getLocale()));
                saveErrorMessage(status, HttpStatus.CONFLICT.ordinal());
            }
            OrderEntity order = new OrderEntity();
            order.setOrderNumber(HangoverUtil.generateOrderNumber(placeOrder.getUserId(), cartItems.get(0).getShoppingCart().getId()));
            order.setCustomer(UserEntity.getUser(placeOrder.getUserId()));
            order.setAddress(AddressEntity.getAddress(placeOrder.getAddressId()));
            order.setOrderFrom(placeOrder.getOrderFrom());
            order.setTotalAmount(cartSummary.getNetAmount());
            for(ShoppingCartItemEntity cartItem :  cartItems){
                OrderItemEntity orderItem = new OrderItemEntity();
                orderItem.setItem(cartItem.getItem());
                orderItem.setItemSize(cartItem.getItemDetail().getItemSize());
                orderItem.setPrice(cartItem.getPrice());
                orderItem.setOrder(order);
                order.addOrderItem(orderItem);
            }
            shoppingDao.save(order);
            placeOrder.setOrderNumber(order.getOrderNumber());
            saveSuccessMessage(status,"");
        }
        return placeOrder;
    }

}
