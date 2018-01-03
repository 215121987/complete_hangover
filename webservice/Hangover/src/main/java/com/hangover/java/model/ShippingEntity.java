package com.hangover.java.model;

import com.hangover.java.model.type.ShippingState;
import com.hangover.java.model.type.ShippingType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/10/16
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "shipping")
@XmlRootElement(name = "shipping")
public class ShippingEntity extends BaseEntity{

    private String shippingNumber;
    private Set<OrderItemEntity> orderItem;
    private OrderEntity order;
    private AddressEntity address;
    private Double charge;
    private UserEntity pickedBy;
    private ShippingState state;

    @Column(name = "shipping_number", nullable = false, unique = true, updatable = false)
    public String getShippingNumber() {
        return shippingNumber;
    }

    public void setShippingNumber(String shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    @OneToMany(mappedBy = "shipping", fetch = FetchType.EAGER)
    public Set<OrderItemEntity> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Set<OrderItemEntity> orderItem) {
        this.orderItem = orderItem;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Column(name = "shipping_charge")
    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "picked_by", nullable = false)
    public UserEntity getPickedBy() {
        return pickedBy;
    }

    public void setPickedBy(UserEntity pickedBy) {
        this.pickedBy = pickedBy;
    }

    @Enumerated(EnumType.STRING)
    public ShippingState getState() {
        return state;
    }

    public void setState(ShippingState state) {
        this.state = state;
    }
}
