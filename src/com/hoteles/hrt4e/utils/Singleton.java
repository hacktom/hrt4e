/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.utils;

import com.hoteles.hrt4e.models.Habitacion;
import com.hoteles.hrt4e.models.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Tadeo-developer
 */
public class Singleton {
    private static Singleton singleton;

    private Usuario usuario;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Usuario> usuarios;

    public static Singleton getInstance(){
        if(singleton==null){
            singleton = new Singleton();
        }
        return singleton;
    }

    private Singleton(){

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Habitacion> getHabitaciones(){
        return this.habitaciones;
    }

    public ArrayList<Habitacion> getHabitacionesTipo(int idTipoHabitacion){

        ArrayList<Habitacion> aux = new ArrayList<>();

        for(Habitacion habitacion : habitaciones){
            if(habitacion.getTipoHabitacion()==idTipoHabitacion){
                aux.add(habitacion);
            }

        }

        return aux;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones){
        this.habitaciones = habitaciones;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Usuario> getUsuariosRol(int idRol){
        ArrayList<Usuario> usuariosAux = new ArrayList<>();

        for(Usuario usuario:usuarios){
            if(usuario.getIdRol()==idRol){
                usuariosAux.add(usuario);
            }
        }

        return usuariosAux;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
