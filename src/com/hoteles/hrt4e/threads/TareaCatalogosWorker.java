/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.threads;

import com.caronte.json.JSONObject;
import com.hoteles.hrt4e.Principal;
import com.hoteles.hrt4e.models.Catalogo;
import com.hoteles.hrt4e.models.CatalogoTipoHabitacion;
import com.hoteles.hrt4e.models.Catalogos;
import com.hoteles.hrt4e.models.Habitacion;
import com.hoteles.hrt4e.models.Usuario;
import com.hoteles.hrt4e.utils.Singleton;
import com.hoteles.hrt4e.ws.WebServices;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author Tadeo-developer
 */
public class TareaCatalogosWorker extends Worker {

    @Override
    protected JSONObject doInBackground() throws Exception {
        String mac = Singleton.getInstance().getMacAddress();
        int idHotel = Singleton.getInstance().getIdHotel();
        JSONObject jsonObject = WebServices.servicioCatalogos(mac, idHotel);

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

                        ArrayList<JSONObject> catalogo = jsonObject.getJSONArray("catalogo_habitacion");

                        ArrayList<Habitacion> habitaciones = new ArrayList<>();

                        for (int i = 0; i < catalogo.size(); i++) {
                            JSONObject habitacionJson = catalogo.get(i);
                            Habitacion habitacion = new Habitacion();
                            habitacion.setId(habitacionJson.getInt("id"));
                            habitacion.setNumeroHabitacion(habitacionJson.getInt("numero_habitacion"));
                            habitacion.setTipoHabitacion(habitacionJson.getInt("id_catalogo_tipo_habitacion"));
                            habitacion.setTipoHabitacionText(habitacionJson.getString("nombre"));
                            habitacion.setCosto(habitacionJson.getDouble("costo"));
                            habitacion.setCostoPersonaExtra(habitacionJson.getDouble("costo_persona_extra"));
                            habitacion.setCostoHoraExtra(habitacionJson.getDouble("costo_hora_extra"));
                            habitacion.setEstadoText(habitacionJson.getString("estado"));
                            habitacion.setEstado(habitacionJson.getInt("id_catalogo_estado"));
                            habitaciones.add(habitacion);

                        }

                        ArrayList<JSONObject> catalogoUsuario = jsonObject.getJSONArray("catalogo_usuario");

                        ArrayList<Usuario> usuarios = new ArrayList<>();

                        for (int i = 0; i < catalogoUsuario.size(); i++) {
                            JSONObject usuarioJSon = catalogoUsuario.get(i);
                            Usuario usuario = new Usuario();
                            usuario.setId(usuarioJSon.getInt("id"));
                            usuario.setNombre(usuarioJSon.getString("nombre"));
                            usuario.setIdRol(usuarioJSon.getInt("id_rol"));
                            usuario.setRol(usuarioJSon.getString("nombre_rol"));
                            usuarios.add(usuario);

                        }

                        
                        
                        Catalogos catalogos = new Catalogos();
                        catalogos.setHabitaciones(habitaciones);
                        catalogos.setUsuarios(usuarios);
                        catalogos.setEstados(getCatalogos(jsonObject.getJSONArray("catalogo_estado")));
                        catalogos.setEstadosTransicion(getCatalogos(jsonObject.getJSONArray("catalogo_estado_transicion")));
                        catalogos.setRoles(getCatalogos(jsonObject.getJSONArray("catalogo_rol")));
                        catalogos.setTipoPago(getCatalogos(jsonObject.getJSONArray("catalogo_tipo_pago")));
                        catalogos.setTipoHabitaciones(getCatalogoTipoHabitaciones(jsonObject.getJSONArray("catalogo_tipo_habitacion")));

                        if (onPostExecuteListener != null) {
                            onPostExecuteListener.onPostExecute(catalogos);
                        }

                    } else {
                        String error = jsonObject.getString("error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("TareaLogin jsonObject es null");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TareaCatalogosWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(TareaCatalogosWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<Catalogo> getCatalogos(ArrayList<JSONObject> catalogo) {

        ArrayList<Catalogo> elementos = new ArrayList<>();

        for (int i = 0; i < catalogo.size(); i++) {
            JSONObject cat = catalogo.get(i);
            Catalogo catalogoElemento = new Catalogo();
            catalogoElemento.setId(cat.getInt("id"));
            catalogoElemento.setNombre(cat.getString("nombre"));
            elementos.add(catalogoElemento);

        }
        
        return elementos;

    }
    
    private ArrayList<CatalogoTipoHabitacion> getCatalogoTipoHabitaciones(ArrayList<JSONObject> catalogo){
        ArrayList<CatalogoTipoHabitacion> elementos = new ArrayList<>();

        for (int i = 0; i < catalogo.size(); i++) {
            JSONObject cat = catalogo.get(i);
            CatalogoTipoHabitacion catalogoElemento = new CatalogoTipoHabitacion();
            catalogoElemento.setId(cat.getInt("id"));
            catalogoElemento.setNombre(cat.getString("nombre"));
            catalogoElemento.setCosto(cat.getDouble("costo"));
            catalogoElemento.setCostoPersonaExtra(cat.getDouble("costo_persona_extra"));
            catalogoElemento.setCostoHoraExtra(cat.getDouble("costo_hora_extra"));
            elementos.add(catalogoElemento);

        }
        
        return elementos;
    }

}
