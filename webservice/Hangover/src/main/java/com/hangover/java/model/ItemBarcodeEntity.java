package com.hangover.java.model;

import com.hangover.java.model.master.ItemDetailTypeEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/30/16
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "item_barcode")
@XmlRootElement(name = "item_barcode")
public class ItemBarcodeEntity extends BaseEntity{

    private String barcode;
    private ItemEntity item;
    private boolean sold;


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

}
