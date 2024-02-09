package de.schulungen.spring.customers.boundary.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Bean
  OpenAPI api() {
    return new OpenAPI()
      .info(
        new Info()
          .title("Customer Management API")
          .version("1.0.0")
      );
  }

}
