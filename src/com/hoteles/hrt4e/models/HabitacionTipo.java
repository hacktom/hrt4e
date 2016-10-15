/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.models;

/**
 *
 * @author Hacktom
 */
public class HabitacionTipo {
    private String tipo;
    private int cantidadLimpio;
    private int cantidadOcupado;
    private int cantidadSucio;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadLimpio() {
        return cantidadLimpio;
    }

    public void setCantidadLimpio(int cantidadLimpio) {
        this.cantidadLimpio = cantidadLimpio;
    }

    public int getCantidadOcupado() {
        return cantidadOcupado;
    }

    public void setCantidadOcupado(int cantidadOcupado) {
        this.cantidadOcupado = cantidadOcupado;
    }

    public int getCantidadSucio() {
        return cantidadSucio;
    }

    public void setCantidadSucio(int cantidadSucio) {
        this.cantidadSucio = cantidadSucio;
    }
    
}
