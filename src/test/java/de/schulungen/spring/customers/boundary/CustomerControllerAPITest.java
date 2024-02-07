package de.schulungen.spring.customers.boundary;

import de.schulungen.spring.customers.domain.CustomersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Initialize only @Controller, @ControllerAdvice
// Auto-configure MockMvc
// (not: @Service & @Component or any database connections !)
@WebMvcTest
// Initialize components from boundary package too (e.g. the mapper!)
@ComponentScan(basePackageClasses = CustomersController.class)
public class CustomerControllerAPITest {

  @MockBean // create mock and put it into the application context for injection
  CustomersService service;
  @Autowired
  MockMvc mvc;

  @Test
  void shouldReturn404WhenCustomerNotExisting() throws Exception {
    // Arrange
    UUID uuid = UUID.randomUUID();
    when(service.findByUuid(uuid))
      .thenReturn(Optional.empty());
    // Act+Assert
    mvc.perform(
        get("/api/v1/customers/{id}", uuid)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNotFound());
    verify(service).findByUuid(uuid);
  }

}
