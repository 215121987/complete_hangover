package com.hangover.java.controller;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.SupplierEntity;
import com.hangover.java.model.SupplierStoreEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;
import com.hangover.java.model.type.Status;
import com.hangover.java.util.CustomClassMapperUtil;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.StringUtil;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 3/15/16
 * Time: 3:42 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
/*@RequestMapping("/admin")*/
public class AdminController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private CommonBL commonBL;


    /*@RequestMapping("/supplier")
    public String supplier(HttpServletRequest request) {
        Map<String,Object>  paramMap = new HashMap<String, Object>();
        paramMap.put("status", Status.ACTIVE);
        List<SupplierEntity>  suppliers = commonBL.getEntities(SupplierEntity.class, paramMap);
        request.setAttribute("suppliers", suppliers);
        return "supplier";
    }*/

    @RequestMapping("/{className}/form")
    public String supplierForm(HttpServletRequest request,
                               @PathVariable("className")String classDTOName,
                               @QueryParam("id")Long id, WebRequest webRequest,
                               @QueryParam("view")String view) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        Object dto = CustomClassMapperUtil.getMappedClassDTO(classDTOName).newInstance();
        bindRequestToObject(dto, webRequest);
        if(null!=id){
            String entityName = classDTOName.replace("DTO","Entity");
            Object entity = commonBL.getEntity(CustomClassMapperUtil.getMappedClass(entityName), id);
            CustomClassMapperUtil.invokeMethod("update",dto, entity);
            request.setAttribute("id", id);
        }
        request.setAttribute("entity", dto);
        return view;
    }


    @RequestMapping("/save/{className}")
    public String save(HttpServletRequest request,  HttpServletResponse response,
                            @PathVariable("className") String className,
                            @QueryParam("redirect") String redirectURL,
                             WebRequest webRequest) throws ClassNotFoundException, IOException,
            IllegalAccessException, InstantiationException, JSONException {
        StatusDTO statusDTO = new StatusDTO();
        Class clazzDTO=   CustomClassMapperUtil.getMappedClassDTO(className);
        Object objDTO = clazzDTO.newInstance();
        bindRequestToObject(objDTO, webRequest);
        Object entity=null;
        String entityName = className.replace("DTO","Entity");
        Class entityClass = CustomClassMapperUtil.getMappedClass(entityName);
        Object id =  CustomClassMapperUtil.invokeMethod("getId",objDTO);
        if(null!=id){
           entity = commonBL.getEntity(entityClass, (Long)id);
        }else{
            entity = CustomClassMapperUtil.getMappedClass(entityName).newInstance();
        }
        entity = CustomClassMapperUtil.invokeMethod("get",objDTO, entity);
        commonBL.saveEntity(entity, statusDTO);
        if(isAjaxRequest(request)){
            responseAsJSON(response, getStatusAsJSON(statusDTO));
            return null;
        }
        saveStatus(request, statusDTO);
        return sendRedirect(redirectURL);
    }


    @RequestMapping("/supplier/save")
    public String saveSupplier(HttpServletRequest request, SupplierEntity supplier) {
        StatusDTO statusDTO = new StatusDTO();
        commonBL.saveEntity(supplier, statusDTO);
        request.setAttribute("status", statusDTO);
        return "home";
    }


    /*@RequestMapping("/supplier/delete/{supplierId}")
    public String deleteSupplier(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("supplierId")Long supplierId) throws JSONException, IOException {
        StatusDTO statusDTO = new StatusDTO();
        commonBL.softDelete(SupplierEntity.class, supplierId);
        if(isAjaxRequest(request)){
            JSONObject object = new JSONObject();
            object.put("status", statusDTO.getCode());
            object.put("message", statusDTO.getMessage());
            responseAsJSON(response, object);
            return null;
        }
        return sendRedirect("/admin/supplier.html");
    }*/


    /*@RequestMapping("/supplier/{supplierId}/location")
    public String location(HttpServletRequest request,
                           @PathVariable("supplierId")Long supplierId) {
        Map<String,Object>  paramMap = new HashMap<String, Object>();
        paramMap.put("supplier.id", supplierId);
        paramMap.put("status", Status.ACTIVE);
        List<SupplierStoreEntity> supplierStores = commonBL.getEntities(SupplierStoreEntity.class, paramMap);
        request.setAttribute("supplierStores", supplierStores);
        return "supplierStore";
    }*/

    @RequestMapping("/supplier/{supplierId}/store/form")
    public String storeForm(HttpServletRequest request, @PathVariable("supplierId")Long supplierId) {
        return "home";
    }

    @RequestMapping("/supplier/{supplierId}/store/save")
    public String saveStore(HttpServletRequest request,
                               @PathVariable("supplierId") Long supplierId,
                               SupplierStoreEntity supplierStore) {
        StatusDTO statusDTO = new StatusDTO();
        SupplierEntity supplier = new SupplierEntity();
        supplier.setId(supplierId);
        supplierStore.setSupplier(supplier);
        commonBL.saveEntity(supplierStore, statusDTO);
        request.setAttribute("status", statusDTO);
        return "home";
    }

    /*@RequestMapping("/supplier/{supplierId}/location/delete/{locationId}")
    public String deleteLocation(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable("supplierId")Long supplierId,
                                 @PathVariable("locationId")Long locationId) throws JSONException, IOException {

        StatusDTO statusDTO = new StatusDTO();
        commonBL.softDelete(SupplierStoreEntity.class, locationId);
        if(isAjaxRequest(request)){
            JSONObject object = new JSONObject();
            object.put("status", statusDTO.getCode());
            object.put("message", statusDTO.getMessage());
            responseAsJSON(response, object);
            return null;
        }
        return sendRedirect("/admin/supplier/"+supplierId+"location.html");
    }*/



    @RequestMapping("/staff")
    public String staff(HttpServletRequest request) {
        return "home";
    }

    @RequestMapping("/staff/form")
    public String staffForm(HttpServletRequest request) {
        UserEntity user = getCurrentUsers();

        return "home";
    }

    @RequestMapping("/staff/save")
    public String saveStaff(HttpServletRequest request) {
        return "home";
    }

    @RequestMapping("/staff/delete")
    public String deleteStaff(HttpServletRequest request) {
        return "home";
    }


    @RequestMapping("/{className}/{id}")
    public String get(HttpServletRequest request, HttpServletResponse response,
                      @PathVariable("className")String className,
                      @PathVariable("id")Long id,
                      @QueryParam("view")String view) throws ClassNotFoundException, IOException, JSONException {
        try {
            CustomClassMapperUtil customClassMapperUtil = CustomClassMapperUtil.getInstance();
            Map<String,Object>  paramMap = new HashMap<String, Object>();
            paramMap.put("status", Status.ACTIVE);
            Object entity = commonBL.getEntity(customClassMapperUtil.getClassByName(className), id);
            request.setAttribute("entity", entity);
        } catch (IOException e) {

        } catch (ClassNotFoundException e){

        }
        return view;
    }

    @RequestMapping("/{className}/{id}/{associatedProp}")
    public String get(HttpServletRequest request, HttpServletResponse response,
                      @PathVariable("className")String className,
                      @PathVariable("id")Long id,
                      @PathVariable("associatedProp")String associatedProp,
                      @QueryParam("view")String view) throws ClassNotFoundException, IOException, JSONException {
        try {
            Map<String, String[]> queryParam = request.getParameterMap();
            CustomClassMapperUtil customClassMapperUtil = CustomClassMapperUtil.getInstance();
            Class clazz = customClassMapperUtil.getClassByName(className);
            Map<String,Object>  paramMap = new HashMap<String, Object>();
            if(CustomClassMapperUtil.classHasField(clazz,"status")){
                paramMap.put("status", Status.ACTIVE);
            }
            paramMap.put(associatedProp+".id", id);
            CustomClassMapperUtil.transferQueryParamToPramMap(clazz, queryParam, paramMap);
            List  entities = commonBL.getEntities(customClassMapperUtil.getClassByName(className), paramMap);
            Class associatedClass = clazz.getDeclaredField(associatedProp).getType();
            Object associatedEntity = commonBL.getEntity(associatedClass, id);
            request.setAttribute("entities", entities);
            request.setAttribute("astEntity", associatedEntity);
        } catch (IOException e) {

        } catch (ClassNotFoundException e){

        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return view;
    }



    @RequestMapping("/{className}")
    public String get(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("className")String className,
                               @QueryParam("view")String view) throws ClassNotFoundException, IOException, JSONException {
        try {
            Map<String, String[]> queryParam = request.getParameterMap();
            CustomClassMapperUtil customClassMapperUtil = CustomClassMapperUtil.getInstance();
            Class clazz = customClassMapperUtil.getClassByName(className);
            Map<String,Object>  paramMap = new HashMap<String, Object>();
            if(CustomClassMapperUtil.classHasField(clazz,"status")){
                paramMap.put("status", Status.ACTIVE);
            }
            CustomClassMapperUtil.transferQueryParamToPramMap(clazz, queryParam, paramMap);
            List  entities = commonBL.getEntities(clazz,paramMap);
            if(StringUtil.isNotNullOrEmpty(view) && view.equalsIgnoreCase(VIEW_JSON)){
                writeJSONObject(response, entities);
                return null;
            }
            request.setAttribute("entities", entities);
        } catch (IOException e) {
            logger.error("Error "+className +" exception "+e);
        } catch (ClassNotFoundException e){
            logger.error("Error "+className +" exception "+e);
        }
        return view;
    }
    

    @RequestMapping("/delete/{className}/{id}")
    public String deleteRecord(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("className")String className,
                               @PathVariable("id")Long id,
                               @QueryParam("redirectTo")String redirectTo) throws ClassNotFoundException, IOException, JSONException {
        StatusDTO statusDTO = new StatusDTO();
        commonBL.delete(className, id, statusDTO);
        /*try {
            CustomClassMapperUtil customClassMapperUtil = CustomClassMapperUtil.getInstance();
            Class clazz =   CustomClassMapperUtil.getMappedClass(className);
            if(CustomClassMapperUtil.classHasField(clazz, "status")){
                commonBL.softDelete(customClassMapperUtil.getClassByName(className), id);
            }else{
                commonBL.delete(clazz, id);
            }
            statusDTO.setCode(HttpStatus.OK.value());
            statusDTO.setMessage(commonUtil.getText("success.delete", request.getLocale()));
        } catch (IOException e) {
            statusDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } catch (ClassNotFoundException e){
           statusDTO.setCode(HttpStatus.NOT_FOUND.value());
        }*/
        if(isAjaxRequest(request)){
            responseAsJSON(response, getStatusAsJSON(statusDTO));
            return null;
        }
        saveStatus(request, statusDTO);
        return sendRedirect(redirectTo);
    }


}
