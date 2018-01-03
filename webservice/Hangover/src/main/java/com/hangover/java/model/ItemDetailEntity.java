package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hangover.java.model.master.ItemDetailTypeEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/27/16
 * Time: 1:24 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "item_detail")
@XmlRootElement(name = "item_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDetailEntity extends BaseEntity{

    private String itemSize;
    private String quantity;
    private String modelNumber;
    private Double sellingPrice;
    private Double costPrice;
    private Double minSellingPrice;
    private ItemEntity item;
    private List<ItemSubDetailEntity> itemSubDetails;

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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "itemDetail")
    public List<ItemSubDetailEntity> getItemSubDetails() {
        return itemSubDetails;
    }

    public void setItemSubDetails(List<ItemSubDetailEntity> itemSubDetails) {
        this.itemSubDetails = itemSubDetails;
    }

    @Transient
    @JsonIgnore
    public static ItemDetailEntity getItemDetail(Long itemDetailId){
        ItemDetailEntity itemDetail = new ItemDetailEntity();
        itemDetail.setId(itemDetailId);
        return itemDetail;
    }
}
