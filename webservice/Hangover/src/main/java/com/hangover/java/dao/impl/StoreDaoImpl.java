package com.hangover.java.dao.impl;

import com.hangover.java.dao.StoreDao;
import com.hangover.java.model.*;
import com.hangover.java.model.type.OfferFor;
import com.hangover.java.model.type.OfferType;
import com.hangover.java.model.type.OrderState;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/5/16
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl implements StoreDao {


    @Override
    public SupplierStoreEntity getStaffStore(Long staffId) {
        Criteria criteria = getCurrentSession().createCriteria(SupplierStaffEntity.class)
                .createAlias("user", "user")
                .add(Restrictions.eq("user.id", staffId))
                .setProjection(Projections.projectionList().add(Projections.property("store")));
        return (SupplierStoreEntity)criteria.uniqueResult();
    }

    @Override
    public List<OrderEntity> getStoreOrder(Long storeId, Long staffId, List<OrderState> orderStates, int startIndex, int maxResult) {
        Criteria criteria = getCurrentSession().createCriteria(OrderEntity.class)
                .createAlias("store", "store")
                .add(Restrictions.eq("store.id", storeId))
                .add(Restrictions.in("state", orderStates))
                .addOrder(Order.asc("updatedAt"));
        if(maxResult>0)
             criteria.setFirstResult(startIndex)
                     .setMaxResults(maxResult);
        if(null != staffId)
            criteria.createAlias("staff","staff")
                    .add(Restrictions.eq("staff.id", staffId));
        return criteria.list();
    }

    @Override
    public List<SupplierStoreEntity> getStoreByLocation(String zipCode) {
        Criteria criteria = getCurrentSession().createCriteria(SupplierStoreEntity.class)
                .add(Restrictions.eq("zipCode", zipCode));
        return criteria.list();
    }

    @Override
    public List<LoginStatusEntity> getAvailableStoreStaff(Long storeId) {
        Criteria criteria  = getCurrentSession().createCriteria(LoginStatusEntity.class)
                .createAlias("store","store")
                .add(Restrictions.eq("store.id", storeId));
        return criteria.list();
    }

    @Override
    public List<OffersEntity> getOffer(OfferType offerType ,OfferFor offerFor) {
        Criteria criteria = getCurrentSession().createCriteria(OffersEntity.class)
                .add(Restrictions.eq("offerFor", offerFor))
                .add(Restrictions.eq("offerType", offerType));
        return criteria.list();
    }
}
