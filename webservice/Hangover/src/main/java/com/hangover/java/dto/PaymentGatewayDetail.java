package com.hangover.java.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 10/9/16
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "payment_gateway_detail")
public class PaymentGatewayDetail {
    
    
    private String actionURL;
    private List<NameValuePair> params;

    @XmlElement(name = "action_url")
    public String getActionURL() {
        return actionURL;
    }

    public void setActionURL(String actionURL) {
        this.actionURL = actionURL;
    }

    @XmlElement(name = "parameters")
    public List<NameValuePair> getParams() {
        return params;
    }

    public void setParams(List<NameValuePair> params) {
        this.params = params;
    }

    public void addParam(String name, String value){
        if(null == getParams())
            setParams(new ArrayList<NameValuePair>());
        getParams().add(new NameValuePair(name, value));
    }

    @JsonIgnore
    public TreeMap<String,String> gerParamAsTreeMap(){
        TreeMap<String,String> treeMap = new TreeMap<String, String>();
        if(null!= this.params){
            for(NameValuePair nameValuePair : getParams()){
                treeMap.put(nameValuePair.getName(),nameValuePair.getValue());
            }
        }
        return treeMap;
    }
}
