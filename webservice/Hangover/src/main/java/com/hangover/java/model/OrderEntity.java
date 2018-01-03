package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hangover.java.model.type.OrderFrom;
import com.hangover.java.model.type.OrderState;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/10/16
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "customer_order")
@XmlRootElement(name = "order")
public class OrderEntity extends BaseEntity{

    private String orderNumber;
    private UserEntity customer;
    private Double totalAmount;
    private OrderFrom orderFrom;
    private AddressEntity address;
    private Set<OrderItemEntity> orderItem;
    private SupplierStoreEntity store;
    private OrderState state = OrderState.ORDER_CREATED;
    private UserEntity staff;
    private Date orderPlacedAt;
    private Date orderUpdatedAt;

    @Column(name = "order_number", nullable = false, unique = true, updatable = false)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public UserEntity getCustomer() {
        return customer;
    }

    public void setCustomer(UserEntity customer) {
        this.customer = customer;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "order_from")
    public OrderFrom getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(OrderFrom orderFrom) {
        this.orderFrom = orderFrom;
    }

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    @Fetch(value = FetchMode.SELECT)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    public Set<OrderItemEntity> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Set<OrderItemEntity> orderItem) {
        this.orderItem = orderItem;
    }

    public void addOrderItem(OrderItemEntity orderItem){
        if(null == getOrderItem())
            setOrderItem(new HashSet<OrderItemEntity>());
        getOrderItem().add(orderItem);
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = true)
    public SupplierStoreEntity getStore() {
        return store;
    }

    public void setStore(SupplierStoreEntity store) {
        this.store = store;
    }

    @Enumerated(EnumType.STRING)
    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = true)
    public UserEntity getStaff() {
        return staff;
    }

    public void setStaff(UserEntity staff) {
        this.staff = staff;
    }


    @Transient
    @XmlElement(name = "order_placed_at")
    public Date getOrderPlacedAt() {
        return getCreatedAt();
    }


    @Transient
    @XmlElement(name = "order_updated_at")
    public Date getOrderUpdatedAt() {
        return getUpdatedAt();
    }
}
