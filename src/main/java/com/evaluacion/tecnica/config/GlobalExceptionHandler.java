package com.evaluacion.tecnica.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation failed for one or more fields");
        response.put("errorCount", fieldErrors.size());

        List<Map<String, String>> errors = fieldErrors.stream().map(error -> {
            Map<String, String> errorDetails = new HashMap<>();
            errorDetails.put("field", error.getField());
            errorDetails.put("rejectedValue",
                    error.getRejectedValue() != null ? error.getRejectedValue().toString() : "null");
            errorDetails.put("message", error.getDefaultMessage());
            return errorDetails;
        }).collect(Collectors.toList());

        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = AccountNumberValidator.class)
    public @interface ValidAccountNumber {
        String message() default "El número de cuenta debe tener exactamente 10 dígitos";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
}
