package com.hotel.hotel.models;

import java.util.ArrayList;

/**
 * Created by Tadeo-developer on 03/10/16.
 */

public class Catalogos {

    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Usuario> usuarios;

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
}
