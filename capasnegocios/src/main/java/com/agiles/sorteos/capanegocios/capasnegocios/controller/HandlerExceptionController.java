package com.agiles.sorteos.capanegocios.capasnegocios.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.agiles.sorteos.capanegocios.capasnegocios.Exception.NotFoundException;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> userNotFounfException(Exception ex){
        Map <String, String> error = new HashMap<>();
        error.put("date", new Date().toString());
        error.put("error", "No se ha encontrado el " + "" + ex.getMessage());
        error.put("message", ex.getMessage());
        error.put("status", HttpStatus.NOT_FOUND.value() + "");

        return error;
    }

}
