package clases;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;

public class GUIppal extends JFrame implements Runnable 
{
    JDialog ssDialog, cantDialog, verifDialog;
    JPanel panel_medio = new JPanel();
    JPanel panelConsola = new JPanel();
    JButton boton, guardarSS, cancelarSS, playButton,stopButton, guardarCant, cancelarCant, staButton, routerButton,apButton;    
    JFrame ventana;
    
    int auxCantRouter, auxCantSTA, auxCantAP, limite;
    String tipoBoton, tipoRed = "", tipoIP, lanIP, maskLanIP, nombreRouter;
    volatile boolean stop =  false;
    
    ArrayList <JButton> botonesRouter = new ArrayList<>();
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    ArrayList <JButton> botonesAP = new ArrayList<>();
    
    PlayClass play = new PlayClass();
    Revisiones revisar;
    Resetear reset;
    ConfigRouter configRouter;
    PingDialog pingDialog = new PingDialog(this, true);
    Ping ping = new Ping("");
    AddBotones addBoton = new AddBotones();
    Consola consola = new Consola();
    CargarSim cargar = new CargarSim();
    Abrir abrir = new Abrir(this, true);
    SignalRange senal = new SignalRange();
    Guardar guardar = new Guardar(this, true);
    Router router;
    
    HashMap routers = new HashMap();
    HashMap stas = new HashMap();
    HashMap aps = new HashMap();
    
    Thread hilo = new Thread(this);
    
    public GUIppal()
    {
        ImageIcon icono;
        initComponents();
        
        Toolkit tk = Toolkit.getDefaultToolkit();  
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight())-30;
        
        this.setSize(xSize, ySize);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        JPanel panel_izquierdo = new JPanel();
        
        panel_superior.setSize(xSize,50);
        
        routerButton = new JButton();
        icono = new ImageIcon("src/Imagenes/botonrouter.png");
        routerButton.setIcon(icono);
        routerButton.setText("R");
        routerButton.setHorizontalTextPosition(JButton.CENTER);
        routerButton.setToolTipText("Router");
        routerButton.setSize(44,37);
        routerButton.setBorder(null);
        routerButton.setContentAreaFilled(false);
        routerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               addRouterActionPerformed(e);
            }
        });
        routerButton.setEnabled(false);
        
        staButton = new JButton();
        icono = new ImageIcon("src/Imagenes/botonSTA.png");
        staButton.setIcon(icono);
        staButton.setToolTipText("STA");
        staButton.setSize(38,37);
        staButton.setBorder(null);
        staButton.setContentAreaFilled(false);
        staButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               addSTAActionPerformed(e);
            }
        });
        staButton.setEnabled(false);
        
        apButton = new JButton();
        icono = new ImageIcon("src/Imagenes/botonrouter.png");
        apButton.setIcon(icono);
        apButton.setText("AP");
        apButton.setHorizontalTextPosition(JButton.CENTER);
        apButton.setToolTipText("Access Point");
        apButton.setSize(44,37);
        apButton.setBorder(null);
        apButton.setContentAreaFilled(false);
        apButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               addAPActionPerformed(e);
            }
        });
        apButton.setEnabled(false);
        
        panel_izquierdo.add(routerButton);
        panel_izquierdo.add(staButton);
        panel_izquierdo.add(apButton);
        
        JPanel panel_central = new JPanel();
        playButton = new JButton();
        icono = new ImageIcon("src/Imagenes/botonplay.png");
        playButton.setIcon(icono);
        playButton.setSize(40,37);
        playButton.setBorder(null);
        playButton.setContentAreaFilled(false);
        playButton.setOpaque(false);
        
        playButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               if (!addBoton.arraySTA.isEmpty()) activarSimulacion();
               else JOptionPane.showMessageDialog(null, "No se puede iniciar el envío de paquetes ya que no hay estaciones configuradas.");
            }
        });
        playButton.setEnabled(false);
        panel_central.add(playButton);
        
        stopButton = new JButton();
        icono = new ImageIcon("src/Imagenes/botonstop.png");
        stopButton.setIcon(icono);
        stopButton.setSize(40,38);
        stopButton.setBorder(null);
        stopButton.setContentAreaFilled(false);
        stopButton.setOpaque(false);
        stopButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                detenerSimulacion();
            }
        });
        stopButton.setEnabled(false);
        panel_central.add(stopButton);
        
        panel_superior.setLayout(new BorderLayout());
        panel_superior.add(panel_izquierdo, BorderLayout.WEST);
        panel_superior.add(panel_central, BorderLayout.CENTER);
        
        this.add(new JScrollPane(panel_superior), BorderLayout.NORTH);

        subpanel.setLayout(new BorderLayout());
        panelConsola.setPreferredSize(new Dimension(xSize-30,100));
        
        Consola.textArea.setPreferredSize(new Dimension(xSize-20,panelConsola.getPreferredSize().height-10));
        
        panelConsola.add(new JScrollPane(Consola.textArea), BorderLayout.SOUTH);
        
        this.add(new JScrollPane(panelConsola), BorderLayout.SOUTH);
        
        subpanel.setLayout(new BorderLayout());
        panel_medio.setPreferredSize(new Dimension(xSize-30,ySize-panel_superior.getPreferredSize().height-panelConsola.getPreferredSize().height-menu.getPreferredSize().height-50));
        addBoton.panelBoton.setPreferredSize(new Dimension(xSize-20, panel_medio.getPreferredSize().height));
        
        panel_medio.add(new JScrollPane(addBoton.panelBoton),BorderLayout.CENTER);
        
        this.add(new JScrollPane(panel_medio));
        
        revisar = new Revisiones();
        reset = new Resetear();
        ponLaAyuda();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ibssPanel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        cantSTAIBSS = new javax.swing.JTextField();
        bssPanel = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cantSTABSS = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        comboBoxBSS = new javax.swing.JComboBox();
        apPanel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        comboCantAP = new javax.swing.JComboBox();
        staPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cantSTATextField = new javax.swing.JTextField();
        routerPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboCantRouter = new javax.swing.JComboBox();
        menu = new javax.swing.JMenuBar();
        archivoMenu = new javax.swing.JMenu();
        nuevo = new javax.swing.JMenuItem();
        guardarArchivo = new javax.swing.JMenuItem();
        cargarArchivo = new javax.swing.JMenuItem();
        cerrar = new javax.swing.JMenuItem();
        recurso = new javax.swing.JMenu();
        tipoRedMenu = new javax.swing.JMenu();
        adHocMenu = new javax.swing.JMenuItem();
        infraestructura = new javax.swing.JMenuItem();
        addRouter = new javax.swing.JMenuItem();
        addSTA = new javax.swing.JMenuItem();
        addAP = new javax.swing.JMenuItem();
        addBSS = new javax.swing.JMenuItem();
        addIBSS = new javax.swing.JMenuItem();
        simulador = new javax.swing.JMenu();
        iniciar = new javax.swing.JMenuItem();
        detener = new javax.swing.JMenuItem();
        ayudaMenu = new javax.swing.JMenu();
        acercaMenu = new javax.swing.JMenuItem();
        ayudaItem = new javax.swing.JMenuItem();

        jLabel32.setText("Introduzca la cantidad de Estaciones que conformarán el IBSS: ");

        javax.swing.GroupLayout ibssPanelLayout = new javax.swing.GroupLayout(ibssPanel);
        ibssPanel.setLayout(ibssPanelLayout);
        ibssPanelLayout.setHorizontalGroup(
            ibssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ibssPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cantSTAIBSS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        ibssPanelLayout.setVerticalGroup(
            ibssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ibssPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(ibssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cantSTAIBSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jLabel33.setText("Introduzca la cantidad de Estaciones que conformarán el BSS: ");

        cantSTABSS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantSTABSSKeyTyped(evt);
            }
        });

        jLabel15.setText("Seleccione el dispositivo central a utilizar:");

        comboBoxBSS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Dispositivo>", "Router", "Punto de Acceso" }));
        comboBoxBSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxBSSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bssPanelLayout = new javax.swing.GroupLayout(bssPanel);
        bssPanel.setLayout(bssPanelLayout);
        bssPanelLayout.setHorizontalGroup(
            bssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bssPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel15))
                .addGroup(bssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bssPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantSTABSS, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bssPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboBoxBSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        bssPanelLayout.setVerticalGroup(
            bssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bssPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(bssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(cantSTABSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bssPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(comboBoxBSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        apPanel.setName(""); // NOI18N

        jLabel13.setText("Introduzca la cantidad de puntos de acceso que desea");

        jLabel14.setText("Cantidad:");

        comboCantAP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        javax.swing.GroupLayout apPanelLayout = new javax.swing.GroupLayout(apPanel);
        apPanel.setLayout(apPanelLayout);
        apPanelLayout.setHorizontalGroup(
            apPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, apPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
            .addGroup(apPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(apPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(apPanelLayout.createSequentialGroup()
                    .addGap(115, 115, 115)
                    .addComponent(comboCantAP, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(115, Short.MAX_VALUE)))
        );
        apPanelLayout.setVerticalGroup(
            apPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(apPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(apPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(apPanelLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(comboCantAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
        );

        jLabel11.setText("Introduzca la cantidad de estaciones que desea");

        jLabel12.setText("Cantidad:");

        cantSTATextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantSTATextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout staPanelLayout = new javax.swing.GroupLayout(staPanel);
        staPanel.setLayout(staPanelLayout);
        staPanelLayout.setHorizontalGroup(
            staPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(staPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(staPanelLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(cantSTATextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staPanelLayout.setVerticalGroup(
            staPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(staPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantSTATextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jLabel8.setText("Introduzca la cantidad de enrutadores que desea");

        jLabel10.setText("Cantidad:");

        comboCantRouter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        javax.swing.GroupLayout routerPanelLayout = new javax.swing.GroupLayout(routerPanel);
        routerPanel.setLayout(routerPanelLayout);
        routerPanelLayout.setHorizontalGroup(
            routerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, routerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
            .addGroup(routerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(comboCantRouter, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        routerPanelLayout.setVerticalGroup(
            routerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(routerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(routerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comboCantRouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ICARO Wi-Fi Simulator");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        menu.setName(""); // NOI18N

        archivoMenu.setText("Archivo");

        nuevo.setText("Nuevo");
        nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoActionPerformed(evt);
            }
        });
        archivoMenu.add(nuevo);

        guardarArchivo.setText("Guardar");
        guardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarArchivoActionPerformed(evt);
            }
        });
        archivoMenu.add(guardarArchivo);

        cargarArchivo.setText("Cargar");
        cargarArchivo.setEnabled(false);
        cargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarArchivoActionPerformed(evt);
            }
        });
        archivoMenu.add(cargarArchivo);

        cerrar.setText("Cerrar");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        archivoMenu.add(cerrar);

        menu.add(archivoMenu);

        recurso.setText("Recurso");

        tipoRedMenu.setText("Tipo de Red");
        tipoRedMenu.setToolTipText("");

        adHocMenu.setText("Ad-Hoc");
        adHocMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        adHocMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adHocMenuActionPerformed(evt);
            }
        });
        tipoRedMenu.add(adHocMenu);

        infraestructura.setText("Infraestructura");
        infraestructura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infraestructuraActionPerformed(evt);
            }
        });
        tipoRedMenu.add(infraestructura);

        recurso.add(tipoRedMenu);

        addRouter.setText("Añadir Enrutador");
        addRouter.setEnabled(false);
        addRouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRouterActionPerformed(evt);
            }
        });
        recurso.add(addRouter);

        addSTA.setText("Añadir Estación");
        addSTA.setEnabled(false);
        addSTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSTAActionPerformed(evt);
            }
        });
        recurso.add(addSTA);

        addAP.setText("Añadir Punto de Acceso");
        addAP.setEnabled(false);
        addAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAPActionPerformed(evt);
            }
        });
        recurso.add(addAP);

        addBSS.setText("Añadir BSS");
        addBSS.setEnabled(false);
        addBSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBSSActionPerformed(evt);
            }
        });
        recurso.add(addBSS);

        addIBSS.setText("Añadir IBSS");
        addIBSS.setEnabled(false);
        addIBSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIBSSActionPerformed(evt);
            }
        });
        recurso.add(addIBSS);

        menu.add(recurso);

        simulador.setText("Simulador");

        iniciar.setText("Enviar paquetes");
        iniciar.setEnabled(false);
        iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarActionPerformed(evt);
            }
        });
        simulador.add(iniciar);

        detener.setText("Detener");
        detener.setEnabled(false);
        detener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerActionPerformed(evt);
            }
        });
        simulador.add(detener);

        menu.add(simulador);

        ayudaMenu.setText("?");

        acercaMenu.setText("Acerca De...");
        acercaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acercaMenuActionPerformed(evt);
            }
        });
        ayudaMenu.add(acercaMenu);

        ayudaItem.setText("Ayuda");
        ayudaMenu.add(ayudaItem);

        menu.add(ayudaMenu);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void detenerSimulacion()
    {
        stop = true;
        JOptionPane.showMessageDialog(null, "Simulación culminada. Se enviaron: "+play.sent+" paquetes y se recibieron: "+play.rec);
        play.animacion.eliminarAnimacion();
        if (play.continuar) play.animacion2.eliminarAnimacion();
        hilo.stop();
        playButton.setEnabled(true);
        stopButton.setEnabled(false);
    }
    
    public void activarSimulacion()
    {
        stopButton.setEnabled(true);
        detener.setEnabled(true);
        playButton.setEnabled(false);
        stop = false;

        JOptionPane.showMessageDialog(null, "Simulación iniciada");

        play.tipoRed = tipoRed;
        play.panel = addBoton.panelBoton;
        play.selectFuente(routers, stas, aps);
        
        hilo = new Thread(this);
        hilo.start();
    }
    
    private void addBSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBSSActionPerformed
        // añadir bss
        ssDialog = new JDialog(this, true);
        ssDialog.setTitle("Añadir BSS");
        ssDialog.setName("BSS");
        
        ssDialog.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(bssPanel);
        ssDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarSS = new JButton("Guardar");
        guardarSS.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!revisar.CheckVacioTabbedPane(bssPanel) && (comboBoxBSS.getSelectedIndex()!=0))
                {
                    if (auxCantRouter == 1) checkRouter(ssDialog);
                    if (auxCantAP==1) checkAP(null);

                    auxCantSTA = Integer.parseInt(cantSTABSS.getText().trim());
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GUIppal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    checkSTA(bssPanel, ssDialog);
                    dibujarBoton();
                }
            }
        });
        
        cancelarSS = new JButton("Cancelar");
        cancelarSS.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ssDialog.setVisible(false);
            }
        });
        
        ssDialog.getRootPane().setDefaultButton(guardarSS);
        
        guardarSS.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode()== KeyEvent.VK_ENTER){}
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
        panel_inferior.add(guardarSS);
        panel_inferior.add(cancelarSS);
        ssDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        ssDialog.pack();
 
        ssDialog.setVisible(true);
    }//GEN-LAST:event_addBSSActionPerformed

    private void adHocMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adHocMenuActionPerformed
        // Ad-Hoc
        this.setTitle(this.getTitle()+" "+"-"+ " "+ "Ad-hoc");
        tipoRed = "AdHoc";
        addSTA.setEnabled(true);
        addIBSS.setEnabled(true);
        addRouter.setEnabled(false);
        addAP.setEnabled(false);
        addBSS.setEnabled(false);
        adHocMenu.setSelected(true);
        tipoRedMenu.setEnabled(false);
        staButton.setEnabled(true);
        apButton.setEnabled(false);
        routerButton.setEnabled(false);
        cargarArchivo.setEnabled(true);
    }//GEN-LAST:event_adHocMenuActionPerformed

    private void infraestructuraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infraestructuraActionPerformed
        // Infraestructura
        this.setTitle(this.getTitle()+" "+"-"+ " "+ "Infraestructura");
        tipoRed = "Infraestructura";
        addRouter.setEnabled(true);
        addAP.setEnabled(true);
        addBSS.setEnabled(true);
        addSTA.setEnabled(true);
        addIBSS.setEnabled(false);
        tipoRedMenu.setEnabled(false);
        staButton.setEnabled(true);
        apButton.setEnabled(true);
        routerButton.setEnabled(true);
        cargarArchivo.setEnabled(true);
    }//GEN-LAST:event_infraestructuraActionPerformed

    public void checkRouter(JDialog dialog)
    {
        tipoBoton = "Router";
        addBoton.cant = auxCantRouter;
        addBoton.tipoBoton = tipoBoton;
        addBoton.tipoRed = tipoRed;
        limite = auxCantRouter + botonesRouter.size()+botonesAP.size();
        addBoton.configR = configRouter;
        
        if (limite<=10)
        {
            addBoton.inicializarBotones(auxCantRouter);
            if (!dialog.getName().equals("BSS"))dibujarBoton();
        }
        else JOptionPane.showMessageDialog(null, "No se pueden agregar más AP y/o Enrutadores, ha superado el límite permitido(10)");
        if (!dialog.getName().equals("BSS")) dialog.setVisible(false);
    }
    
    private void addRouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRouterActionPerformed
        // Añadir Router
        cantDialog = new JDialog(this, true);
        cantDialog.setTitle("Cantidad de Routers");
        cantDialog.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(routerPanel);
        cantDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarCant = new JButton("Guardar");
        guardarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                auxCantRouter = comboCantRouter.getSelectedIndex()+1;
                checkRouter(cantDialog);
            }
        });
        
        cancelarCant = new JButton("Cancelar");
        cancelarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cantDialog.setVisible(false);
            }
        });
        
        cantDialog.getRootPane().setDefaultButton(guardarCant);
        
        guardarCant.addKeyListener(new KeyListener()
        {

            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode()== KeyEvent.VK_ENTER){}
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
        panel_inferior.add(guardarCant);
        panel_inferior.add(cancelarCant);
        cantDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        cantDialog.pack();
 
        cantDialog.setVisible(true);
    }//GEN-LAST:event_addRouterActionPerformed

    public void checkSTA(JPanel panel, JDialog dialog)
    {
        if(auxCantSTA==0) JOptionPane.showMessageDialog(null, "Solo se permiten números mayores a 0");
        else
        {
            tipoBoton = "STA";
            addBoton.cant = auxCantSTA;
            addBoton.tipoBoton = tipoBoton;
            addBoton.tipoRed = tipoRed;
            limite = auxCantSTA + botonesSTA.size();
            
            if (limite<=20)
            {
                addBoton.inicializarBotones(addBoton.cant);
                if (!dialog.getName().equals("BSS")) dibujarBoton();
            }
            else JOptionPane.showMessageDialog(null, "No se pueden agregar más estaciones, ha superado el límite permitido (20)");

            reset.ResetValoresTabbedPane(panel);
            dialog.setVisible(false);
        }
    }
    
    private void addSTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSTAActionPerformed
        /*Añadir estación*/
        cantDialog = new JDialog(this, true);
        cantDialog.setTitle("Cantidad de Estaciones");
        
        cantDialog.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(staPanel);
        cantDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarCant = new JButton("Guardar");
        guardarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!(revisar.CheckVacioTabbedPane(staPanel)))
                {
                   auxCantSTA= Integer.parseInt(cantSTATextField.getText().trim());
                   checkSTA(staPanel, cantDialog);
                }
            }
        });
        
        cancelarCant = new JButton("Cancelar");
        cancelarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reset.ResetValoresTabbedPane(staPanel);
                cantDialog.setVisible(false);
            }
        });
        
        cantDialog.getRootPane().setDefaultButton(guardarCant);
        
        guardarCant.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode()== KeyEvent.VK_ENTER){}
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
        panel_inferior.add(guardarCant);
        panel_inferior.add(cancelarCant);
        cantDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        cantDialog.pack();
 
        cantDialog.setVisible(true);
    }//GEN-LAST:event_addSTAActionPerformed

    private void addIBSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIBSSActionPerformed
        // Añadir IBSS
        ssDialog = new JDialog(this, true);
        ssDialog.setTitle("Añadir IBSS");
        ssDialog.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(ibssPanel);
        ssDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarSS = new JButton("Guardar");
        guardarSS.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!(revisar.CheckVacioTabbedPane(ibssPanel)))
                {
                   auxCantSTA = Integer.parseInt(cantSTAIBSS.getText().trim());
                   checkSTA(ibssPanel, ssDialog);
                }
            }
        });
        
        cancelarSS = new JButton("Cancelar");
        cancelarSS.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reset.ResetValoresTabbedPane(ibssPanel);
                ssDialog.setVisible(false);
            }
        });
        
        ssDialog.getRootPane().setDefaultButton(guardarSS);
        
        guardarSS.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode()== KeyEvent.VK_ENTER){}
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
        panel_inferior.add(guardarSS);
        panel_inferior.add(cancelarSS);
        
        ssDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        ssDialog.pack();
        ssDialog.setVisible(true);
    }//GEN-LAST:event_addIBSSActionPerformed

    private void iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarActionPerformed
        //Iniciar Simulación
        activarSimulacion();
    }//GEN-LAST:event_iniciarActionPerformed

    private void detenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerActionPerformed
        //Detener Simulación
        detenerSimulacion();
    }//GEN-LAST:event_detenerActionPerformed

    public void dibujarBoton()
    {
        if (addBoton.panelBoton.getParent()==null)
        {
            panel_medio.add(addBoton.panelBoton, 8);
            panel_medio.revalidate();
            panel_medio.repaint();
        }
        
        routers = addBoton.arrayRouter;
        stas = addBoton.arraySTA;
        aps = addBoton.arrayAP;
        botonesRouter = addBoton.botonesRouter;
        botonesSTA = addBoton.botonesSTA;
        botonesAP = addBoton.botonesAP;
        guardarArchivo.setEnabled(true);
        playButton.setEnabled(true);
        iniciar.setEnabled(true);
    }
    
    private void cantSTATextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantSTATextFieldKeyTyped
        // Tipo de dato introducido en la cantidad de las STA
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_cantSTATextFieldKeyTyped
    
    public void checkAP(JDialog dialog)
    {
        tipoBoton = "AP";
        addBoton.cant = auxCantAP;
        addBoton.tipoBoton = tipoBoton;
        addBoton.tipoRed = tipoRed;
        limite = auxCantAP + botonesRouter.size()+botonesAP.size();
        
        if (limite<=10)
        {
            addBoton.inicializarBotones(addBoton.cant);
            dibujarBoton();
        }
        else JOptionPane.showMessageDialog(null, "No se pueden agregar más AP y/o Enrutadores, ha superado el límite permitido (10)");

        if (dialog!= null) dialog.setVisible(false);
    }
    
    private void addAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAPActionPerformed
        cantDialog = new JDialog(this, true);
        cantDialog.setTitle("Cantidad de Access Point");
        cantDialog.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(apPanel);
        cantDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarCant = new JButton("Guardar");
        guardarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                auxCantAP = comboCantAP.getSelectedIndex()+1;
                checkAP(cantDialog);
            }
        });
        
        cancelarCant = new JButton("Cancelar");
        cancelarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cantDialog.setVisible(false);
            }
        });
        
        cantDialog.getRootPane().setDefaultButton(guardarCant);
        
        guardarCant.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode()== KeyEvent.VK_ENTER){}
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        
        panel_inferior.add(guardarCant);
        panel_inferior.add(cancelarCant);
        cantDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        cantDialog.pack();
 
        cantDialog.setVisible(true);
    }//GEN-LAST:event_addAPActionPerformed

    private void cantSTABSSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantSTABSSKeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_cantSTABSSKeyTyped

    private void comboBoxBSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxBSSActionPerformed
        // TODO add your handling code here:
        if (comboBoxBSS.getSelectedIndex()==1)
        {
            auxCantRouter = 1;
            auxCantAP = 0;
            tipoBoton = "Router";
        }
        if (comboBoxBSS.getSelectedIndex()==2)
        {
            auxCantAP = 1;
            auxCantRouter = 0;
            tipoBoton = "AP";
        }
    }//GEN-LAST:event_comboBoxBSSActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        cerrarActionPerformed(null);
    }//GEN-LAST:event_formWindowClosing

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        // Cerrar
        String [] opciones = {"Si", "No","Cancelar"};
        int i = JOptionPane.showOptionDialog(this, "¿Desea guardar el proyecto antes de salir?", "Confirmar", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (i == JOptionPane.OK_OPTION)
        {
            guardarArchivoActionPerformed(evt);
            if (!guardar.cancel) System.exit(0);
        }
        if (i == JOptionPane.NO_OPTION) System.exit(0);
    }//GEN-LAST:event_cerrarActionPerformed

    private void cargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarArchivoActionPerformed
        if ((addBoton.botonesAP.isEmpty())&&(addBoton.botonesRouter.isEmpty())&&(addBoton.botonesSTA.isEmpty()))
        {
            abrir = new Abrir(this, true);
            abrir.tipoRed = tipoRed;
            abrir.abrirArchivo("Cargar Simulación", "Cargar", "Simulación");

            addBoton.tipoRed = tipoRed;

            addBoton.arraySTA = abrir.stas;

            if (tipoRed.equals("Infraestructura"))
            {
                addBoton.arrayRouter = abrir.routers;

                tipoBoton = "Router";
                addBoton.cant = abrir.routers.size();
                addBoton.tipoBoton = tipoBoton;
                addBoton.cargarRouter();

                addBoton.arrayAP = abrir.aps;
                tipoBoton = "AP";
                addBoton.cant = abrir.aps.size();
                addBoton.tipoBoton = tipoBoton;
                addBoton.cargarAP();
            }
            tipoBoton = "STA";
            addBoton.cant = abrir.stas.size();
            addBoton.tipoBoton = tipoBoton;
            addBoton.cargarSTA();

            dibujarBoton();

            if (Consola.textArea.getParent()==null)
            {
                panelConsola.add(consola, 8);
                panelConsola.revalidate();
                panelConsola.repaint();
            }
            routers = addBoton.arrayRouter;
            aps = addBoton.arrayAP;
            stas = addBoton.arraySTA;
        }
        else JOptionPane.showMessageDialog(this, "Error: no se puede cargar el archivo ya que existen nodos en la pantalla");
    }//GEN-LAST:event_cargarArchivoActionPerformed

    private void guardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarArchivoActionPerformed
        if (!botonesAP.isEmpty()||!botonesRouter.isEmpty()||!botonesSTA.isEmpty())
        {
            guardar = new Guardar(this, true);
            guardar.tipoRed = tipoRed;
            try
            {
                if (tipoRed.equals("Infraestructura"))
                {
                    guardar.routerFunctions(Router.class);
                    guardar.router = routers;
                    guardar.apFunctions(AccessPoint.class);
                    guardar.ap = aps;
                }
                guardar.staFunctions(STA.class);
                guardar.sta = stas;
                try
                {
                    guardar.guardarArchivo();
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(GUIppal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(GUIppal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else JOptionPane.showMessageDialog(this, "No hay nodos que almacenar");
    }//GEN-LAST:event_guardarArchivoActionPerformed

    private void nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoActionPerformed
        // Archivo-> Nuevo
        String [] opciones = {"Si", "No"};
        int i = JOptionPane.showOptionDialog(this, "¿Está seguro que desea eliminar todos los componentes?", "Confirmar", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (i == JOptionPane.OK_OPTION)
        {
            Consola.mostrarTexto("");
            addBoton.panelBoton.removeAll();
            addBoton.panelBoton.repaint();
            addBoton.botonesRouter.clear();
            addBoton.botonesAP.clear();
            addBoton.arrayAP.clear();
            addBoton.arrayRouter.clear();
            addBoton.arraySTA.clear();
            addBoton.arrayCirculoAP.clear();
            addBoton.arrayCirculoRouter.clear();
            addBoton.botonesSTA.clear();
            addBoton.panelBoton.removeAll();
            addBoton.panelBoton.repaint();
            tipoRedMenu.setEnabled(true);
            addSTA.setEnabled(false);
            addIBSS.setEnabled(false);
            addRouter.setEnabled(false);
            addAP.setEnabled(false);
            addBSS.setEnabled(false);
            adHocMenu.setSelected(false);
            infraestructura.setSelected(false);
            routerButton.setEnabled(false);
            apButton.setEnabled(false);
            staButton.setEnabled(false);
            botonesRouter.clear();
            botonesAP.clear();
            botonesSTA.clear();
            addBoton.redesAdHoc.clear();
            aps.clear();
            routers.clear();
            stas.clear();
            cargarArchivo.setEnabled(false);
            guardarArchivo.setEnabled(false);

            this.setTitle("ICARO Wi-Fi Simulator");
            tipoRed = "";
            tipoBoton = "";
            playButton.setEnabled(false);
            stopButton.setEnabled(false);
            stop = false;
        }
    }//GEN-LAST:event_nuevoActionPerformed

    private void acercaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acercaMenuActionPerformed
        // TODO add your handling code here:
        cantDialog = new JDialog(this, true);
        cantDialog.setTitle("Acerca De...");
        cantDialog.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        JTextPane texto = new JTextPane();
        
        try
        {
        texto.setContentType("text/html"); //assuming it's an html document

        Reader fileRead=new FileReader("src/help/html/main.html"); // name is the file you want to read

        texto.read(fileRead,null);

        } catch(Exception e) { }
        
        panel_superior.add(texto);
        
        JPanel panel_inferior = new JPanel();
        
        cancelarCant = new JButton("Cerrar");
        cancelarCant.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cantDialog.setVisible(false);
            }
        });
        
        cantDialog.getRootPane().setDefaultButton(cancelarCant);
        
        cancelarCant.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke)
            {
                if (ke.getKeyCode()== KeyEvent.VK_ENTER){}
            }

            @Override
            public void keyReleased(KeyEvent ke) {}
        });
        panel_inferior.add(cancelarCant);
        cantDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        cantDialog.pack();
 
        cantDialog.setVisible(true);
    }//GEN-LAST:event_acercaMenuActionPerformed

    @Override
    public void run() 
    {
        Consola.mostrarTexto("");
        while (!stop)
        {
            play.enviarPaquete();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            play.recibirPaquete();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void ponLaAyuda()
    {
        try
        {
            // Carga el archivo de ayuda
            File archivo = new File("src/help/help_set.hs");
            URL hsURL = archivo.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            hb.enableHelpOnButton(ayudaItem, "principal", helpset);
            hb.enableHelpKey(this.getContentPane(), "ppal",helpset);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIppal().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem acercaMenu;
    private javax.swing.JMenuItem adHocMenu;
    private javax.swing.JMenuItem addAP;
    private javax.swing.JMenuItem addBSS;
    private javax.swing.JMenuItem addIBSS;
    private javax.swing.JMenuItem addRouter;
    private javax.swing.JMenuItem addSTA;
    private javax.swing.JPanel apPanel;
    private javax.swing.JMenu archivoMenu;
    private javax.swing.JMenuItem ayudaItem;
    private javax.swing.JMenu ayudaMenu;
    private javax.swing.JPanel bssPanel;
    private javax.swing.JTextField cantSTABSS;
    private javax.swing.JTextField cantSTAIBSS;
    private javax.swing.JTextField cantSTATextField;
    private javax.swing.JMenuItem cargarArchivo;
    private javax.swing.JMenuItem cerrar;
    private javax.swing.JComboBox comboBoxBSS;
    private javax.swing.JComboBox comboCantAP;
    private javax.swing.JComboBox comboCantRouter;
    private javax.swing.JMenuItem detener;
    private javax.swing.JMenuItem guardarArchivo;
    private javax.swing.JPanel ibssPanel;
    private javax.swing.JMenuItem infraestructura;
    private javax.swing.JMenuItem iniciar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem nuevo;
    private javax.swing.JMenu recurso;
    private javax.swing.JPanel routerPanel;
    private javax.swing.JMenu simulador;
    private javax.swing.JPanel staPanel;
    private javax.swing.JMenu tipoRedMenu;
    // End of variables declaration//GEN-END:variables

}
