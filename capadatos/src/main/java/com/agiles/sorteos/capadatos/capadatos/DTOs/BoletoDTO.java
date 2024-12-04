package com.agiles.sorteos.capadatos.capadatos.DTOs;

import java.util.Date;

import com.agiles.sorteos.capadatos.capadatos.dominio.Cliente;
import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;


public class BoletoDTO {

    private Integer id;


    private Integer numeroBoleto;

    private BOLETOESTADO estado;

   
    private Date fechaLimApart;


    private float precio;


    private Cliente idCliente;

    public BoletoDTO() {
    }

    public BoletoDTO(Integer id, Integer numeroBoleto, BOLETOESTADO estado, Date fechaLimApart, float precio,
            Cliente idCliente) {
        this.id = id;
        this.numeroBoleto = numeroBoleto;
        this.estado = estado;
        this.fechaLimApart = fechaLimApart;
        this.precio = precio;
        this.idCliente = idCliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(Integer numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public BOLETOESTADO getEstado() {
        return estado;
    }

    public void setEstado(BOLETOESTADO estado) {
        this.estado = estado;
    }

    public Date getFechaLimApart() {
        return fechaLimApart;
    }

    public void setFechaLimApart(Date fechaLimApart) {
        this.fechaLimApart = fechaLimApart;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }


}
