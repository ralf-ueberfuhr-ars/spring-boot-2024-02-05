package de.schulungen.spring.customers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

  // replace later
  private final Map<UUID, Customer> customers = new HashMap<>();

  {
    Customer c1 = new Customer();
    c1.setUuid(UUID.randomUUID());
    c1.setName("Tom");
    c1.setBirthdate(LocalDate.of(2000, Month.FEBRUARY, 14));
    c1.setState("active");
    customers.put(c1.getUuid(), c1);
    Customer c2 = new Customer();
    c2.setUuid(UUID.randomUUID());
    c2.setName("Julia");
    c2.setBirthdate(LocalDate.of(2005, Month.OCTOBER, 1));
    c2.setState("active");
    customers.put(c2.getUuid(), c2);
  }

  @GetMapping
  Collection<Customer> findAllCustomers() {
    return customers.values();
  }

}
