package com.hangover.java.dto;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/25/16
 * Time: 1:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class CartDTO {
    
    private Long id;
    private Long userId;
    private Long itemId;
    private Long itemDetailId;
    private int quantity;
    private String name;
    private String description;
    private Double price;
    private String size;
    private boolean taxable;
    private String imageURL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(Long itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity){
        this.quantity = this.quantity+quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartDTO cartDTO = (CartDTO) o;

        if (itemDetailId != null ? !itemDetailId.equals(cartDTO.itemDetailId) : cartDTO.itemDetailId != null)
            return false;
        if (itemId != null ? !itemId.equals(cartDTO.itemId) : cartDTO.itemId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (itemDetailId != null ? itemDetailId.hashCode() : 0);
        return result;
    }
}
