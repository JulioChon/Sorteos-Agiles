package com.agiles.sorteos.capanegocios.capasnegocios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;

@RestController
@RequestMapping("/sorteos")
public class SorteoController {

    @Autowired
    private IFachadaSorteos fachadaSorteos;

    @PostMapping
    public Sorteo crearSorteo(@RequestBody Sorteo sorteo) {
        return fachadaSorteos.guardarSorteo(sorteo);
    }

}
