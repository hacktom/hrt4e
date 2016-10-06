/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.threads;

import com.caronte.json.JSONObject;
import com.hotel.hotel.models.Usuario;
import com.hoteles.hrt4e.ws.WebServices;

/**
 *
 * @author Tadeo-developer
 */
public class TareaLogin extends Tarea{

    private String usuario;
    private String contrasenia;
    
    public TareaLogin(String usuario,String contrasenia){
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
    
    @Override
    public void run() {
    
        JSONObject jsonObject = WebServices.servicioLogin(usuario, contrasenia);
        
        parseJson(jsonObject);
       
    }
    
    private void parseJson(JSONObject jsonObject){
    
        if(jsonObject!=null){
            try {
                Object code = jsonObject.getInt("code");

                System.out.println("TareaLogin: "+code);
                /*if(code==1){

                    
                    JSONObject usuarioJson = jsonObject.getJSONObject("usuario");
                    Usuario usuario = new Usuario();
                    usuario.setId(usuarioJson.getInt("id"));
                    usuario.setNombre(usuarioJson.getString("nombre"));
                    usuario.setIdRol(usuarioJson.getInt("id_rol"));
                    usuario.setRol(usuarioJson.getString("rol"));


                    if(onPostExecuteListener!=null){
                        onPostExecuteListener.onPostExecute(null);
                    }

                }*/




            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
           System.out.println("TareaLogin jsonObject es null");
        }
        
        
        
        
    }
    
}
