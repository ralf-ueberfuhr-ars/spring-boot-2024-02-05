package de.schulungen.spring.customers.boundary;

import de.schulungen.spring.customers.domain.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  void handleNotFoundException() {
    // do nothing
    // NotFoundException als Parameter
    // Objekte als Rückgabewert -> JSON-Body
    // u.a. ProblemDetail (RFC-9457)
    // u.a. ResponseEntity
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  void handleValidationException() {
    // do nthing
  }


}
