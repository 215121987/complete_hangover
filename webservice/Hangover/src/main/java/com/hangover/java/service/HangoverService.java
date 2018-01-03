package com.hangover.java.service;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.BaseEntity;
import com.hangover.java.model.type.Status;
import com.hangover.java.util.Constants;
import com.hangover.java.util.CustomClassMapperUtil;
import com.hangover.java.util.HangoverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashifqureshi
 * Date: 23/06/15
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/api")
@Transactional
public class HangoverService extends BaseService implements Constants{

    private Logger logger = LoggerFactory.getLogger(HangoverService.class);

    private CommonBL commonBL;

    @Autowired(required = true)
    public void setCommonBL(CommonBL commonBL) {
        this.commonBL = commonBL;
    }

    private ShoppingBL shoppingBL;

    @Autowired(required = true)
    public void setShoppingBL(ShoppingBL shoppingBL) {
        this.shoppingBL = shoppingBL;
    }

    @GET
    @Path("/{className}")
    public Response getEntities(@PathParam("className") String clazzName, @Context UriInfo uriInfo) throws IOException, ClassNotFoundException{
        Class clazz = CustomClassMapperUtil.getMappedClass(clazzName);
        Map<String,Object> paramMap = getQueryParam(uriInfo);
        if(CustomClassMapperUtil.classHasField(clazz, "status")){
            paramMap.put("status", Status.ACTIVE);
        }
        List entities= commonBL.getEntities(clazz, paramMap);
        return sendResponse(entities);
    }

    @GET
    @Path("/{className}/{id}")
    public Response getEntity(@PathParam("className") String className,@PathParam("id")Long id, @Context UriInfo uriInfo) throws IOException, ClassNotFoundException{
        Class clazz = CustomClassMapperUtil.getMappedClass(className);
        BaseEntity entity = commonBL.getEntity(clazz,id);
        return sendResponse(entity);
    }


    @GET
    @Path("/{className}/{id}/{associatedClassName}")
    public Response getEntityByAssociatedEntity(@PathParam("className") String className,
                                         @PathParam("associatedClassName")String associatedProp,@PathParam("id")Long id, @Context UriInfo uriInfo) throws IOException, ClassNotFoundException{
        Class clazz = CustomClassMapperUtil.getMappedClass(className);
        List entities = commonBL.getEntities(clazz, associatedProp, id);
        if(null!= entities && entities.size()>0){
            HangoverUtil.initializeAssociatedEntity(entities, associatedProp);
        }
        return sendResponse(entities);
    }


    @DELETE
    @Path("/{className}/{id}")
    public Response deleteEntity(@PathParam("className") String className,@PathParam("id")Long id,@Context UriInfo uriInfo) throws IOException, ClassNotFoundException{
        StatusDTO statusDTO = new StatusDTO();
        commonBL.delete(className, id, statusDTO);
        return sendResponse(statusDTO);
    }

    @PUT
    @Path("{className}/{id}")
    public Response updateEntity(@PathParam("className") String clazz,@PathParam("id")Long id,@Context UriInfo uriInfo) throws IOException, ClassNotFoundException{
        return null;
    }

}
