package com.hangover.ashqures.hangover.entity.mapper;

import com.hangover.ashqures.hangover.dto.AddressDTO;
import com.hangover.ashqures.hangover.dto.CartDTO;
import com.hangover.ashqures.hangover.dto.CartItemDTO;
import com.hangover.ashqures.hangover.dto.ItemDTO;
import com.hangover.ashqures.hangover.dto.ItemDetailDTO;
import com.hangover.ashqures.hangover.dto.StatusDTO;
import com.hangover.ashqures.hangover.dto.UserDTO;
import com.hangover.ashqures.hangover.entity.AddressEntity;
import com.hangover.ashqures.hangover.entity.CartEntity;
import com.hangover.ashqures.hangover.entity.CartItemEntity;
import com.hangover.ashqures.hangover.entity.ItemDetailEntity;
import com.hangover.ashqures.hangover.entity.ItemEntity;
import com.hangover.ashqures.hangover.entity.StatusEntity;
import com.hangover.ashqures.hangover.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/7/16.
 */
public class DataMapper {

    @Inject
    public DataMapper() {
    }

    public UserDTO transform(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setMobile(userEntity.getMobile());
        userDTO.setAgeVerified(userEntity.isAgeVerified());
        userDTO.setMobileVerified(userEntity.isMobileVerified());
        userDTO.setToken(userEntity.getToken());
        userDTO.setRole(userEntity.getRoles());
        return  userDTO;
    }

    public StatusDTO transform(StatusEntity statusEntity){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setCode(statusEntity.getCode());
        statusDTO.setMessage(statusEntity.getMessage());
        statusDTO.setErrors(statusEntity.getErrors());
        return statusDTO;
    }

    public ItemDTO transform(ItemEntity itemEntity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemEntity.getId());
        itemDTO.setName(itemEntity.getName());
        itemDTO.setDescription(itemEntity.getDescription());
        for(ItemDetailEntity itemDetail :  itemEntity.getItemDetailList()){
            ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
            itemDetailDTO.setId(itemDetail.getId());
            itemDetailDTO.setSize(itemDetail.getSize());
            itemDetailDTO.setItemId(itemEntity.getId());
            itemDetail.setQuantity(itemDetail.getQuantity());
            itemDTO.addItemDetailDTO(itemDetailDTO);
        }
        itemDTO.setImageUrl(itemEntity.getImageURL());
        return itemDTO;
    }

    public List<ItemDTO> transform(List<ItemEntity> itemEntities) {
        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntities) {
            itemDTOs.add(transform(itemEntity));
        }
        return itemDTOs;
    }

    public CartItemDTO transform(CartItemEntity cartItemEntity){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItemEntity.getId());
        cartItemDTO.setItemId(cartItemEntity.getItemId());
        cartItemDTO.setItemName(cartItemEntity.getItemName());
        cartItemDTO.setImageUrl(cartItemEntity.getImageUrl());
        cartItemDTO.setItemDetailId(cartItemEntity.getItemDetailId());
        cartItemDTO.setItemPrice(cartItemEntity.getItemPrice());
        cartItemDTO.setItemQuantity(cartItemEntity.getItemQuantity());
        cartItemDTO.setItemSize(cartItemEntity.getItemSize());
        cartItemDTO.setSellingPrice(cartItemEntity.getSellingPrice());
        cartItemDTO.setTaxable(cartItemEntity.isTaxable());
        return cartItemDTO;
    }

    public CartDTO transform(CartEntity cartEntity){
        CartDTO cartDTO =  new CartDTO();
        return cartDTO;
    }

    public List<AddressDTO> transformAddress(List<AddressEntity> addressEntities){
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for(AddressEntity addressEntity : addressEntities){
            addressDTOs.add(transform(addressEntity));
        }
        return addressDTOs;
    }

    public AddressDTO transform(AddressEntity addressEntity){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(addressEntity.getId());
        addressDTO.setAddress(addressEntity.getAddress());
        addressDTO.setStreet(addressEntity.getStreet());
        addressDTO.setCity(addressEntity.getCity());
        addressDTO.setState(addressEntity.getState());
        addressDTO.setCountry(addressEntity.getCountry());
        addressDTO.setZipCode(addressEntity.getZipCode());
        addressDTO.setLatitude(addressEntity.getLatitude());
        addressDTO.setLongitude(addressEntity.getLongitude());
        return addressDTO;
    }

}
