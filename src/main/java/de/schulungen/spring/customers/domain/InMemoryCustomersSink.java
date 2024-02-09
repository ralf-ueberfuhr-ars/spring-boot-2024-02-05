package de.schulungen.spring.customers.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

//@Component
public class InMemoryCustomersSink implements CustomersSink {

  private final Map<UUID, Customer> customers = new HashMap<>();

  @Override
  public long count() {
    return customers.size();
  }

  @Override
  public Stream<Customer> readAll() {
    return customers.values().stream();
  }

  @Override
  public Optional<Customer> readByUUid(UUID uuid) {
    return Optional.ofNullable(customers.get(uuid));
  }

  @Override
  public boolean existsByUuid(UUID uuid) {
    return customers.containsKey(uuid);
  }

  @Override
  public void create(Customer customer) {
    UUID uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    customers.put(customer.getUuid(), customer);
  }

  @Override
  public void update(Customer customer) {
    customers.put(customer.getUuid(), customer);
  }

  @Override
  public void deleteByUuid(UUID uuid) {
    customers.remove(uuid);
  }
}
