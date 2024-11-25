package com.agiles.sorteos.capadatos.capadatos.dominio;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @NotBlank(message = "La contraseña no debe de estar en blanco")
    @NotNull(message = "La contraseña no debe de ser nula")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasenia;


    public Cliente(){

    }
    
    


    public Cliente(Integer id,
            @NotBlank(message = "El nombre no debe de estar en blanco") @NotNull(message = "El nombre no debe de ser nulo") String nombre,
            @NotBlank(message = "El correo no debe de estar en blanco") @NotNull(message = "El correo no debe de ser nulo") String correo,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String telefono,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
    }




    public Cliente(
            @NotBlank(message = "El nombre no debe de estar en blanco") @NotNull(message = "El nombre no debe de ser nulo") String nombre,
            @NotBlank(message = "El correo no debe de estar en blanco") @NotNull(message = "El correo no debe de ser nulo") String correo,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String telefono,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
    }




    public Cliente(
            @NotBlank(message = "El nombre no debe de estar en blanco") @NotNull(message = "El nombre no debe de ser nulo") String nombre,
            @NotBlank(message = "El telefono no debe de estar en blanco") @NotNull(message = "El telefono no debe de ser nulo") String contrasenia) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
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




    public String getContrasenia() {
        return contrasenia;
    }




    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    

}
