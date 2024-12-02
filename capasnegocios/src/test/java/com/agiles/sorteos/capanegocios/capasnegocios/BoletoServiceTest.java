package com.agiles.sorteos.capanegocios.capasnegocios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agiles.sorteos.capadatos.capadatos.dominio.Boleto;
import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.fachadas.IFachadaSorteos;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;
import com.agiles.sorteos.capanegocios.capasnegocios.Exception.NotFoundException;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.BoletoService;
import com.agiles.sorteos.capanegocios.capasnegocios.servicios.IEnvioCorreoService;

public class BoletoServiceTest {

    @Mock
    private IFachadaSorteos fachadaSorteos;

    @Mock
    private IEnvioCorreoService envioCorreoService;

    @InjectMocks
    private BoletoService boletoService;

    private Sorteo sorteo;
    private Boleto boleto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sorteo = new Sorteo();
        sorteo.setId(1);

        boleto = new Boleto();
        boleto.setId(1);
        boleto.setNumeroBoleto(123);
        boleto.setEstado(BOLETOESTADO.LIBRE);
        boleto.setIdSorteo(sorteo);
        boleto.setPrecio(150.0f);
    }

    @Test
    public void testGenerarBoletos() {
        when(fachadaSorteos.obtenerSorteoPorId(1)).thenReturn(sorteo);

        boletoService.generarBoletos(5L, 1L, 100.0f, 1);

        verify(fachadaSorteos, times(5)).guardarBoleto(any(Boleto.class));
    }

    @Test
    public void testGuardarBoleto() {
        when(fachadaSorteos.guardarBoleto(boleto)).thenReturn(boleto);

        Boleto result = boletoService.guardarBoleto(boleto);

        assertNotNull(result);
        assertEquals(boleto, result);
        verify(fachadaSorteos, times(1)).guardarBoleto(boleto);
    }

    @Test
    public void testEliminarBoleto() {
        when(fachadaSorteos.obtenerBoletoPorId(1)).thenReturn(boleto);

        boletoService.eliminarBoleto(1);

        verify(fachadaSorteos, times(1)).eliminarBoleto(1);
    }

    @Test
    public void testEliminarBoleto_NotFoundException() {
        when(fachadaSorteos.obtenerBoletoPorId(1)).thenReturn(null);

        assertThrows(NotFoundException.class, () -> boletoService.eliminarBoleto(1));
    }

    @Test
    public void testObtenerBoletoPorId() {
        when(fachadaSorteos.obtenerBoletoPorId(1)).thenReturn(boleto);

        Boleto result = boletoService.obtenerBoletoPorId(1);

        assertNotNull(result);
        assertEquals(boleto, result);
    }

    @Test
    public void testObtenerBoletosPorIdSorteo() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto);
        when(fachadaSorteos.obtenerBoletosPorIdSorteo(1)).thenReturn(boletos);

        List<Boleto> result = boletoService.obtenerBoletosPorIdSorteo(1);

        assertEquals(1, result.size());
        verify(fachadaSorteos, times(1)).obtenerBoletosPorIdSorteo(1);
    }

    @Test
    public void testCambiarEstadoLibre() {
        when(fachadaSorteos.obtenerBoletoPorId(1)).thenReturn(boleto);
        boleto.setEstado(BOLETOESTADO.APARTADO);

        Boleto result = boletoService.cambiarEstadoLibre(1);

        assertEquals(BOLETOESTADO.LIBRE, result.getEstado());
        assertNull(result.getFechaLimApart());
        verify(fachadaSorteos, times(1)).guardarBoleto(boleto);
    }

    @Test
    public void testCambiarEstadoApartado() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setCorreo("test@correo.com");

        Boleto boleto = new Boleto();
        boleto.setEstado(BOLETOESTADO.LIBRE);

        when(fachadaSorteos.obtenerBoletoPorId(1)).thenReturn(boleto);
        when(fachadaSorteos.clienteExiste("test@correo.com")).thenReturn(cliente);

        Boleto result = boletoService.cambiarEstadoApartado(1, "test@correo.com");

        assertEquals(BOLETOESTADO.APARTADO, result.getEstado());
        assertNotNull(result.getFechaLimApart());
        verify(envioCorreoService, times(1)).enviarCorreoConfirmacionApartado("test@correo.com", boleto);
        verify(fachadaSorteos, times(1)).guardarBoleto(boleto);
    }

    @Test
    public void testCambiarEstadoVendido() {
        when(fachadaSorteos.obtenerBoletoPorId(1)).thenReturn(boleto);

        Boleto result = boletoService.cambiarEstadoVendido(1);

        assertEquals(BOLETOESTADO.VENDIDO, result.getEstado());
        assertNull(result.getFechaLimApart());
        verify(fachadaSorteos, times(1)).guardarBoleto(boleto);
    }

    @Test
    public void testObtenerBoletosCliente() {
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(boleto);
        when(fachadaSorteos.obtenerBoletosCliente(1)).thenReturn(boletos);

        List<Boleto> result = boletoService.obtenerBoletosCliente(1);

        assertEquals(1, result.size());
        verify(fachadaSorteos, times(1)).obtenerBoletosCliente(1);
    }
}
