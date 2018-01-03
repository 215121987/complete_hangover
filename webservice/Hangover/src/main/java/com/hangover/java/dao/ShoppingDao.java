package com.hangover.java.dao;

import com.hangover.java.model.*;
import com.hangover.java.model.master.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/21/16
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ShoppingDao extends CommonDao{


    ShoppingCartItemEntity getCartItem(Long userId, Long itemId, Long itemDetailId);

    boolean deleteItemFromCart(Long userId, Long itemId, Long itemDetailId);

    void emptyUserCart(Long userId);

    List<ShoppingCartItemEntity> getCartItems(Long userId);

    ShoppingCartEntity getCart(Long userId);
    
    WishListEntity getWishList(Long userId);
    
    WishListItemEntity getWishListItem(Long userId, Long itemId);
    
    List<WishListItemEntity> getWishListItems(Long userId);
    
    List<ItemEntity> getItem(Map<String,String> paramMap);

    List<CategoryEntity> getChildCategory(String categoryIds);

    CategoryEntity getCategory(Long id);
    
    List<BrandEntity> getBrands(Long categoryId, Integer startIndex, Integer maxResult);

    List<ItemEntity> search(String zipCode, String queryStr, int startIndex, int maxResult);
    
}
