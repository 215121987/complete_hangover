package com.hangover.java.model.master;

import com.hangover.java.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/19/16
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "service_charge")
@XmlRootElement(name = "service_charge")
public class ServiceChargeEntity extends BaseEntity{
    
    
    private String name;
    private Double value;
    private boolean percent = false;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Column(name = "is_percent")
    public boolean isPercent() {
        return percent;
    }

    public void setPercent(boolean percent) {
        this.percent = percent;
    }
}
