package com.agiles.sorteos.capadatos.capadatos.dominio;

import java.util.Date;

import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;
import com.agiles.sorteos.capadatos.capadatos.utilis.IesRequerido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sorteos")
public class Sorteo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @IesRequerido(message = "El nombre es requerido")
    private String nombre;

    @IesRequerido(message = "La imagen es requerida")
    private String imagenSorteo;

    
    private Long rangoNum;


    private Date ferchaInicio;


    private Date fechaFin;

    private ESTADO estado;


    public Sorteo() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
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


    public Long getRangoNum() {
        return rangoNum;
    }


    public void setRangoNum(Long rangoNum) {
        this.rangoNum = rangoNum;
    }


    public Date getFerchaInicio() {
        return ferchaInicio;
    }


    public void setFerchaInicio(Date ferchaInicio) {
        this.ferchaInicio = ferchaInicio;
    }


    public Date getFechaFin() {
        return fechaFin;
    }


    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }


    public ESTADO getEstado() {
        return estado;
    }


    public void setEstado(ESTADO estado) {
        this.estado = estado;
    }

    


}
