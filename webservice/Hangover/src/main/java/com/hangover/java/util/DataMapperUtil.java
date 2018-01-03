package com.hangover.java.util;

import com.hangover.java.dto.*;
import com.hangover.java.model.BrandEntity;
import com.hangover.java.model.ItemDetailEntity;
import com.hangover.java.model.ItemEntity;
import com.hangover.java.model.ItemSubDetailEntity;
import com.hangover.java.model.master.CategoryEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMapperUtil {


    public static ItemDTO transform(ItemEntity item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setItemFor(item.getItemFor());
        itemDTO.setStatus(item.getStatus());
        itemDTO.setCount(item.getCount());
        if(null != item.getItemDetailList() && item.getItemDetailList().size()>0){
            for(ItemDetailEntity itemDetail :  item.getItemDetailList()){
                itemDTO.addItemDetail(transform(itemDetail));
            }
        }
        /*itemDTO.setBrand(transform(item.getBrand()));
        itemDTO.setCategory(transform(item.getCategory()));*/
        return itemDTO;
    }

    public static ItemDetailDTO transform(ItemDetailEntity itemDetail){
        ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
        itemDetailDTO.setId(itemDetail.getId());
        itemDetailDTO.setCostPrice(itemDetail.getCostPrice());
        itemDetailDTO.setItemSize(itemDetail.getItemSize());
        itemDetailDTO.setMinSellingPrice(itemDetail.getMinSellingPrice());
        itemDetailDTO.setModelNumber(itemDetail.getModelNumber());
        itemDetailDTO.setQuantity(itemDetail.getQuantity());
        itemDetailDTO.setSellingPrice(itemDetail.getSellingPrice());
        if(null != itemDetail.getItemSubDetails() && itemDetail.getItemSubDetails().size()>0){
            for(ItemSubDetailEntity itemSubDetail : itemDetail.getItemSubDetails())
                itemDetailDTO.addItemSubDetail(transform(itemSubDetail));
        }
        return itemDetailDTO;
    }

    public static ItemSubDetailDTO transform(ItemSubDetailEntity itemSubDetail){
        ItemSubDetailDTO itemSubDetailDTO = new ItemSubDetailDTO();
        itemSubDetailDTO.setId(itemSubDetail.getId());
        itemSubDetailDTO.setName(itemSubDetail.getItemDetailType().getName());
        itemSubDetailDTO.setValue(itemSubDetail.getValue());
        return itemSubDetailDTO;
    }
    
    public static BrandDTO transform(BrandEntity brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(brand.getId());
        brandDTO.setName(brand.getName());
        brandDTO.setCode(brand.getCode());
        brandDTO.setContactNumber(brand.getContactNumber());
        brandDTO.setContactPerson(brand.getContactPerson());
        brandDTO.setDescription(brand.getDescription());
        brandDTO.setDisplayName(brand.getDisplayName());
        brandDTO.setUrl(brand.getUrl());
        /*if(null!= brand.getCategory()){
            brandDTO.setCategory(transform(brand.getCategory()));
        }*/
        return brandDTO;
    }
    
    public static CategoryDTO transform(CategoryEntity category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setLevel(category.getLevel());
        if(null!= category.getParentCategory())
            categoryDTO.setParentCategory(transform(category.getParentCategory()));
        if(null != category.getChildCategories() && category.getChildCategories().size()>0){
            for(CategoryEntity cat : category.getChildCategories()){
                categoryDTO.addChildCategory(transform(cat));
            }
        }
        return categoryDTO;
    }
}
