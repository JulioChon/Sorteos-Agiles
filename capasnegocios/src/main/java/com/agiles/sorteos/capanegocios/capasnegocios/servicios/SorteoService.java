package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capanegocios.capasnegocios.Exception.NotFoundException;

@Service
public class SorteoService implements ISorteoService {
    @Autowired
    private IFachadaSorteos fachadaSorteos;

    @Override
    public Sorteo guardarSorteo(Sorteo sorteo, Integer id) {
        sorteo.setIdAdministrador(fachadaSorteos.obteAdministradorPorId(id));
       return fachadaSorteos.guardarSorteo(sorteo);
    }

    @Override
    public Sorteo actualizarSorteo(Integer id, Sorteo sorteo) {
       Sorteo sorteoViejo = fachadaSorteos.obtenerSorteoPorId(id);
            if (sorteoViejo==null) {
                throw new NotFoundException("sorteo con ID " + id );
            } else {
                sorteo.setId(id);
                sorteo.setIdAdministrador(sorteoViejo.getIdAdministrador());
                return fachadaSorteos.guardarSorteo(sorteo);
            }
    }

    @Override
    public void eliminarSorteo(Integer id) {
        Sorteo sorteoElim = this.obtenerSorteoPorId(id);

        if (sorteoElim == null) {
            throw new NotFoundException("sorteo con ID " + id );
        }
        fachadaSorteos.eliminarSorteo(id);

    }

    @Override
    public Sorteo obtenerSorteoPorId(Integer id) {
        Sorteo sorteo =  fachadaSorteos.obtenerSorteoPorId(id);
        if (sorteo==null) {
            throw new NotFoundException("sorteo con ID " + id );
        }
        return sorteo;
    }

    @Override
    public List<Sorteo> obtenerSorteos() {

        List<Sorteo> sorteos = fachadaSorteos.obtenerSorteos();

        if (sorteos.isEmpty()) {
            throw new NotFoundException("No hay sorteos");
        }
        return sorteos;
    }

    @Override
    public List<Sorteo> obtenerSorteosPorIdAdmin(Integer idAdmin) {
       List <Sorteo> sorteos = fachadaSorteos.findSorteosByAdministradorId(idAdmin);
        if (sorteos.isEmpty()) {
            throw new NotFoundException("No hay sorteos para el administrador con ID " + idAdmin );
        }
        return sorteos;
    }

}
