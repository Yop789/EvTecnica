package com.evaluacion.tecnica.Models;

import java.math.BigDecimal;

import com.evaluacion.tecnica.config.GlobalExceptionHandler.ValidAccountNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The account number cannot be null")
    @ValidAccountNumber
    @Column(unique = true)
    private Long numeroCuenta;

    @NotNull(message = "The balance cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "The income must be greater than 0")
    private BigDecimal ingreso;
}
