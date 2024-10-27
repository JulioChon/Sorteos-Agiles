package com.agiles.sorteos.capadatos.capadatos.utilis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = EsRequerido.class) 
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface IesRequerido {
    String message() default "es requerido usando";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
