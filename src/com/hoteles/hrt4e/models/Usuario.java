package com.hoteles.hrt4e.models;

import java.io.Serializable;

/**
 * Created by Tadeo-developer on 03/10/16.
 */

public class Usuario implements Serializable{

    private int id;
    private String nombre;
    private int idRol;
    private String rol;
    private int edad;
    
    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
       if(edad>100){
           System.out.println("La edad debe ser menor a 100");
       }
       else if (edad<0){
           System.out.println("La edad debe ser mayo a 0 ");
       }
       else    this.edad = edad;
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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
