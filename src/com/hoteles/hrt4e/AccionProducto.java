/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoteles.hrt4e;

import com.hoteles.hrt4e.models.Catalogo;
import com.hoteles.hrt4e.models.CatalogoProducto;
import com.hoteles.hrt4e.threads.TareaProductoWorker;
import com.hoteles.hrt4e.threads.Worker;
import com.hoteles.hrt4e.utils.Singleton;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Hacktom
 */
public class AccionProducto extends FormGeneral {

    private static final int AGREGAR = 1;
    private static final int ACTUALIZAR = 2;
    private static final int ELIMINAR = 3;
    String cantidad;
    String costo;
    String producto;
    private ArrayList<Catalogo> catTipoProducto;
    private CatalogoProducto catalogoProducto;
    private int tipoAccion;
    
    public AccionProducto(int tipoAccion, CatalogoProducto catalogoProducto) {
        initComponents();
      
        this.tipoAccion = tipoAccion;
        this.catalogoProducto = catalogoProducto;
        catTipoProducto = Singleton.getInstance().getCatalogos().getTipoProducto();
       
        jComboBox1.removeAllItems();
        
        for(Catalogo cat : catTipoProducto){
            jComboBox1.addItem(cat.getNombre());
        }
        
        if(tipoAccion==AGREGAR){
            jComboBox1.setSelectedItem(setSelectedComboBox());
            jButton1.setText("Agregar");
            jLabel4.setText("Agregar Producto");
        }else if(tipoAccion==ACTUALIZAR){
            
            textFieldProducto.setText(catalogoProducto.getNombre());
            textFieldCosto.setText(catalogoProducto.getCosto()+"");
            textFieldCantidad.setText(catalogoProducto.getCantidad()+"");
       
            jComboBox1.setSelectedItem(setSelectedComboBox());
            
            jComboBox1.setEnabled(false);
            jButton1.setText("Actualizar");
            jLabel4.setText("Actualizar Producto");
           
           
        }else if(tipoAccion==ELIMINAR){
            jComboBox1.setSelectedItem(setSelectedComboBox());
            textFieldProducto.setText(catalogoProducto.getNombre());
            textFieldProducto.setEnabled(false);
            
            textFieldCosto.setText(catalogoProducto.getCosto()+"");
            textFieldCosto.setEnabled(false);
            
            textFieldCantidad.setText(catalogoProducto.getCantidad()+"");
            textFieldCantidad.setEnabled(false);
            
            jComboBox1.setEnabled(false);
            jButton1.setText("Eliminar");
            jLabel4.setText("Eliminar Producto");
        }
        
    }
    
    private String setSelectedComboBox(){
        for(int i = 0;i<catTipoProducto.size();i++){
            System.out.println("catTipo: "+catTipoProducto.get(i).getId());
            if(catTipoProducto.get(i).getId()==catalogoProducto.getIdCatalogoTipoProducto()){
                return catTipoProducto.get(i).getNombre();
            }
        }
        return null;
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldCantidad = new javax.swing.JTextField();
        textFieldProducto = new javax.swing.JTextField();
        textFieldCosto = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textFieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldCantidadActionPerformed(evt);
            }
        });

        textFieldProducto.setToolTipText("");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Cantidad");

        jLabel2.setText("Producto");

        jLabel3.setText("Costo");

        jLabel4.setText("Agregar Producto");

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Tipo");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cantidad =textFieldCantidad.getText(); 
        int cantidadNumero = Integer.parseInt(cantidad);
        System.out.println("Cantidad: "+cantidad);
        ////////////////////////////////////////////////////////
        costo =textFieldCosto.getText(); 
        double costoNumero = Double.parseDouble(costo);
        System.out.println("Costo: "+costo);
        ////////////////////////////////////////////////////////
        producto =textFieldProducto.getText(); 
        
        System.out.println("Producto : "+producto);
        
        Catalogo tipoSeleccionado = catTipoProducto.get(jComboBox1.getSelectedIndex());
        
        System.out.println("tipoSeleccionado : "+tipoSeleccionado.getId());
        
        String method = "";
        boolean ejecutar = true;
        if(tipoAccion==AGREGAR){
       
            catalogoProducto.setIdInventario(1);
            catalogoProducto.setIdCatalogoTipoProducto(tipoSeleccionado.getId());
            catalogoProducto.setNombre(producto);
            catalogoProducto.setCosto(costoNumero);
            catalogoProducto.setCantidad(cantidadNumero);
            method = "POST";
            
           
        }else if(tipoAccion==ACTUALIZAR){
            method = "PUT";
            catalogoProducto.setIdInventario(1);
            catalogoProducto.setIdCatalogoTipoProducto(tipoSeleccionado.getId());
            catalogoProducto.setNombre(producto);
            catalogoProducto.setCosto(costoNumero);
            catalogoProducto.setCantidad(cantidadNumero);
            
        }else{
            method = "DELETE";
            
           int option = JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar "+cantidad+" productos?");
           
           
           if(option!=0){
                ejecutar = false;
           }
           
           
            
        }
        
        if(ejecutar){
            tarea(method);
        }
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tarea(String method){
        TareaProductoWorker tarea = new TareaProductoWorker(method);
        tarea.setCatalogoProducto(catalogoProducto);
        tarea.setOnPostExecuteListener(new Worker.OnPostExecuteListener() {

            @Override
            public void onPostExecute(Object object) {
            
               
                
                if(getOnFormResultListener()!=null){   
                    getOnFormResultListener().onFormResult((CatalogoProducto)object);
                    setVisible(false);
                }else{
                    System.out.println("getOnFormResultListener es null");
                }
         
            }
        });
        
        tarea.execute();
    }
    
    private void textFieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldCantidadActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField textFieldCantidad;
    private javax.swing.JTextField textFieldCosto;
    private javax.swing.JTextField textFieldProducto;
    // End of variables declaration//GEN-END:variables
}
