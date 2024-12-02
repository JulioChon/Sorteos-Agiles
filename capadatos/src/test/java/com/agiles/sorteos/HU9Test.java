package com.agiles.sorteos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;

import com.agiles.sorteos.capadatos.capadatos.DAOS.IBoletosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
import com.agiles.sorteos.capadatos.capadatos.fachadas.FachadaSorteos;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;

@ExtendWith(MockitoExtension.class)
public class HU9Test {

    @Mock
    private IBoletosDAO boletosDAO;

    @InjectMocks
    private FachadaSorteos fachadaSorteos;

    private Boleto boletoMock;
    private Cliente clienteMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteMock = new Cliente();
        clienteMock.setId(1);
        clienteMock.setNombre("Juan Pérez");

        boletoMock = new Boleto();
        boletoMock.setId(1);
        boletoMock.setNumeroBoleto(12345);
        boletoMock.setEstado(BOLETOESTADO.LIBRE);
    }

    @Test
    void testApartarBoletoExitoso() {
        when(boletosDAO.findById(1)).thenReturn(Optional.of(boletoMock));

        boletoMock.setEstado(BOLETOESTADO.APARTADO);
        boletoMock.setIdCliente(clienteMock);
        boletoMock.setFechaLimApart(new Date()); // Fecha límite
        when(boletosDAO.save(any(Boleto.class))).thenReturn(boletoMock);

        Boleto boletoApartado = fachadaSorteos.cambiarEstado(1, boletoMock);

        assertNotNull(boletoApartado);
        assertEquals(BOLETOESTADO.APARTADO, boletoApartado.getEstado());
        assertEquals(clienteMock, boletoApartado.getIdCliente());
        assertNotNull(boletoApartado.getFechaLimApart());

        verify(boletosDAO).findById(1);
        verify(boletosDAO).save(boletoMock);
    }

    @Test
    void testApartarBoletoNoExistente() {
        when(boletosDAO.findById(1)).thenReturn(Optional.empty());

        Boleto boletoApartado = fachadaSorteos.cambiarEstado(1, boletoMock);

        assertNull(boletoApartado);

        verify(boletosDAO).findById(1);
        verify(boletosDAO, never()).save(any(Boleto.class));
    }

    @Test
    void testApartarBoletoYaApartado() {
        boletoMock.setEstado(BOLETOESTADO.APARTADO);
        when(boletosDAO.findById(1)).thenReturn(Optional.of(boletoMock));

        Boleto boletoApartado = fachadaSorteos.cambiarEstado(1, boletoMock);

        assertNotNull(boletoApartado);
        assertEquals(BOLETOESTADO.APARTADO, boletoApartado.getEstado());

        verify(boletosDAO).findById(1);
        verify(boletosDAO, never()).save(any(Boleto.class));
    }

    @Test
    void testLiberarBoletosVencidos() {
        Boleto boleto1 = new Boleto();
        boleto1.setId(1);
        boleto1.setEstado(BOLETOESTADO.APARTADO);
        boleto1.setFechaLimApart(new Date(System.currentTimeMillis() - 86400000)); // Fecha vencida

        Boleto boleto2 = new Boleto();
        boleto2.setId(2);
        boleto2.setEstado(BOLETOESTADO.APARTADO);
        boleto2.setFechaLimApart(new Date(System.currentTimeMillis() + 86400000)); // Fecha válida

        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto1);
        boletos.add(boleto2);

        fachadaSorteos.liberarBoletosVencidos(new Date());

        verify(boletosDAO).liberarBoletosVencidos(any(Date.class));
    }

    @Test
    void testObtenerBoletosApartados() {
        Boleto boleto1 = new Boleto();
        boleto1.setId(1);
        boleto1.setEstado(BOLETOESTADO.APARTADO);

        Boleto boleto2 = new Boleto();
        boleto2.setId(2);
        boleto2.setEstado(BOLETOESTADO.APARTADO);

        List<Boleto> boletosApartados = new ArrayList<>();
        boletosApartados.add(boleto1);
        boletosApartados.add(boleto2);

        when(boletosDAO.findByEstado(BOLETOESTADO.APARTADO)).thenReturn(boletosApartados);

        List<Boleto> resultado = fachadaSorteos.obtenerBoletosApartados();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(BOLETOESTADO.APARTADO, resultado.get(0).getEstado());

        verify(boletosDAO).findByEstado(BOLETOESTADO.APARTADO);
    }

}
