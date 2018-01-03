package com.hangover.ashqures.hangover.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashqures on 7/12/16.
 */
public class ItemDTO extends BaseDTO {

    private String name;
    private String description;
    private String itemFor;
    private Long categoryId;
    private String categoryName;
    private Long brandId;
    private String brandName;
    private Long supplierId;
    private String supplierName;
    private String storeZipCode;
    private List<ItemDetailDTO> itemDetailDTOs;
    private List<String> imageURL;

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

    public String getItemFor() {
        return itemFor;
    }

    public void setItemFor(String itemFor) {
        this.itemFor = itemFor;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getStoreZipCode() {
        return storeZipCode;
    }

    public void setStoreZipCode(String storeZipCode) {
        this.storeZipCode = storeZipCode;
    }

    public List<ItemDetailDTO> getItemDetailDTOs() {
        return itemDetailDTOs;
    }

    public void setItemDetailDTOs(List<ItemDetailDTO> itemDetailDTOs) {
        this.itemDetailDTOs = itemDetailDTOs;
    }

    public void addItemDetailDTO(ItemDetailDTO itemDetailDTO){
        if(null ==getItemDetailDTOs())
            setItemDetailDTOs(new ArrayList<ItemDetailDTO>());
        getItemDetailDTOs().add(itemDetailDTO);
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageUrl(List<String> imageURL) {
        this.imageURL = imageURL;
    }

    public void addImageURL(String url){
        if(null==getImageURL())
            setImageUrl(new ArrayList<String>());
        getImageURL().add(url);
    }
}
