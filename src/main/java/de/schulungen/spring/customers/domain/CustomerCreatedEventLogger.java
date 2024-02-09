package de.schulungen.spring.customers.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerCreatedEventLogger {

  @EventListener
  void log(CustomerCreatedEvent evt) {
    log.info("Customer created: {}", evt.customer());
  }

}
