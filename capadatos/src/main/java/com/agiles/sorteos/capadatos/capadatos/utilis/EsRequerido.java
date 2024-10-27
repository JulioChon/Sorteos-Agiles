package com.agiles.sorteos.capadatos.capadatos.utilis;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


import org.springframework.util.StringUtils;


public class EsRequerido implements ConstraintValidator<IesRequerido, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value);
    }

}
