package com.hangover.java.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/15/16
 * Time: 1:53 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "wishlist_item")
@XmlRootElement(name = "wishlist_item")
public class WishListItemEntity extends BaseEntity{


    private WishListEntity wishList;
    private ItemEntity item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", nullable = false)
    public WishListEntity getWishList() {
        return wishList;
    }

    public void setWishList(WishListEntity wishList) {
        this.wishList = wishList;
    }

    @ManyToOne()
    @JoinColumn(name = "item_id", nullable = false)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WishListItemEntity that = (WishListItemEntity) o;

        if (item != null ? !item.getId().equals(that.item.getId()) : that.item != null) return false;
        if (wishList != null ? !wishList.getId().equals(that.wishList.getId()) : that.wishList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wishList != null ? wishList.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
