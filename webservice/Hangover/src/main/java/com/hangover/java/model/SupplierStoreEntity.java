package com.hangover.java.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/26/16
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "supplier_store")
@XmlRootElement(name = "supplier_store")
public class SupplierStoreEntity extends BaseEntity{

    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String contactPerson;
    private String contactNumber;
    private SupplierEntity supplier;
    private boolean mainBranch=false;


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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public boolean isMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(boolean mainBranch) {
        this.mainBranch = mainBranch;
    }


    @Transient
    @JsonIgnore
    public static SupplierStoreEntity getSupplierStore(Long storeId){
        SupplierStoreEntity store=null;
        if(null!=storeId){
            store = new SupplierStoreEntity();
            store.setId(storeId);
        }
        return store;
    }

}
