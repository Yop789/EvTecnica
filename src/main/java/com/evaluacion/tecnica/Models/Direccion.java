package com.evaluacion.tecnica.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "The street cannot be null")
    @Size(max = 6, min = 6, message = "The postal code must have 6 digits")
    private String codigoPostal;

    @NotNull(message = "The state cannot be null")
    private String estado;

}
