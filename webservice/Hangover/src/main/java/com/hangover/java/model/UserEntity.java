package com.hangover.java.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hangover.java.model.master.Role;
import com.hangover.java.model.type.PasswordType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.security.Principal;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: Ashif.Qureshi
 * Date: 21/8/14
 * Time: 11:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@javax.persistence.Table(name = "user")
@XmlRootElement(name = "user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEntity extends BaseEntity implements UserDetails, Principal {

    //private static final long serialVersionUID = 3256446889040622647L;

    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String email;
    private String mobile;
    private boolean numberVerified = true;
    private boolean emailVerified = false;
    private boolean ageVerified = false;
    private String alternateNumber;
    private String dob;
    private Set<Role> roles;
    private PasswordType passwordType = PasswordType.PERMANENT;
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=true;
    private Set<String> roleName = new HashSet<String>();
    private String ageProofFilePath;
    private MultipartFile ageProof;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column()
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(unique = true)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Column(name = "is_number_verified")
    public boolean isNumberVerified() {
        return numberVerified;
    }

    public void setNumberVerified(boolean numberVerified) {
        this.numberVerified = numberVerified;
    }

    @Column(name = "is_email_verified")
    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Column(name = "is_age_verified")
    public boolean isAgeVerified() {
        return ageVerified;
    }

    public void setAgeVerified(boolean ageVerified) {
        this.ageVerified = ageVerified;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        if(null==getRoles())
            setRoles(new HashSet<Role>());
        getRoles().add(role);
    }

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "password_type")
    public PasswordType getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(PasswordType passwordType) {
        this.passwordType = passwordType;
    }

    @Transient
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();
        for (Role role : getRoles()) {
            AUTHORITIES.add(new SimpleGrantedAuthority(role.getName()));
        }
        //AUTHORITIES.add(new SimpleGrantedAuthority(Constants.ROLE_NAME_ADMIN));
        return AUTHORITIES;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @javax.persistence.Column(name = "username", nullable = false, insertable = true, updatable = false, length = 255, precision = 0)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    @Transient
    public boolean isUserInRole(String... roleNames){
        boolean isValid = false;
        if(null!=getRoles()){
            for(Role role :getRoles()){
                for(String roleName : roleNames){
                    if(roleName.equals(role.getName())){
                        isValid=true;
                        break;
                    }
                }
                if(isValid)
                    break;
            }
        }
        return isValid;
    }

    @Transient
    @JsonIgnore
    public Set<String> getRoleName() {
        if(null!= getRoles()){
            for(Role role : getRoles()){
                roleName.add(role.getName());
            }
        }
        return roleName;
    }

    public void setRoleName(Set<String> roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        UserEntity userEntity = (UserEntity) o;

        if (getId() != null ? !getId().equals(userEntity.getId()) : userEntity.getId() != null) return false;
        if (username != null ? !username.equals(userEntity.username) : userEntity.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Transient
    @JsonIgnore
    public static UserEntity getUser(Long userId){
        UserEntity user = new UserEntity();
        user.setId(userId);
        return user;
    }

    @Column(name = "age_proof_file_path")
    public String getAgeProofFilePath() {
        return ageProofFilePath;
    }

    public void setAgeProofFilePath(String ageProofFilePath) {
        this.ageProofFilePath = ageProofFilePath;
    }

    @Transient
    @JsonIgnore
    public MultipartFile getAgeProof() {
        return ageProof;
    }

    public void setAgeProof(MultipartFile ageProof) {
        this.ageProof = ageProof;
    }
}

    