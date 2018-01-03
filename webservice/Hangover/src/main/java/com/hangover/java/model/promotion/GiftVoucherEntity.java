package com.hangover.java.model.promotion;

import com.hangover.java.model.BaseEntity;
import com.hangover.java.model.UserEntity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 9/2/16
 * Time: 1:45 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "voucher")
@XmlRootElement(name = "voucher")
public class GiftVoucherEntity extends BaseEntity{
    
    private String code;
    private String pin;
    private String value;
    private Date validTill;
    private VoucherStatus status;
    private UserEntity usedBy;
    private Date usedAt;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Column(name = "valid_till")
    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    @Enumerated(EnumType.STRING)
    public VoucherStatus getStatus() {
        return status;
    }

    public void setStatus(VoucherStatus status) {
        this.status = status;
    }

    @ManyToOne
    @JoinColumn(name = "used_by",nullable = false)
    public UserEntity getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(UserEntity usedBy) {
        this.usedBy = usedBy;
    }

    @Column(name = "used_at")
    public Date getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(Date usedAt) {
        this.usedAt = usedAt;
    }
}
