package com.hangover.java.dto;

import com.hangover.java.model.SupplierEntity;
import com.hangover.java.model.SupplierStoreEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/12/16
 * Time: 11:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class SupplierStoreDTO {


    private Long id;
    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String storeContactPerson;
    private String storeContactNumber;
    private boolean mainBranch=false;
    private Long supplierId;
    private String companyName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SupplierStoreEntity get(SupplierStoreEntity store){
        //SupplierStoreEntity store = new SupplierStoreEntity();
        //store.setId(getId());
        store.setZipCode(getZipCode());
        store.setAddress(getAddress());
        store.setStreet(getStreet());
        store.setCity(getCity());
        store.setState(getState());
        store.setCountry(getCountry());
        store.setContactPerson(getStoreContactPerson());
        store.setContactNumber(getStoreContactNumber());
        store.setMainBranch(isMainBranch());
        if(null==getId()){
            SupplierEntity supplier = new SupplierEntity();
            supplier.setId(getSupplierId());
            store.setSupplier(supplier);
        }
        return store;
    }


    public void update(SupplierStoreEntity store){
        setId(store.getId());
        setZipCode(store.getZipCode());
        setAddress(store.getAddress());
        setStreet(store.getStreet());
        setCity(store.getCity());
        setState(store.getState());
        setCountry(store.getCountry());
        setStoreContactPerson(store.getContactPerson());
        setStoreContactNumber(store.getContactNumber());
        setMainBranch(store.isMainBranch());
        if(null !=store.getSupplier()){
            setSupplierId(store.getSupplier().getId());
            setCompanyName(store.getSupplier().getCompanyName());
        }
    }
}
