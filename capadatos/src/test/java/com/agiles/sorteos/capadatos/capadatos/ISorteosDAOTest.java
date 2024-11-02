package com.agiles.sorteos.capadatos.capadatos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.agiles.sorteos.capadatos.capadatos.DAOS.ISorteosDAO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;

@SpringBootTest
public class ISorteosDAOTest {

    @Autowired
    ISorteosDAO sorteosDAO;

    @Test
    void guardarSorteo() {
        Sorteo sorteo = new Sorteo("Test Sorteo", "imagen.png", 100L, new Date(), new Date(), new Date(),
                ESTADO.ACTIVO);
        Sorteo savedSorteo = sorteosDAO.save(sorteo);
        assertNotNull(savedSorteo);
        assertNotNull(savedSorteo.getId());
    }

    @Test
    public void testObtenerSorteoPorId() {
        Sorteo sorteo = new Sorteo("Test Sorteo", "imagen.png", 100L, new Date(), new Date(), new Date(),
                ESTADO.ACTIVO);
        Sorteo savedSorteo = sorteosDAO.save(sorteo);
        Optional<Sorteo> foundSorteo = sorteosDAO.findById(savedSorteo.getId());
        assertTrue(foundSorteo.isPresent());
        assertEquals(savedSorteo.getId(), foundSorteo.get().getId());
    }

    @Test
    public void testActualizarSorteo() {
        Sorteo sorteo = new Sorteo("Test Sorteo", "imagen.png", 100L, new Date(), new Date(), new Date(),
                ESTADO.ACTIVO);
        Sorteo savedSorteo = sorteosDAO.save(sorteo);
        savedSorteo.setNombre("Updated Sorteo");
        Sorteo updatedSorteo = sorteosDAO.save(savedSorteo);
        assertEquals("Updated Sorteo", updatedSorteo.getNombre());
    }

    @Test
    public void testEliminarSorteo() {
        Sorteo sorteo = new Sorteo("Test Sorteo", "imagen.png", 100L, new Date(), new Date(), new Date(),
                ESTADO.ACTIVO);
        Sorteo savedSorteo = sorteosDAO.save(sorteo);
        sorteosDAO.deleteById(savedSorteo.getId());
        Optional<Sorteo> deletedSorteo = sorteosDAO.findById(savedSorteo.getId());
        assertFalse(deletedSorteo.isPresent());
    }

    @Test
    public void testObtenerSorteos() {
        Sorteo sorteo1 = new Sorteo("Sorteo1", "imagen1.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        Sorteo sorteo2 = new Sorteo("Sorteo2", "imagen2.png", 200L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        sorteosDAO.save(sorteo1);
        sorteosDAO.save(sorteo2);
        Iterable<Sorteo> sorteos = sorteosDAO.findAll();
        assertNotNull(sorteos);
        assertTrue(sorteos.iterator().hasNext());
    }

}
