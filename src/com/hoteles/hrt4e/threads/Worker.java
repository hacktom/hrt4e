/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.threads;

import com.caronte.json.JSONObject;
import javax.swing.SwingWorker;

/**
 *
 * @author Tadeo-developer
 */
public abstract class Worker extends SwingWorker<JSONObject, Void>{
     public interface OnPostExecuteListener {

        void onPostExecute(Object object);
    }

    protected OnPostExecuteListener onPostExecuteListener;
    
    public OnPostExecuteListener getOnPostExecuteListener() {
        return onPostExecuteListener;
    }

    public void setOnPostExecuteListener(OnPostExecuteListener onPostExecuteListener) {
        this.onPostExecuteListener = onPostExecuteListener;
    }
}
