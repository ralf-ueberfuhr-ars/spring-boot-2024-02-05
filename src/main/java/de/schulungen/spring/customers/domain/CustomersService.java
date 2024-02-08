package de.schulungen.spring.customers.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
public class CustomersService {

  // replace later
  private final Map<UUID, Customer> customers = new HashMap<>();

  public long count() {
    return customers.size();
  }

  public Stream<Customer> getAll() {
    return customers.values().stream();
  }

  public Optional<Customer> findByUuid(@NotNull UUID uuid) {
    return Optional.ofNullable(customers.get(uuid));
  }

  public void create(@Valid @NotNull Customer customer) {
    UUID uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    customers.put(uuid, customer);
  }

  private void checkExisting(@NotNull UUID uuid) {
    if(!customers.containsKey(uuid)) {
      throw new NotFoundException();
    }
  }

  public void replace(@Valid Customer customer) {
    if(null == customer.getUuid()) {
      throw new IllegalArgumentException("customer must have a uuid");
    }
    checkExisting(customer.getUuid());
    customers.put(customer.getUuid(), customer);
  }

  public void delete(@NotNull UUID uuid) {
    checkExisting(uuid);
    customers.remove(uuid);
  }

}
