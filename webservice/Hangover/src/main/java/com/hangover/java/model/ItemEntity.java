package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hangover.java.model.converter.StringArrayToStringConverter;
import com.hangover.java.model.master.CategoryEntity;
import com.hangover.java.model.type.Gender;
import com.hangover.java.model.type.ItemStatus;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/26/16
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "item")
@XmlRootElement(name = "item")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemEntity extends BaseEntity{

    private String name;
    private String description;
    private SupplierStoreEntity supplierStore;
    private Gender itemFor = Gender.UNISEX;
    private CategoryEntity category;
    private BrandEntity brand;
    private int count=0;
    private List<ItemDetailEntity> itemDetailList;
    private ItemStatus status = ItemStatus.AVAILABLE;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    public SupplierStoreEntity getSupplierStore() {
        return supplierStore;
    }

    public void setSupplierStore(SupplierStoreEntity supplierStore) {
        this.supplierStore = supplierStore;
    }


    @Enumerated(EnumType.STRING)
    public Gender getItemFor() {
        return itemFor;
    }

    public void setItemFor(Gender itemFor) {
        this.itemFor = itemFor;
    }


    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item", fetch = FetchType.EAGER)
    public List<ItemDetailEntity> getItemDetailList() {
        return itemDetailList;
    }

    public void setItemDetailList(List<ItemDetailEntity> itemDetailList) {
        this.itemDetailList = itemDetailList;
    }
    
    public void addItemDetail(ItemDetailEntity itemDetail){
        if(null==getItemDetailList())
            setItemDetailList(new ArrayList<ItemDetailEntity>());
        getItemDetailList().add(itemDetail);
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status", nullable = true)
    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    @Column(name = "image_url")
    @Convert(converter = StringArrayToStringConverter.class)
    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }
    
    public void addImageURL(String url){
        if(null== getImageURL())
            setImageURL(new ArrayList<String>());
        getImageURL().add(url);
    }

    @Transient
    @JsonIgnore
    public static ItemEntity getItem(Long itemId){
        ItemEntity item = new ItemEntity();
        item.setId(itemId);
        return item;
    }
}
