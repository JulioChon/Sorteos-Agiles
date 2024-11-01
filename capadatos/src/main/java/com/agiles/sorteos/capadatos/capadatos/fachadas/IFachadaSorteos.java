package com.agiles.sorteos.capadatos.capadatos.fachadas;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

@Service
public interface IFachadaSorteos {

    Sorteo guardarSorteo(Sorteo sorteo);

    Sorteo actualizarSorteo(Integer id, Sorteo sorteo);

    void eliminarSorteo(Integer id);

    Sorteo obtenerSorteoPorId(Integer id);

    List<Sorteo> obtenerSorteos();
    
}
