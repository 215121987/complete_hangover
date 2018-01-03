package com.hangover.java.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.UserEntity;
import com.hangover.java.util.Constants;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebRequestDataBinder;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 25/8/14
 * Time: 1:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class BaseController implements Constants {

    public UserEntity getCurrentUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getPrincipal();
    }

    protected boolean isUserLoggedIn(HttpServletRequest request) {
        return null != request.getUserPrincipal();
    }

    public String sendRedirect(String url) {
        return "redirect:" + url;
    }

    public String redirectToHome() {
        return sendRedirect("/comm/home.html");
    }

    public void saveStatus(HttpServletRequest request, StatusDTO status) {
        request.getSession().setAttribute(Constants.RESPONSE_STATUS, status);
    }

    protected void responseAsJSON(HttpServletResponse response, JSONObject object) throws IOException {
        response.setContentType("application/x-json");
        response.getWriter().print(object);
    }


    protected JSONObject getStatusAsJSON(StatusDTO statusDTO) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", statusDTO.getCode());
        jsonObject.put("message", statusDTO.getMessage());
        JSONObject fieldError = new JSONObject();
        if(null!= statusDTO.getFielderror()){
            for(String key : statusDTO.getFielderror().keySet()){
                fieldError.put(key,statusDTO.getFielderror().get(key));
            }
        }
        jsonObject.put("fieldError", fieldError);
        return jsonObject;
    }

    protected boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(ajaxHeader);
    }


    protected void bindRequestToObject(Object obj, WebRequest webRequest){
        WebRequestDataBinder binder = new WebRequestDataBinder(obj);
        binder.bind(webRequest);
    }

    protected void writeJSONObject(HttpServletResponse response, Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.writeValue(response.getWriter(), obj);
    }
    
    
    protected void removeCookie(HttpServletResponse response, String contextPath, String name){
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        cookie.setPath(contextPath);
        response.addCookie(cookie);
    }
}
