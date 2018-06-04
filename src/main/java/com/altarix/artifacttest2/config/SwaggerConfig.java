package com.altarix.artifacttest2.config;

import io.swagger.jaxrs.config.BeanConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//@ApplicationPath("com/altarix/artifacttest2")
@EnableSwagger2
@Configuration
public class SwaggerConfig extends Application {

    public SwaggerConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("/sqlQueries");
        beanConfig.setResourcePackage("resources");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return resources;
    }
//    public SwaggerConfig() {
//        init();
//    }

//    @Bean
//    public Docket productApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.altarix.artifacttest2"))
//                .paths(regex("/sqlQueries.*"))
//                .build();
//    }

//    private void init() {
//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setVersion("1.0.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("/com/altarix/artifacttest2");
//        beanConfig.setResourcePackage(Controller.class.getPackage().getName());
//        beanConfig.setTitle("RESTEasy, Swagger and Swagger UI Example");
//        beanConfig.setDescription("Sample RESTful API built using RESTEasy, Swagger and Swagger UI");
//        beanConfig.setScan(true);
//    }
}
