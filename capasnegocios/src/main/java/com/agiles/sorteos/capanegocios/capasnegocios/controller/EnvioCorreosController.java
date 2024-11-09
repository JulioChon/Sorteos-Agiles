package com.agiles.sorteos.capanegocios.capasnegocios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.IEnvioCorreoService;

@RestController
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/config")
public class EnvioCorreosController {

    @Autowired
    private IEnvioCorreoService correoService;

 @PostMapping("")
    public ResponseEntity<?> guardarConfiguracion(@RequestBody ConfiguracionEnvio configuracionEnvio) {
        correoService.guardarConfiguracionEnvio(configuracionEnvio);
        correoService.verificarDatosYProgramarEnvio();
        return ResponseEntity.ok("Configuración guardada");
    }
}
