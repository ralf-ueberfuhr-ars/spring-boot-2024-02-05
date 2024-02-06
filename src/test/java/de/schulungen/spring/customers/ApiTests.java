package de.schulungen.spring.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
      .andExpect(status().isOk())
      // .andExpect(header().exists(HttpHeaders.CONTENT_TYPE))
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      // Größe des Arrays unbekannt
      //.andExpect(jsonPath("$.size()").value(1));
      .andExpect(jsonPath("$.size()").exists());

  }

  @Test
  void shouldCreateCustomer() throws Exception {
    String location = mvc
      .perform(
        post("/api/v1/customers")
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON)
          .content("""
            {
              "name": "Julia",
              "birthdate": "1996-03-04",
              "state": "active"
            }
                      """
          )
      )
      .andExpect(status().isCreated())
      .andExpect(header().exists(HttpHeaders.LOCATION))
      .andReturn()
      .getResponse()
      .getHeader(HttpHeaders.LOCATION);
    // jetzt muss GET /customers/{id} auch klappen
      assert location != null;
      mvc.perform(
        get(location)
          .accept(MediaType.APPLICATION_JSON)
      )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Julia"))
        .andExpect(jsonPath("$.uuid").exists());
  }


}
