package com.evaluacion.tecnica.Models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The name cannot be null")
    @NotBlank(message = "plate is required")
    private String nombre;

    @NotNull(message = "The last name cannot be null")
    @NotBlank(message = "plate is required")
    private String apellidoPaterno;

    @NotNull(message = "The last name cannot be null")
    @NotBlank(message = "plate is required")
    private String apellidoMaterno;

    @NotNull(message = "The birthdate cannot be null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "The birthdate must be a past date")
    private LocalDate fechaNacimiento;

    @JoinColumn(name = "id_direccion")
    @OneToOne
    private Direccion direccion;

    @JoinColumn(name = "id_cuenta")
    @OneToOne
    private Cuenta cuenta;
}
