package com.agiles.sorteos.capadatos.capadatos.fachadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiles.sorteos.capadatos.capadatos.DAOS.ISorteosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;

import jakarta.transaction.Transactional;

@Service
public class FachadaSorteos implements IFachadaSorteos {


    @Autowired
    private ISorteosDAO sorteosDAO;

    @Override
    @Transactional
    public Sorteo guardarSorteo(Sorteo sorteo) {
       return sorteosDAO.save(sorteo);
    }

}
