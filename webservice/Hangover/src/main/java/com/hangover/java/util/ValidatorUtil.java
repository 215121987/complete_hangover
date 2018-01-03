package com.hangover.java.util;

import com.hangover.java.dto.StatusDTO;
import com.hangover.java.model.UserEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by IntelliJ IDEA.
 * User: ashif
 * Date: 9/19/14
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ValidatorUtil implements Constants {

    private Logger logger = LoggerFactory.getLogger(ValidatorUtil.class);

    private final static String PATTERN_PASSWORD = "^(?=.*\\d)(?=.*[A-Za-z])[A-Za-z0-9]{6,18}$";
    private final static String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final static String PATTERN_MOBILE = "^((\\+[1-9]?[0-9])|0)?[7-9][0-9]{9}$";
    private final static int NORMAL_TEXT_FIELD_LENGTH = 60;
    public final static String PATTERN_DOUBLE = "^[1-9]([0-9]{1,6})?(\\.[0-9]{1,2})?$";
    public static final String DATE_FORMAT = "dd/MM/yyyy";


    @Autowired
    private CommonUtil commonUtil;

    public void validate(UserEntity userEntity, StatusDTO status) {
        /*if (isNullOrEmpty(userEntity.getEmail()))
            status.addError(commonUtil.getText("error.email.required", status.getLocale()));
        else*/
        if (StringUtils.isNotEmpty(userEntity.getEmail()) && !isValidEmail(userEntity.getEmail())){
            status.addError(commonUtil.getText("error.email.invalid", status.getLocale()));
            status.put("email", commonUtil.getText("error.email.invalid", status.getLocale()));
        }

        /*if (isNullOrEmpty(userEntity.getUsername()))
            status.addError(commonUtil.getText("error.username.required", status.getLocale()));
        else if (!isValidEmail(userEntity.getEmail()) && !isValidMobile(userEntity.getMobile()))
            status.addError(commonUtil.getText("error.username.invalid", status.getLocale()));*/

        if (StringUtils.isEmpty(userEntity.getMobile())){
            status.addError(commonUtil.getText("error.mobile.required", status.getLocale()));
            status.put("mobile", commonUtil.getText("error.mobile.required", status.getLocale()));
        } else if (!isValidMobile(userEntity.getMobile())){
            status.addError(commonUtil.getText("error.mobile.invalid", status.getLocale()));
            status.put("mobile",commonUtil.getText("error.mobile.invalid", status.getLocale()));
        }
        /*if (StringUtils.isEmpty(userEntity.getPassword()))
            status.addError(commonUtil.getText("error.password.required", status.getLocale()));
        else if (!userEntity.getPassword().equals(userEntity.getConfirmPassword()))
            status.addError(commonUtil.getText("error.password.and.confirm.password.mismatch", status.getLocale()));
        else if (!isValidPassword(userEntity.getPassword()))
            status.addError(commonUtil.getText("error.password.invalid", status.getLocale()));*/

        if (StringUtils.isEmpty(userEntity.getName())){
            status.addError(commonUtil.getText("error.first.name.required", status.getLocale()));
            status.put("name",commonUtil.getText("error.first.name.required", status.getLocale()));
        }else if (!isValidLength(userEntity.getName(), NORMAL_TEXT_FIELD_LENGTH)){
            status.addError(commonUtil.getText("error.text.length.must.be.less.than",
                    new Object[]{PARAM_USER_NAME, NORMAL_TEXT_FIELD_LENGTH}, status.getLocale()));
            status.put("name",commonUtil.getText("error.text.length.must.be.less.than", new Object[]{PARAM_USER_NAME, NORMAL_TEXT_FIELD_LENGTH}, status.getLocale()));
        }

        if(StringUtils.isEmpty(userEntity.getDob())){
            status.addError(commonUtil.getText("error.dob.required", status.getLocale()));
            status.put("dob",commonUtil.getText("error.dob.required", status.getLocale()));
        }else if(!isDateValid(userEntity.getDob())){
            status.addError(commonUtil.getText("error.invalid.dob", status.getLocale()));
            status.put("dob",commonUtil.getText("error.invalid.dob", status.getLocale()));
        }else if(calculateAge(userEntity.getDob())< Integer.parseInt(CommonUtil.getProperty("user.age.limit"))){
            status.addError(commonUtil.getText("error.age.must.be.greater.than.18", status.getLocale()));
            status.put("dob",commonUtil.getText("error.age.must.be.greater.than.18", status.getLocale()));
        }

        if(null == userEntity.getAgeProof() || userEntity.getAgeProof().isEmpty()){
            status.addError(commonUtil.getText("error.age.proof.required"));
            status.put("ageProof",commonUtil.getText("error.age.proof.required"));
        }else{
            //TDO: Need to add validation to check file type.
        }
    }


    public static int calculateAge(String dobText) {
        Date birthDate = null;
        try {
            birthDate = DateUtil.convertStringToDate(dobText);
            Calendar dob = Calendar.getInstance();
            dob.setTime(birthDate);
            Calendar today = Calendar.getInstance();
            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if(today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)){
                age--;
            }else if (today.get(Calendar.MONTH)== dob.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))
                age--;
            return age;
        } catch (DataFormatException e) {
            return 0;
        }
    }

    public void validateUserUpdateAbleFiled(String name, String value, StatusDTO status) {
        name = null!= name?name.trim():name;
        if(!userUpdateAbleField().contains(name)){
            status.addError(commonUtil.getText("error.unknown.field.name", status.getLocale()));
            return;
        }
        value = null != value ? value.trim() : value;
        if (name.equals(PARAM_USER_NAME)) {
            if (isNullOrEmpty(value))
                status.addError(commonUtil.getText("error.first.name.required", status.getLocale()));
        }
    }

    public void validatePassword(String password, String confirmPassword, StatusDTO status){
        if(isNullOrEmpty(password))
            status.addError(commonUtil.getText("error.password.required", status.getLocale()));
        else if(!password.equals(confirmPassword))
            status.addError(commonUtil.getText("error.password.and.confirm.password.mismatch"));
        else if(!isValidPassword(password))
            status.addError(commonUtil.getText("error.password.invalid"));
    }

    public static List<String> userUpdateAbleField(){
        List<String> filedNameList = new ArrayList<String>();
        filedNameList.add(PARAM_USER_NAME);
        filedNameList.add(PARAM_USER_PROFILE_PICK);
        return filedNameList;
    }

    public static boolean isValidLength(String text, int length) {
        return text.length() <= length;
    }

    public static boolean isValidEmail(String email) {
        return email.matches(PATTERN_EMAIL);
    }


    public static boolean isValidPassword(String password) {
        return password.matches(PATTERN_PASSWORD);
    }

    public static boolean isValidMobile(String mobile) {
        return mobile.matches(PATTERN_MOBILE);
    }


    public static boolean isNullOrEmpty(String text) {
        return null == text || "".equals(text);
    }

    public static boolean isDateValid(String dateToValidate) {
        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
