package com.hangover.ashqures.hangover.dto;

import java.util.Map;

/**
 * Created by ashqures on 8/15/16.
 */
public class ItemDetailDTO extends BaseDTO{

    private Integer id;
    private String size;
    private int quantity;
    private Double price;
    private Long itemId;
    private Map<String,String> detailMap;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Map<String, String> getDetailMap() {
        return detailMap;
    }

    public void setDetailMap(Map<String, String> detailMap) {
        this.detailMap = detailMap;
    }
}
