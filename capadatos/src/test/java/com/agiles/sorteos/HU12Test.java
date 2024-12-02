package com.agiles.sorteos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

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
public class HU12Test {

    @Mock
    private IBoletosDAO boletosDAO;

    @InjectMocks
    private FachadaSorteos fachadaSorteos;

    @Test
    public void testObtenerBoletosApartados_Exito() {
        Boleto boleto1 = new Boleto();
        boleto1.setNumeroBoleto(1);
        boleto1.setEstado(BOLETOESTADO.APARTADO);

        Boleto boleto2 = new Boleto();
        boleto2.setNumeroBoleto(2);
        boleto2.setEstado(BOLETOESTADO.APARTADO);

        List<Boleto> boletosApartados = Arrays.asList(boleto1, boleto2);

        when(boletosDAO.findByEstado(BOLETOESTADO.APARTADO)).thenReturn(boletosApartados);

        List<Boleto> resultado = fachadaSorteos.obtenerBoletosApartados();

        assertEquals(2, resultado.size());
        assertEquals(1, resultado.get(0).getNumeroBoleto());
        assertEquals(2, resultado.get(1).getNumeroBoleto());
        verify(boletosDAO, times(1)).findByEstado(BOLETOESTADO.APARTADO);
    }

    @Test
    public void testObtenerBoletosApartados_SinBoletos() {
        when(boletosDAO.findByEstado(BOLETOESTADO.APARTADO)).thenReturn(Arrays.asList());

        List<Boleto> resultado = fachadaSorteos.obtenerBoletosApartados();

        assertEquals(0, resultado.size());
        verify(boletosDAO, times(1)).findByEstado(BOLETOESTADO.APARTADO);
    }
}

