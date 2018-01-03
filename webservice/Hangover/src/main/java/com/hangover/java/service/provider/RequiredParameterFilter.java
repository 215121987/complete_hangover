package com.hangover.java.service.provider;

/*import java.lang.reflect.Parameter;*/
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/25/16
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RequiredParameterFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext){
        // Loop through each parameter
        /*for (MethodParameter parameter : resourceInfo.getResourceMethod().getParameters()){
            QueryParam queryAnnotation = parameter.getAnnotation(QueryParam.class);
            Required requiredAnnotation = parameter.getAnnotation(Required.class);
            if (null != queryAnnotation && null == requiredAnnotation){
                // ... and whether it was not specified
                //MultivaluedMap<String,String> queryParams = requestContext.getUriInfo().Q
                //if (!requestContext.getUriInfo().getQueryParameters().containsKey(queryAnnotation.value())){
                    // We pass the query variable name to the constructor so that the exception can generate a meaningful error message
                    //throw new YourCustomRuntimeException(queryAnnotation.value());
                //}
            }
        }*/
    }
}