package com.hangover.java.dto;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/10/16
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class NameValuePair {
    
    
    private String name;
    private String value;

    public NameValuePair(){}

    public NameValuePair(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
