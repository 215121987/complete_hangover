package com.hangover.java.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/15/16
 * Time: 1:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "wish_list")
@XmlRootElement(name = "wish_list")
public class WishListEntity extends BaseEntity{
    
    
    private String name;
    private UserEntity user;
    private List<WishListItemEntity> wishListItems;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }


    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    public List<WishListItemEntity> getWishListItems() {
        return wishListItems;
    }

    public void setWishListItems(List<WishListItemEntity> wishListItems) {
        this.wishListItems = wishListItems;
    }

    public void addWishListItem(WishListItemEntity wishListItem){
        if(null==getWishListItems())
            setWishListItems(new ArrayList<WishListItemEntity>());
        getWishListItems().add(wishListItem);
    }


    @JsonIgnore
    @Transient
    public boolean isItemExist(WishListItemEntity wishListItem){
        boolean isExit = false;
        if(null!=getWishListItems()){
            isExit = getWishListItems().contains(wishListItem);
        }
        return isExit;
    }
}
