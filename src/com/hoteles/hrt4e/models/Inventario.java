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
public class Inventario extends Catalogo{
    
    private int idHotel;
    private ArrayList<CatalogoProducto> productos;

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public ArrayList<CatalogoProducto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<CatalogoProducto> productos) {
        this.productos = productos;
    }
    
    public void addProducto(CatalogoProducto catalogoProducto){
        if(productos==null)
            productos = new ArrayList<>();
        productos.add(catalogoProducto);
    }
}
