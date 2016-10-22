/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.models;

/**
 *
 * @author Tadeo-developer
 */
public class CatalogoTipoHabitacion extends Catalogo{
    
    private double costo;
    private double costoPersonaExtra;
    private double costoHoraExtra;

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
    
    
    
}
