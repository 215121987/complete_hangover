package com.hangover.java.dto;

import com.hangover.java.model.SupplierEntity;
import com.hangover.java.model.SupplierStaffEntity;
import com.hangover.java.model.SupplierStoreEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/15/16
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class SupplierStaffDTO {
    
    private Long id;
    private String zipCode;
    private Long userId;
    private Long supplierId;
    private Long storeId;
    private Date startDate;
    private Date endDate;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private String confirmPassword;
    private String roleName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public SupplierStaffEntity get(SupplierStaffEntity supplierStaff){
        /*if(null!=getUserId()){
            supplierStaff.setUser(UserEntity.getUser(getUserId()));
        }else{*/
            UserEntity user = new UserEntity();
        user.setId(getUserId());
            user.setName(getName());
        user.setUsername(getEmail());
            user.setEmail(getEmail());
            user.setPassword(getPassword());
            user.setConfirmPassword(getConfirmPassword());
            user.setMobile(getMobile());
            Role role  =  new Role();
            user.addRole(role);
            supplierStaff.setUser(user);
        /*}*/
        supplierStaff.setSupplier(SupplierEntity.getSupplier(getSupplierId()));
        supplierStaff.setStore(SupplierStoreEntity.getSupplierStore(getStoreId()));
        supplierStaff.setStartDate(getStartDate());
        supplierStaff.setEndDate(getEndDate());
        return supplierStaff;
    }


    public void update(SupplierStaffEntity supplierStaff){
        setUserId(supplierStaff.getUser().getId());
        setSupplierId(supplierStaff.getSupplier().getId());
        if(null!=supplierStaff.getStore()){
            setStoreId(supplierStaff.getStore().getId());
        }
        setStartDate(supplierStaff.getStartDate());
        setEndDate(supplierStaff.getEndDate());
        setName(supplierStaff.getUser().getName());
        setEmail(supplierStaff.getUser().getEmail());
        setMobile(supplierStaff.getUser().getMobile());
        setStartDate(supplierStaff.getStartDate());
        if(null!= supplierStaff.getStore()){
            setStoreId(supplierStaff.getStore().getId());
            setZipCode(supplierStaff.getStore().getZipCode());
        }

    }
}
