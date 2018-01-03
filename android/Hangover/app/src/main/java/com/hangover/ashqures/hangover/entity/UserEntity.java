package com.hangover.ashqures.hangover.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ashqures on 8/19/16.
 */
public class UserEntity extends BaseEntity {

    private String name;
    private String email;
    private String mobile;
    private boolean ageVerified;
    private boolean mobileVerified;
    private Set<String> roles;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public boolean isAgeVerified() {
        return ageVerified;
    }

    public void setAgeVerified(boolean ageVerified) {
        this.ageVerified = ageVerified;
    }

    public boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }


    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void addRole(String roleName){
        if(null == getRoles())
            setRoles(new HashSet<String>());
        getRoles().add(roleName);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
