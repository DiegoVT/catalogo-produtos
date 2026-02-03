package br.com.diego.catalogo_produtos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", Instant.now().toString());
    body.put("status", 404);
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }
}
