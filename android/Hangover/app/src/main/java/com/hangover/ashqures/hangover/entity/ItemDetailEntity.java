package com.hangover.ashqures.hangover.entity;

/**
 * Created by ashqures on 8/15/16.
 */
public class ItemDetailEntity extends BaseEntity{

    private String size;
    private String quantity;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
