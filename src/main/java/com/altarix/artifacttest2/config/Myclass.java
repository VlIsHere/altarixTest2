package com.altarix.artifacttest2.config;

import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Myclass extends Application {

    public Myclass() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("com/altarix/artifacttest2/controllers");
        beanConfig.setResourcePackage("swagger");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }
}
