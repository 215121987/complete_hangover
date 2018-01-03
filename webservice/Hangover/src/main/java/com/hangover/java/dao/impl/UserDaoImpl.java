package com.hangover.java.dao.impl;

import com.hangover.java.dao.UserDao;
import com.hangover.java.exception.DuplicateObjectException;
import com.hangover.java.model.LoginStatusEntity;
import com.hangover.java.model.OTPEntity;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.master.Role;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 20/8/14
 * Time: 12:03 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public Role getRole(String name) {
        Criteria criteria  = getCurrentSession().createCriteria(Role.class)
                .add(Restrictions.eq("name", name));
        return (Role)criteria.uniqueResult();
    }

    @Override
    public List<UserEntity> getUser() {
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class);
        return criteria.list();
    }

    public UserEntity getUserByUsername(String username){
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .add(Restrictions.or(Restrictions.eq("email", username.trim()),Restrictions.eq("mobile", username)));
        return (UserEntity)criteria.uniqueResult();
    }


    public UserEntity getUserByEmail(String email){
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .add(Restrictions.eq("email", email.trim()));
        return (UserEntity)criteria.uniqueResult();
    }

    public UserEntity getUserByMobile(String mobile){
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .add(Restrictions.eq("mobile", mobile.trim()));
        return (UserEntity)criteria.uniqueResult();
    }

    public UserEntity getUser(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .add(Restrictions.eq("id", id))
                .setFetchMode("roles", FetchMode.SELECT);
        return (UserEntity)criteria.uniqueResult();
    }

    public void saveUser(UserEntity userEntity) throws DuplicateObjectException{
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .add(Restrictions.or(Restrictions.eq("email", userEntity.getUsername()),
                        Restrictions.eq("mobile", userEntity.getMobile())));
        if(null!= userEntity.getId()){
            criteria.add(Restrictions.ne("id", userEntity.getId()));
        }
        UserEntity users = (UserEntity)criteria.uniqueResult();
        if(null!=users)
            throw new DuplicateObjectException("Email or mobile already exist");
        save(userEntity);
    }

    public void updateProfile(String name, String value, Long id) throws SQLException{
        String query = "update user set "+ name+" = '" +value+"'where id="+id+"";
        getCurrentSession().createSQLQuery(query).executeUpdate();
    }

    @Override
    public List<UserEntity> getUserByRole(String userRole) {
        Criteria criteria = getCurrentSession().createCriteria(UserEntity.class)
                .createAlias("roles","role")
                .add(Restrictions.eq("role.name", userRole))
                .add(Restrictions.eq("enabled",true));
        return criteria.list();
    }


    public LoginStatusEntity getLoginStatus(String token) {
        Criteria criteria = getCurrentSession().createCriteria(LoginStatusEntity.class)
                .add(Restrictions.eq("token", token));
        //.add(Restrictions.eq("loginStatus", LoginStatus.LOGIN));
        return (LoginStatusEntity)criteria.uniqueResult();
    }

    @Override
    public OTPEntity getOTP(String sendTo, String otp) {
        Criteria criteria = getCurrentSession().createCriteria(OTPEntity.class)
                .add(Restrictions.eq("sendTo", sendTo))
                .add(Restrictions.eq("otp", otp))
                .add(Restrictions.eq("inValidate", false));
        return (OTPEntity)criteria.uniqueResult();
    }


}
