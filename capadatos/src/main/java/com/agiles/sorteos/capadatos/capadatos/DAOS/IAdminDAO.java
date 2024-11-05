package com.agiles.sorteos.capadatos.capadatos.DAOS;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.agiles.sorteos.capadatos.capadatos.dominio.Administrador;

public interface IAdminDAO extends CrudRepository<Administrador, Integer> {
 
    
        @Query("SELECT a FROM Administrador a WHERE a.email = ?1 AND a.contrasena = ?2")
        Administrador findByEmailAndContrasena( String email, String contrasena);

    
}
