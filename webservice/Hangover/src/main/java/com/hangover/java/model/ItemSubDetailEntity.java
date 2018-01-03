package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hangover.java.model.master.ItemDetailTypeEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/11/16
 * Time: 12:16 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "item_sub_detail")
@XmlRootElement(name = "item_sub_detail")
public class ItemSubDetailEntity extends BaseEntity{
    
    private ItemDetailTypeEntity itemDetailType;
    private String name;
    private String value;
    private ItemDetailEntity itemDetail;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "item_detail_type", nullable = false)
    public ItemDetailTypeEntity getItemDetailType() {
        return itemDetailType;
    }

    public void setItemDetailType(ItemDetailTypeEntity itemDetailType) {
        this.itemDetailType = itemDetailType;
    }

    public String getName() {
        if(null!=getItemDetailType())
            this.name=getItemDetailType().getName();
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_detail", nullable = false)
    public ItemDetailEntity getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetailEntity itemDetail) {
        this.itemDetail = itemDetail;
    }
}
