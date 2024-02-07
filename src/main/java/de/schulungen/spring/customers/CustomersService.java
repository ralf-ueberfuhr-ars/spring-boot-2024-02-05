package de.schulungen.spring.customers;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CustomersService {

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

  Stream<Customer> getAll() {
    return customers.values().stream();
  }

  Optional<Customer> findByUuid(UUID uuid) {
    return Optional.ofNullable(customers.get(uuid));
  }

  void create(Customer customer) {
    UUID uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    customers.put(uuid, customer);
  }

  private void checkExisting(UUID uuid) {
    if(!customers.containsKey(uuid)) {
      throw new NotFoundException();
    }
  }

  void replace(Customer customer) {
    if(null == customer.getUuid()) {
      throw new IllegalArgumentException("customer must have a uuid");
    }
    checkExisting(customer.getUuid());
    customers.put(customer.getUuid(), customer);
  }

  void delete(UUID uuid) {
    checkExisting(uuid);
    customers.remove(uuid);
  }

}
