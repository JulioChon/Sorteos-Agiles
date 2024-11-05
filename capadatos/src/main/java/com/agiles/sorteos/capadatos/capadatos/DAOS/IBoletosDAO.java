package com.agiles.sorteos.capadatos.capadatos.DAOS;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;

public interface IBoletosDAO extends CrudRepository<Boleto, Integer> {
    @Query("SELECT b FROM Boleto b WHERE b.idSorteo.id = ?1")
    List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo);

    @Query("SELECT b FROM Boleto b WHERE b.idSorteo.id = ?2 AND b.numeroBoleto = ?1")
    Boleto obtenerBoletoSorteo(Integer numBoleto, Integer idSorteo);

    @Query("SELECT b FROM Boleto b WHERE b.idCliente.id = ?1")
    List<Boleto> obtenerBoletosPorIdCliente(Integer idCliente);

}
