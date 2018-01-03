package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 21/8/14
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "user_address")
@XmlRootElement(name = "user_address")
public class AddressEntity extends BaseEntity{

    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String mobileNumber;
    private String alternateNumber;
    private String latitude;
    private String longitude;
    private UserEntity user;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    @Transient
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Transient
    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Transient
    public boolean isAddressCompleted(){
        return isValid(address)&& isValid(street)&& isValid(city) && isValid(street) && isValid(country) && isValid(zipCode);
    }

    @Transient
    private boolean isValid(String attribute){
        return null!=attribute && !"".equals(attribute);
    }

    @Transient
    public static AddressEntity getAddress(Long addressId){
        AddressEntity address = new AddressEntity();
        address.setId(addressId);
        return address;
    }
}
