package com.evaluacion.tecnica.config;

import com.evaluacion.tecnica.config.GlobalExceptionHandler.ValidAccountNumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<ValidAccountNumber, Long> {

    @Override
    public boolean isValid(Long numeroCuenta, ConstraintValidatorContext context) {
        if (numeroCuenta == null) {
            return false;
        }
        // Verifica si el número tiene exactamente 10 dígitos
        return String.valueOf(numeroCuenta).length() == 10;
    }
}
