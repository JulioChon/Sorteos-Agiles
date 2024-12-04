package com.agiles.sorteos.capanegocios.capasnegocios.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.DTOs.BoletoDTO;
import com.agiles.sorteos.capadatos.capadatos.DTOs.SorteoDTO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capanegocios.capasnegocios.Exception.NotFoundException;

@Service
public class SorteoService implements ISorteoService {
    @Autowired
    private IFachadaSorteos fachadaSorteos;

    @Autowired
    private IBoletoService boletoService;

    @Override
    public Sorteo guardarSorteo(Sorteo sorteo, Integer id) {
        sorteo.setIdAdministrador(fachadaSorteos.obteAdministradorPorId(id));
        Sorteo sorteoNuevo = fachadaSorteos.guardarSorteo(sorteo);
        boletoService.generarBoletos(sorteoNuevo.getRangoMax(), sorteoNuevo.getRangoMin(), sorteo.getPrecio(), sorteoNuevo.getId());
        return sorteoNuevo;
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
        return sorteos;
    }

    @Override
    public List<SorteoDTO> obtenerSorteosDeudores(Integer idAdmin) {
        List<Sorteo> sorteos = fachadaSorteos.findSorteosByAdministradorId(idAdmin);
    
        return sorteos.stream().map(sorteo -> {
            SorteoDTO sorteoDTO = new SorteoDTO(
                sorteo.getId(),
                sorteo.getNombre(),
                sorteo.getImagenSorteo(),
                sorteo.getRangoMax(),
                sorteo.getRangoMin(),
                sorteo.getFechaInicioVenta(),
                sorteo.getFechaFinVenta(),
                sorteo.getFechaSorteo(),
                sorteo.getEstado(),
                sorteo.getPrecio()
            );
    
            List<BoletoDTO> boletos = fachadaSorteos.obtenerBoletosApartadosPorSorteo(sorteo.getId()).stream()
                .map(boleto -> new BoletoDTO(
                    boleto.getId(),
                    boleto.getNumeroBoleto(),
                    boleto.getEstado(),
                    boleto.getFechaLimApart(),
                    boleto.getPrecio(),
                    boleto.getIdCliente()
                ))
                .collect(Collectors.toList());
    
            sorteoDTO.setBoletos(boletos);
    
            return sorteoDTO;
        }).collect(Collectors.toList());
    }
    

}
