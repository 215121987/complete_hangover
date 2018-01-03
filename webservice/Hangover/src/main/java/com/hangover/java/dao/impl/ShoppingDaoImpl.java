package com.hangover.java.dao.impl;

import com.hangover.java.dao.ShoppingDao;
import com.hangover.java.model.*;
import com.hangover.java.model.master.CategoryEntity;
import com.hangover.java.model.type.ItemStatus;
import com.hangover.java.util.Constants;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/21/16
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("shoppingDao")
public class ShoppingDaoImpl extends CommonDaoImpl implements ShoppingDao, Constants {

    private Logger logger = LoggerFactory.getLogger(ShoppingDaoImpl.class);

    public ShoppingCartItemEntity getCartItem(Long userId, Long itemId, Long itemDetailId) {
        Criteria criteria = getCurrentSession().createCriteria(ShoppingCartItemEntity.class)
                .createAlias("shoppingCart", "sc")
                .createAlias("sc.user", "user")
                .add(Restrictions.eq("user.id", userId))
                .createAlias("item", "item")
                .add(Restrictions.eq("item.id", itemId))
                .createAlias("itemDetail", "itemDetail")
                .add(Restrictions.eq("itemDetail.id", itemDetailId));
        return (ShoppingCartItemEntity) criteria.uniqueResult();
    }

    @Override
    public boolean deleteItemFromCart(Long userId, Long itemId, Long itemDetailId) {
        //TODO: This  query has to take it in proper way. its just a temporary solution.
        /*String hql = "delete from ShoppingCartItemEntity sci where sci.shoppingCart.user.id =:userId and sci.item.id=:itemId and sci.itemDetail.id=:itemDetailId";*/
        String hql = "From ShoppingCartItemEntity sci where sci.shoppingCart.user.id=:userId and sci.item.id=:itemId and sci.itemDetail.id=:itemDetailId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("itemId", itemId);
        query.setParameter("itemDetailId", itemDetailId);
        ShoppingCartItemEntity shoppingCartItem = (ShoppingCartItemEntity) query.uniqueResult();
        getCurrentSession().delete(shoppingCartItem);
        logger.info(query.toString());
        return true;//query.executeUpdate() > 0;
    }

    @Override
    public void emptyUserCart(Long userId) {
        String cartItemHQL = "delete from ShoppingCartItemEntity sci where sci.shoppingCart.id = (select sc.id from ShoppingCartEntity sc where sc.user.id=:userId)";
        Query cartItemQuery = getCurrentSession().createQuery(cartItemHQL);
        cartItemQuery.setParameter("userId", userId);
        cartItemQuery.executeUpdate();
        String cartHql = "delete from ShoppingCartEntity sc where sc.user.id="+userId;
        Query cartHqlQuery = getCurrentSession().createQuery(cartHql);
        cartHqlQuery.executeUpdate();
    }

    @Override
    public List<ShoppingCartItemEntity> getCartItems(Long userId) {
        Criteria criteria = getCurrentSession().createCriteria(ShoppingCartItemEntity.class)
                .createAlias("shoppingCart", "sc")
                .createAlias("sc.user", "user")
                .add(Restrictions.eq("user.id", userId));
        return criteria.list();
    }

    @Override
    public ShoppingCartEntity getCart(Long userId) {
        List<ShoppingCartEntity> shoppingCarts = gets(ShoppingCartEntity.class, "user", userId);
        return null != shoppingCarts && shoppingCarts.size() > 0 ? shoppingCarts.get(0) : null;
    }

    @Override
    public WishListEntity getWishList(Long userId) {
        List<WishListEntity> wishList = gets(WishListEntity.class, "user", userId);
        return null != wishList && wishList.size() > 0 ? wishList.get(0) : null;
    }

    @Override
    public WishListItemEntity getWishListItem(Long userId, Long itemId) {
        Criteria criteria = getCurrentSession().createCriteria(WishListItemEntity.class)
                .createAlias("wishList", "wl")
                .createAlias("wl.user", "user")
                .add(Restrictions.eq("user.id", userId))
                .createAlias("item", "item")
                .add(Restrictions.eq("item.id", itemId));
        return (WishListItemEntity) criteria.uniqueResult();
    }

    @Override
    public List<WishListItemEntity> getWishListItems(Long userId) {
        Criteria criteria = getCurrentSession().createCriteria(WishListItemEntity.class)
                .createAlias("wishList", "wl")
                .createAlias("wl.user", "user")
                .add(Restrictions.eq("user.id", userId));
        return criteria.list();
    }

    @Override
    public List<ItemEntity> getItem(Map<String, String> paramMap) {
        int startIndex = 0;
        int maxResult = 20;
        StringBuilder hql = new StringBuilder("select DISTINCT it from ItemEntity it join fetch it.itemDetailList as ide ");
        //String hql = "from ItemEntity it ";
        String queryToken = " where ";
        if (paramMap.containsKey(BRAND_NAME)) {
            hql.append(queryToken);
            hql.append("it.brand.name in (" + paramMap.get(BRAND_NAME) + ")");
            queryToken = getQueryToken(queryToken);
        }
        if (paramMap.containsKey(ITEM_SIZE)) {
            hql.append(queryToken);
            hql.append(" ide.itemSize in (" + paramMap.get(ITEM_SIZE) + ")");
            queryToken = getQueryToken(queryToken);
        }
        if (paramMap.containsKey(ITEM_MIN_PRICE)) {
            hql.append(queryToken);
            hql.append(" ide.sellingPrice >=" + paramMap.get(ITEM_MIN_PRICE));
            queryToken = getQueryToken(queryToken);
        }
        if (paramMap.containsKey(ITEM_MAX_PRICE)) {
            hql.append(queryToken);
            hql.append(" ide.sellingPrice <=" + paramMap.get(ITEM_MAX_PRICE));
            queryToken = getQueryToken(queryToken);
        }
        if (paramMap.containsKey(SUPPLIER_ZIP_CODE)) {
            hql.append(queryToken);
            hql.append(" it.supplierStore.zipCode in (" + paramMap.get(SUPPLIER_ZIP_CODE) + ") ");
            queryToken = getQueryToken(queryToken);
        }
        if (paramMap.containsKey(ITEM_STATUS)) {
            hql.append(queryToken);
            hql.append(" it.itemStatus = '" + ItemStatus.get(paramMap.get(ITEM_STATUS)) + "' ");
            queryToken = getQueryToken(queryToken);
        }
        if (paramMap.containsKey(ITEM_CATEGORY)) {
            hql.append(queryToken);
            hql.append(" it.category in (:categories) ");
        }

        hql.append(getOrderBy(paramMap.get(ORDER_BY)));
        if (paramMap.containsKey(PAGE_NUMBER)) {
            startIndex = Integer.parseInt(paramMap.get(START_INDEX));
            maxResult = Integer.parseInt(paramMap.get(MAX_RESULT));
        }
        logger.info(hql.toString());
        Query query = getCurrentSession().createQuery(hql.toString());
        if (paramMap.containsKey(ITEM_CATEGORY)) {
            List<CategoryEntity> categories = getChildCategory(paramMap.get(ITEM_CATEGORY));
            query.setParameterList("categories", categories);
        }
        query.setFirstResult(startIndex);
        query.setMaxResults(maxResult);
        return query.list();
    }

    private String getOrderBy(String sortingType) {
        Map<String, String> subQueryMap = new HashMap<String, String>();
        subQueryMap.put(SORT_BY_PRICE_LOW_TO_HIGH, "Order by ide.sellingPrice asc");
        subQueryMap.put(SORT_BY_PRICE_HIGH_TO_LOW, "Order by ide.sellingPrice desc");
        subQueryMap.put(SORT_BY_DEFAULT, "Order by it.createdAt desc");
        String query = subQueryMap.get(sortingType);
        return null != query && !"".equals(query) ? query : subQueryMap.get(SORT_BY_DEFAULT);
    }

    private String getQueryToken(String token) {
        return "where".equals(token.trim()) ? token = " and " : token;
    }

    public List<CategoryEntity> getChildCategory(String categoryIds) {
        String sqlQuery = "select  id,  name, description, parent_category_id, level from (select * from category order by parent_category_id, id) base, (select @pv\\:='" + categoryIds + "') tmp where   find_in_set(parent_category_id, @pv) > 0 and  @pv\\:=concat(@pv, ',', id)";
        //String sqlQuery = "select  id,  name, description, parent_category_id, level from    (select * from category) base, (select @pv \\:= '" + categoryIds + "') tmp where find_in_set(parent_category_id, @pv)>0 or find_in_set(id, @pv) and     @pv \\:= concat(@pv, ',', id);";
        SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
        query.addEntity(CategoryEntity.class);
        List<CategoryEntity> categories = query.list();
        CategoryEntity category = getCategory(Long.parseLong(categoryIds));
        if (null == categories) {
            categories = new ArrayList<CategoryEntity>();
        }
        categories.add(category);
        return categories;
    }

    @Override
    public CategoryEntity getCategory(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(CategoryEntity.class)
                .add(Restrictions.eq("id", id));
        //CategoryEntity category =  criteria.uniqueResult();
        return (CategoryEntity) criteria.uniqueResult();
    }

    @Override
    public List<BrandEntity> getBrands(Long categoryId, Integer startIndex, Integer maxResult) {
        List<CategoryEntity> categories = getChildCategory(categoryId + "");
        Criteria criteria = getCurrentSession().createCriteria(BrandEntity.class)
                .add(Restrictions.in("category", categories));
        if (null != startIndex && null != maxResult) {
            criteria.setFirstResult(startIndex)
                    .setMaxResults(maxResult);
        }
        return criteria.list();
    }

    @Override
    public List<ItemEntity> search(String zipCode, String queryStr, int startIndex, int maxResult) {
        StringBuilder hql = new StringBuilder("select DISTINCT it from ItemEntity it join fetch it.itemDetailList as ide ");
        hql.append(" where ");
        hql.append(" it.supplierStore.zipCode=");
        hql.append(zipCode);
        /*hql.append(" and it.status = '");
        hql.append(ItemStatus.AVAILABLE);
        hql.append("'");*/
        hql.append(" and (it.name like '");
        hql.append(queryStr);
        hql.append("%'");
        hql.append(" or it.brand.name like '");
        hql.append(queryStr);
        hql.append("%'");
        List<Long> categories = getChildCategoryByName(queryStr);
        if(null!= categories && categories.size()>0){
            hql.append(" or it.category.id in ( ");
            hql.append(StringUtils.join(categories,","));
            hql.append(")");
        }
        hql.append(" ) Order by ide.sellingPrice asc");
        logger.info(hql.toString());
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setFirstResult(startIndex);
        query.setMaxResults(maxResult);
        logger.info(query.toString());
        return query.list();
    }

    public List<Long> getChildCategoryByName(String categoryName) {
        Criteria criteria = getCurrentSession().createCriteria(CategoryEntity.class)
                .add(Restrictions.ilike("name", categoryName, MatchMode.START))
                .setProjection(Projections.projectionList().add(Projections.property("id")));
        List<Long> categoryIdList = criteria.list();
        if(null!= categoryIdList && categoryIdList.size()>0){
            //String sqlQuery = "select  id,  name, description, parent_category_id, level from (select * from category order by parent_category_id, id) base, (select @pv\\:='" + StringUtils.join(categoryIdList,",") + "%') tmp where   find_in_set(parent_category_id, @pv) > 0 and  @pv\\:=concat(@pv, ',', id)";
            String sqlQuery = "select  id from (select * from category order by parent_category_id, id) base, (select @pv\\:='" + StringUtils.join(categoryIdList,",") + "') tmp where   find_in_set(parent_category_id, @pv) > 0 and  @pv\\:=concat(@pv, ',', id)";
            SQLQuery query = getCurrentSession().createSQLQuery(sqlQuery);
            List<Long> categories = query.list();
            if(null!= categories)
                categoryIdList.addAll(categories);
        }else{
            categoryIdList = new ArrayList<Long>();
        }
        return categoryIdList;
    }

}
