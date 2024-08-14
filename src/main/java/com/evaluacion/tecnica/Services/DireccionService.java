package com.evaluacion.tecnica.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.tecnica.Daos.IDireccionDaos;
import com.evaluacion.tecnica.Models.Direccion;

@Service
public class DireccionService implements IService<Direccion> {

    @Autowired
    private IDireccionDaos direccionesDao;

    @Override
    public Direccion save(Direccion t) {

        return this.direccionesDao.save(t);
    }

    @Override
    public Direccion findById(Integer id) {

        return this.direccionesDao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {

        this.direccionesDao.deleteById(id);
    }

    @Override
    public Iterable<Direccion> findAll() {

        return this.direccionesDao.findAll();
    }

    @Override
    public Direccion update(Direccion t) {
        if (this.direccionesDao.findById(t.getId()).isPresent()) {
            return this.direccionesDao.save(t);

        }
        return null;
    }

}
