package com.agiles.sorteos.capanegocios.capasnegocios.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.ISorteoService;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.repository.query.Param;

@RestController
@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/sorteos")
public class SorteoController {

    @Autowired
    private ISorteoService sorteoService;

    @PostMapping("/{id}")
    public ResponseEntity<?> crearSorteo(@Valid @RequestBody Sorteo sorteo, BindingResult result, @PathVariable Integer id) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(sorteoService.guardarSorteo(sorteo, id));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerSorteoPorId(@PathVariable Integer id, @Param("tickets") boolean tickets) {
        Sorteo sorteos = sorteoService.obtenerSorteoPorId(id);
        if (!tickets) {
            sorteos.setBoletos(null);
        }
        return ResponseEntity.ok(sorteos);
    }

    @GetMapping("/admin/{idAdmin}")
    public ResponseEntity<?> obtenerSorteoPorIdAdmin(@PathVariable Integer idAdmin) {
        List<Sorteo> sorteos = sorteoService.obtenerSorteosPorIdAdmin(idAdmin);
        sorteos.forEach(sorteo -> sorteo.setBoletos(null));
        return ResponseEntity.ok(sorteos);
    }

    @GetMapping
    public ResponseEntity<?> obtenerSorteos(@Param("tickets") boolean tickets) {
        List<Sorteo> sorteos = sorteoService.obtenerSorteos();
        if (!tickets) {
            sorteos.forEach(sorteo -> sorteo.setBoletos(null));
        }
        return ResponseEntity.ok(sorteos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSorteo(@PathVariable Integer id) {
        sorteoService.eliminarSorteo(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El sorteo con id " + id + " ha sido eliminado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSorteo(@PathVariable Integer id, @Valid @RequestBody Sorteo sorteo,
            BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(sorteoService.actualizarSorteo(id, sorteo));
    }

    @GetMapping("/deudores/{idAdmin}")
    public ResponseEntity<?> obtenerSorteosDeudores(@PathVariable Integer idAdmin) {
        return ResponseEntity.ok(sorteoService.obtenerSorteosDeudores(idAdmin));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
