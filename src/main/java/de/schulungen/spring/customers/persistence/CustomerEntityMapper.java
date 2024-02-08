package de.schulungen.spring.customers.persistence;

import de.schulungen.spring.customers.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

  Customer map(CustomerEntity source);
  CustomerEntity map(Customer source);
  void copy(CustomerEntity source, @MappingTarget Customer target);

}
