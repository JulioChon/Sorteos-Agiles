package com.agiles.sorteos.capadatos.capadatos.dominio;

import java.util.Date;

import com.agiles.sorteos.capadatos.capadatos.utilis.ESTADO;
import com.agiles.sorteos.capadatos.capadatos.utilis.IesRequerido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.List;

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

    @NotNull(message = "El rango maximo es requerido")
    private Long rangoMax;

    @NotNull(message = "El rango minimo es requerido")
    private Long rangoMin;
    @NotNull(message = "La fecha de inicio es requerida")
    @Column(name = "fecha_inicio_venta", nullable = false)
    private Date fechaInicioVenta;

    @NotNull(message = "La fecha de fin es requerida")
    @Column(name = "fecha_fin_venta", nullable = false)
    private Date fechaFinVenta;

    @NotNull(message = "La fecha del sorteo es requerida")
    @Column(name = "fecha_sorteo", nullable = false)
    private Date fechaSorteo;

    @NotNull(message = "El estado es requerido")
    private ESTADO estado;

    @NotNull(message = "El precio es requerido")
    private Float precio;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    @JsonIgnore
    private Administrador idAdministrador;

    @OneToMany(mappedBy = "idSorteo", orphanRemoval = true)
    private List<Boleto> boletos;

    // Constructor vacío
    public Sorteo() {
    }

    // Constructor sin ID
    // Getters y Setters
    public Sorteo(Integer id, String nombre, String imagenSorteo,
            @NotNull(message = "El rango maximo es requerido") Long rangoMax,
            @NotNull(message = "El rango minimo es requerido") Long rangoMin,
            @NotNull(message = "La fecha de inicio es requerida") Date fechaInicioVenta,
            @NotNull(message = "La fecha de fin es requerida") Date fechaFinVenta,
            @NotNull(message = "La fecha del sorteo es requerida") Date fechaSorteo,
            @NotNull(message = "El estado es requerido") ESTADO estado,
            @NotNull(message = "El precio es requerido") Float precio, Administrador idAdministrador) {
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
        this.idAdministrador = idAdministrador;
    }

    public Sorteo(String nombre, String imagenSorteo, @NotNull(message = "El rango maximo es requerido") Long rangoMax,
            @NotNull(message = "El rango minimo es requerido") Long rangoMin,
            @NotNull(message = "La fecha de inicio es requerida") Date fechaInicioVenta,
            @NotNull(message = "La fecha de fin es requerida") Date fechaFinVenta,
            @NotNull(message = "La fecha del sorteo es requerida") Date fechaSorteo,
            @NotNull(message = "El estado es requerido") ESTADO estado,
            @NotNull(message = "El precio es requerido") Float precio, Administrador idAdministrador) {
        this.nombre = nombre;
        this.imagenSorteo = imagenSorteo;
        this.rangoMax = rangoMax;
        this.rangoMin = rangoMin;
        this.fechaInicioVenta = fechaInicioVenta;
        this.fechaFinVenta = fechaFinVenta;
        this.fechaSorteo = fechaSorteo;
        this.estado = estado;
        this.precio = precio;
        this.idAdministrador = idAdministrador;
    }

    public Integer getId() {
        return id;
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

    public Administrador getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Administrador idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
