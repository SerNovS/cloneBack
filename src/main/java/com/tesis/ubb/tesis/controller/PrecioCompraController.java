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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tesis.ubb.tesis.models.PrecioCompra;
import com.tesis.ubb.tesis.models.Producto;
import com.tesis.ubb.tesis.service.PrecioCompraService;
import com.tesis.ubb.tesis.service.ProductoService;



@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PrecioCompraController {

    @Autowired
    PrecioCompraService tipoProductoService;

    @Autowired
    ProductoService productoService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TRABAJADOR')")
    @PostMapping("/PrecioCompra")

    public ResponseEntity<?> create(@Valid @RequestBody PrecioCompra precioCompra, BindingResult result) {

        PrecioCompra precioCompraNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }
        try {
            precioCompraNew = tipoProductoService.save(precioCompra);
            
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al registrar un nuevo precio de compra de productos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("", "El nuevo precio de compra de productos ha sido agregado con éxito");
        response.put("Registro: ", precioCompraNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TRABAJADOR')")
    @GetMapping("/precioCompra")
    @ResponseStatus(code = HttpStatus.OK)
    public List<PrecioCompra> index() {
        return tipoProductoService.findAll();
    }



    // save
    // eliminar duda
    // editar   duda
    // listar


    
}
