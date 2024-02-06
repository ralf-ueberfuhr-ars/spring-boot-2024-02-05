package de.schulungen.spring.customers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

  // replace later
  private final Map<UUID, Customer> customers = new HashMap<>();

  {
    Customer c1 = new Customer();
    c1.setUuid(UUID.randomUUID());
    c1.setName("Tom");
    c1.setBirthdate(LocalDate.of(2000, Month.FEBRUARY, 14));
    c1.setState("active");
    customers.put(c1.getUuid(), c1);
    Customer c2 = new Customer();
    c2.setUuid(UUID.randomUUID());
    c2.setName("Julia");
    c2.setBirthdate(LocalDate.of(2005, Month.OCTOBER, 1));
    c2.setState("active");
    customers.put(c2.getUuid(), c2);
  }

  @GetMapping
  Collection<Customer> findAllCustomers() {
    return customers.values();
  }

  @GetMapping("/{uuid}")
  ResponseEntity<Customer> findByUuid(@PathVariable UUID uuid) {
    if(!customers.containsKey(uuid)) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(customers.get(uuid));
  }

  // Anlegen: POST /customers + Body -> 201 + Body + Location-Header
  @PostMapping
  //@ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<Customer> create(@RequestBody Customer customer) {
    UUID uuid = UUID.randomUUID();
    customer.setUuid(uuid);
    customers.put(uuid, customer);
    URI location = linkTo(methodOn(CustomersController.class).findByUuid(uuid)).toUri();
/*
      ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .build(customer.getUuid());
*/
    return ResponseEntity
      .created(location)
      .body(customer);
  }

  // Überschreiben: PUT /customers/{uuid} + Body ->
  @PutMapping("/{uuid}")
  //@ResponseStatus(HttpStatus.NO_CONTENT)
  ResponseEntity<Void> replace(@PathVariable UUID uuid, @RequestBody Customer customer) {
    if(!customers.containsKey(uuid)) {
      return ResponseEntity.notFound().build();
    }
    customer.setUuid(uuid);
    customers.put(uuid, customer);
    return ResponseEntity.noContent().build();
  }

  // Löschen


}
