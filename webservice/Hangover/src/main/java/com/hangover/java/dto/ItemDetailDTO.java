package com.hangover.java.dto;

import com.hangover.java.model.ItemEntity;
import com.hangover.java.model.ItemSubDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemDetailDTO extends BaseDTO{

    private String itemSize;
    private String quantity;
    private String modelNumber;
    private Double sellingPrice;
    private Double costPrice;
    private Double minSellingPrice;
    private ItemDTO item;
    private List<ItemSubDetailDTO> itemSubDetails;


    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getMinSellingPrice() {
        return minSellingPrice;
    }

    public void setMinSellingPrice(Double minSellingPrice) {
        this.minSellingPrice = minSellingPrice;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public List<ItemSubDetailDTO> getItemSubDetails() {
        return itemSubDetails;
    }

    public void setItemSubDetails(List<ItemSubDetailDTO> itemSubDetails) {
        this.itemSubDetails = itemSubDetails;
    }

    public void addItemSubDetail(ItemSubDetailDTO itemSubDetailDTO){
        if(null==getItemSubDetails())
            setItemSubDetails(new ArrayList<ItemSubDetailDTO>());
        getItemSubDetails().add(itemSubDetailDTO);
    }
}
