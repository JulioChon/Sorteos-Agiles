package com.agiles.sorteos.capadatos.capadatos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConfiguracionEnvio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer dias;
    private Integer horas;
    private Integer minutos;
    private Integer segundos;



    public ConfiguracionEnvio() {
    }

    

    public ConfiguracionEnvio(Integer dias, Integer horas, Integer minutos, Integer segundos) {
        this.dias = dias;
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }



    public ConfiguracionEnvio(Long id, Integer dias, Integer horas, Integer minutos, Integer segundos) {
        this.id = id;
        this.dias = dias;
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }



    public long getIntervaloEnMilisegundos() {
        return ((dias * 24L * 60 * 60) + (horas * 60L * 60) + (minutos * 60L) + segundos) * 1000;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }


    
}
