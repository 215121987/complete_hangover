package com.hangover.java.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/3/16
 * Time: 11:22 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "otp")
@XmlRootElement(name = "otp")
public class OTPEntity extends BaseEntity{
    
    private String sendTo;
    private UserEntity user;
    private String otp;
    private String requestedFrom;
    private String verifiedFrom;
    private boolean inValidate = false;


    @Column(name = "send_to")
    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Column(name = "otp")
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Column(name = "requested_from")
    public String getRequestedFrom() {
        return requestedFrom;
    }

    public void setRequestedFrom(String requestedFrom) {
        this.requestedFrom = requestedFrom;
    }

    public String getVerifiedFrom() {
        return verifiedFrom;
    }

    public void setVerifiedFrom(String verifiedFrom) {
        this.verifiedFrom = verifiedFrom;
    }

    @Column(name = "invalidated")
    public boolean isInValidate() {
        return inValidate;
    }

    public void setInValidate(boolean inValidate) {
        this.inValidate = inValidate;
    }
}
