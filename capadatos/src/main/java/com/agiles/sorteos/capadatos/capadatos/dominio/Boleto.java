package com.agiles.sorteos.capadatos.capadatos.dominio;

import java.util.Date;

import com.agiles.sorteos.capadatos.capadatos.utilis.BOLETOESTADO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "boletos")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_boleto")
    private Integer numeroBoleto;

    @NotNull(message = "El estado es requerido")
    @Column(name = "estado")
    private BOLETOESTADO estado;

    @Column(name = "fecha_lim_apart")
    private Date fechaLimApart;

    @Column(name = "precio")
    @NotNull(message = "El precio es requerido")
    private float precio;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_sorteo")
    private Sorteo idSorteo;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    public Boleto() {
    }

    public Boleto(Integer numeroBoleto, @NotNull(message = "El estado es requerido") BOLETOESTADO estado,
            Date fechaLimApart, @NotNull(message = "El precio es requerido") float precio, Sorteo idSorteo) {
        this.numeroBoleto = numeroBoleto;
        this.estado = estado;
        this.fechaLimApart = fechaLimApart;
        this.precio = precio;
        this.idSorteo = idSorteo;
    }

    public Boleto(Integer id, Integer numeroBoleto, @NotNull(message = "El estado es requerido") BOLETOESTADO estado,
            Date fechaLimApart, @NotNull(message = "El precio es requerido") float precio, Sorteo idSorteo) {
        this.id = id;
        this.numeroBoleto = numeroBoleto;
        this.estado = estado;
        this.fechaLimApart = fechaLimApart;
        this.precio = precio;
        this.idSorteo = idSorteo;
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

    public Sorteo getIdSorteo() {
        return idSorteo;
    }

    public void setIdSorteo(Sorteo idSorteo) {
        this.idSorteo = idSorteo;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

}
