package com.hangover.java.service.provider;

import com.hangover.java.bl.UserBL;
import com.hangover.java.exception.AuthenticationException;
import com.hangover.java.exception.CredentialNotFoundException;
import com.hangover.java.exception.HangoverException;
import com.hangover.java.model.UserEntity;
import com.hangover.java.model.type.PasswordType;
import com.hangover.java.security.Authorizer;
import com.hangover.java.security.PasswordEncoder;
import com.hangover.java.util.CommonUtil;
import com.hangover.java.util.Constants;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Ashif.Qureshi
 * Date: 9/18/14
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Provider
public class RestSecurityInterceptor implements ContainerRequestFilter, Constants {

    private Logger logger = LoggerFactory.getLogger(RestSecurityInterceptor.class);

    @Context
    private ResourceInfo resourceInfo;

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
            .entity("You cannot access this resource").build();
    private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
            .entity("Access blocked for all users !!").build();

    private UserBL userBL = (UserBL)CommonUtil.getBean("userBL");

    private CommonUtil commonUtil = (CommonUtil)CommonUtil.getBean("commonUtil");

    private PasswordEncoder passwordEncoder = (PasswordEncoder)CommonUtil.getBean("passwordEncoder");

    public void filter(ContainerRequestContext containerRequest) {
        Method method = this.resourceInfo.getResourceMethod();
        if (method.isAnnotationPresent(PermitAll.class)|| method.isAnnotationPresent(DenyAll.class) || method.isAnnotationPresent(RolesAllowed.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                containerRequest.abortWith(ACCESS_FORBIDDEN);
                return;
            }
            final MultivaluedMap<String, String> headers = containerRequest.getHeaders();
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);
            if (null == authorization || authorization.isEmpty()) {
                containerRequest.abortWith(ACCESS_DENIED);
                return;
            }
            final String encodedToken = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
            String token = encodedToken;//new String(Base64.decode(encodedToken.getBytes()));
            UserEntity user = userBL.validateAuthentication(token);
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
                if (Collections.disjoint(rolesSet, user.getRoleName()))
                    containerRequest.abortWith(ACCESS_DENIED);
            }
            containerRequest.setSecurityContext(new Authorizer(user));
        }
    }

    private boolean isUserAllowed(ContainerRequestContext containerRequest, String token, Set<String> roles) {
        UserEntity user = userBL.validateAuthentication(token);
        if (roles.containsAll(user.getRoleName())) {
            containerRequest.setSecurityContext(new Authorizer(user));
            return true;
        } else
            return false;
    }
    













    private void processMediaType(ContainerRequestContext containerRequest) {
        String requestUri = containerRequest.getUriInfo().getPath();
        if (requestUri.contains(".xml")) {
            requestUri = requestUri.replace(".xml", "");
        } else if (requestUri.contains(".json")) {
            requestUri = requestUri.replace(".json", "");
            containerRequest.getHeaders().putSingle("accept", MediaType.APPLICATION_JSON);
        }
        containerRequest.setRequestUri(containerRequest.getUriInfo().getBaseUri(), UriBuilder.fromUri(requestUri).build());
    }

    private void checkToken(ContainerRequestContext containerRequest) {
        List<String> securityToken = containerRequest.getHeaders().get(SECURITY_TOKEN);
        if (null == securityToken || securityToken.isEmpty()) {
            throw new CredentialNotFoundException("Token not found");
        }
        //String token = new String(Base64.decode(securityToken.get(0)));
        UserEntity user = userBL.validateAuthentication(securityToken.get(0));
        if(user.getPasswordType().equals(PasswordType.TEMPORARY) && !containerRequest.getUriInfo().getPath().contains("/change/password"))
            throw new HangoverException(HttpStatus.TEMPORARY_REDIRECT.value(),commonUtil.getText("change.password.message"));
        containerRequest.setSecurityContext(new Authorizer(user));
    }

    private void checkUsernameAndPassword(ContainerRequestContext containerRequest) {
        List<String> authorization = containerRequest.getHeaders().get(AUTHORIZATION_PROPERTY);
        if (null == authorization || authorization.isEmpty()) {
            throw new CredentialNotFoundException("Username and password not found");
        }
        String encodeUsernameAndPassword = authorization.get(0).replace(AUTHENTICATION_SCHEME, "");
        String usernameAndPassword = Base64.decodeAsString(encodeUsernameAndPassword);
        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();
        UserEntity user = userBL.loadUserByUsername(username);
        if (passwordEncoder.isPasswordValid(password, user.getPassword(), null)) {
            containerRequest.setSecurityContext(new Authorizer(user));
        } else {
            throw new AuthenticationException("Invalid credential");
        }
    }
}
