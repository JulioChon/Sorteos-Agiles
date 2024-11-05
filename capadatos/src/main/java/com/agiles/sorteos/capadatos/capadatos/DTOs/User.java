package com.agiles.sorteos.capadatos.capadatos.DTOs;

public class User {

    private Integer id;
    private String correo;
    private String nombre;
    private Boolean admin;

    public User(){

    }

    
    public User(Integer id, String correo, String nombre, Boolean admin) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.admin = admin;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    
}
