package com.hangover.java.dto;

import com.hangover.java.model.BaseEntity;
import com.hangover.java.model.SupplierEntity;
import com.hangover.java.model.SupplierStoreEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/12/16
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SupplierDTO {
    
    private Long id;
    private String code;
    private String companyName;
    private String url;
    private String description;
    private String contactPerson;
    private String contactNumber;
    
    private Long storeId;
    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String storeContactPerson;
    private String storeContactNumber;
    private boolean mainBranch=false;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStoreContactPerson() {
        return storeContactPerson;
    }

    public void setStoreContactPerson(String storeContactPerson) {
        this.storeContactPerson = storeContactPerson;
    }

    public String getStoreContactNumber() {
        return storeContactNumber;
    }

    public void setStoreContactNumber(String storeContactNumber) {
        this.storeContactNumber = storeContactNumber;
    }

    public boolean isMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(boolean mainBranch) {
        this.mainBranch = mainBranch;
    }

    public SupplierEntity get(SupplierEntity supplier){
        //SupplierEntity supplier = new SupplierEntity();
        //supplier.setId(getId());
        supplier.setCode(getCode());
        supplier.setCompanyName(getCompanyName());
        supplier.setUrl(getUrl());
        supplier.setDescription(getDescription());
        supplier.setContactPerson(getContactPerson());
        supplier.setContactNumber(getContactNumber());
        if(null==getId()){
            SupplierStoreEntity store = new SupplierStoreEntity();
            store.setId(getStoreId());
            store.setAddress(getAddress());
            store.setStreet(getStreet());
            store.setCity(getCity());
            store.setState(getState());
            store.setCountry(getCountry());
            store.setZipCode(getZipCode());
            store.setContactPerson(getStoreContactPerson());
            store.setContactNumber(getStoreContactNumber());
            store.setMainBranch(isMainBranch());
            store.setSupplier(supplier);
            supplier.addStore(store);
        }
        return supplier;
    }


    /*public SupplierStoreEntity get(){
        SupplierStoreEntity store = new SupplierStoreEntity();
        store.setId(getStoreId());
        store.setAddress(getAddress());
        store.setStreet(getStreet());
        store.setCity(getCity());
        store.setState(getState());
        store.setCountry(getCountry());
        store.setContactPerson(getStoreContactPerson());
        store.setContactNumber(getStoreContactNumber());
        store.setMainBranch(isMainBranch());
        return store;
    }*/

    public void update(SupplierEntity supplier){
        setId(supplier.getId());
        setCode(supplier.getCode());
        setCompanyName(supplier.getCompanyName());
        setUrl(supplier.getUrl());
        setDescription(supplier.getDescription());
        setContactPerson(supplier.getContactPerson());
        setContactNumber(supplier.getContactNumber());
    }

    public void update(SupplierStoreEntity store){
        setStoreId(store.getId());
        setAddress(store.getAddress());
        setStreet(store.getStreet());
        setCity(store.getCity());
        setState(store.getState());
        setCountry(store.getCountry());
        setStoreContactPerson(store.getContactPerson());
        setStoreContactNumber(store.getContactNumber());
        setMainBranch(store.isMainBranch());
        setId(store.getSupplier().getId());
    }


}
