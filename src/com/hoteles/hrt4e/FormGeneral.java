/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e;

import javax.swing.JFrame;

/**
 *
 * @author Tadeo-developer
 */
public abstract class FormGeneral extends JFrame{
    interface OnFormResultListener{
        void onFormResult(Object object);
    }
    
    private OnFormResultListener onFormResultListener;
    
    public void setOnFormResultListener(OnFormResultListener onFormResultListener){
        this.onFormResultListener = onFormResultListener;
    }
    
    public OnFormResultListener getOnFormResultListener(){
        return onFormResultListener;
    }
}
