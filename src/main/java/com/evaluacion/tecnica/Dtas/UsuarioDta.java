package com.evaluacion.tecnica.Dtas;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.evaluacion.tecnica.Models.Cuenta;
import com.evaluacion.tecnica.Models.Direccion;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class UsuarioDta {

    private Integer id;

    @NotNull(message = "The name cannot be null")
    @NotBlank
    private String nombre;

    @NotNull(message = "The last name cannot be null")
    @NotBlank
    private String apellidoPaterno;

    @NotNull(message = "The last name cannot be null")
    @NotBlank
    private String apellidoMaterno;

    @NotNull(message = "The birthdate cannot be null")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Past(message = "The birthdate must be a past date")
    private LocalDate fechaNacimiento;

    @NotNull(message = "The address cannot be null")

    private Integer id_direccion;

    @NotNull(message = "The account cannot be null")

    private Integer id_cuenta;

}
