package com.hangover.java.dao.impl;

import com.hangover.java.dao.BaseDao;
import com.hangover.java.model.BaseEntity;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 20/8/14
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class BaseDaoImpl implements BaseDao {

    private Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
  }

    @Override
    public void executeSQLQueryUpdate(String sqlQuery) {
        SQLQuery query =getCurrentSession().createSQLQuery(sqlQuery);
        query.executeUpdate();
    }

    public void save(Object object) {
        getCurrentSession().saveOrUpdate(object);
    }

    @Override
    public void saveAll(List objectList) {
        for (Object object : objectList) {
            try{
                getCurrentSession().saveOrUpdate(object);
            }catch (JDBCException jdbce){
                logger.error(jdbce.toString());
               if(jdbce.getErrorCode()==1062 && jdbce.getSQLState().equals("23000")){
                   logger.error("Duplicate Object :- "+ object.toString());
               }else{
                   //throw jdbce;
               }
            }
        }
    }

    public void update(Object object){
        getCurrentSession().update(object);
    }

    public void delete(Object object){
        getCurrentSession().delete(object);
    }

    @Override
    public void delete(Class clazz, Long id) {
        String hql = "delete from "+ clazz.getName() + " where id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        query.executeUpdate();
    }


    public void update(Class clazz, Map<String, Object> valueMap, Map<String, Object> criteriaMap){
        String hql = "update "+ clazz.getName();
        String token = "set";
        for(String key : valueMap.keySet()){
            hql = hql +" "+ token+ " " +key+" = '"+ valueMap.get(key)+"'";
            getQueryToken(token);
        }

        token = "where";
        for(String key : criteriaMap.keySet()){
            hql = hql +" "+ token+ " " +key+" = '"+ criteriaMap.get(key)+"'";
            getQueryToken(token);
        }
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    private String getQueryToken(String token) {
        return "where".equals(token.trim()) || "set".equals(token.trim())? token = " and " : token;
    }

    @Override
    public <T extends BaseEntity> T get(Class<T> clazz, Long id) {
        Criteria criteria  = getCurrentSession().createCriteria(clazz)
                .add(Restrictions.eq("id", id));
        return (T)criteria.uniqueResult();
    }

    @Override
    public <T extends BaseEntity> List<T> gets(Class<T> clazz, String associatedProp, Long id) {
        Criteria criteria  = getCurrentSession().createCriteria(clazz)
                .createAlias(associatedProp,"prop")
                .add(Restrictions.eq("prop.id", id));
        return criteria.list();
    }

    @Override
    public <T extends BaseEntity> List<T> gets(Class<T> clazz, String subQuery) {
        Criteria criteria  = getCurrentSession().createCriteria(clazz);
        return criteria.list();
    }


    @Override
    public <T extends BaseEntity> List<T> gets(Class<T> clazz, Map<String,Object> filterParam) {
        Criteria criteria  = getCurrentSession().createCriteria(clazz);
        if(null!=filterParam){
            if(filterParam.containsKey("page")){
                criteria.setFirstResult(Integer.parseInt(filterParam.get("page")+"")*Integer.parseInt(filterParam.get("maxResult")+"")-Integer.parseInt(filterParam.get("maxResult")+""));
                criteria.setMaxResults(Integer.parseInt(filterParam.get("maxResult")+""));
                filterParam.remove("page");
                filterParam.remove("maxResult");
            }
            for(String key : filterParam.keySet()){
               /* if(key.contains(".")){
                    criteria.createAlias(key.split("\\.")[0],key.split("\\.")[0]);
                }*/
                Object val = filterParam.get(key);
                if(val instanceof String){
                    criteria.add(Restrictions.ilike(key, val.toString(), MatchMode.START));
                }else{
                    criteria.add(Restrictions.eq(key, filterParam.get(key)));
                }
            }
        }
        return criteria.list();
    }

    @Override
    public <T extends BaseEntity> List<T> gets(Class<T> clazz, Map<String,Object> filterParam, int pagNumber, int maxResult) {
        Criteria criteria  = getCurrentSession().createCriteria(clazz);
        if(null!=filterParam){
            for(String key : filterParam.keySet()){
                /* if(key.contains(".")){
                    criteria.createAlias(key.split("\\.")[0],key.split("\\.")[0]);
                }*/
                Object val = filterParam.get(key);
                if(val instanceof String){
                    criteria.add(Restrictions.ilike(key, val.toString(), MatchMode.START));
                }else{
                    criteria.add(Restrictions.eq(key, filterParam.get(key)));
                }
            }
        }
        criteria.setFirstResult(pagNumber*maxResult-maxResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    @Override
    public <T extends BaseEntity> List<T> gets(Class<T> clazz, List<Long> ids) {
        Criteria criteria  = getCurrentSession().createCriteria(clazz)
                .add(Restrictions.in("id", ids));
        return criteria.list();
    }

    @Override
    public <T> T getMasterData(Class<T> clazz, String columnName, Object value) {
        Criteria criteria = getCurrentSession().createCriteria(clazz)
                .add(Restrictions.eq(columnName, value));
        return (T)criteria.uniqueResult();
    }

    protected Session getCurrentSession() {
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException he){
            session = sessionFactory.openSession();
        }
        if(null == session)
            session = sessionFactory.openSession();
        return session;
    }

}
