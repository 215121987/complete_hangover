package com.hangover.java.dao;

import com.hangover.java.model.OrderEntity;
import com.hangover.java.model.master.BannerEntity;
import com.hangover.java.model.type.BannerType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 13/07/15
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CommonDao extends BaseDao{



    OrderEntity getOrder(String orderNumber);

    OrderEntity getOrder(Long orderId);


    List<BannerEntity> getBanner(BannerType bannerType);

}
