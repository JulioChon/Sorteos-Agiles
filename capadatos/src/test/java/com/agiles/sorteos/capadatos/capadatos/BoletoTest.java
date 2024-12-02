package com.agiles.sorteos.capadatos.capadatos;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class BoletoTest {

    private Boleto boleto;

    @BeforeEach
    public void setUp() {
        boleto = new Boleto();
        boleto.setNumeroBoleto(123);
        boleto.setEstado(BOLETOESTADO.APARTADO);
        boleto.setFechaLimApart(new Date());
        boleto.setPrecio(150.0f);
    }

    @Test
    public void testNumeroBoleto() {
        boleto.setNumeroBoleto(456);
        assertEquals(456, boleto.getNumeroBoleto());
    }

    @Test
    public void testEstado() {
        boleto.setEstado(BOLETOESTADO.VENDIDO);
        assertEquals(BOLETOESTADO.VENDIDO, boleto.getEstado());
    }

    @Test
    public void testFechaLimApart() {
        Date nuevaFecha = new Date();
        boleto.setFechaLimApart(nuevaFecha);
        assertEquals(nuevaFecha, boleto.getFechaLimApart());
    }

    @Test
    public void testPrecio() {
        boleto.setPrecio(200.0f);
        assertEquals(200.0f, boleto.getPrecio());
    }
}

