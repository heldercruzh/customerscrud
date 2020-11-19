package br.com.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
 * This class is user for allowed cross reference "Sprint Cors" in all of project,
 * this is important because diferent of postman, the browsers need to send preflight 
 * requests for undestend the response os Rest API
 * 
 */

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }
}
