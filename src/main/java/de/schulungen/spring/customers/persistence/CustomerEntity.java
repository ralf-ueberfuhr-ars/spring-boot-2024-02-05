package de.schulungen.spring.customers.persistence;

import de.schulungen.spring.customers.domain.CustomerState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Customer") // JPQL
@Table(name = "CUSTOMERS") // DB Table
@Getter
@Setter
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID uuid;
  @NotNull
  @Size(min = 3)
  @Column(columnDefinition = "varchar(100)")
  private String name;
  @PastOrPresent
  @Column(name = "DAY_OF_BIRTH")
  private LocalDate birthdate;
  private CustomerState state;

}
