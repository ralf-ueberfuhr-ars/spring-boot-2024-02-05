package de.schulungen.spring.customers.boundary.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ConfigurationProperties("cors")
@Component
public class CorsConfigurationProperties {

  private String allowedOrigins = "";
  private boolean allowedCredentials = false;

}
