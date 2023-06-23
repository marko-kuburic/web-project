package acs.uns.ac.rs.webproject.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsRunner implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9090", "http://localhost:9092")
                .allowedMethods("GET", "PUT", "POST", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
