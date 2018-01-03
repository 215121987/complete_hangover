package com.hangover.ashqures.hangover.entity;

/**
 * Created by ashqures on 8/27/16.
 */
public class ServiceChargeEntity extends BaseEntity {

    private String name;
    private Double value;
    private boolean percent;

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
