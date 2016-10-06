package com.hotel.hotel.models;

import java.io.Serializable;

/**
 * Created by Tadeo-developer on 28/09/16.
 */

public class HabitacionRenta implements Serializable {

    private int idRentaHabitacion;
    private int tipoPago;
    private String tipoPagoText;
    private String tipoCliente;
    private String identificacion;
    private String modelo;
    private int personasExtra;
    private int horasExtra;
    private String fecha;

    public int getIdRentaHabitacion() {
        return idRentaHabitacion;
    }

    public void setIdRentaHabitacion(int idRentaHabitacion) {
        this.idRentaHabitacion = idRentaHabitacion;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getTipoPagoText() {
        return tipoPagoText;
    }

    public void setTipoPagoText(String tipoPagoText) {
        this.tipoPagoText = tipoPagoText;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getPersonasExtra() {
        return personasExtra;
    }

    public void setPersonasExtra(int personasExtra) {
        this.personasExtra = personasExtra;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
