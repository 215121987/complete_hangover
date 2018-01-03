package com.hangover.java.dto;

import com.hangover.java.model.UserEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 3/14/16
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class UserDTO {
    
    private Long id;
    private String mobile;
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private boolean numberVerified;
    private boolean emailVerified;

    public UserDTO(){}

    public UserDTO(UserEntity user){
        this.id = user.getId();
        this.mobile = user.getMobile();
        this.email= user.getEmail();
        this.name = user.getName();
        this.numberVerified = user.isNumberVerified();
        this.emailVerified = user.isEmailVerified();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isNumberVerified() {
        return numberVerified;
    }

    public void setNumberVerified(boolean numberVerified) {
        this.numberVerified = numberVerified;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }
}
