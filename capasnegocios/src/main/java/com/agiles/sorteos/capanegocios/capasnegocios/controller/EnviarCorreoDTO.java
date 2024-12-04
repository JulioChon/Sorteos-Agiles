package com.agiles.sorteos.capanegocios.capasnegocios.controller;

public class EnviarCorreoDTO {
    private String correo;
    private Integer idBoleto;

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(Integer idBoleto) {
        this.idBoleto = idBoleto;
    }
}
