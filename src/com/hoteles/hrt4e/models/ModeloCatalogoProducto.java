/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.models;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Hacktom
 */
public class ModeloCatalogoProducto extends AbstractTableModel {
    private String [] estados = {"Nombre","Costo","Cantidad"};
   public ArrayList<CatalogoProducto> productos = new ArrayList<>();
    
    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return estados.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       CatalogoProducto producto = productos.get(rowIndex);
       
       if(columnIndex == 0 ){
           return producto.getNombre();
       }
       else if(columnIndex == 1) {
           return "$"+producto.getCosto();
       }
       else if (columnIndex == 2){
           return producto.getCantidad();
       }
       return null;
    }

    @Override
    public String getColumnName(int column) {
        return estados[column];
    }
    
    

 
}
