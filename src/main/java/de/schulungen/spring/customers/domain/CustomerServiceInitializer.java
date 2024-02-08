package de.schulungen.spring.customers.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class CustomerServiceInitializer {

  private final CustomersService service;

  @EventListener(ContextRefreshedEvent.class)
  public void initializeSamples() {
    if(service.count()<1) {
      Customer c1 = new Customer();
      c1.setName("Tom");
      c1.setBirthdate(LocalDate.of(2000, Month.FEBRUARY, 14));
      c1.setState(CustomerState.ACTIVE);
      service.create(c1);
      Customer c2 = new Customer();
      c2.setName("Julia");
      c2.setBirthdate(LocalDate.of(2005, Month.OCTOBER, 1));
      c2.setState(CustomerState.ACTIVE);
      service.create(c2);
    }
  }

}
