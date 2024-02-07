package de.schulungen.spring.customers.boundary;

import de.schulungen.spring.customers.domain.Customer;
import de.schulungen.spring.customers.domain.CustomerState;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOMapper {

  CustomerDTO map(Customer source) {
    CustomerDTO result = new CustomerDTO();
    result.setUuid(source.getUuid());
    result.setName(source.getName());
    result.setBirthdate(source.getBirthdate());
    result.setState(
      switch (source.getState()) {
        case ACTIVE -> "active";
        case LOCKED -> "locked";
        case DISABLED -> "disabled";
      }
    );
    return result;
  }

  Customer map(CustomerDTO source) {
    Customer result = new Customer();
    result.setUuid(source.getUuid());
    result.setName(source.getName());
    result.setBirthdate(source.getBirthdate());
    result.setState(
      switch (source.getState()) {
        case "active" -> CustomerState.ACTIVE;
        case "locked" -> CustomerState.LOCKED;
        case "disabled" -> CustomerState.DISABLED;
        default -> throw new IllegalArgumentException(); // TODO
      }
    );
    return result;
  }

}
