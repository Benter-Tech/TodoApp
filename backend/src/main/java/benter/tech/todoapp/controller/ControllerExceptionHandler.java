package benter.tech.todoapp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity serviceException(Exception exception) {
    log.warn(exception.getMessage());
    return ResponseEntity.internalServerError().body(exception.getMessage());
  }

}
