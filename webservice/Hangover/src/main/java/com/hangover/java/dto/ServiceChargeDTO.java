package com.hangover.java.dto;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/23/16
 * Time: 11:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceChargeDTO extends BaseDTO{

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

    public boolean isPercent() {
        return percent;
    }

    public void setPercent(boolean percent) {
        this.percent = percent;
    }
}
