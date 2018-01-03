package com.hangover.java.service;

import com.hangover.java.dto.BrandDTO;
import com.hangover.java.dto.CartDTO;
import com.hangover.java.dto.OfferDTO;
import com.hangover.java.dto.ShoppingDTO;
import com.hangover.java.model.BrandEntity;
import com.hangover.java.model.OffersEntity;
import com.hangover.java.model.ShoppingCartItemEntity;
import com.hangover.java.service.wso.CartWSO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 9:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataMapper {


    public static BrandDTO transform(BrandEntity brandEntity){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setCode(brandEntity.getCode());
        brandDTO.setName(brandEntity.getName());
        brandDTO.setLogo(brandEntity.getLogo());
        brandDTO.setDisplayName(brandEntity.getDisplayName());
        brandDTO.setUrl(brandEntity.getUrl());
        brandDTO.setDescription(brandEntity.getDescription());
        return brandDTO;
    }
    
    public static List<BrandDTO> transformBrands(List<BrandEntity> brandEntities){
        List<BrandDTO> brandDTOs = new ArrayList<BrandDTO>();
        for(BrandEntity brandEntity : brandEntities)
            brandDTOs.add(transform(brandEntity));
        return brandDTOs;
    }
    
    public static OfferDTO transform(OffersEntity offersEntity){
        OfferDTO offerDTO = new OfferDTO() ;
        offerDTO.setTitle(offersEntity.getTitle());
        offerDTO.setSubTitle(offersEntity.getSubTitle());
        offerDTO.setDescription(offersEntity.getDescription());
        offerDTO.setImageURL(offersEntity.getImageURL());
        offerDTO.setId(offersEntity.getId());
        offerDTO.setOfferType(offersEntity.getOfferType());
        offerDTO.setPriority(offersEntity.getPriority());
        return offerDTO;
    }
    
    public static List<OfferDTO> transformOffers(List<OffersEntity> offersEntities){
        List<OfferDTO> offerDTOs = new ArrayList<OfferDTO>();
        for(OffersEntity offersEntity : offersEntities)
            offerDTOs.add(transform(offersEntity));
        return offerDTOs;
    }

    public static CartDTO transform(ShoppingCartItemEntity cartItemEntity){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cartItemEntity.getId());
        cartDTO.setItemId(cartItemEntity.getItem().getId());
        cartDTO.setItemDetailId(cartItemEntity.getItemDetail().getId());
        cartDTO.setSize(cartItemEntity.getItemDetail().getItemSize());
        cartDTO.setPrice(cartItemEntity.getItemDetail().getSellingPrice());
        cartDTO.setQuantity(cartItemEntity.getQuantity());
        cartDTO.setName(cartItemEntity.getItem().getName());
        cartDTO.setDescription(cartItemEntity.getItem().getDescription());
        cartDTO.setImageURL(cartItemEntity.getItem().getImageURL().get(0));
        return cartDTO;
    }

    public static List<CartDTO> transformCartItems(List<ShoppingCartItemEntity> cartItemEntities){
        List<CartDTO> cartDTOs = new ArrayList<CartDTO>();
        for(ShoppingCartItemEntity cartItemEntity : cartItemEntities)
            cartDTOs.add(transform(cartItemEntity));
        return cartDTOs;
    }

}
