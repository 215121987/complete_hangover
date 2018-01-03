package com.hangover.ashqures.hangover.dto;

import java.util.Set;

/**
 * Created by ashqures on 8/18/16.
 */
public class UserDTO {


    private Long id;
    private String name;
    private String email;
    private String token;
    private String mobile;
    private Set<String> role;
    private boolean mobileVerified = false;
    private boolean ageVerified = false;
    private boolean emailVerified = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public boolean isMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    public boolean isAgeVerified() {
        return ageVerified;
    }

    public void setAgeVerified(boolean ageVerified) {
        this.ageVerified = ageVerified;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
