package com.agiles.sorteos.capadatos.capadatos.dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El nombre es requerido")
    private String nombre;

    @NotNull(message = "El email es requerido")
    private String email;

    @NotNull(message = "La contraseña es requerida")
    private String contrasena;

    @NotNull(message = "El telefono es requerido")
    private String telefono;


    public Administrador() {
    }
    
    public Administrador(@NotNull(message = "El nombre es requerido") String nombre,
            @NotNull(message = "El email es requerido") String email,
            @NotNull(message = "La contraseña es requerida") String contrasena,
            @NotNull(message = "El telefono es requerido") String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }

    public Administrador(Integer id, @NotNull(message = "El nombre es requerido") String nombre,
            @NotNull(message = "El email es requerido") String email,
            @NotNull(message = "La contraseña es requerida") String contrasena,
            @NotNull(message = "El telefono es requerido") String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    


}
