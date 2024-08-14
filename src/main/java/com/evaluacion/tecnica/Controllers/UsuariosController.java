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

import com.evaluacion.tecnica.Dtas.UsuarioDta;
import com.evaluacion.tecnica.Services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuariosService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UsuarioDta t) {

        try {
            return ResponseEntity.ok(this.usuariosService.save(t));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        try {

            return ResponseEntity.ok(this.usuariosService.findById(id));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        try {
            this.usuariosService.delete(id);
            return ResponseEntity.ok().body("Se ha eliminado con exito");
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {

            return ResponseEntity.ok(this.usuariosService.findAll());
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioDta t) {

        try {
            return ResponseEntity.ok(this.usuariosService.update(t));
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
