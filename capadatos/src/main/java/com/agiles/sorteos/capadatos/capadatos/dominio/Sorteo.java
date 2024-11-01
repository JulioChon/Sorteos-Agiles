package com.agiles.sorteos.capadatos.capadatos.dominio;

import java.util.Date;

import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;
import com.agiles.sorteos.capadatos.capadatos.utilis.IesRequerido;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

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

    @NotNull(message = "El rango es requerido")
    private Long rangoNum;

    @NotNull(message = "La fecha de inicio es requerida")
    private Date ferchaInicioVenta;

    @NotNull(message = "La fecha de fin es requerida")
    private Date fechaFinVenta;

    @NotNull(message = "La fecha del sorteo es requerida")
    private Date fechaSorteo;

    @NotNull(message = "El estado es requerido")
    private ESTADO estado;


    public Sorteo() {
    }


    public Sorteo(Integer id, String nombre, String imagenSorteo,
            @NotNull(message = "El rango es requerido") Long rangoNum,
            @NotNull(message = "La fecha de inicio es requerida") Date ferchaInicioVenta,
            @NotNull(message = "La fecha de fin es requerida") Date fechaFinVenta,
            @NotNull(message = "La fecha del sorteo es requerida") Date fechaSorteo,
            @NotNull(message = "El estado es requerido") ESTADO estado) {
        this.id = id;
        this.nombre = nombre;
        this.imagenSorteo = imagenSorteo;
        this.rangoNum = rangoNum;
        this.ferchaInicioVenta = ferchaInicioVenta;
        this.fechaFinVenta = fechaFinVenta;
        this.fechaSorteo = fechaSorteo;
        this.estado = estado;
    }

        

    public Sorteo(String nombre, String imagenSorteo, 
            @NotNull(message = "El rango es requerido") Long rangoNum,
            @NotNull(message = "La fecha de inicio es requerida") Date ferchaInicioVenta,
            @NotNull(message = "La fecha de fin es requerida") Date fechaFinVenta,
            @NotNull(message = "La fecha del sorteo es requerida") Date fechaSorteo,
            @NotNull(message = "El estado es requerido") ESTADO estado) {
        this.nombre = nombre;
        this.imagenSorteo = imagenSorteo;
        this.rangoNum = rangoNum;
        this.ferchaInicioVenta = ferchaInicioVenta;
        this.fechaFinVenta = fechaFinVenta;
        this.fechaSorteo = fechaSorteo;
        this.estado = estado;
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


    public Date getferchaInicioVenta() {
        return ferchaInicioVenta;
    }


    public void setferchaInicioVenta(Date ferchaInicioVenta) {
        this.ferchaInicioVenta = ferchaInicioVenta;
    }


    public Date getfechaFinVenta() {
        return fechaFinVenta;
    }


    public void setfechaFinVenta(Date fechaFinVenta) {
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

    


}
