package com.hangover.java.service.provider;


import javax.ws.rs.ext.ContextResolver;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 2/8/16
 * Time: 12:53 AM
 * To change this template use File | Settings | File Templates.
 */
/*@Provider*/
public class CustomXMLMapper /*implements ContextResolver<XmlMapper>*/ {
    /*@Override
    public XmlMapper getContext(Class<?> aClass) {
        XmlMapper mapper = new XmlMapper();
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.registerModule(module);
        Hibernate4Module hbm = new Hibernate4Module();
        hbm.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, false);
        mapper.registerModule(hbm);
        return mapper;
    }*/
}
