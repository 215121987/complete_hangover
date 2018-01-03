package com.hangover.java.bl.impl;

import com.hangover.java.bl.StoreBL;
import com.hangover.java.dao.CommonDao;
import com.hangover.java.dao.StoreDao;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.exception.NoRecordFoundException;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.ShippingEntity;
import com.hangover.java.model.SupplierStoreEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.type.OrderState;
import com.hangover.java.task.AsyncTask;
import com.hangover.java.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/5/16
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("storeBL")
@Transactional
public class StoreBLImpl extends BaseBL implements StoreBL {


    @Autowired
    private CommonDao commonDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public List<OrderEntity> getStoreOpenOrder(Long staffId) {
        SupplierStoreEntity store = storeDao.getStaffStore(staffId);
        List<OrderState> orderStates = new ArrayList<OrderState>();
        orderStates.add(OrderState.PAYMENT_SUCCESS);
        orderStates.add(OrderState.ORDER_ACCEPTED);
        orderStates.add(OrderState.ORDER_PACKED);
        orderStates.add(OrderState.ORDER_INVOICE_GENERATED);
        orderStates.add(OrderState.ORDER_DISPATCHED);
        return storeDao.getStoreOrder(store.getId(),null, orderStates, 0, 0);
    }

    @Override
    public List<OrderEntity> getStoreStaffArchiveOrder(Long staffId, int pageNumber) {
        SupplierStoreEntity store = storeDao.getStaffStore(staffId);
        List<OrderState> orderStates = new ArrayList<OrderState>();
        orderStates.add(OrderState.ORDER_DELIVERED);
        orderStates.add(OrderState.ORDER_DELIVERY_FAILED);
        int maxResult = Integer.parseInt(CommonUtil.getProperty("max.record.per.page"));
        int startIndex = pageNumber* maxResult;
        return storeDao.getStoreOrder(store.getId(),staffId, orderStates, startIndex, maxResult);
    }

    @Override
    public List<OrderEntity> getStoreArchiveOrder(Long staffId, int pageNumber) {
        SupplierStoreEntity store = storeDao.getStaffStore(staffId);
        List<OrderState> orderStates = new ArrayList<OrderState>();
        orderStates.add(OrderState.ORDER_DELIVERED);
        orderStates.add(OrderState.ORDER_DELIVERY_FAILED);
        return getSoreOrder(store.getId(), orderStates, pageNumber);
    }

    private List<OrderEntity> getSoreOrder(Long storeId, List<OrderState> orderStates, int pageNumber){
        int maxResult = Integer.parseInt(CommonUtil.getProperty("max.record.per.page"));
        int startIndex = pageNumber* maxResult;
        return storeDao.getStoreOrder(storeId,null, orderStates, startIndex, maxResult);
    }
    

    @Override
    public void updateState(Long orderId, Long staffId, String state,StatusDTO status) {
        OrderEntity order = commonDao.getOrder(orderId);
        if(null==orderId)
            throw new NoRecordFoundException("Invalid order id");
        SupplierStoreEntity store = storeDao.getStaffStore(staffId);
        if(!store.getId().equals(order.getStore().getId()))
            throw new AccessDeniedException("User doesn't have permission to update status for this order. Order Id="+orderId +" and User Id="+staffId);
        /*switch (OrderState.valueOf(state)){
            case ORDER_INVOICE_GENERATED:
                order.setState(OrderState.ORDER_IN_PROCESS);
                ShippingEntity shipping = new ShippingEntity();

                break;
            case ORDER_PACKED:
                break;
            case ORDER_DISPATCHED:
                break;
            case ORDER_DELIVERED:
                break;
            case ORDER_DELIVERY_FAILED:
                break;
            default:
                status.setMessage("Invalid state");
                saveErrorMessage(status, HttpStatus.BAD_REQUEST.value());
                return;
        }*/
        order.setUpdatedAt(new Date());
        order.setStaff(UserEntity.getUser(staffId));
        order.setState(OrderState.valueOf(state));
        storeDao.save(order);
        Map<String,String> map = new HashMap<String, String>();
        map.put("orderNumber",order.getOrderNumber());
        this.taskExecutor.execute(this.asyncTask.getOrderNotificationTask(map));
        saveSuccessMessage(status,"Order State Updated");
    }
}
