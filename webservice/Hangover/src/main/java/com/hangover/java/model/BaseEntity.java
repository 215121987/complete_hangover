package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 21/8/14
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
@MappedSuperclass
public abstract class BaseEntity implements java.io.Serializable {

    private static final long serialVersionUID = 3256446889040622647L;

    private Long id;
    private Date createdAt=new Date();
    private Date updatedAt=new Date();


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @javax.persistence.Column(name = "created_at", nullable = true, insertable = true, updatable = false, length = 29, precision = 6)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    @javax.persistence.Column(name = "updated_at", nullable = true, insertable = true, updatable = true, length = 29, precision = 6)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
