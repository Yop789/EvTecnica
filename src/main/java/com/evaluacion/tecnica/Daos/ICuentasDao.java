package com.evaluacion.tecnica.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.tecnica.Models.Cuenta;

public interface ICuentasDao extends JpaRepository<Cuenta, Integer> {

}
