/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e.threads;

/**
 *
 * @author Tadeo-developer
 */
public abstract class Tarea implements Runnable{
    public interface OnPostExecuteListener{
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
