package com.evaluacion.tecnica.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.tecnica.Models.Direccion;
import com.evaluacion.tecnica.Services.IService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionesController implements IController<Direccion> {

    @Autowired
    private IService<Direccion> direccionesService;

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody Direccion t) {
        try {
            return ResponseEntity.ok(this.direccionesService.save(t));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        try {
            return ResponseEntity.ok(this.direccionesService.findById(id));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        try {
            this.direccionesService.delete(id);
            // mandar mensaje que se a eliminado con exito

            return ResponseEntity.ok().body("Se ha eliminado con exito");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping
    @Override
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(this.direccionesService.findAll());
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Override
    public ResponseEntity<?> update(@RequestBody Direccion t) {
        try {
            return ResponseEntity.ok(this.direccionesService.update(t));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
