package com.agiles.sorteos.capadatos.capadatos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.DAOS.IBoletosDAO;
import com.agiles.sorteos.capadatos.capadatos.fachadas.FachadaSorteos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class IBoletosDAOTest {

    @Mock
    private IBoletosDAO boletosDAO;

    @InjectMocks
    private FachadaSorteos fachadaSorteos;

    private Boleto boleto1;
    private Boleto boleto2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        boleto1 = new Boleto();
        boleto1.setId(1);
        boleto1.setNumeroBoleto(101);

        boleto2 = new Boleto();
        boleto2.setId(2);
        boleto2.setNumeroBoleto(102);
    }

    @Test
    public void testObtenerBoletosPorIdSorteo() {
        when(boletosDAO.obtenerBoletosPorIdSorteo(1)).thenReturn(Arrays.asList(boleto1, boleto2));

        List<Boleto> boletos = fachadaSorteos.obtenerBoletosPorIdSorteo(1);
        assertEquals(2, boletos.size());
        verify(boletosDAO, times(1)).obtenerBoletosPorIdSorteo(1);
    }

    @Test
    public void testObtenerBoletoSorteo() {
        when(boletosDAO.obtenerBoletoSorteo(101, 1)).thenReturn(boleto1);

        Boleto boleto = fachadaSorteos.obtenerBoletoSorteo(101, 1);
        assertNotNull(boleto);
        assertEquals(101, boleto.getNumeroBoleto());
        verify(boletosDAO, times(1)).obtenerBoletoSorteo(101, 1);
    }

    @Test
    public void testObtenerBoletosPorIdCliente() {
        when(boletosDAO.obtenerBoletosPorIdCliente(1)).thenReturn(Arrays.asList(boleto1, boleto2));

        List<Boleto> boletos = fachadaSorteos.obtenerBoletosCliente(1);
        assertEquals(2, boletos.size());
        verify(boletosDAO, times(1)).obtenerBoletosPorIdCliente(1);
    }
}

