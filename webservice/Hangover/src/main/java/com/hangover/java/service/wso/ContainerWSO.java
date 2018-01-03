package com.hangover.java.service.wso;

import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.BaseEntity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/5/16
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "container")
public class ContainerWSO {

    private List items;
    private int count;
    private Object summary;
    private StatusDTO status;

    @XmlElementWrapper(name="items")
    @XmlElement
    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public  void addItems(BaseEntity item){
        if(null!=item){
            if(null == getItems())
                items = new ArrayList();
            getItems().add(item);
        }
    }

    @XmlElementRef
    public int getCount() {
        this.count = getItems().size();
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    @XmlElementRef
    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
