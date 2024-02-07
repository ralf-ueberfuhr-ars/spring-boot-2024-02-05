package de.schulungen.spring.customers.boundary;

import de.schulungen.spring.customers.domain.Customer;
import de.schulungen.spring.customers.domain.CustomersService;
import de.schulungen.spring.customers.domain.NotFoundException;
import lombok.RequiredArgsConstructor;
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
  private final CustomerDTOMapper mapper;


  @GetMapping
  Collection<CustomerDTO> findAllCustomers() {
      return service
        .getAll()
        .map(mapper::map)
        .toList();
  }

  @GetMapping("/{uuid}")
  CustomerDTO findByUuid(@PathVariable UUID uuid) {
    return service
      .findByUuid(uuid)
      .map(mapper::map)
      .orElseThrow(NotFoundException::new);
  }

  // Anlegen: POST /customers + Body -> 201 + Body + Location-Header
  @PostMapping
  //@ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDto) {
    Customer customer = mapper.map(customerDto);
    service.create(customer);
    CustomerDTO body = mapper.map(customer);
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
      .body(body);
  }

  // Überschreiben: PUT /customers/{uuid} + Body ->
  @PutMapping("/{uuid}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void replace(@PathVariable UUID uuid, @RequestBody CustomerDTO customer) {
    customer.setUuid(uuid);
    service.replace(mapper.map(customer));
  }

  // Löschen
  @DeleteMapping("/{uuid}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void delete(@PathVariable UUID uuid) {
    service.delete(uuid);
  }

}
