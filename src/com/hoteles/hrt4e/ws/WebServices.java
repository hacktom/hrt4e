package com.hoteles.hrt4e.ws;

import com.caronte.json.JSON;
import com.caronte.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Tadeo-developer on 10/09/16.
 */
public class WebServices {

    public static final String URL_SERVIDOR = "http://www.luctadeveloper.com/hoteles/";
    public static final String LOGIN = "login.php";
    public static final String CATALOGOS = "catalogos.php";
    public static final String DETALLE_HABITACION = "detalle_habitacion.php";
    public static final String CAMBIAR_ESTADO = "cambiar_estado.php";
    public static final String REGISTRAR_HABITACION = "registrar_habitacion.php";
    public static final String ACTUALIAZR_HABITACION_RENTA = "actualizar_habitacion_renta.php";

    private static JSONObject jObj 	= null;
    private static String json 		= "";

    public static JSONObject servicioLogin(String usuario,String contrasenia){

         JSONObject jsonObject = new JSONObject();
            
        try {  
            jsonObject.addPair("usuario",usuario);
            jsonObject.addPair("contrasenia",contrasenia);
        
        } catch (Exception ex) {
            Logger.getLogger(WebServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
            return request(URL_SERVIDOR+LOGIN,jsonObject.toString(),true,"POST");
    }

    public static JSONObject servicioCatalogos(){

        return request(URL_SERVIDOR+CATALOGOS,"",true,"POST");
    }

    public static JSONObject servicioDetalleHabitacion(int idHabitacion){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.addPair("idHabitacion",idHabitacion);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request(URL_SERVIDOR+DETALLE_HABITACION,jsonObject.toString(),true,"POST");
    }

    public static JSONObject servicioCambiarEstado(String idHabitacion,String nuevoEstado,String idUsuario,String actividad,String fecha){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.addPair("idHabitacion",Integer.parseInt(idHabitacion));
            jsonObject.addPair("nuevoEstado",Integer.parseInt(nuevoEstado));
            jsonObject.addPair("idUsuario",Integer.parseInt(idUsuario));
            jsonObject.addPair("actividad",actividad);
            jsonObject.addPair("fecha",fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request(URL_SERVIDOR+CAMBIAR_ESTADO,jsonObject.toString(),true,"POST");
    }

    public static JSONObject servicioActualizarHabitacionRenta(String idHabitacionRenta,String personasExtra,String horasExtra){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.addPair("idHabitacionRenta",Integer.parseInt(idHabitacionRenta));
            jsonObject.addPair("personasExtra",Integer.parseInt(personasExtra));
            jsonObject.addPair("horasExtra",Integer.parseInt(horasExtra));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request(URL_SERVIDOR+ACTUALIAZR_HABITACION_RENTA,jsonObject.toString(),true,"POST");
    }

    public static JSONObject servicioRegistrarHabitacion(String idHabitacion,String idTipoPago,String tipoCliente,String identificacion,String modelo,String personasExtra,String horasExtra,String fechaHora){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.addPair("idHabitacion",Integer.parseInt(idHabitacion));
            jsonObject.addPair("idTipoPago",Integer.parseInt(idTipoPago));
            jsonObject.addPair("tipoCliente",tipoCliente);
            jsonObject.addPair("identificacion",identificacion);
            jsonObject.addPair("modelo",modelo);
            jsonObject.addPair("personasExtra",Integer.parseInt(personasExtra));
            jsonObject.addPair("horasExtra",Integer.parseInt(horasExtra));
            jsonObject.addPair("fechaHora",fechaHora);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return request(URL_SERVIDOR+REGISTRAR_HABITACION,jsonObject.toString(),true,"POST");
    }

    private static JSONObject request(String requestURL, String params, boolean contentTypeJson, HashMap<String,String> headers, String method){


        URL url;
        String response = "";
        try {
           
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(method);

            //conn.setDoInput(true);
            //conn.setDoOutput(true);


            if(headers!=null){
                String txtHeaders = "[";
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();

                    txtHeaders+=key+":"+value+",";
                    conn.setRequestProperty(key,value);

                }
                txtHeaders+="]";

                 System.out.println("WebServices headers: "+txtHeaders);
            }

            if(contentTypeJson)conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            System.out.println("WebServices request: " + params.toString());

            if(method.equals("POST")){
                conn.setDoOutput(true);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
                bw.write(params);

                bw.flush();
                bw.close();
            }



            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK || responseCode == HttpsURLConnection.HTTP_UNAUTHORIZED ||
                    responseCode == HttpsURLConnection.HTTP_CONFLICT) {

                BufferedReader br;
                if(responseCode == HttpsURLConnection.HTTP_OK){

                    br=new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                }else{
                    br=new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
                }
                String line;
                while ((line=br.readLine()) != null) {
                    response+=line;
                }

            }else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            json = response;
             System.out.println("WebServices response txt: "+json);
             
            jObj = JSON.parse(json);
          
            
             System.out.println("WebServices response: " + jObj.toString());

        } catch (Exception e) {
            System.out.println("WebServices http json object exception: " + e);
           /* try{
                 System.out.println("WebServices http json object exception: " + e);
                 System.out.println("WebServices convert to JsonArray: "+json);
               
                JSONArray jsonArray =  new JSONArray(json);
                JSONObject jsonObjectAux = new JSONObject();
                jsonObjectAux.addPair("values",jsonArray);
                jObj = jsonObjectAux;
            }catch (Exception e1){
                System.out.println("WebServices http json object exception to Array: " + e);
                jObj = null;
            }*/


        }
        return jObj;
    }

    private static JSONObject request(String requestURL,
                                      String params, boolean contentTypeJson,String method) {

        return request(requestURL,params,contentTypeJson,null,method);


    }

}
