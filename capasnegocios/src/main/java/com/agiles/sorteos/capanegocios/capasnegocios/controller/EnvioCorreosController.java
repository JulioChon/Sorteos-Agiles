package com.agiles.sorteos.capanegocios.capasnegocios.controller;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.agiles.sorteos.capadatos.capadatos.dominio.ConfiguracionEnvio;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.IEnvioCorreoService;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/config")
public class EnvioCorreosController {

    @Autowired
    private IEnvioCorreoService correoService;
    
    @Autowired
    private IFachadaSorteos fachadaSorteos;

    @PostMapping("")
    public ResponseEntity<?> guardarConfiguracion(@RequestBody ConfiguracionEnvio configuracionEnvio) {
        correoService.guardarConfiguracionEnvio(configuracionEnvio);
        correoService.verificarDatosYProgramarEnvio();
        return ResponseEntity.ok("Configuración guardada");
    }
    
    @PostMapping("correo/enviar")
    public ResponseEntity<?> enviarCorreoDeudor(@RequestBody EnviarCorreoDTO enviarDTO) {
        try {
            if (enviarDTO.getCorreo() == null || enviarDTO.getCorreo().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El campo 'correo' es obligatorio.");
            }

            if (enviarDTO.getIdBoleto() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El campo 'idBoleto' es obligatorio.");
            }

            Boleto boleto = fachadaSorteos.obtenerBoletoPorId(enviarDTO.getIdBoleto());
            if (boleto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró un boleto con el ID proporcionado.");
            }

            // Enviar correo
            correoService.enviarCorreo(enviarDTO.getCorreo(), boleto);

            return ResponseEntity.ok("Correo enviado exitosamente.");
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al enviar el correo: " + e.getMessage());
        }
    }
}
