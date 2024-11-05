package com.agiles.sorteos.capanegocios.capasnegocios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
 import com.agiles.sorteos.capanegocios.capasnegocios.servicios.IClienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name="email") String email, @RequestParam(name="contrasenia") String contrasenia){
        return ResponseEntity.ok(clienteService.verificarCliente(email, contrasenia));
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Cliente cliente){
          return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.agregarCliente(cliente));
    }
    
}
