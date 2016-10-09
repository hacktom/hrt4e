package com.hoteles.hrt4e.models;

import java.io.Serializable;

/**
 * Created by Tadeo-developer on 27/09/16.
 */

public class Habitacion implements Serializable,Comparable<Habitacion> {
    private int id;
    private int numeroHabitacion;
    private int tipoHabitacion;
    private String tipoHabitacionText;
    private int estado;
    private String estadoText;
    private double costo;
    private double costoPersonaExtra;
    private double costoHoraExtra;

    private HabitacionRenta habitacionRenta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(int numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public int getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(int tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getTipoHabitacionText() {
        return tipoHabitacionText;
    }

    public void setTipoHabitacionText(String tipoHabitacionText) {
        this.tipoHabitacionText = tipoHabitacionText;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoText() {
        return estadoText;
    }

    public void setEstadoText(String estadoText) {
        this.estadoText = estadoText;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCostoPersonaExtra() {
        return costoPersonaExtra;
    }

    public void setCostoPersonaExtra(double costoPersonaExtra) {
        this.costoPersonaExtra = costoPersonaExtra;
    }

    public double getCostoHoraExtra() {
        return costoHoraExtra;
    }

    public void setCostoHoraExtra(double costoHoraExtra) {
        this.costoHoraExtra = costoHoraExtra;
    }

    public HabitacionRenta getHabitacionRenta() {
        return habitacionRenta;
    }

    public void setHabitacionRenta(HabitacionRenta habitacionRenta) {
        this.habitacionRenta = habitacionRenta;
    }

    @Override
    public int compareTo(Habitacion habitacion) {

        if(getNumeroHabitacion()>habitacion.getNumeroHabitacion()){
            return 1;
        } else if (getNumeroHabitacion()<habitacion.getNumeroHabitacion()) {
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        
        int numHAbitacion = Integer.parseInt((String)obj);
        
        if(numeroHabitacion==numHAbitacion)
            return true;
        else
            return false;
    }
    
    
}
