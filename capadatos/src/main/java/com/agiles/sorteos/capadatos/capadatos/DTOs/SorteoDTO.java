package com.agiles.sorteos.capadatos.capadatos.DTOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;

public class SorteoDTO {

   
    private Integer id;

    
    private String nombre;

    private String imagenSorteo;

    private Long rangoMax;

    private Long rangoMin;
    private Date fechaInicioVenta;

    private Date fechaFinVenta;

    private Date fechaSorteo;

    private ESTADO estado;

    private Float precio;

    private List<BoletoDTO> boletos;



    public SorteoDTO() {
    }
    
    public SorteoDTO(Integer id, String nombre, String imagenSorteo, Long rangoMax, Long rangoMin,
            Date fechaInicioVenta, Date fechaFinVenta, Date fechaSorteo, ESTADO estado, Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.imagenSorteo = imagenSorteo;
        this.rangoMax = rangoMax;
        this.rangoMin = rangoMin;
        this.fechaInicioVenta = fechaInicioVenta;
        this.fechaFinVenta = fechaFinVenta;
        this.fechaSorteo = fechaSorteo;
        this.estado = estado;
        this.precio = precio;
        this.boletos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenSorteo() {
        return imagenSorteo;
    }

    public void setImagenSorteo(String imagenSorteo) {
        this.imagenSorteo = imagenSorteo;
    }

    public Long getRangoMax() {
        return rangoMax;
    }

    public void setRangoMax(Long rangoMax) {
        this.rangoMax = rangoMax;
    }

    public Long getRangoMin() {
        return rangoMin;
    }

    public void setRangoMin(Long rangoMin) {
        this.rangoMin = rangoMin;
    }

    public Date getFechaInicioVenta() {
        return fechaInicioVenta;
    }

    public void setFechaInicioVenta(Date fechaInicioVenta) {
        this.fechaInicioVenta = fechaInicioVenta;
    }

    public Date getFechaFinVenta() {
        return fechaFinVenta;
    }

    public void setFechaFinVenta(Date fechaFinVenta) {
        this.fechaFinVenta = fechaFinVenta;
    }

    public Date getFechaSorteo() {
        return fechaSorteo;
    }

    public void setFechaSorteo(Date fechaSorteo) {
        this.fechaSorteo = fechaSorteo;
    }

    public ESTADO getEstado() {
        return estado;
    }

    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public List<BoletoDTO> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<BoletoDTO> boletos) {
        this.boletos = boletos;
    }


    
}
