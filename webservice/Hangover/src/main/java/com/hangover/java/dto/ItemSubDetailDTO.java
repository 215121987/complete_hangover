package com.hangover.java.dto;

import com.hangover.java.model.ItemDetailEntity;
import com.hangover.java.model.master.ItemDetailTypeEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 11:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemSubDetailDTO extends BaseDTO{

    private String name;
    private String value;
    private ItemDetailDTO itemDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ItemDetailDTO getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetailDTO itemDetail) {
        this.itemDetail = itemDetail;
    }
}
