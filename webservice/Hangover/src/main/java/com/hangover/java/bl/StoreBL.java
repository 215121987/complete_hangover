package com.hangover.java.bl;

import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.type.OrderState;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/5/16
 * Time: 8:59 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StoreBL {


    List<OrderEntity> getStoreOpenOrder(Long staffId);

    List<OrderEntity> getStoreStaffArchiveOrder(Long staffId, int pageNumber);

    List<OrderEntity> getStoreArchiveOrder(Long staffId, int pageNumber);
    
    void updateState(Long orderId, Long staffId, String state,StatusDTO statusDTO);

}
