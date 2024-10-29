package com.agiles.sorteos.capadatos.capadatos.fachadas;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.DAOS.ISorteosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class FachadaSorteos implements IFachadaSorteos {

    @Autowired
    private ISorteosDAO sorteosDAO;

    @Override
    @Transactional
    public Sorteo guardarSorteo(Sorteo sorteo) {
        return sorteosDAO.save(sorteo);
    }

    @Override
    @Transactional
    public Sorteo actualizarSorteo(Integer id, Sorteo sorteo) {
        if(sorteosDAO.existsById(id)) {
            sorteo.setId(id);
            return sorteosDAO.save(sorteo);
        }
        return null;
        
    }

    @Override
    @Transactional
    public void eliminarSorteo(Integer id) {
        if(sorteosDAO.existsById(id)) {
            sorteosDAO.deleteById(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Sorteo obtenerSorteoPorId(Integer id) {

        return sorteosDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sorteo> obtenerSorteos() {
        Iterable<Sorteo> iterable = sorteosDAO.findAll();
        List<Sorteo> lista = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return lista;
    }

}
