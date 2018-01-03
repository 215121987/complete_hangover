package com.hangover.java.bl;

import com.hangover.java.dto.*;
import com.hangover.java.model.*;
import com.hangover.java.model.master.CategoryEntity;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/21/16
 * Time: 12:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ShoppingBL {


    List<ShoppingCartItemEntity> updateCart(List<ShoppingDTO> shoppingDTOs,Long userId, StatusDTO statusDTO);

    List<ShoppingCartItemEntity> addItemToCart(ShoppingDTO shoppingDTO, StatusDTO statusDTO);
    
    void deleteItemFromCart(ShoppingDTO shoppingDTO, StatusDTO statusDTO);

    List<ShoppingCartItemEntity> getCartItem(Long userId);

    void addItemToWishList(ShoppingDTO shoppingDTO, StatusDTO statusDTO);

    void updateWishList(List<Long> itemIds,Long userId, StatusDTO statusDTO);
    
    List<WishListItemEntity> getWishListItem(Long userId);
    
    List<WishListEntity> getWishList(Long userId);

    List<ItemEntity> getItem(Map<String, String> paramMap);
    
    ItemEntity getItem(Long itemId);

    List<ItemEntity> getItems(List<Long> itemIds);
    
    ItemDetailEntity getItemDetailWithItem(Long itemDetailIds);

    CategoryEntity getCategory();

    List<CategoryEntity> getChildCategories();

    List<BrandEntity> getBrands(Long categoryId, int pageIndex);

    CartSummaryDTO prepareSummary(List<ShoppingCartItemEntity> cartItems);

    CartSummaryDTO getCartSummary(Long userId);

    CartSummaryDTO getCartSummary(List<CartDTO> cartDTOs);

    List<ItemEntity> search(String zipCode, String query);

    PlaceOrderDTO placeOrder(PlaceOrderDTO placeOrderDTO, StatusDTO statusDTO);
    
    
}
