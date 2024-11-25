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

import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;
import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.IBoletoService;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/boletos")
public class BoletoController {

    @Autowired
    private IBoletoService boletoService;

    @Autowired
    private IFachadaSorteos fachadaSorteos;




    @PostMapping("/config")
    public ResponseEntity<?> guardarConfiguracion(@RequestBody ConfiguracionEnvio configuracionEnvio) {
        fachadaSorteos.guardarConfiguracionEnvio(configuracionEnvio);

        return ResponseEntity.ok("Configuración guardada");
    }

    @GetMapping
    public ResponseEntity<?> obtenerBoletos() {
        return ResponseEntity.ok(boletoService.obtenerBoletos());
    }

    @GetMapping("/query")
    public ResponseEntity<?> obtenerBoletoPorId(@RequestParam(name="numeroBoleto") Integer numeroBoleto, @RequestParam(name="idSorteo" )Integer idSorteo) {
        return ResponseEntity.ok(boletoService.obtenerBoletoPorNumeroSorteo(numeroBoleto, idSorteo));
    }

    @GetMapping("/sorteo/{idSorteo}")
    public ResponseEntity<?> obtenerBoletosPorIdSorteo(@PathVariable Integer idSorteo) {
        List<Boleto> boletos = boletoService.obtenerBoletosPorIdSorteo(idSorteo);
        
        return ResponseEntity.ok(boletos);
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> obtenerBoletosPorIdCliente(@PathVariable Integer idCliente){
        return ResponseEntity.ok(boletoService.obtenerBoletosCliente(idCliente));
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

    @PutMapping("/libre/{id}")
    public ResponseEntity<?> cambiarEstadoLibre(@PathVariable Integer id) {
        return ResponseEntity.ok(boletoService.cambiarEstadoLibre(id));
    }
    @PutMapping("/vendido/{id}")
    public ResponseEntity<?> cambiarEstadoVendido(@PathVariable Integer id) {
        return ResponseEntity.ok(boletoService.cambiarEstadoVendido(id));
    }
    @PutMapping("/apartado/query")
    public ResponseEntity<?> cambiarEstadoApartado(@RequestParam(name ="idBoleto") Integer id, @RequestParam(name="correo") String correo) {
        return ResponseEntity.ok(boletoService.cambiarEstadoApartado(id, correo));
    }
    



}
