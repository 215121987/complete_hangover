package com.hangover.java.dto;

import com.hangover.java.model.BrandEntity;
import com.hangover.java.model.ItemDetailEntity;
import com.hangover.java.model.SupplierStoreEntity;
import com.hangover.java.model.master.CategoryEntity;
import com.hangover.java.model.type.Gender;
import com.hangover.java.model.type.ItemStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemDTO extends BaseDTO{

    private String name;
    private String description;
    private SupplierStoreDTO supplierStore;
    private Gender itemFor;
    private CategoryDTO category;
    private BrandDTO brand;
    private int count=0;
    private List<ItemDetailDTO> itemDetailList;
    private ItemStatus status = ItemStatus.AVAILABLE;


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

    public SupplierStoreDTO getSupplierStore() {
        return supplierStore;
    }

    public void setSupplierStore(SupplierStoreDTO supplierStore) {
        this.supplierStore = supplierStore;
    }

    public Gender getItemFor() {
        return itemFor;
    }

    public void setItemFor(Gender itemFor) {
        this.itemFor = itemFor;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ItemDetailDTO> getItemDetailList() {
        return itemDetailList;
    }

    public void setItemDetailList(List<ItemDetailDTO> itemDetailList) {
        this.itemDetailList = itemDetailList;
    }
    
    public void addItemDetail(ItemDetailDTO itemDetailDTO){
        if(null==getItemDetailList())
            setItemDetailList(new ArrayList<ItemDetailDTO>());
        getItemDetailList().add(itemDetailDTO);
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
