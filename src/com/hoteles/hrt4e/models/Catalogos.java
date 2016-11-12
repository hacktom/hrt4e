package com.hoteles.hrt4e.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tadeo-developer on 03/10/16.
 */

public class Catalogos implements Serializable{

    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Catalogo> estados;
    private ArrayList<Catalogo> estadosTransicion;
    private ArrayList<Catalogo> roles;
    private ArrayList<Catalogo> tipoPago;
    private ArrayList<Catalogo> tipoProducto;
    private ArrayList<Catalogo> tipoPromocion;
    private ArrayList<CatalogoTipoHabitacion> tipoHabitaciones;
    
    private Inventario inventario;

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Catalogo> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<Catalogo> estados) {
        this.estados = estados;
    }

    public ArrayList<Catalogo> getEstadosTransicion() {
        return estadosTransicion;
    }

    public void setEstadosTransicion(ArrayList<Catalogo> estadosTransicion) {
        this.estadosTransicion = estadosTransicion;
    }

    public ArrayList<Catalogo> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Catalogo> roles) {
        this.roles = roles;
    }

    public ArrayList<Catalogo> getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(ArrayList<Catalogo> tipoPago) {
        this.tipoPago = tipoPago;
    }

    public ArrayList<Catalogo> getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(ArrayList<Catalogo> tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public ArrayList<Catalogo> getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(ArrayList<Catalogo> tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }
    
    

    public ArrayList<CatalogoTipoHabitacion> getTipoHabitaciones() {
        return tipoHabitaciones;
    }

    public void setTipoHabitaciones(ArrayList<CatalogoTipoHabitacion> tipoHabitaciones) {
        this.tipoHabitaciones = tipoHabitaciones;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }
    
    
    
}
