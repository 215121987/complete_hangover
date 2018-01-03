package com.hangover.java.bl.impl;

import com.hangover.java.dto.StatusDTO;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Ashif.Qureshi
 * Date: 9/19/14
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseBL {


    protected void saveErrorMessage(StatusDTO status, int code) {
        status.setCode(code);
    }

    protected void saveSuccessMessage(StatusDTO status, String message) {
        status.setCode(HttpStatus.OK.value());
        status.setMessage(message);
    }

    protected void saveSuccessMessage(StatusDTO status) {
        status.setCode(HttpStatus.OK.value());
    }
    
}
