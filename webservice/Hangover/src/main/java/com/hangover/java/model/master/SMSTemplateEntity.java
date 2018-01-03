package com.hangover.java.model.master;

import com.hangover.java.model.BaseEntity;
import com.hangover.java.model.type.MessageType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/4/16
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "sms_template")
@XmlRootElement(name = "sms_template")
public class SMSTemplateEntity{
    
    private Long id;
    private String message;
    private MessageType type;


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Enumerated(EnumType.STRING)
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
