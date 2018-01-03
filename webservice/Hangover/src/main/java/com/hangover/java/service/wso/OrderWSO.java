package com.hangover.java.service.wso;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "order")
public class OrderWSO {
    
    private String orderNumber;

    @XmlElement(name = "order_number")
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
