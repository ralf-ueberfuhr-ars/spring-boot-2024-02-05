package de.schulungen.spring.customers.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface CustomersSink {

  long count();

  Stream<Customer> readAll();

  Optional<Customer> readByUUid(UUID uuid);

  boolean existsByUuid(UUID uuid);

  void create(Customer customer);

  void update(Customer customer);

  void deleteByUuid(UUID uuid);


}
