package de.schulungen.spring.customers.boundary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LandingPageConfiguration {

  @Bean
  WebMvcConfigurer landingPageConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
        registry
          .addViewController("/")
          .setViewName("redirect:/index.html");
      }
    };
  }

}
