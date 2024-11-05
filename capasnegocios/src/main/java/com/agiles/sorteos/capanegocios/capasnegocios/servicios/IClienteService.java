package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import com.agiles.sorteos.capadatos.capadatos.DTOs.User;
import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;

public interface IClienteService {
    Cliente agregarCliente(Cliente cliente);

    User verificarCliente(String correo, String contrasenia);
}
