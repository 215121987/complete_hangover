package com.hangover.java.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/10/16
 * Time: 11:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "shopping_cart")
@XmlRootElement(name = "shopping_cart")
public class ShoppingCartEntity extends BaseEntity{
    
    private UserEntity user;
    List<ShoppingCartItemEntity> shoppingCartItems;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shoppingCart", orphanRemoval = true)
    public List<ShoppingCartItemEntity> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItemEntity> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public void addShoppingCartItem(ShoppingCartItemEntity shoppingCartItem){
        if(null==getShoppingCartItems())
            setShoppingCartItems(new ArrayList<ShoppingCartItemEntity>());
        getShoppingCartItems().add(shoppingCartItem);
    }

    @JsonIgnore
    @Transient
    public boolean isItemExist(ShoppingCartItemEntity shoppingCartItem){
        boolean isExit = false;
        if(null!=getShoppingCartItems()){
            isExit = getShoppingCartItems().contains(shoppingCartItem);
        }
        return isExit;
    }
}
