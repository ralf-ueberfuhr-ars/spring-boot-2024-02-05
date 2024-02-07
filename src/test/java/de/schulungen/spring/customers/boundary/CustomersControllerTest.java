package de.schulungen.spring.customers.boundary;

import de.schulungen.spring.customers.domain.CustomersService;
import de.schulungen.spring.customers.domain.NotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomersControllerTest {

  /*
   * Testfall:
   *  - GIVEN Customer mit ID .... existiert nicht
   *    WENN controller.findByUuid()
   *    THEN NotFoundException
   */

  @Test
  void shouldThrowNotFoundExceptionWhenCustomerNotExisting() {
    // Arrange
    UUID uuid = UUID.randomUUID();
    CustomersService service = mock(CustomersService.class);
    CustomersController controller = new CustomersController(service);
    when(service.findByUuid(uuid))
      .thenReturn(Optional.empty());
    // Act + Assert
    assertThatThrownBy(() -> controller.findByUuid(uuid))
      .isInstanceOf(NotFoundException.class);
  }

}
