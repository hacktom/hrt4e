/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.threads;

import com.caronte.json.JSONObject;
import com.hoteles.hrt4e.Principal;
import com.hoteles.hrt4e.models.CatalogoProducto;
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
public class TareaAgregarProductoWorker extends Worker {

    private CatalogoProducto catalogoProducto;
    
    public void setCatalogoProducto(CatalogoProducto catalogoProducto){
        this.catalogoProducto = catalogoProducto;
    }
    @Override
    protected JSONObject doInBackground() throws Exception {
        
        int idInventario = catalogoProducto.getIdInventario();
        int idTipoProducto = catalogoProducto.getIdCatalogoTipoProducto();
        String nombre = catalogoProducto.getNombre();
        double costo = catalogoProducto.getCosto();
        int cantidad = catalogoProducto.getCantidad();
        
        JSONObject jsonObject = WebServices.servicioAgregarProducto(idInventario,idTipoProducto,nombre,costo,cantidad);

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

                        JSONObject catalogoProductoJson = jsonObject.getJSONObject("catalogo_producto");
                        
                        CatalogoProducto catalogoProducto = new CatalogoProducto();
                        catalogoProducto.setId(catalogoProductoJson.getInt("id"));
                        catalogoProducto.setIdInventario(catalogoProductoJson.getInt("id_inventario"));
                        catalogoProducto.setIdCatalogoTipoProducto(catalogoProductoJson.getInt("id_catalogo_tipo_producto"));
                        catalogoProducto.setNombre(catalogoProductoJson.getString("nombre"));
                        catalogoProducto.setCosto(catalogoProductoJson.getDouble("costo"));
                        catalogoProducto.setCantidad(catalogoProductoJson.getInt("cantidad"));
                        if (onPostExecuteListener != null) {
                            onPostExecuteListener.onPostExecute(catalogoProducto);
                        }

                    } else {

                        String error = jsonObject.getString("error");
                        System.out.println("Worker error: "+error);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Worker jsonObject es null");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TareaAgregarProductoWorker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(TareaAgregarProductoWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
