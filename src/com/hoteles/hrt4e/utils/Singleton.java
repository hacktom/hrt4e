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
    
    private String macAddress;
    
    private int idHotel;

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
    
    public Habitacion buscarNumeroHabitacion(int numero){
        for(Habitacion habitacion: habitaciones){
            if(habitacion.getNumeroHabitacion()==numero)
                return habitacion;
        }
        return null;
    }
    
    public int buscarIndiceNumeroHabitacion(int numero){
        for(int i = 0;i<habitaciones.size();i++){
            if(habitaciones.get(i).getNumeroHabitacion()==numero)
                return i;
        }
        return -1;
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
    
    public void actualizarHabitaciones(ArrayList<Habitacion> habitaciones){
        
       /* for(int i = 0;i<habitaciones.size();i++){
            
            Habitacion hab = buscarNumeroHabitacion(habitaciones.get(i).getNumeroHabitacion());
            hab = habitaciones.get(i);
           
            
        }*/
        
        for(Habitacion habitacion : habitaciones){
            int indexHabitacion = buscarIndiceNumeroHabitacion(habitacion.getNumeroHabitacion());
            if(indexHabitacion>=0){
                this.habitaciones.set(indexHabitacion,habitacion);
            }
        }
        
        
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

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }
    
    

    
    
}
