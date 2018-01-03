package com.hangover.java.service.wso;

import com.hangover.java.model.ShoppingCartItemEntity;
import com.hangover.java.model.master.ServiceChargeEntity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 9:33 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "cart")
public class CartWSO {

    @XmlElementWrapper(name="items")
    private List<ShoppingCartItemEntity> cartItems;

    @XmlElementWrapper(name="service_charges")
    private List<ServiceChargeEntity> serviceCharges;
    
    private int count;


    public List<ShoppingCartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public List<ServiceChargeEntity> getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(List<ServiceChargeEntity> serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    @XmlElement(name = "item_count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
