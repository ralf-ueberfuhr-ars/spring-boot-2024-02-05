package de.schulungen.spring.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiTests {

  @Autowired
  MockMvc mvc;

  @Test
  void shouldReturn200JsonOnGetCustomers() throws Exception {
    mvc
      .perform(
        get("/api/v1/customers")
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk());
  }


}
