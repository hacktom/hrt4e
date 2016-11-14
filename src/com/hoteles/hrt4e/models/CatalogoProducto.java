/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.models;

import java.util.ArrayList;

/**
 *
 * @author Tadeo-developer
 */
public class CatalogoProducto extends Catalogo{
    
    public static final String PRODUCTO_RESTAURANT = "Restaurant";
    public static final String PRODUCTO_FARMACIA = "Farmacia";
    public static final String PRODUCTO_XXX = "xxx";
    
    private int idInventario;
    private int idCatalogoTipoProducto;
    private double costo;
    private int cantidad;

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdCatalogoTipoProducto() {
        return idCatalogoTipoProducto;
    }

    public void setIdCatalogoTipoProducto(int idCatalogoTipoProducto) {
        this.idCatalogoTipoProducto = idCatalogoTipoProducto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
}
