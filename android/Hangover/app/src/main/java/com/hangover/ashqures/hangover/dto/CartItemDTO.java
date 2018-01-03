package com.hangover.ashqures.hangover.dto;

import java.util.Map;

/**
 * Created by ashqures on 8/27/16.
 */
public class CartItemDTO extends BaseDTO{

    private Long itemId;
    private String itemName;
    private String imageUrl;
    private Long itemDetailId;
    private String itemSize;
    private Integer itemQuantity;
    private Double itemPrice;
    private Double sellingPrice;
    private boolean taxable;
    private Map<String,Double> serviceCharges;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(Long itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public Map<String, Double> getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Map<String, Double> serviceCharges) {
        this.serviceCharges = serviceCharges;
    }
}
