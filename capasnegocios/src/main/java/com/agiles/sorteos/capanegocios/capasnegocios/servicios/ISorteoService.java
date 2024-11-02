package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.util.List;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

public interface ISorteoService {

    Sorteo guardarSorteo(Sorteo sorteo);

    Sorteo actualizarSorteo(Integer id, Sorteo sorteo);

    void eliminarSorteo(Integer id);

    Sorteo obtenerSorteoPorId(Integer id);

    List<Sorteo> obtenerSorteos();
}
