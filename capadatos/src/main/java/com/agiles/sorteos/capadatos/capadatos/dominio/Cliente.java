package com.agiles.sorteos.capadatos.capadatos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre no debe de estar en blanco")
    @NotNull(message = "El nombre no debe de ser nulo")
    private String nombre;
    @NotBlank(message = "El correo no debe de estar en blanco")
    @NotNull(message = "El correo no debe de ser nulo")
    private String correo;
    @NotBlank(message = "El telefono no debe de estar en blanco")
    @NotNull(message = "El telefono no debe de ser nulo")
    private String telefono;


    public Cliente(){
        
    }
    
    public Cliente(Integer id,
            @NotBlank(message = "El nombre no debe de estar en blanco") @NotNull(message = "El nombre no debe de ser nulo") String nombre,
            @NotBlank(message = "El correo no debe de estar en blanco") @NotNull(message = "El correo no debe de ser nulo") String correo,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    
    public Cliente(
            @NotBlank(message = "El nombre no debe de estar en blanco") @NotNull(message = "El nombre no debe de ser nulo") String nombre,
            @NotBlank(message = "El correo no debe de estar en blanco") @NotNull(message = "El correo no debe de ser nulo") String correo,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
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
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    

}
