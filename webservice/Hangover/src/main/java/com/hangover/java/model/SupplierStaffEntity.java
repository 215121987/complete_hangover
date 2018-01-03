package com.hangover.java.model;

import com.hangover.java.model.type.Status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/10/16
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "supplier_staff")
@XmlRootElement(name = "supplier_staff")
public class SupplierStaffEntity extends BaseEntity{


    private UserEntity user;
    private SupplierEntity supplier;
    private Date startDate;
    private Date endDate;
    private Status status = Status.ACTIVE;
    private SupplierStoreEntity store;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @Fetch(FetchMode.SELECT)
    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
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


    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @ManyToOne
    @JoinColumn(name = "store_id")
    public SupplierStoreEntity getStore() {
        return store;
    }

    public void setStore(SupplierStoreEntity store) {
        this.store = store;
    }
}
