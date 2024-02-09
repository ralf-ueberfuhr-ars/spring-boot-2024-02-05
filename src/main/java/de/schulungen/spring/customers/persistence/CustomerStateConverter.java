package de.schulungen.spring.customers.persistence;

import de.schulungen.spring.customers.domain.CustomerState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.PersistenceException;

@Converter(autoApply = true)
public class CustomerStateConverter implements AttributeConverter<CustomerState, String> {
  @Override
  public String convertToDatabaseColumn(CustomerState state) {
    return switch (state) {
      case ACTIVE -> "active";
      case LOCKED -> "locked";
      case DISABLED -> "disabled";
    };
  }

  @Override
  public CustomerState convertToEntityAttribute(String state) {
    return switch (state) {
      case "active" -> CustomerState.ACTIVE;
      case "locked" -> CustomerState.LOCKED;
      case "disabled" -> CustomerState.DISABLED;
      default -> throw new PersistenceException();
    };
  }
}
