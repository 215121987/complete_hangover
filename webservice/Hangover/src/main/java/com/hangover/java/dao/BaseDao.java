package com.hangover.java.dao;

import com.hangover.java.model.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 20/8/14
 * Time: 6:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface BaseDao {
    
    void executeSQLQueryUpdate(String sqlQuery);

    void save(Object object);
    
    void saveAll(List objectList);

    void update(Object object);
    
    void delete(Object object);
    
    void delete(Class clazz, Long id);

    void update(Class clazz, Map<String, Object> valueMap, Map<String, Object> criteriaMap);

    <T extends BaseEntity> T get(Class<T> clazz, Long id);

    <T extends BaseEntity> List<T> gets(Class<T> clazz, String associatedProp, Long id);
    
    <T extends BaseEntity> List<T> gets(Class<T> clazz, String subQuery);

    <T extends BaseEntity> List<T> gets(Class<T> clazz, Map<String,Object> filterParam);

    <T extends BaseEntity> List<T> gets(Class<T> clazz, Map<String,Object> filterParam, int pagNumber, int maxResult);

    <T extends BaseEntity> List<T> gets(Class<T> clazz, List<Long> ids);

    <T> T getMasterData(Class<T> clazz, String columnName, Object value);


}
