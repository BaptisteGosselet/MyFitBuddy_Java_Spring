package com.platine.myFitBuddy.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleSecurityException(Exception exception) {
    ProblemDetail errorDetail = null;

    exception.printStackTrace();

    if (exception instanceof BadCredentialsException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(401),
          exception.getMessage()
        );
      errorDetail.setProperty("description", "The username or password is incorrect");
      return errorDetail;
    }

    if (exception instanceof AccountStatusException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(403),
          exception.getMessage()
        );
      errorDetail.setProperty("description", "The account is locked");
    }

    if (exception instanceof AccessDeniedException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(403),
          exception.getMessage()
        );
      errorDetail.setProperty(
        "description",
        "You are not authorized to access this resource"
      );
    }

    if (exception instanceof SignatureException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(403),
          exception.getMessage()
        );
      errorDetail.setProperty("description", "The JWT signature is invalid");
    }

    if (exception instanceof ExpiredJwtException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(403),
          exception.getMessage()
        );
      errorDetail.setProperty("description", "The JWT token has expired");
    }

    // Gestion des exceptions de validation de formulaire
    if (exception instanceof MethodArgumentNotValidException) {
      Map<String, String> errors = new HashMap<>();
      ((MethodArgumentNotValidException) exception).getBindingResult()
        .getAllErrors()
        .forEach(
          error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
          }
        );

      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(400),
          "Validation failed"
        );
      errorDetail.setProperty("description", errors);
      return errorDetail;
    }

    // Gestion des violations d'unicité
    if (exception instanceof DataIntegrityViolationException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(409),
          "Unique constraint violation"
        );
      errorDetail.setProperty("description", exception.getMessage());
      return errorDetail;
    }

    if (exception instanceof ConstraintViolationException) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(409),
          "Unique constraint violation"
        );
      errorDetail.setProperty("description", exception.getMessage());
      return errorDetail;
    }

    if (errorDetail == null) {
      errorDetail =
        ProblemDetail.forStatusAndDetail(
          HttpStatusCode.valueOf(500),
          exception.getMessage()
        );
      errorDetail.setProperty("description", "Unknown internal server error.");
    }

    return errorDetail;
  }
}
