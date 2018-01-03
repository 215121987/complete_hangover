package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hangover.java.model.type.Status;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/27/16
 * Time: 12:41 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "supplier")
@XmlRootElement(name = "supplier")
public class SupplierEntity extends BaseEntity{

    private String code;
    private String companyName;
    private String url;
    private String description;
    private String contactPerson;
    private String contactNumber;
    private Status status = Status.ACTIVE;
    private Set<SupplierStoreEntity> stores;


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

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier", fetch = FetchType.LAZY)
    public Set<SupplierStoreEntity> getStores() {
        return stores;
    }

    public void setStores(Set<SupplierStoreEntity> stores) {
        this.stores = stores;
    }

    public void addStore(SupplierStoreEntity store){
        if(null == getStores()){
            setStores(new HashSet<SupplierStoreEntity>());
        }
        getStores().add(store);
    }


    @Transient
    @JsonIgnore
    public static SupplierEntity getSupplier(Long supplierId){
        SupplierEntity supplier = null;
        if(null!= supplierId){
            supplier = new SupplierEntity();
            supplier.setId(supplierId);
        }
        return supplier;
    }
}
