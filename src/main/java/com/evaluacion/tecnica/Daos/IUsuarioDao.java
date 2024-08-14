package com.evaluacion.tecnica.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaluacion.tecnica.Models.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer> {

}
