package kg.banksystem.deliveryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class DeliveryClientApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryClientApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/libs/**").addResourceLocations("classpath:/libs/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}