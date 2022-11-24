package com.tesis.ubb.tesis.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tesis.ubb.tesis.models.Producto;
import com.tesis.ubb.tesis.models.TipoProducto;
import com.tesis.ubb.tesis.models.UnidadMedida;
import com.tesis.ubb.tesis.service.ProductoService;
import com.tesis.ubb.tesis.service.TipoProductoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    private final Logger log = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping("/producto")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Producto> index() {
        return productoService.findAll();
    }

    @GetMapping("/producto/page/{page}")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<Producto> index(@PathVariable Integer page) {

        PageRequest pageable = PageRequest.of(page, 5);
        return productoService.findAll(pageable);
    }

    @GetMapping(value = "producto/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<?> show(@PathVariable Long id) {

        Producto producto = null;
        Map<String, Object> response = new HashMap<>();
        try {
            producto = productoService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (producto == null) {
            response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);

    }

    @PostMapping("/producto")
    public ResponseEntity<?> create(@Valid @RequestBody Producto producto, BindingResult result) {

        Producto productoNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }
        try {
            productoNew = productoService.save(producto);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al registrar al nuevo producto");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("", "El producto ha sido creado con éxito");
        response.put("producto", productoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("producto/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result,
            @PathVariable Long id) {

        Producto productoActual = productoService.findById(id);
        Producto productoUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream().map(err -> {
                return "El campo '" + err.getField() + "' " + err.getDefaultMessage();
            }).collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        if (productoActual == null) {
            response.put("mensaje", "El producto ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            productoActual.setNombreProducto(producto.getNombreProducto());
            productoActual.setImagen(producto.getImagen());
            productoActual.setStock(producto.getStock());
            productoActual.setUnidadMedida(producto.getUnidadMedida());
            productoActual.setUltimoPrecioCompra(producto.getUltimoPrecioCompra());
            productoActual.setUltimoPrecioVenta(producto.getUltimoPrecioVenta());
            productoActual.setVisibilidad(producto.getVisibilidad());
            productoActual.setTipoProducto(producto.getTipoProducto());

            productoUpdated = productoService.save(productoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el producto");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("", "El producto ha sido actualizado con éxito");
        response.put("producto", productoUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("producto/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {

            Producto producto = productoService.findById(id);
            String nombreFotoAnterior = producto.getImagen();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterio = rutaFotoAnterior.toFile();
                if (archivoFotoAnterio.exists() && archivoFotoAnterio.canRead()) {
                    archivoFotoAnterio.delete();
                }
            }
            productoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar al producto");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Producto eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @GetMapping("/producto/tipo")
    public List<TipoProducto> listarTipoProductos() {
        return productoService.findAllTipos();
    }

    @GetMapping("/producto/unidad")
    public List<UnidadMedida> listarUnidadMedida() {
        return productoService.findAllUnidades();
    }

    @PostMapping("producto/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Producto producto = productoService.findById(id);

        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            log.info(rutaArchivo.toString());
            try {
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e) {

                response.put("mensaje", "Error al subir la imagen: " + nombreArchivo);
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = producto.getImagen();
            if (nombreFotoAnterior != null && nombreFotoAnterior.length() > 0) {
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterio = rutaFotoAnterior.toFile();
                if (archivoFotoAnterio.exists() && archivoFotoAnterio.canRead()) {
                    archivoFotoAnterio.delete();
                }
            }
            producto.setImagen(nombreArchivo);
            productoService.save(producto);

            response.put("producto", producto);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;
        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error, no se pudo cargar la imagen: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}
