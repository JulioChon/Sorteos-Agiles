package com.agiles.sorteos.capadatos.capadatos.fachadas;

import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

@Service
public interface IFachadaSorteos {

    Sorteo guardarSorteo(Sorteo sorteo);
    
}
