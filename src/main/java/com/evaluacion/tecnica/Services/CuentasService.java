package com.evaluacion.tecnica.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evaluacion.tecnica.Daos.ICuentasDao;
import com.evaluacion.tecnica.Models.Cuenta;

@Component
public class CuentasService implements IService<Cuenta> {

    @Autowired
    private ICuentasDao cuentasDao;

    @Override
    public Cuenta save(Cuenta t) {

        return this.cuentasDao.save(t);
    }

    @Override
    public Cuenta findById(Integer id) {
        return this.cuentasDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada con ID: " + id));
    }

    @Override
    public void delete(Integer id) {
        this.cuentasDao.deleteById(id);
    }

    @Override
    public Iterable<Cuenta> findAll() {
        return this.cuentasDao.findAll();
    }

    @Override
    public Cuenta update(Cuenta t) {
        if (this.cuentasDao.existsById(t.getId())) {
            return this.cuentasDao.save(t);
        } else {
            throw new RuntimeException("No se puede actualizar. Cuenta no encontrada con ID: " + t.getId());
        }
    }

}
