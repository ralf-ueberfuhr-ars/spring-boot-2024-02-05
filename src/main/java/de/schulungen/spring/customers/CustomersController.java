package de.schulungen.spring.customers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomersController {

  private final CustomersService service;

  @GetMapping
  Collection<Customer> findAllCustomers() {
      return service
        .getAll()
        .toList();
  }

  @GetMapping("/{uuid}")
  Customer findByUuid(@PathVariable UUID uuid) {
    return service
      .findByUuid(uuid)
      .orElseThrow(NotFoundException::new);
  }

  // Anlegen: POST /customers + Body -> 201 + Body + Location-Header
  @PostMapping
  //@ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<Customer> create(@RequestBody Customer customer) {
    service.create(customer);
    URI location = linkTo(
      methodOn(CustomersController.class).findByUuid(customer.getUuid())
    ).toUri();
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
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void replace(@PathVariable UUID uuid, @RequestBody Customer customer) {
    customer.setUuid(uuid);
    service.replace(customer);
  }

  // Löschen
  @DeleteMapping("/{uuid}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable UUID uuid) {
    service.delete(uuid);
  }

}
