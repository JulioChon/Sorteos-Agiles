package com.agiles.sorteos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agiles.sorteos.capadatos.capadatos.DAOS.IBoletosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;
import com.agiles.sorteos.capadatos.capadatos.fachadas.FachadaSorteos;

@ExtendWith(MockitoExtension.class)
public class HU10Test {

    @Mock
    private IBoletosDAO boletosDAO;

    @InjectMocks
    private FachadaSorteos fachadaSorteos;

    @Test
    public void testCambiarEstado_Exito() {
        Integer idBoleto = 1;
        Boleto boletoExistente = new Boleto();
        boletoExistente.setNumeroBoleto(idBoleto);
        boletoExistente.setEstado(BOLETOESTADO.APARTADO);

        Boleto boletoActualizado = new Boleto();
        boletoActualizado.setNumeroBoleto(idBoleto);
        boletoActualizado.setEstado(BOLETOESTADO.VENDIDO);

        when(boletosDAO.existsById(idBoleto)).thenReturn(true);
        when(boletosDAO.save(any(Boleto.class))).thenReturn(boletoActualizado);

        Boleto resultado = fachadaSorteos.cambiarEstado(idBoleto, boletoActualizado);

        assertNotNull(resultado);
        assertEquals(BOLETOESTADO.VENDIDO, resultado.getEstado());
        verify(boletosDAO, times(1)).existsById(idBoleto);
        verify(boletosDAO, times(1)).save(boletoActualizado);
    }

    @Test
    public void testCambiarEstado_BoletoNoExiste() {
        Integer idBoleto = 2;
        Boleto boletoActualizado = new Boleto();
        boletoActualizado.setNumeroBoleto(idBoleto);
        boletoActualizado.setEstado(BOLETOESTADO.VENDIDO);

        when(boletosDAO.existsById(idBoleto)).thenReturn(false);

        Boleto resultado = fachadaSorteos.cambiarEstado(idBoleto, boletoActualizado);

        assertNull(resultado);
        verify(boletosDAO, times(1)).existsById(idBoleto);
        verify(boletosDAO, never()).save(any(Boleto.class));
    }
}

