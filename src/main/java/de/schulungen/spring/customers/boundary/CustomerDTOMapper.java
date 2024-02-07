package de.schulungen.spring.customers.boundary;

import de.schulungen.spring.customers.domain.Customer;
import de.schulungen.spring.customers.domain.CustomerState;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDTOMapper {

  CustomerDTO map(Customer source);
  Customer map(CustomerDTO source);

  default String mapState(CustomerState state) {
    return switch (state) {
      case ACTIVE -> "active";
      case LOCKED -> "locked";
      case DISABLED -> "disabled";
    };
  }

  default CustomerState mapState(String state) {
    return switch (state) {
      case "active" -> CustomerState.ACTIVE;
      case "locked" -> CustomerState.LOCKED;
      case "disabled" -> CustomerState.DISABLED;
      default -> throw new IllegalArgumentException(); // TODO
    };
  }

}
