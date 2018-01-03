package com.hangover.java.dto;

import com.hangover.java.model.LoginStatusEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/29/16
 * Time: 10:08 AM
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "login_status")
public class LoginStatusDTO {
    
    private Long id;
    private UserDTO user;
    private String token;



    public LoginStatusDTO(){

    }

    public LoginStatusDTO(LoginStatusEntity loginStatus){
        setId(loginStatus.getId());
        setUser(new UserDTO(loginStatus.getUser()));
        setToken(loginStatus.getToken());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
