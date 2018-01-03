package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hangover.java.model.type.OrderItemState;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/10/16
 * Time: 4:56 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "order_item")
@XmlRootElement(name = "order_item")
public class OrderItemEntity extends BaseEntity{


    private ItemEntity item;
    private String itemSize;
    private Double price;
    private int quantity;
    private OrderEntity order;
    private OrderItemState orderItemState=OrderItemState.ORDERED;
    private Date deliverAt;
    private ShippingEntity shipping;


    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }


    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "order_item_state")
    public OrderItemState getOrderItemState() {
        return orderItemState;
    }

    public void setOrderItemState(OrderItemState orderItemState) {
        this.orderItemState = orderItemState;
    }

    @Column(name = "deliver_at")
    public Date getDeliverAt() {
        return deliverAt;
    }

    public void setDeliverAt(Date deliverAt) {
        this.deliverAt = deliverAt;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_id", nullable = true)
    public ShippingEntity getShipping() {
        return shipping;
    }

    public void setShipping(ShippingEntity shipping) {
        this.shipping = shipping;
    }

}
