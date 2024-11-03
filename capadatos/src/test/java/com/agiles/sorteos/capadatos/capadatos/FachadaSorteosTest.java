package com.agiles.sorteos.capadatos.capadatos;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.agiles.sorteos.capadatos.capadatos.DAOS.ISorteosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.fachadas.FachadaSorteos;
import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;

public class FachadaSorteosTest {
/* 
    @Mock
    private ISorteosDAO sorteosDAO;

    @InjectMocks
    private FachadaSorteos fachadaSorteos;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarSorteo() {
        Sorteo sorteo = new Sorteo("Sorteo1", "imagen.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        when(sorteosDAO.save(sorteo)).thenReturn(sorteo);

        Sorteo result = fachadaSorteos.guardarSorteo(sorteo);
        assertNotNull(result);
        verify(sorteosDAO, times(1)).save(sorteo);
    }

    @Test
    public void testActualizarSorteo() {
        Sorteo sorteo = new Sorteo("Sorteo1", "imagen.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        sorteo.setId(1);
        when(sorteosDAO.existsById(1)).thenReturn(true);
        when(sorteosDAO.save(sorteo)).thenReturn(sorteo);

        Sorteo result = fachadaSorteos.actualizarSorteo(1, sorteo);
        assertNotNull(result);
        verify(sorteosDAO, times(1)).save(sorteo);
    }

    @Test
    public void testEliminarSorteo() {
        when(sorteosDAO.existsById(1)).thenReturn(true);
        fachadaSorteos.eliminarSorteo(1);
        verify(sorteosDAO, times(1)).deleteById(1);
    }

    @Test
    public void testObtenerSorteoPorId() {
        Sorteo sorteo = new Sorteo("Sorteo1", "imagen.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        sorteo.setId(1);
        when(sorteosDAO.findById(1)).thenReturn(Optional.of(sorteo));

        Sorteo result = fachadaSorteos.obtenerSorteoPorId(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testObtenerSorteos() {
        Sorteo sorteo1 = new Sorteo("Sorteo1", "imagen1.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        Sorteo sorteo2 = new Sorteo("Sorteo2", "imagen2.png", 200L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        when(sorteosDAO.findAll()).thenReturn(Arrays.asList(sorteo1, sorteo2));

        Iterable<Sorteo> sorteos = fachadaSorteos.obtenerSorteos();

        List<Sorteo> sorteosList = StreamSupport.stream(sorteos.spliterator(), false).collect(Collectors.toList());

        assertNotNull(sorteos);
        assertEquals(2, sorteosList.size());
        verify(sorteosDAO, times(1)).findAll();
    }
        */
}