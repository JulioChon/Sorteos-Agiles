package com.agiles.sorteos.capadatos.capadatos.DAOS;

import org.springframework.data.repository.CrudRepository;

import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Integer>  {
    Cliente findByCorreo(String correo);
}
