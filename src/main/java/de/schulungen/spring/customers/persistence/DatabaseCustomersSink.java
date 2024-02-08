package de.schulungen.spring.customers.persistence;

import de.schulungen.spring.customers.domain.Customer;
import de.schulungen.spring.customers.domain.CustomersSink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DatabaseCustomersSink implements CustomersSink {

  private final CustomersRepository repo;
  private final CustomerEntityMapper mapper;

  @Override
  public long count() {
    return repo.count();
  }

  @Override
  public Stream<Customer> readAll() {
    return repo
      .findAll()
      .stream()
      .map(mapper::map);
  }

  @Override
  public Optional<Customer> readByUUid(UUID uuid) {
    return repo
      .findById(uuid)
      .map(mapper::map);
  }

  @Override
  public boolean existsByUuid(UUID uuid) {
    return repo.existsById(uuid);
  }

  @Override
  public void create(Customer customer) {
    var entity = mapper.map(customer);
    var savedEntity = repo.save(entity);
    mapper.copy(savedEntity, customer);
  }

  @Override
  public void update(Customer customer) {
    var entity = mapper.map(customer);
    var savedEntity = repo.save(entity);
    mapper.copy(savedEntity, customer);
  }

  @Override
  public void deleteByUuid(UUID uuid) {
    repo.deleteById(uuid);
  }
}
