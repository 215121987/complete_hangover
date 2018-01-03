package com.hangover.java.dao;

import com.hangover.java.exception.DuplicateObjectException;
import com.hangover.java.model.LoginStatusEntity;
import com.hangover.java.model.OTPEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 20/8/14
 * Time: 12:02 PM
 * To change this template use File | Settings | File Templates.
 */
public interface UserDao extends BaseDao{

    Role getRole(String name);

    List<UserEntity> getUser();

    UserEntity getUserByUsername(String username);

    UserEntity getUserByEmail(String email);

    UserEntity getUserByMobile(String mobile);

    UserEntity getUser(Long id);

    void saveUser(UserEntity userEntity) throws DuplicateObjectException;

    void updateProfile(String name, String value, Long id) throws SQLException;

    List<UserEntity> getUserByRole(String userRole);

    LoginStatusEntity getLoginStatus(String token);

    OTPEntity getOTP(String sendTo, String otp);
}
