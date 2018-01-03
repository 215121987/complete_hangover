package com.hangover.java.service;

import com.hangover.java.service.provider.RestExceptionMapper;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/25/16
 * Time: 7:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestApplication extends ResourceConfig {


    public  RestApplication(){
        // register application resources
        register(HangoverService.class);
        register(PaymentService.class);

        // register filters
        register(RequestContextFilter.class);
        /*register(LoggingResponseFilter.class);
        register(CORSResponseFilter.class);*/

        // register exception mappers
        register(RestExceptionMapper.class);

        // register features
        register(JacksonFeature.class);
        /*register(MultiPartFeature.class);*/
    }

}
