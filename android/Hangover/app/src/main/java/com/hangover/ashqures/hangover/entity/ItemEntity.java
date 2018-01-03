package com.hangover.ashqures.hangover.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashqures on 8/7/16.
 */
public class ItemEntity extends BaseEntity{

    private String name;
    private String description;
    private List<ItemDetailEntity> itemDetailList;
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

    public List<ItemDetailEntity> getItemDetailList() {
        return itemDetailList;
    }

    public void setItemDetailList(List<ItemDetailEntity> itemDetailList) {
        this.itemDetailList = itemDetailList;
    }

    public void addItemDetail(ItemDetailEntity itemDetail){
        if(null== getItemDetailList())
            setItemDetailList(new ArrayList<ItemDetailEntity>());
        getItemDetailList().add(itemDetail);
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
