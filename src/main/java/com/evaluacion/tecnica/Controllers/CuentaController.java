package com.evaluacion.tecnica.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluacion.tecnica.Models.Cuenta;
import com.evaluacion.tecnica.Services.IService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cuentas")

public class CuentaController implements IController<Cuenta> {

    @Autowired
    private IService<Cuenta> cuentasService;

    @PostMapping
    @Override
    public ResponseEntity<?> save(@Valid @RequestBody Cuenta cuenta) {
        try {
            return ResponseEntity.ok(cuentasService.save(cuenta));
        } catch (DataIntegrityViolationException e) {
            // Analizar el mensaje de la excepción para proporcionar detalles más
            // específicos
            String message = extractUniqueConstraintViolationMessage(e.getMessage());
            Map<String, String> errors = new HashMap<>();
            errors.put("numeroCuenta",
                    message != null ? message : "Error al guardar la cuenta. Verifique los datos ingresados.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    private String extractUniqueConstraintViolationMessage(String exceptionMessage) {
        if (exceptionMessage.contains("Unique index or primary key violation")) {
            // Proporciona un mensaje específico si es una violación de clave única
            return "El número de cuenta ya está en uso. Verifique que el número de cuenta sea único.";
        }
        // Puedes agregar más patrones aquí para otras violaciones específicas
        return null;
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<?> findById(@PathVariable(name = "id") Integer id) {
        try {
            return ResponseEntity.ok(this.cuentasService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        try {
            this.cuentasService.delete(id);
            return ResponseEntity.ok().body("Se ha eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @Override
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(this.cuentasService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Override
    public ResponseEntity<?> update(@Valid @RequestBody Cuenta t) {
        try {
            return ResponseEntity.ok(this.cuentasService.update(t));
        } catch (DataIntegrityViolationException e) {
            // Analizar el mensaje de la excepción para proporcionar detalles más
            // específicos
            String message = extractUniqueConstraintViolationMessage(e.getMessage());
            Map<String, String> errors = new HashMap<>();
            errors.put("numeroCuenta",
                    message != null ? message : "Error al guardar la cuenta. Verifique los datos ingresados.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : fieldErrors) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
