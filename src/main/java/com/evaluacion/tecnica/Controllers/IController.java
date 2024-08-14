package com.evaluacion.tecnica.Controllers;

import org.springframework.http.ResponseEntity;

public interface IController<T> {

    ResponseEntity<?> save(T t);

    ResponseEntity<?> findById(Integer id);

    ResponseEntity<?> delete(Integer id);

    ResponseEntity<?> findAll();

    ResponseEntity<?> update(T t);

}
