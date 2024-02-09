package de.schulungen.spring.customers.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Customer {

  private UUID uuid;
  @NotNull
  @Size(min = 3)
  private String name;
  @PastOrPresent
  private LocalDate birthdate;
  private CustomerState state;

}
