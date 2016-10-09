/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.threads;

import com.caronte.json.JSONObject;
import com.hoteles.hrt4e.models.HabitacionRenta;
import com.hoteles.hrt4e.ws.WebServices;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tadeo-developer
 */
public class TareaDetalleHabitacionWorker extends Worker {

    private int idHabitacion;

    public TareaDetalleHabitacionWorker(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    @Override
    protected JSONObject doInBackground() throws Exception {
        JSONObject jsonObject = WebServices.servicioDetalleHabitacion(idHabitacion);

        return jsonObject;
    }

    @Override
    protected void done() {

        JSONObject jsonObject;
        try {
            jsonObject = get();

            if (jsonObject != null) {
                try {
                    int code = jsonObject.getInt("code");

                    if (code == 1) {

                        ArrayList<JSONObject> habitacionArray = jsonObject.getJSONArray("habitacion");

                        JSONObject habitacionJson = (JSONObject) habitacionArray.get(0);
                        HabitacionRenta habitacionRenta = new HabitacionRenta();
                        habitacionRenta.setIdRentaHabitacion(habitacionJson.getInt("id_habitacion_renta"));
                        habitacionRenta.setTipoCliente(habitacionJson.getString("tipo_cliente"));
                        habitacionRenta.setIdentificacion(habitacionJson.getString("identificacion"));
                        habitacionRenta.setModelo(habitacionJson.getString("modelo"));
                        habitacionRenta.setPersonasExtra(habitacionJson.getInt("personas_extra"));
                        habitacionRenta.setHorasExtra(habitacionJson.getInt("horas_extra"));
                        habitacionRenta.setFecha(habitacionJson.getString("fecha"));

                        if (onPostExecuteListener != null) {
                            onPostExecuteListener.onPostExecute(habitacionRenta);
                        }

                    } else {
                        String error = jsonObject.getString("error");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(TareaCatalogosWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(TareaCatalogosWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
