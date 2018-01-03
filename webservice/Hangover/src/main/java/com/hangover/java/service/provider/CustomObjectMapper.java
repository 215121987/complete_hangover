package com.hangover.java.service.provider;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.hangover.java.model.BaseEntity;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/25/16
 * Time: 9:59 PM
 * To change this template use File | Settings | File Templates.
 */
/*@Provider*/
public class CustomObjectMapper implements ContextResolver<ObjectMapper>{
    @Override
    public ObjectMapper getContext(Class<?> aClass) {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        //mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        /*JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
        mapper.registerModule(module);
        Hibernate4Module hbm = new Hibernate4Module();
        hbm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);
        mapper.registerModule(hbm);
        SimpleModule simpleModule = new SimpleModule();
        //simpleModule.addDeserializer(Map.class, new MapDeserializier());
        simpleModule.addDeserializer(BaseEntity.class, new CustomDeserializer());
        mapper.registerModule(simpleModule);*/
        return mapper;
    }
}
