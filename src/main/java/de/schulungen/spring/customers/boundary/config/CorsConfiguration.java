package de.schulungen.spring.customers.boundary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.http.HttpHeaders.*;

@Configuration
@Profile("!cloud")
public class CorsConfiguration {

  @Bean
  WebMvcConfigurer corsConfigurer(CorsConfigurationProperties corsConfig) {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
          .addMapping("/api/v1/**")
          .exposedHeaders(LOCATION, LINK)
          .allowedHeaders(ORIGIN, CONTENT_TYPE, ACCEPT, ACCEPT_LANGUAGE, IF_MATCH, IF_NONE_MATCH, AUTHORIZATION)
          .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
          .allowedOrigins(corsConfig.getAllowedOrigins().split(","))
          .allowCredentials(corsConfig.isAllowedCredentials());
      }
    };
  }

}
