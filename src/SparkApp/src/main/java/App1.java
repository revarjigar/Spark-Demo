package main.java;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class App1 extends ResourceConfig {
	
	public App1(){
		packages("main.java");// could be ...rest.controller

		
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/SparkApp/api");
        beanConfig.setResourcePackage("main.java");// could be ...rest.controller
        beanConfig.setTitle("RESTApi Documentation");
        beanConfig.setDescription("RESTApi for RESTApp");
        beanConfig.setScan(true);
	}
}
