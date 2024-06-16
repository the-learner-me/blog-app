package com.fse.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig class is responsible for configuring web-related settings in the application.
 * It enables cross-origin request processing (CORS) by allowing requests from any origin.
 *
 * - {@link Configuration}: Indicates that this class defines Spring configuration.
 * - {@link EnableWebMvc}: Enables Spring Web MVC configuration.
 * - {@link WebMvcConfigurer}: Interface for customizing Spring Web MVC configuration.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
