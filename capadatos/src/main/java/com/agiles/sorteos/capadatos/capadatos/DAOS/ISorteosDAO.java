package com.agiles.sorteos.capadatos.capadatos.DAOS;

import org.springframework.data.repository.CrudRepository;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

public interface ISorteosDAO extends CrudRepository<Sorteo, Integer> {

}
