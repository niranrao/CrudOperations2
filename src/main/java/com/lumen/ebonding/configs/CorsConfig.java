package com.lumen.ebonding.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")
	   	  .allowedOrigins("*")
		  .allowedMethods("GET", "POST", "PUT", "DELETE")
		  .allowedHeaders("*")
		  .allowCredentials(false)
		  .maxAge(4800);
    }
}
