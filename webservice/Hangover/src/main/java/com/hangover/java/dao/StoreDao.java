package com.hangover.java.dao;

import com.hangover.java.model.*;
import com.hangover.java.model.type.OfferFor;
import com.hangover.java.model.type.OfferType;
import com.hangover.java.model.type.OrderState;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/5/16
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StoreDao extends BaseDao{
    
    
    SupplierStoreEntity getStaffStore(Long staffId);

    List<OrderEntity> getStoreOrder(Long storeId,Long staffId, List<OrderState> orderStates, int startIndex, int maxResult);

    List<SupplierStoreEntity> getStoreByLocation(String zipCode);
    
    List<LoginStatusEntity> getAvailableStoreStaff(Long storeId);

    List<OffersEntity> getOffer(OfferType offerType ,OfferFor offerFor);

}
