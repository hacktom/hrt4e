package com.hoteles.hrt4e;

import com.hoteles.hrt4e.models.Catalogos;
import com.hoteles.hrt4e.models.Habitacion;
import com.hoteles.hrt4e.models.HabitacionRenta;
import com.hoteles.hrt4e.models.HabitacionTipo;
import com.hoteles.hrt4e.models.Margenes;
import com.hoteles.hrt4e.models.MiModelo;
import com.hoteles.hrt4e.threads.Tarea;
import com.hoteles.hrt4e.threads.TareaLogin;
import com.hoteles.hrt4e.ws.WebServices;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import com.hoteles.hrt4e.models.Usuario;
import com.hoteles.hrt4e.threads.TareaCatalogos;
import com.hoteles.hrt4e.threads.TareaCatalogosWorker;
import com.hoteles.hrt4e.threads.TareaDetalleHabitacionWorker;
import com.hoteles.hrt4e.threads.TareaHabitacionesTransicionWorker;
import com.hoteles.hrt4e.threads.Worker;
import com.hoteles.hrt4e.utils.Singleton;
import com.hoteles.hrt4e.utils.Utils;
import java.awt.Dimension;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

public class Principal extends javax.swing.JFrame implements MouseListener {

    private static final int MARGEN = 10;
    private static final int CANTIDAD_HORIZONTAL = 10;
    private static final int CANTIDAD_VERTICAL = 10;

    public Principal() {
        initComponents();

        Singleton.getInstance().setIdHotel(1);
        Singleton.getInstance().setMacAddress(Utils.getMacAddress());

        //jPanel3.removeAll();
        jPanel3.setVisible(true);
        addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                if (Singleton.getInstance().getHabitaciones() != null) {
                    //addViews(MARGEN, CANTIDAD_HORIZONTAL, CANTIDAD_VERTICAL, Singleton.getInstance().getHabitaciones());
                    //addViews(MARGEN, CANTIDAD_HORIZONTAL, CANTIDAD_VERTICAL, genereateHabitacionesDummy(120));
                    initViewsVertical(Singleton.getInstance().getHabitaciones());
                }

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        setExtendedState(MAXIMIZED_BOTH);
        //para evitar dejar solamente maximizado
      //  setVisible(true);
       // setResizable(false);

        TareaCatalogosWorker wor = new TareaCatalogosWorker();
        wor.setOnPostExecuteListener(new TareaCatalogosWorker.OnPostExecuteListener() {

            @Override
            public void onPostExecute(Object object) {
                if (object instanceof Catalogos) {

                    System.out.println("actualizando catalogos");
                    Catalogos catalogos = (Catalogos) object;
                    Singleton.getInstance().setHabitaciones(catalogos.getHabitaciones());
                    MiModelo modelo = new MiModelo();
                    ArrayList<HabitacionTipo> habitacionesTipo = new ArrayList<>();
                    habitacionesTipo.add(obtenerCantidadTipoHabitacion(1));
                    habitacionesTipo.add(obtenerCantidadTipoHabitacion(2));

                    modelo.habitaciones = habitacionesTipo;
                    jTable1.setModel(modelo);
                    //addViews(MARGEN, CANTIDAD_HORIZONTAL, CANTIDAD_VERTICAL, catalogos.getHabitaciones());
                    //addViews(MARGEN, CANTIDAD_HORIZONTAL, CANTIDAD_VERTICAL, genereateHabitacionesDummy(120));
                    initViewsVertical(catalogos.getHabitaciones());
                    initUpdates();

                }
            }
        });
        wor.execute();

    }

    private void initUpdates() {
        Thread refresh = new Thread(new Runnable() {

            @Override
            public void run() {

                while (true) {

                    TareaHabitacionesTransicionWorker wor = new TareaHabitacionesTransicionWorker();
                    wor.setOnPostExecuteListener(new TareaHabitacionesTransicionWorker.OnPostExecuteListener() {

                        @Override
                        public void onPostExecute(Object object) {
                            if (object instanceof Catalogos) {

                                System.out.println("actualizando habitaciones");
                                Catalogos catalogos = (Catalogos) object;
                                Singleton.getInstance().actualizarHabitaciones(catalogos.getHabitaciones());
                                MiModelo modelo = new MiModelo();
                                ArrayList<HabitacionTipo> habitacionesTipo = new ArrayList<>();
                                habitacionesTipo.add(obtenerCantidadTipoHabitacion(1));
                                habitacionesTipo.add(obtenerCantidadTipoHabitacion(2));

                                modelo.habitaciones = habitacionesTipo;
                                jTable1.setModel(modelo);
                                //addViews(MARGEN, CANTIDAD_HORIZONTAL, CANTIDAD_VERTICAL, Singleton.getInstance().getHabitaciones());
                                //addViews(MARGEN, CANTIDAD_HORIZONTAL, CANTIDAD_VERTICAL, genereateHabitacionesDummy(120));
                                initViewsVertical(Singleton.getInstance().getHabitaciones());
                            }
                        }
                    });
                    wor.execute();

                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        refresh.start();
    }

    private void initViewsVertical(ArrayList<Habitacion> habitaciones) {
        jPanel1.removeAll();
        Margenes margenes = new Margenes();
        margenes.setMargenArriba(0);
        margenes.setMargenDerecha(0);
        margenes.setMargenAbajo(0);
        margenes.setMargenIzquierda(0);
        addViewsVertical(margenes, 10, separarNumeroHabitacionesInicioFin(habitaciones,39,75,true));
        
        
        Margenes margenes2a = new Margenes();
        margenes2a.setMargenArriba(2);
        margenes2a.setMargenDerecha(0);
        margenes2a.setMargenAbajo(0);
        margenes2a.setMargenIzquierda(4);
        addViewsVertical(margenes2a, 10, separarNumeroHabitacionesInicioFin(habitaciones,40,54,true));
        
        Margenes margenes2b = new Margenes();
        margenes2b.setMargenArriba(15);
        margenes2b.setMargenDerecha(0);
        margenes2b.setMargenAbajo(0);
        margenes2b.setMargenIzquierda(4);
        addViewsVertical(margenes2b, 10, separarNumeroHabitacionesInicioFin(habitaciones,60,74,true));
        
        
        
        Margenes margenes3a = new Margenes();
        margenes3a.setMargenArriba(2);
        margenes3a.setMargenDerecha(0);
        margenes3a.setMargenAbajo(0);
        margenes3a.setMargenIzquierda(6);
        addViewsVertical(margenes3a, 10, separarNumeroHabitacionesFinInicio(habitaciones,38,24,true));
        
        Margenes margenes3b = new Margenes();
        margenes3b.setMargenArriba(15);
        margenes3b.setMargenDerecha(0);
        margenes3b.setMargenAbajo(0);
        margenes3b.setMargenIzquierda(6);
        addViewsVertical(margenes3b, 10, separarNumeroHabitacionesFinInicio(habitaciones,16,2,true));
        
        
        Margenes margenes4 = new Margenes();
        margenes4.setMargenArriba(0);
        margenes4.setMargenDerecha(0);
        margenes4.setMargenAbajo(0);
        margenes4.setMargenIzquierda(10);
        addViewsVertical(margenes4, 10, separarNumeroHabitacionesFinInicio(habitaciones,37,1,true));
        
        Margenes margenes5 = new Margenes();
        margenes5.setMargenArriba(0);
        margenes5.setMargenDerecha(0);
        margenes5.setMargenAbajo(0);
        margenes5.setMargenIzquierda(2);
        addViewsHorizontal(margenes5, 10, separarNumeroHabitacionesFinInicio(habitaciones,83,76,false));
    }
    
    private ArrayList<Habitacion> separarNumeroHabitacionesInicioFin(ArrayList<Habitacion> habitaciones, int inicio, int fin, boolean par){
    
        ArrayList<Habitacion> habitacionesAux = new ArrayList<>();
       
        
        for(int i = inicio;i<=fin;){
            
            for(Habitacion habitacion:habitaciones){
                if(i==habitacion.getNumeroHabitacion()){
                    habitacionesAux.add(habitacion);
                }
            }
            
            if(par)
                i+=2;
            else
                i++;
            
           
        }
        
        return habitacionesAux;
        
    }
    
    private ArrayList<Habitacion> separarNumeroHabitacionesFinInicio(ArrayList<Habitacion> habitaciones, int fin, int inicio, boolean par){
    
        ArrayList<Habitacion> habitacionesAux = new ArrayList<>();
        
        for(int i = fin;i>=inicio;){
            
            for(Habitacion habitacion:habitaciones){
                if(i==habitacion.getNumeroHabitacion()){
                    habitacionesAux.add(habitacion);
                }
            }
            
            if(par)
                i-=2;
            else
                i--;
            
        }
        
        return habitacionesAux;
        
    }

    private ArrayList<Habitacion> genereateHabitacionesDummyInicioFin(int inicio, int fin,boolean par) {
        ArrayList<Habitacion> habs = new ArrayList<>();
        for (int i = inicio; i <= fin;) {
            Habitacion aux = new Habitacion();
            aux.setNumeroHabitacion(i );
            aux.setEstado(1);
            habs.add(aux);
            
            if(par)
                i+=2;
            else
                i++;
        }

        return habs;
    }
    
    
    
    private ArrayList<Habitacion> genereateHabitacionesDummyFinInicio(int fin, int inicio, boolean par) {
        ArrayList<Habitacion> habs = new ArrayList<>();
        for (int i = fin; i >= inicio;) {
            Habitacion aux = new Habitacion();
            aux.setNumeroHabitacion(i);
            aux.setEstado(1);
            habs.add(aux);
            
            if(par)
                i-=2;
            else
                i--;
        }

        return habs;
    }

    private void addViews(int margen, int cantidadHorizontal, int cantidadVertical, ArrayList<Habitacion> habitaciones) {

        jPanel1.removeAll();

        int altoGeneral = ((jPanel1.getHeight() - ((cantidadVertical + 1) * margen)) / cantidadVertical);
        int anchoGeneral = ((jPanel1.getWidth() - ((cantidadHorizontal + 1) * margen)) / cantidadHorizontal);
        int margenInitAlto = margen;
        int margenInitAncho = margen;
        int contadorHabitaciones = 0;

        for (int i = 0; i < cantidadVertical; i++) {

            int altoActual = margenInitAlto + (altoGeneral * i);
            margenInitAncho = margen;
            for (int j = 0; j < cantidadHorizontal; j++) {

                if (contadorHabitaciones < habitaciones.size()) {

                    int anchoActual = margenInitAncho + (anchoGeneral * j);

                    JPanel panel = new JPanel();
                    int estado = habitaciones.get(contadorHabitaciones).getEstado();
                    if (estado == 1) {
                        panel.setBackground(new Color(1, 223, 1));
                    } else if (estado == 2) {
                        panel.setBackground(new Color(239, 127, 42));
                    } else {
                        panel.setBackground(new Color(102, 102, 102));
                    }

                    panel.setBounds(anchoActual, altoActual, anchoGeneral, altoGeneral);
                    JLabel label = new JLabel((i * cantidadHorizontal) + (j + 1) + "");

                    int lenghtLabel = label.getText().length() * 3;
                    label.setBounds((panel.getWidth() / 2) - lenghtLabel, 0, panel.getWidth(), panel.getHeight());
                    panel.add(label);
                    panel.addMouseListener(this);
                    margenInitAncho += margen;
                    jPanel1.add(panel);
                }

                contadorHabitaciones++;

            }

            margenInitAlto += margen;

        }

        jPanel1.repaint();
        //add(jPanel1);
        //repaint();
        //invalidate();

    }

    private void addViewsVertical(Margenes margenes, int separador, ArrayList<Habitacion> habitaciones) {

        int cantidadHorizontal = 10;
        int cantidadAlto = 20;
        int margen = separador;
        //int altoGeneral = (((jPanel1.getHeight() - ((cantidadVertical + 1) * margen)) - (marginTop + marginButtom)) / cantidadVertical);
        int altoGeneral = ((jPanel1.getHeight() - ((cantidadAlto + 1) * margen))  / cantidadAlto);

        //int anchoGeneral = (((jPanel1.getWidth() - ((cantidadHorizontal + 1) * margen)) - (marginLeft + marginRight)) / cantidadHorizontal);
        int anchoGeneral = ((jPanel1.getWidth() - ((cantidadHorizontal + 1) * margen))  / cantidadHorizontal);

        int marginTop = altoGeneral*margenes.getMargenArriba();
        int marginButtom = altoGeneral*margenes.getMargenAbajo();
        int marginLeft = anchoGeneral*margenes.getMargenIzquierda();
        int marginRight = anchoGeneral*margenes.getMargenDerecha();
        
       
        int margenInitAlto = margen + marginTop;
        int margenInitAncho = margen + marginLeft;

        int anchoActual = margenInitAncho + (anchoGeneral * 0);
        for (int i = 0; i < habitaciones.size(); i++) {
            int altoActual = margenInitAlto + (altoGeneral * i);

            //for (int j = 0; j < cantidadHorizontal; j++) {
            // anchoActual = margenInitAncho + (anchoGeneral * j);
            JPanel panel = new JPanel();
            panel = getPanelEstado(panel, habitaciones.get(i).getEstado());
            panel.setBounds(anchoActual, altoActual, anchoGeneral, altoGeneral);
            JLabel label = new JLabel(habitaciones.get(i).getNumeroHabitacion()+"");

            int lenghtLabel = label.getText().length() * 3;
            label.setBounds((panel.getWidth() / 2) - lenghtLabel, 0, panel.getWidth(), panel.getHeight());
            panel.add(label);
            panel.addMouseListener(this);
            //margenInitAncho += margen;
            jPanel1.add(panel);

            //}
            margenInitAlto += margen;
        }

        jPanel1.repaint();
    }
    
       private void addViewsHorizontal(Margenes margenes, int separador,  ArrayList<Habitacion> habitaciones) {

        int cantidadHorizontal = 12;
        int cantidadAlto = 20;
        int margen = separador;
        //int altoGeneral = (((jPanel1.getHeight() - ((cantidadVertical + 1) * margen)) - (marginTop + marginButtom)) / cantidadVertical);
        int altoGeneral = ((jPanel1.getHeight() - ((cantidadAlto + 1) * margen))  / cantidadAlto);

        //int anchoGeneral = (((jPanel1.getWidth() - ((cantidadHorizontal + 1) * margen)) - (marginLeft + marginRight)) / cantidadHorizontal);
        int anchoGeneral = ((jPanel1.getWidth() - ((cantidadHorizontal + 1) * margen))  / cantidadHorizontal);

        int marginTop = altoGeneral*margenes.getMargenArriba();
        int marginButtom = altoGeneral*margenes.getMargenAbajo();
        int marginLeft = anchoGeneral*margenes.getMargenIzquierda();
        int marginRight = anchoGeneral*margenes.getMargenDerecha();
        
       
        int margenInitAlto = margen + marginTop;
        int margenInitAncho = margen + marginLeft;

        
        int altoActual = margenInitAlto + (altoGeneral * 0);
        for (int i = 0; i < habitaciones.size(); i++) {
            int anchoActual = margenInitAncho + (anchoGeneral * i);

            //for (int j = 0; j < cantidadHorizontal; j++) {
            // anchoActual = margenInitAncho + (anchoGeneral * j);
            JPanel panel = new JPanel();
            panel = getPanelEstado(panel, habitaciones.get(i).getEstado());
            panel.setBounds(anchoActual, altoActual, anchoGeneral, altoGeneral);
            JLabel label = new JLabel(habitaciones.get(i).getNumeroHabitacion()+"");

            int lenghtLabel = label.getText().length() * 3;
            label.setBounds((panel.getWidth() / 2) - lenghtLabel, 0, panel.getWidth(), panel.getHeight());
            panel.add(label);
            panel.addMouseListener(this);
            //margenInitAncho += margen;
            jPanel1.add(panel);

            //}
            margenInitAncho += margen;
        }

        jPanel1.repaint();
    }

    private JPanel getPanelEstado(JPanel panel, int estado) {
        if (estado == 1) {
            panel.setBackground(new Color(1, 223, 1));
        } else if (estado == 2) {
            panel.setBackground(new Color(239, 127, 42));
        } else {
            panel.setBackground(new Color(102, 102, 102));
        }

        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel1);

        jTextField1.setMinimumSize(new java.awt.Dimension(22, 20));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Recepcionista:");

        jLabel5.setText("Ricardo Zavala");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("2 /Octubre/2016");

        jLabel6.setText("Habitaciones Total:");

        jLabel7.setText("45");

        jLabel3.setText("1:53 a.m.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jButton1))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(106, 106, 106))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Restaurante");

        jMenuItem2.setText("Agregar pedido");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Inventario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Contabilidad");

        jMenuItem3.setText("Balance");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Clientes");

        jMenuItem4.setText("Dar de alta");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Ver lista");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Empleados");

        jMenuItem7.setText("Lista");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane2)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Inventario inventario = new Inventario();
        inventario.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        inventario.setVisible(true);
        inventario.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Pedido pedido = new Pedido();
        pedido.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pedido.setVisible(true);
        pedido.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Balance balance = new Balance();
        balance.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        balance.setVisible(true);
        balance.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        AgregarCliente agregarCliente = new AgregarCliente();
        agregarCliente.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        agregarCliente.setVisible(true);
        agregarCliente.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        ListaClientes listaClientes = new ListaClientes();
        listaClientes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        listaClientes.setVisible(true);
        listaClientes.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        JPanel panel = (JPanel) e.getComponent();
        JLabel label = (JLabel) panel.getComponent(0);
        String text = label.getText();
        System.out.println("text: " + text);

        Habitacion hab = Singleton.getInstance().buscarNumeroHabitacion(Integer.parseInt(text));
        System.out.println("hab: " + hab.getNumeroHabitacion());
        System.out.println("estado: " + hab.getEstado());

        TareaDetalleHabitacionWorker tarea = new TareaDetalleHabitacionWorker(hab.getNumeroHabitacion());
        tarea.setOnPostExecuteListener(new Worker.OnPostExecuteListener() {

            @Override
            public void onPostExecute(Object object) {
                HabitacionRenta habitacionRenta = (HabitacionRenta) object;
                System.out.println("modelo: " + habitacionRenta.getModelo());
            }
        });
        tarea.execute();

        //PropiedadesHabitacion pro = new PropiedadesHabitacion();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public HabitacionTipo obtenerCantidadTipoHabitacion(int tipoHabitacion) {
        ArrayList<Habitacion> habitaciones = Singleton.getInstance().getHabitacionesTipo(tipoHabitacion);
        HabitacionTipo habitacionTipo = new HabitacionTipo();
        int contadorLimpias = 0;
        int contadorOcupadas = 0;
        int contadorSucias = 0;

        for (Habitacion hab : habitaciones) {
            if (hab.getEstado() == 1) {
                contadorLimpias++;
            } else if (hab.getEstado() == 2) {
                contadorOcupadas++;
            } else if (hab.getEstado() == 3) {
                contadorSucias++;
            }
        }
        if (habitaciones.size() > 1) {
            habitacionTipo.setTipo(habitaciones.get(0).getTipoHabitacionText());
        }
        habitacionTipo.setCantidadLimpio(contadorLimpias);
        habitacionTipo.setCantidadOcupado(contadorOcupadas);
        habitacionTipo.setCantidadSucio(contadorSucias);
        return habitacionTipo;
    }

}
