package de.schulungen.spring.customers.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CustomerDTO {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID uuid;
  @NotNull
  @Size(min = 3)
  private String name;
  @PastOrPresent
  private LocalDate birthdate;
  private String state;

}
