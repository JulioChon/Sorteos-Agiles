package com.agiles.sorteos.capanegocios.capasnegocios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.IBoletoService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/boletos")
public class BoletoController {

    @Autowired
    private IBoletoService boletoService;



    @GetMapping
    public ResponseEntity<?> obtenerBoletos() {
        return ResponseEntity.ok(boletoService.obtenerBoletos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerBoletoPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(boletoService.obtenerBoletoPorId(id));
    }

    @GetMapping("/sorteo/{idSorteo}")
    public ResponseEntity<?> obtenerBoletosPorIdSorteo(@PathVariable Integer idSorteo) {
        return ResponseEntity.ok(boletoService.obtenerBoletosPorIdSorteo(idSorteo));
    }

    @PostMapping("/query")
    public ResponseEntity<?> agregarSorteos(@RequestParam(name = "rangoMaximo") Long rangoMaximo,
     @RequestParam(name ="rangoMin") Long rangoMin,
     @RequestParam(name="precio") float precio,
     @RequestParam(name="idSorteo") Integer idSorteo){
        boletoService.generarBoletos(rangoMaximo, rangoMin, precio, idSorteo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Boletos generados");
    } 
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBoleto(@PathVariable Integer id) {
        boletoService.eliminarBoleto(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("El Boleto con id " + id + " ha sido eliminado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @Valid @RequestBody Boleto boleto) {
        return ResponseEntity.ok(boletoService.cambiarEstado(id,boleto));
    }

}
