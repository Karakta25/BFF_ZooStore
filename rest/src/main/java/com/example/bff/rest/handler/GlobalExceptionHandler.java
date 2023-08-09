package com.example.bff.rest.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.bff.core.exceptions.*;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler{

  @ExceptionHandler(value = ConstraintViolationException.class)

  public ResponseEntity<String> handlerConstraintViolationException(ConstraintViolationException ex)
  {
    StringBuilder sb = new StringBuilder();

    for(ConstraintViolation<?> cv:ex.getConstraintViolations())
    {sb.append(cv.getMessageTemplate()).append("\n");
    }

    return ResponseEntity.badRequest().body(sb.toString());
  }

  @ExceptionHandler(value = MissingServletRequestParameterException.class)
  public ResponseEntity<String> handlerMissingRequestParameterException(MissingServletRequestParameterException ex) {
    String errorMessage = ex.getParameterName() + " is required.";

    return ResponseEntity.badRequest().body(errorMessage);
  }

  @ExceptionHandler(value = InsufficientAvailabilityException.class)
  public ResponseEntity<String> handlerInsufficientAvailabilityException(InsufficientAvailabilityException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = InsufficientFundsException.class)
  public ResponseEntity<String> handlerInsufficientFundsException(InsufficientFundsException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = NoSuchItemException.class)
  public ResponseEntity<String> handlerNoSuchItemException(NoSuchItemException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = NoSuchTagException.class)
  public ResponseEntity<String> handlerNoSuchTagException(NoSuchTagException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = NoSuchUserException.class)
  public ResponseEntity<String> handlerNoSuchUserException(NoSuchUserException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = UserAlreadyExistsException.class)
  public ResponseEntity<String> handlerUserAlreadyExistsException(UserAlreadyExistsException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(value = WrongUserEmailException.class)
  public ResponseEntity<String> handlerWrongUserEmailException(WrongUserEmailException ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
  }

  @ExceptionHandler(value = WrongUserPasswordException.class)
  public ResponseEntity<String> handlerWrongUserPasswordException(WrongUserPasswordException ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
  }

  @ExceptionHandler(value = UsernameNotFoundException.class)
  public ResponseEntity<String> handlerUsernameNotFoundException(UsernameNotFoundException ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<String> handlerException(Exception ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }
  @ExceptionHandler(value = IOException.class)
  public ResponseEntity<String> handlerIOException(IOException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }


  @ExceptionHandler(value = JWTVerificationException.class)
  public ResponseEntity<String> handlerJWTVerificationException(JWTVerificationException ex) {
    return ResponseEntity.status(403).body(ex.getMessage());
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleValidationExceptions(
          MethodArgumentNotValidException ex) {
    StringBuilder errors = new StringBuilder();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String message = error.getDefaultMessage();
      errors.append(message).append("\n");
    });

    return ResponseEntity.badRequest().body(errors.toString());
  }
}
