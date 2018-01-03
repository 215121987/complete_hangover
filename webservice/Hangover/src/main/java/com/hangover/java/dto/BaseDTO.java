package com.hangover.java.dto;

import com.hangover.java.model.BaseEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/12/16
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseDTO {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
