package com.hangover.java.dto;

import com.hangover.java.model.UserEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/21/16
 * Time: 1:34 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class ShoppingDTO {

    private Long id;
    private Long userId;
    private Long itemId;
    private Long itemDetailId;
    private Long wishListId;
    private Integer quantity;
    private String action;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(Long itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public int getQuantity(Long itemId, Long itemDetailId){
        if(getItemId().equals(itemId) && getItemDetailId().equals(itemDetailId)){
            return getQuantity();
        }
        return 0;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "ShoppingDTO{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                ", itemDetailId=" + itemDetailId +
                ", wishListId=" + wishListId +
                ", quantity=" + quantity +
                ", action='" + action + '\'' +
                '}';
    }
}
