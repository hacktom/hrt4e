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
public class ModeloEstatusHabitaciones extends AbstractTableModel {
    private String [] estados = {"Tipo","Limpio","Ocupado","Sucio"};
   public ArrayList<HabitacionTipo> habitaciones = new ArrayList<>();
    
    @Override
    public int getRowCount() {
        return habitaciones.size();
    }

    @Override
    public int getColumnCount() {
        return estados.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       HabitacionTipo habitacion = habitaciones.get(rowIndex);
       
       if(columnIndex == 0 ){
           return habitacion.getTipo();
       }
       else if(columnIndex == 1) {
           return habitacion.getCantidadLimpio();
       }
       else if (columnIndex == 2){
           return habitacion.getCantidadOcupado();
       }
       else if(columnIndex == 3){
           return habitacion.getCantidadSucio();
       }
       return null;
    }

    @Override
    public String getColumnName(int column) {
        return estados[column];
    }
    
    

 
}
