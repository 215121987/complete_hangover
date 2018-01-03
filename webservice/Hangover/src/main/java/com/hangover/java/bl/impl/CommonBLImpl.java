package com.hangover.java.bl.impl;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.dao.CommonDao;
import com.hangover.java.dao.ShoppingDao;
import com.hangover.java.dao.StoreDao;
import com.hangover.java.dto.HomeDTO;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.*;
import com.hangover.java.model.master.BannerEntity;
import com.hangover.java.model.type.*;
import com.hangover.java.service.DataMapper;
import com.hangover.java.task.HangoverBeans;
import com.hangover.java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 13/07/15
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("commonBL")
@Transactional
public class CommonBLImpl extends BaseBL implements CommonBL, Constants {

    private Logger logger = LoggerFactory.getLogger(CommonBLImpl.class);

    @Autowired
    private ValidatorUtil validatorUtil;

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private ShoppingDao shoppingDao;

    @Autowired
    private HangoverBeans hangoverBeans;

    @Override
    public void executeSQLQueryUpdate(String sqlQuery) {
        commonDao.executeSQLQueryUpdate(sqlQuery);
    }

    @Override
    public void saveEntity(Object object, StatusDTO statusDTO) {
        try{
            commonDao.save(object);
            saveSuccessMessage(statusDTO);
        }catch (Exception e){
            logger.error("Unable to save entity "+ object.getClass().getName()+ "  error:- "+ e);
            saveErrorMessage(statusDTO, HttpStatus.BAD_REQUEST.value());
        }
    }

    @Override
    public <T extends BaseEntity> T getEntity(Class<T> clazz, Long id) {
        //UserEntity userEntity = (UserEntity)commonDao.get(clazz, id);
        //userEntity.getRoleName();
        return (T)commonDao.get(clazz, id);
    }

    @Override
    public <T extends BaseEntity> List<T> getEntities(Class<T> clazz, String associatedProp, Long id) {
        List<T> entities = commonDao.gets(clazz, associatedProp, id);
        return entities;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> List<T> getEntities(Class<T> clazz, Map<String, Object> paramMap) {
        List<T> entities = commonDao.gets(clazz, paramMap);

        return entities;
    }

    @Override
    public <T extends BaseEntity> T updateEntity(Class<T> entity, Long id, Map<String, Object> paramMap) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteEntity(Object object) {
        commonDao.delete(object);
    }

    @Override
    public void delete(String className, Long id, StatusDTO statusDTO) {
        try {
            Class clazz =   CustomClassMapperUtil.getMappedClass(className);
            if(CustomClassMapperUtil.classHasField(clazz, "status")){
                Map<String, Object> valueMap = new HashMap<String, Object>();
                valueMap.put("status", Status.DELETED.ordinal());
                Map<String, Object> criteriaMap = new HashMap<String, Object>();
                criteriaMap.put("id", id);
                commonDao.update(clazz, valueMap, criteriaMap);
            }else{
                commonDao.delete(clazz, id);
            }
            statusDTO.setCode(HttpStatus.OK.value());
            statusDTO.setMessage(commonUtil.getText("success.delete", statusDTO.getLocale()));
        } catch (IOException e) {
            statusDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } catch (ClassNotFoundException e){
            statusDTO.setCode(HttpStatus.NOT_FOUND.value());
        }
    }


    @Override
    public void softDelete(Class clazz, Long id) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        valueMap.put("status", Status.DELETED.ordinal());
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put("id", id);
        commonDao.update(clazz, valueMap, criteriaMap);
    }

    @Override
    public void initializeAssociatedEntity(UserEntity userEntity) {
       userEntity.getRoleName();
    }

    @Override
    public OrderEntity getOrder(Long orderId) {
        return commonDao.getOrder(orderId);
    }

    @Override
    public HomeDTO getHome(OfferFor offerFor) {
        HomeDTO homeDTO = new HomeDTO();
        List<OffersEntity> generalOffer = storeDao.getOffer(OfferType.GENERAL_OFFER, offerFor);
        homeDTO.setGeneralOffer(DataMapper.transformOffers(generalOffer));
        List<OffersEntity> expressOffer = storeDao.getOffer(OfferType.EXPRESS_OFFER, offerFor);
        if(null != expressOffer && expressOffer.size()>0){
            homeDTO.setExpressOffer(DataMapper.transform(expressOffer.get(0)));
        }
        List<OffersEntity> specificOffer = storeDao.getOffer(OfferType.SPECIFIC_OFFER, offerFor);
        if(null != specificOffer && specificOffer.size()>0){
            homeDTO.setSpecificOffer(DataMapper.transform(specificOffer.get(0)));
        }
        List<BrandEntity> brands = shoppingDao.getBrands(1L,0,5);
        homeDTO.setBrands(DataMapper.transformBrands(brands));
        List<BannerEntity> banners = commonDao.getBanner(BannerType.HOME_BANNER);
        homeDTO.setBannerURL(banners.get(0).getLImageURL());
        return homeDTO;
    }
}
