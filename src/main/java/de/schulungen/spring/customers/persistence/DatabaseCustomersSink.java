package de.schulungen.spring.customers.persistence;

import de.schulungen.spring.customers.domain.Customer;
import de.schulungen.spring.customers.domain.CustomersSink;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class DatabaseCustomersSink implements CustomersSink {
  @Override
  public long count() {
    return 0;
  }

  @Override
  public Stream<Customer> readAll() {
    return null;
  }

  @Override
  public Optional<Customer> readByUUid(UUID uuid) {
    return Optional.empty();
  }

  @Override
  public boolean existsByUuid(UUID uuid) {
    return false;
  }

  @Override
  public void create(Customer customer) {

  }

  @Override
  public void update(Customer customer) {

  }

  @Override
  public void deleteByUuid(UUID uuid) {

  }
}
