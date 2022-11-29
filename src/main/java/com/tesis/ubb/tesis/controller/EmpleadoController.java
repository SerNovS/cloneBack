package com.tesis.ubb.tesis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.ubb.tesis.models.Cargo;
import com.tesis.ubb.tesis.models.Empleado;
import com.tesis.ubb.tesis.service.EmpleadoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TRABAJADOR')")
    @GetMapping("/empleado")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Empleado> index() {
        return empleadoService.findAll();
    }

    @GetMapping(value = "empleado/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable Long id) {

        Empleado empleado = null;
        Map<String, Object> response = new HashMap<>();
        try {
            empleado = empleadoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (empleado == null) {
            response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);

    }

    @PostMapping("/empleado")
    public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result) {

        Empleado empleadoNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }
        try {
            empleadoNew = empleadoService.save(empleado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al registrar al nuevo empleado");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("", "Empleado creado con éxito");
        response.put("empleado", empleadoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("empleado/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Empleado empleado, BindingResult result,
            @PathVariable Long id) {

        Empleado empleadoActual = empleadoService.findById(id);
        Empleado empleadoUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        if (empleadoActual == null) {
            response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setApellido(empleado.getApellido());
            empleadoActual.setTelefono(empleado.getTelefono());
            empleadoActual.setCargo(empleado.getCargo());

            empleadoUpdated = empleadoService.save(empleadoActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el empleado");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("", "El empleado ha sido actualizado con éxito");
        response.put("empleado", empleadoUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("empleado/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {

            Empleado producto = empleadoService.findById(id);
            empleadoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar al empleado");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Empleado eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/empleado/cargo")
    public List<Cargo> listarTipoProductos() {
        return empleadoService.findAllCargos();
    }
}
