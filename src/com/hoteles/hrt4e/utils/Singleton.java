/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.utils;

import com.hoteles.hrt4e.models.Catalogos;
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
    private Catalogos catalogos;
    
    
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

    public static Singleton getSingleton() {
        return singleton;
    }

    public static void setSingleton(Singleton singleton) {
        Singleton.singleton = singleton;
    }

    public Catalogos getCatalogos() {
        return catalogos;
    }

    public void setCatalogos(Catalogos catalogos) {
        this.catalogos = catalogos;
    }
    
    public Habitacion buscarNumeroHabitacion(int numero){
        for(Habitacion habitacion: catalogos.getHabitaciones()){
            if(habitacion.getNumeroHabitacion()==numero)
                return habitacion;
        }
        return null;
    }
    
    public int buscarIndiceNumeroHabitacion(int numero){
        for(int i = 0;i<catalogos.getHabitaciones().size();i++){
            if(catalogos.getHabitaciones().get(i).getNumeroHabitacion()==numero)
                return i;
        }
        return -1;
    }

    public ArrayList<Habitacion> getHabitacionesTipo(int idTipoHabitacion){

        ArrayList<Habitacion> aux = new ArrayList<>();

        for(Habitacion habitacion : catalogos.getHabitaciones()){
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
                this.catalogos.getHabitaciones().set(indexHabitacion,habitacion);
            }
        }
        
        
    }

    public ArrayList<Usuario> getUsuariosRol(int idRol){
        ArrayList<Usuario> usuariosAux = new ArrayList<>();

        for(Usuario usuario:catalogos.getUsuarios()){
            if(usuario.getIdRol()==idRol){
                usuariosAux.add(usuario);
            }
        }

        return usuariosAux;
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
