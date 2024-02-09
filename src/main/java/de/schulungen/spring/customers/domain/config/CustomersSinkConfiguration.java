package de.schulungen.spring.customers.domain.config;

import de.schulungen.spring.customers.domain.CustomersSink;
import de.schulungen.spring.customers.domain.InMemoryCustomersSink;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomersSinkConfiguration {

  @Bean
  @ConditionalOnMissingBean(CustomersSink.class)
  CustomersSink defaultCustomersSink() {
    return new InMemoryCustomersSink();
  }

}
