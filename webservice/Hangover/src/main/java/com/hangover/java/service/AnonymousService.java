package com.hangover.java.service;

import com.hangover.java.bl.CommonBL;
import com.hangover.java.bl.UserBL;
import com.hangover.java.dto.HomeDTO;
import com.hangover.java.dto.LoginStatusDTO;
import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.type.OfferFor;
import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ashif
 * Date: 10/6/14
 * Time: 10:41 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/ann")
@Transactional
public class AnonymousService extends BaseService{


    @Autowired
    private UserBL userBL;

    @Autowired
    private CommonBL commonBL;

    @POST
    @Path("/login")
    public Response login(@FormParam(PARAM_USERNAME) String username,
                          @FormParam(PARAM_USER_PASSWORD) String password,
                          @FormParam(PARAM_DEVICE_ID) String deviceId,
                          @FormParam(PARAM_DEVICE_PUSH_TOKEN) String pushToken) {
        LoginStatusDTO loginStatusDTO = userBL.login(username, password, deviceId, pushToken);
        return sendResponse(loginStatusDTO);
    }

    @GET
    @Path("/logout")
    public Response logout(@QueryParam("device_id") String deviceId) {
        userBL.logout(deviceId);
        return Response.ok().build();
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveUser(@FormParam(PARAM_USER_EMAIL) String email,
                             @FormParam(PARAM_USER_PASSWORD) String password,
                             @FormParam(PARAM_USER_CONFIRM_PASSWORD) String confirmPassword,
                             @FormParam(PARAM_NAME) String name,
                             @FormParam(PARAM_DOB) String dob,
                             @FormParam(PARAM_MOBILE) String mobile,
                             @FormParam(PARAM_DEVICE_ID)String deviceId,
                             @FormParam(PARAM_DEVICE_PUSH_TOKEN)String pushToken,
                             @FormDataParam(PARAM_AGE_PROOF)  InputStream ageProofStream,
                             @FormDataParam(PARAM_AGE_PROOF) FormDataContentDisposition fileDetail) throws IOException {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        user.setDob(dob);
        user.setMobile(mobile);
        StatusDTO status = new StatusDTO();
        MultipartFile ageProofFile =getMultipartFile(fileDetail, ageProofStream);
        user.setAgeProof(ageProofFile);
        userBL.save(user, status);
        /*if(status.getCode() == HttpStatus.OK.value()){
            LoginStatusDTO loginStatusDTO = userBL.createSession( deviceId,pushToken, user);
            return sendResponse(loginStatusDTO);
        }else
            return sendResponse(status);*/
        return sendResponse(status);
    }

    @POST
    @Path("/help/identify")
    public Response identify(@FormParam(PARAM_USERNAME) String username) {
        StatusDTO status = new StatusDTO();
        userBL.identify(username, status);
        return sendResponse(status);
    }

    @GET
    @Path("/otp/send")
    public Response sendOTP(@QueryParam(PARAM_USERNAME) String username, @QueryParam(PARAM_DEVICE_ID) String deviceId) {
        StatusDTO status = new StatusDTO();
        userBL.sendOTP(username,deviceId, status);
        return sendResponse(status);
    }

    @POST
    @Path("/verify/email")
    public Response verifyUsername(@FormParam(PARAM_USERNAME) String username, @FormParam(PARAM_OTP) String otp,
                              @FormParam(PARAM_DEVICE_ID) String deviceId) {
        StatusDTO status = new StatusDTO();
        userBL.verifyEmail(username, deviceId, otp, status);
        return sendResponse(status);
    }

    @POST
    @Path("/verify/mobile")
    public Response verifyMobile(@FormParam(PARAM_USERNAME) String username, @FormParam(PARAM_OTP) String otp,
                                   @FormParam(PARAM_DEVICE_ID) String deviceId) {
        StatusDTO status = new StatusDTO();
        userBL.verifyMobile(username, deviceId, otp, status);
        return sendResponse(status);
    }

    @GET
    @Path("/home")
    public Response home(@QueryParam("from") String from) {
        HomeDTO homeDTO = commonBL.getHome(OfferFor.valueOf(from));
        return sendResponse(homeDTO);
    }

    private MultipartFile getMultipartFile(final FormDataContentDisposition fileDetail, final InputStream ageProofStream ){
        if(null != fileDetail && null != ageProofStream){
            return new MultipartFile() {
                @Override
                public String getName() {
                    return fileDetail.getName();
                }

                @Override
                public String getOriginalFilename() {
                    return fileDetail.getFileName();
                }

                @Override
                public String getContentType() {
                    return fileDetail.getType();
                }

                @Override
                public boolean isEmpty() {
                    return null== ageProofStream || fileDetail.getSize()<=0;
                }

                @Override
                public long getSize() {
                    return fileDetail.getSize();
                }

                @Override
                public byte[] getBytes() throws IOException {
                    return IOUtils.toByteArray(ageProofStream);
                }

                @Override
                public InputStream getInputStream() throws IOException {
                    return ageProofStream;
                }

                @Override
                public void transferTo(File dest) throws IOException, IllegalStateException {
                    //TODO: No supporting in this application.
                }
            };
        }else{
            return null;
        }
    }
}
