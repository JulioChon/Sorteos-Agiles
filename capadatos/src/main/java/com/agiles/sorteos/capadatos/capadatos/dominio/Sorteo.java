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
    private Date fechaInicioVenta;

    @NotNull(message = "La fecha de fin es requerida")
    private Date fechaFinVenta;

    @NotNull(message = "La fecha del sorteo es requerida")
    private Date fechaSorteo;

    @NotNull(message = "El estado es requerido")
    private ESTADO estado;

    // Constructor vacío
    public Sorteo() {
    }

    // Constructor sin ID
    public Sorteo(String nombre, String imagenSorteo, Long rangoNum,
            Date fechaInicioVenta, Date fechaFinVenta, Date fechaSorteo, ESTADO estado) {
        this.nombre = nombre;
        this.imagenSorteo = imagenSorteo;
        this.rangoNum = rangoNum;
        this.fechaInicioVenta = fechaInicioVenta;
        this.fechaFinVenta = fechaFinVenta;
        this.fechaSorteo = fechaSorteo;
        this.estado = estado;
    }

    // Getters y Setters
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

    public Long getRangoNum() {
        return rangoNum;
    }

    public void setRangoNum(Long rangoNum) {
        this.rangoNum = rangoNum;
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
}
