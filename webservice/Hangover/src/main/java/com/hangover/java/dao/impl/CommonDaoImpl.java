package com.hangover.java.dao.impl;

import com.hangover.java.dao.CommonDao;
import com.hangover.java.model.BaseEntity;
import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.master.BannerEntity;
import com.hangover.java.model.type.BannerType;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 13/07/15
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("commonDao")
public class CommonDaoImpl extends BaseDaoImpl implements CommonDao {

    @Override
    public OrderEntity getOrder(String orderNumber) {
        Criteria criteria = getCurrentSession().createCriteria(OrderEntity.class)
                .add(Restrictions.eq("orderNumber", orderNumber));
        return (OrderEntity)criteria.uniqueResult();
    }


    @Override
    public OrderEntity getOrder(Long orderId) {
        Criteria criteria = getCurrentSession().createCriteria(OrderEntity.class)
                .add(Restrictions.eq("id", orderId));
        return (OrderEntity)criteria.uniqueResult();
    }

    @Override
    public List<BannerEntity> getBanner(BannerType bannerType) {
        Criteria criteria = getCurrentSession().createCriteria(BannerEntity.class)
                .add(Restrictions.eq("bannerType", bannerType));

        return criteria.list();
    }


}
