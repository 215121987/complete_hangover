package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/10/16
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "shopping_cart_item")
@XmlRootElement(name = "shipping_cart_item")
public class ShoppingCartItemEntity extends BaseEntity{
    
    private ItemEntity item;
    private ItemDetailEntity itemDetail;
    private int quantity;
    private ShoppingCartEntity shoppingCart;
    private Double price;
    private boolean taxable;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "item_detail_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public ItemDetailEntity getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetailEntity itemDetail) {
        this.itemDetail = itemDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id", nullable = false)
    public ShoppingCartEntity getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCartEntity shoppingCart) {
        this.shoppingCart = shoppingCart;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCartItemEntity that = (ShoppingCartItemEntity) o;

        if (item != null ? !item.getId().equals(that.item.getId()) : that.item != null) return false;
        if (itemDetail != null ? !itemDetail.getId().equals(that.itemDetail.getId()) : that.itemDetail != null) return false;
        if (shoppingCart != null ? !shoppingCart.getId().equals(that.shoppingCart.getId()) : that.shoppingCart != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (itemDetail != null ? itemDetail.hashCode() : 0);
        result = 31 * result + (shoppingCart != null ? shoppingCart.hashCode() : 0);
        return result;
    }
}
