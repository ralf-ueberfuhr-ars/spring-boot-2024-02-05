package de.schulungen.spring.customers.persistence;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<CustomerEntity, UUID> {

  @Query("select c from Customer c where c.name = :name")
  List<CustomerEntity> meineQueryNachNamen(@Param("name") String name);

  List<CustomerEntity> findCustomerEntitiesByNameContainingIgnoreCaseAndBirthdateAfter(
    @NotNull @Size(min = 3) String name,
    @PastOrPresent LocalDate birthdate
  );

}
