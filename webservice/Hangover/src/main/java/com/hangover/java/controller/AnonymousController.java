package com.hangover.java.controller;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.ShoppingBL;
import com.hangover.java.bl.UserBL;
import com.hangover.java.dto.*;
import com.hangover.java.model.ShoppingCartItemEntity;
import com.hangover.java.model.SupplierStaffEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;
import com.hangover.java.model.type.OfferFor;
import com.hangover.java.model.type.Status;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.HangoverUtil;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 25/8/14
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
/*@RequestMapping("/ann")*/
public class AnonymousController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(AnonymousController.class);

    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    public UserBL userBL;

    @Autowired
    public ShoppingBL shoppingBL;

    @Autowired
    public CommonBL commonBL;

    @Autowired
    public CommonUtil commonUtil;

    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        try {
            if (request.getUserPrincipal() != null) {
                return redirectToHome();
            }
        } catch (Exception e) {
            System.out.print("Error occur:- " + e.getMessage());
        }
        //String referer = request.getHeader("referer");
        request.getSession().setAttribute("referer", request.getHeader("referer"));
        return "login";
    }


    @RequestMapping(value =  "/login", method = RequestMethod.POST)
    public String performLogin(HttpServletRequest request, HttpServletResponse response,
                               @QueryParam("j_username")String j_username ,
                               @QueryParam("j_password")String j_password,
                               @QueryParam("_spring_security_remember_me")Boolean j_rememberme
                                        ) throws JSONException, IOException {
        StatusDTO statusDTO = new StatusDTO();
        UserEntity user = null;
        try {
            Authentication authenticationToken =
                    new UsernamePasswordAuthenticationToken(j_username, j_password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            user = (UserEntity)authentication.getPrincipal();
            if(authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                HangoverUtil.updateUserCart(request, response,this.shoppingBL, user.getId());
                updateUserWishList(request);
                statusDTO.setCode(HttpStatus.OK.value());
                statusDTO.setMessage("Logged in successfully");
            }else{
                statusDTO.setCode(HttpStatus.UNAUTHORIZED.value());
                statusDTO.setMessage(commonUtil.getText("error.credential.invalid"));
            }
        } catch (Exception e) {
            logger.debug("Problem authenticating user " + j_username+ "********"+ e.getMessage());
            statusDTO.setCode(HttpStatus.UNAUTHORIZED.value());
            statusDTO.setMessage(commonUtil.getText("error.credential.invalid"));
        }
        if(isAjaxRequest(request)){
            responseAsJSON(response, getStatusAsJSON(statusDTO));
            return null;
        }
        String redirectTo = "/comm/shop.html";
        if(request.isUserInRole(Role.ROLE_ADMIN) ||request.isUserInRole(Role.ROLE_SUPER_ADMIN)){
            redirectTo = "/SupplierEntity.html?view=hangover/supplierLayout";
        }else{
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("user.id", user.getId());
            paramMap.put("status", Status.ACTIVE);
            List<SupplierStaffEntity> supplierStaffs  =  commonBL.getEntities(SupplierStaffEntity.class, paramMap);
            if(null!=supplierStaffs && supplierStaffs.size()>0){
                SupplierStaffEntity supplierStaff = supplierStaffs.get(0);
                if(request.isUserInRole(Role.ROLE_SUPPLIER)){
                    redirectTo = "/SupplierStoreEntity/"+ supplierStaff.getSupplier().getId()+"/supplier.html?view=hangover/storeLayout";
                }
            }
        }
        return sendRedirect(redirectTo);
    }
    
    private void updateUserWishList(HttpServletRequest request){
        List<Long> itemIds = (List<Long>) request.getSession().getAttribute(SESSION_SHORTLIST);
        if(null!=itemIds && itemIds.size()>0){
            shoppingBL.updateWishList(itemIds, getCurrentUsers().getId(), null);
        }
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request){
        HomeDTO homeDTO = commonBL.getHome(OfferFor.WEB);
        request.setAttribute("home", homeDTO);
        return "home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String performRegister(HttpServletRequest request, HttpServletResponse response,
                                  @ModelAttribute("user")UserEntity user) throws JSONException, IOException {
        StatusDTO status = new StatusDTO();
        userBL.save(user, status);
        saveStatus(request, status);
        if(isAjaxRequest(request)){
            responseAsJSON(response, getStatusAsJSON(status));
            return null;
        }
        request.setAttribute("status", status);
        if(status.getCode()==HttpStatus.OK.value()){
            //this.userBL.writeUserAgeProof(user.getId(), ageProof.getInputStream(), ageProof.getOriginalFilename());
            return sendRedirect("/login.html");
        }else{
            user.setPassword(user.getConfirmPassword());
            request.setAttribute("user", user);
            return "register";
        }
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request){
        return "register";
    }

    @RequestMapping("/forgot/password/form")
    public String forgotPasswordForm(HttpServletRequest request){
        return "forgotPasswordForm";
    }

    @RequestMapping("/forgot/password")
    public String forgotPassword(HttpServletRequest request,
                                 @RequestParam(value = "username", required = true) String username){
        StatusDTO status = new StatusDTO();
        userBL.identify(username,status);
        saveStatus(request,status);
        if(status.getCode()!= HttpStatus.OK.value()){
            return sendRedirect("/forgot/password/form.html");
        }
        return sendRedirect("/login.html");
    }

    @RequestMapping("/validate/mobile")
    public String validateMobile(HttpServletRequest request, HttpServletResponse response,
                                   @QueryParam("mobile")String mobile) throws JSONException, IOException {
        StatusDTO status = new StatusDTO();
        userBL.isMobileExist(mobile, status);
        responseAsJSON(response, getStatusAsJSON(status));
        return null;
    }

    @RequestMapping("/validate/email")
    public String validateEmail(HttpServletRequest request, HttpServletResponse response,
                                   @QueryParam("email")String email) throws JSONException, IOException {
        StatusDTO status = new StatusDTO();
        userBL.isEmailExist(email, status);
        responseAsJSON(response, getStatusAsJSON(status));
        return null;
    }

}
