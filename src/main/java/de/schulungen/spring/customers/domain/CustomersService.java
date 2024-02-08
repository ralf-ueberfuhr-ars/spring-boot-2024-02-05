package de.schulungen.spring.customers.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
public class CustomersService {

  private final CustomersSink sink;

  public long count() {
    return sink.count();
  }

  public Stream<Customer> getAll() {
    return sink.readAll();
  }

  public Optional<Customer> findByUuid(@NotNull UUID uuid) {
    return sink.readByUUid(uuid);
  }

  public void create(@Valid @NotNull Customer customer) {
    UUID uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    sink.create(customer);
  }

  private void checkExisting(@NotNull UUID uuid) {
    if(!sink.existsByUuid(uuid)) {
      throw new NotFoundException();
    }
  }

  public void replace(@Valid Customer customer) {
    if(null == customer.getUuid()) {
      throw new IllegalArgumentException("customer must have a uuid");
    }
    checkExisting(customer.getUuid());
    sink.update(customer);
  }

  public void delete(@NotNull UUID uuid) {
    checkExisting(uuid);
    sink.deleteByUuid(uuid);
  }

}
