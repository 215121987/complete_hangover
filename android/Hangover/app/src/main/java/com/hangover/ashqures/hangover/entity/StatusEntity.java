package com.hangover.ashqures.hangover.entity;

import java.util.List;

/**
 * Created by ashqures on 8/20/16.
 */
public class StatusEntity extends BaseEntity {

    private Integer code;
    private String message;
    private List<String> errors;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
