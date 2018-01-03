package com.hangover.java.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Created with IntelliJ IDEA.
 * User: Ashif.Qureshi
 * Date: 9/18/14
 * Time: 5:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class HangoverException extends RuntimeException {

    private static final long serialVersionUID = -2706518848253209769L;
    private Logger logger = LoggerFactory.getLogger(HangoverException.class);

    private int code;
    private String message;

    public HangoverException() {
        super();
        logger.error("HangoverException occurred");
    }

    public HangoverException(Exception e) {
        super(e);
        logger.error("HangoverException occurred"+ e.getMessage());
    }

    
    public HangoverException(int code) {
        super();
        this.code = code;
        this.message = HttpStatus.valueOf(code).toString();
        logger.error("HangoverException occurred code:- "+ code);
    }

    public HangoverException(String message) {
        super(message);
        this.code=HttpStatus.INTERNAL_SERVER_ERROR.value();
        logger.error("HangoverException occured "+message);
    }

    public HangoverException(int code, String message) {
        super(message);
        this.code = code;
        logger.error("HangoverException occurred code "+code+" | Message "+message);
    }

    public HangoverException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code= code;
        logger.error("HangoverException occurred "+message);
        cause.printStackTrace();
    }

    public int getCode() {
        return code;
    }
}
