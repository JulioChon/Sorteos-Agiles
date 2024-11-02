package com.agiles.sorteos.capadatos.capadatos;

import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;
import com.agiles.sorteos.capadatos.capadatos.dominio.Sorteo;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SorteoTest {

    private final Validator validator;

    public SorteoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSorteo() {
        Sorteo sorteo = new Sorteo("Sorteo1", "imagen1.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        Set<ConstraintViolation<Sorteo>> violations = validator.validate(sorteo);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidSorteoNombreNull() {
        Sorteo sorteo = new Sorteo(null, "imagen.png", 100L, new Date(), new Date(), new Date(), ESTADO.ACTIVO);
        Set<ConstraintViolation<Sorteo>> violations = validator.validate(sorteo);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void testInvalidSorteoEstadoNull() {
        Sorteo sorteo = new Sorteo("Sorteo1", "imagen.png", 100L, new Date(), new Date(), new Date(), null);
        Set<ConstraintViolation<Sorteo>> violations = validator.validate(sorteo);
        assertFalse(violations.isEmpty());
    }
}
