package com.agiles.sorteos.capadatos.capadatos.fachadas;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.DAOS.IAdminDAO;
import com.agiles.sorteos.capadatos.capadatos.DAOS.IBoletosDAO;
import com.agiles.sorteos.capadatos.capadatos.DAOS.ISorteosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Administrador;
import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class FachadaSorteos implements IFachadaSorteos {

    @Autowired
    private ISorteosDAO sorteosDAO;

    @Autowired
    private IBoletosDAO boletosDAO;

    @Autowired
    private IAdminDAO adminDAO;

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
    
    @Override
    @Transactional(readOnly = true)
    public List<Sorteo> findSorteosByAdministradorId(Integer idAdministrador) {
        return sorteosDAO.findSorteosByAdministradorId(idAdministrador);
    }
    

    @Override
    @Transactional
    public Boleto guardarBoleto(Boleto boleto) {
        return boletosDAO.save(boleto);
    }

    @Override
    @Transactional
    public Boleto cambiarEstado(Integer id, Boleto boleto) {
        if(boletosDAO.existsById(id)) {
            boleto.setNumeroBoleto(id);
            return boletosDAO.save(boleto);
        }
        return null;
    }

    @Override
    @Transactional
    public void eliminarBoleto(Integer id) {
        if(boletosDAO.existsById(id)) {
            boletosDAO.deleteById(id);
           
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boleto obtenerBoletoPorId(Integer id) {
        return boletosDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> obtenerBoletos() {
        Iterable<Boleto> iterable = boletosDAO.findAll();
        List<Boleto> lista = StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> obtenerBoletosPorIdSorteo(Integer idSorteo) {
        return boletosDAO.obtenerBoletosPorIdSorteo(idSorteo);
    }

    @Override
    public Administrador obteAdministradorPorId(Integer id) {
        return adminDAO.findById(id).orElse(null);
    }


}
