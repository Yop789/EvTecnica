package com.evaluacion.tecnica.Services;

public interface IService<T> {

    T save(T t);

    T findById(Integer id);

    void delete(Integer id);

    Iterable<T> findAll();

    T update(T t);

}
