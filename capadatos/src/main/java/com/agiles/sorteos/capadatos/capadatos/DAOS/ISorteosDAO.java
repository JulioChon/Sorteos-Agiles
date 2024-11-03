package com.agiles.sorteos.capadatos.capadatos.DAOS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

public interface ISorteosDAO extends CrudRepository<Sorteo, Integer> {
    @Query("SELECT s FROM Sorteo s WHERE s.idAdministrador.id = ?1")
    List<Sorteo> findSorteosByAdministradorId(Integer idAdministrador);

}
