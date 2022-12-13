package clases;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class ConfigSTAInfra extends javax.swing.JDialog
{
    JDialog configSTAInfraDialog;
    JPanel panel;
    
    HashMap stas = new HashMap();
    HashMap routers = new HashMap();
    HashMap aps = new HashMap();
    
    Revisiones revisar = new Revisiones();
    VerificarIP verIp = new VerificarIP();
    AsigDHCP dhcp = new AsigDHCP();
    STAInfraPanel staPanel;
    STA sta;
    Router router = new Router();
    AccessPoint ap = new AccessPoint();
    SignalRange senal = new SignalRange();
    Animacion animacion = new Animacion();
    
    ArrayList <JButton> botonesRouter = new ArrayList<>();
    ArrayList <JButton> botonesAP = new ArrayList<>();
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    
    ArrayList <AddBotones.DrawCanvas> arrayCirculoRouter = new ArrayList<>();
    ArrayList <AddBotones.DrawCanvas> arrayCirculoSTA = new ArrayList<>();
    ArrayList <AddBotones.DrawCanvas> arrayCirculoAP = new ArrayList<>();
    
    ArrayList <String> indicesR = new ArrayList<>();
    ArrayList <String> indicesAP = new ArrayList<>();
    ArrayList <String> ssids = new ArrayList<>();
    ArrayList <String> seguridades = new ArrayList<>();
    ArrayList <String> ssidsAP = new ArrayList<>();
    ArrayList <String> seguridadesAP = new ArrayList<>();
    
    JButton boton = new JButton();
    JButton conectarSTA;
    JButton cancelSTA;
    
    HashMap contenedor = new HashMap();
    
    String ipSTA = "0.0.0.0", tipoSeguridad = "", mask = "0.0.0.0", nombre = "", ssid = "", clave = "", dhcpS = "", origen = "",indiceR,indiceAP;
    
    int inBoton, rX, rY, apX, apY;
    boolean error = false, redes = false;

    public ConfigSTAInfra(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        configSTAInfraDialog = new JDialog(parent, modal);
        staPanel = new STAInfraPanel();
        
        this.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(staPanel);
        configSTAInfraDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        conectarSTA = new JButton("Conectar");
        conectarSTA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!revisar.CheckVacioTabbedPane(staPanel))
                {
                    checkConfig();
                    if (!error)
                    {
                        guardarConfig();
                        configSTAInfraDialog.setVisible(false);
                    }
                    error = false;
                }
            }
        });
        
        cancelSTA = new JButton("Cancelar");
        cancelSTA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                configSTAInfraDialog.setVisible(false);
            }
        });
        
        configSTAInfraDialog.getRootPane().setDefaultButton(conectarSTA);
        
        conectarSTA.addKeyListener(new KeyListener()
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
        
        panel_inferior.add(conectarSTA);
        panel_inferior.add(cancelSTA);
        configSTAInfraDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        configSTAInfraDialog.pack();
        initComponents();
    }

    public void setTitulo (JButton boton, HashMap auxR, HashMap auxSTA, HashMap auxAP,int inBoton, HashMap contenedor)
    {
        this.inBoton = inBoton;
        ssids.clear();
        indicesR.clear();
        seguridades.clear();
        routers = new HashMap();
        
        ssidsAP.clear();
        seguridadesAP.clear();
        indicesAP.clear();
        aps = new HashMap();

        sta = new STA();
        routers = auxR;
        aps = auxAP;
        stas = auxSTA;
        redes = false;
        this.contenedor = contenedor;
     
        boolean nomEncontrado = false;
        
        nombre = boton.getName();
        configSTAInfraDialog.setTitle("Configuración de Estación - " + nombre);
        
        if (stas.isEmpty()) configInicial();
        else
        {
            if (stas.containsKey(nombre))
            {
                nomEncontrado = true;
                sta = (STA) stas.get(nombre);
                origen = sta.getOrigenSSID();
            }
            else nomEncontrado = false;
        }

        senal.routers = routers;
        senal.aps = aps;
        senal.botonesAP = botonesAP;
        senal.botonesRouter = botonesRouter;
        senal.botonesSTA = botonesSTA;
        senal.arrayCirculoAP = arrayCirculoAP;
        senal.arrayCirculoRouter = arrayCirculoRouter;
        senal.arrayCirculoSTA = arrayCirculoSTA;

        if (senal.checkAlcance(inBoton, "Infraestructura")|| senal.junto)
        {
            indicesAP = senal.indicesAP;
            indicesR = senal.indicesR;
            redes = true;
            cargarValoresRouter();
        }
        else
        {
            redes = false;
            staPanel.cargarSSID(null, null, null, null, redes);
        }

        if(!nomEncontrado) configInicial();
        else cargarConfiguracion();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void configInicial()
    {
        String [] octetos = new String [4];
        octetos = "0.0.0.0".split("\\.");
        fillCasillasIP("LAN", octetos);

        octetos = "0.0.0.0".split("\\.");
        fillCasillasIP("MáscaraSTA", octetos);
        staPanel.checkUtilizarDHCP.setSelected(false);
        staPanel.passSTAField.setText("");
    }
    
    public void cargarValoresRouter()
    {
        Router aux = new Router();
        AccessPoint auxAP = new AccessPoint();
        staPanel.routers = routers;
        
        for (int i =0; i<indicesR.size();i++)
        {
            if (routers.containsKey(indicesR.get(i)))
            {
                aux = (Router) routers.get(indicesR.get(i));
                ssids.add(aux.getSSID());
                seguridades.add(aux.getSeguridad());
            }
        }
        
        for (int i =0; i<indicesAP.size();i++)
        {
            if (aps.containsKey(indicesAP.get(i)))
            {
                auxAP = (AccessPoint) aps.get(indicesAP.get(i));
                ssidsAP.add(auxAP.getSSID());
                seguridadesAP.add(auxAP.getSeguridad());
            }
        }
        staPanel.indicesR = indicesR;
        staPanel.indicesAP = indicesAP;
        staPanel.cargarSSID(ssids, seguridades, ssidsAP, seguridadesAP, redes);
        ssid = staPanel.ssid;
        tipoSeguridad = staPanel.tipoSeguridad;
        
        origen = staPanel.origen;
        if (origen.equals("Router"))
        {
            for (Object key : routers.keySet())
            {
                aux = (Router) routers.get(key);
                if (ssid.equals(aux.getSSID()))
                {
                    router = (Router) routers.get(key);
                    indiceR = (String) key;
                    break;
                }
            }
        }
        if (origen.equals("AP"))
        {
            for (Object key : aps.keySet())
            {
                auxAP = (AccessPoint) aps.get(key);
                if (ssid.equals(auxAP.getSSID()))
                {
                    ap = (AccessPoint) aps.get(key);
                    indiceAP = (String) key;
                    break;
                }
            }
        }
    }
    
    public void fillCasillasIP(String tipo, String [] octeto)
    {
        String [] octetos = new String [4];
        octetos = octeto;
        if (tipo.equals("LAN"))
        {
            staPanel.staIP1.setText(octetos[0]);
            staPanel.staIP2.setText(octetos[1]);
            staPanel.staIP3.setText(octetos[2]);
            staPanel.staIP4.setText(octetos[3]);
        }
        if (tipo.equals("MáscaraSTA"))
        {
            staPanel.staMask1.setText(octetos[0]);
            staPanel.staMask2.setText(octetos[1]);
            staPanel.staMask3.setText(octetos[2]);
            staPanel.staMask4.setText(octetos[3]);
        }
    }
    
    public void cargarConfiguracion()
    {
        String [] octetos = new String [4];
        boolean encontrado = false;
        
        tipoSeguridad = sta.getSeguridad();
        ssid = sta.getSSID();
        ipSTA = sta.getIP();
        mask = sta.getMask();
        dhcpS = sta.getDHCP();
        
        if (tipoSeguridad.equals("Ninguna")) staPanel.passSTAField.setEnabled(false);
        else
        {
            staPanel.passSTAField.setEnabled(true);
            clave = sta.getPassword();
            staPanel.passSTAField.setText(clave);
        }
        
        if (dhcpS.equals("Desactivado"))
        {
            //no dhcp
            staPanel.checkUtilizarDHCP.setSelected(false);
            staPanel.activarConfigDHCP(false);
        }
        else
        {
            //si dhcp
            staPanel.checkUtilizarDHCP.setSelected(true);
            staPanel.activarConfigDHCP(true);
        }
        
        octetos = ipSTA.split("\\.");
        fillCasillasIP("LAN", octetos);

        octetos = mask.split("\\.");
        fillCasillasIP("MáscaraSTA", octetos);
        
        if (!ssids.isEmpty() || (!ssidsAP.isEmpty()))
        {
            for (int i=0;i<ssids.size();i++)
            {
                if (ssid.equals(ssids.get(i)))
                {
                    encontrado = true;
                    staPanel.comboBoxSSID.setSelectedItem(ssid);
                    
                    break;
                }
            }
            if (!encontrado)
            {
                for (int i=0;i<ssidsAP.size();i++)
                {
                    if (ssid.equals(ssidsAP.get(i)))
                    {
                        encontrado = true;
                        staPanel.comboBoxSSID.setSelectedItem(ssid);
                        break;
                    }
                }
            }
            if (!encontrado) cargarValoresRouter(); 
        }
    }
    
    public void checkConfig()
    {
        boolean libre = true;   //si se encontraron direcciones por dhcp libres

        if (origen.equals("Router"))
        {
            /*Chequeo de la configuración IP por DHCP o por IP estática*/
            if ((router.getActDHCP().equals("Activado")) && (staPanel.checkUtilizarDHCP.isSelected()))
            {
                for (int i=0; i<router.direcciones.size();i++)
                {
                    if (router.getUsadas(i).equals("No"))
                    {
                        ipSTA = router.getDireccion(i);

                        router.setUsadas("Si", i);
                        mask = router.getMask();

                        dhcpS = "Activado";
                        libre = true;
                        break;
                    }
                    else libre = false;
                }
                if (!libre)
                {
                    //si es falso no encontró un espacio libre
                    JOptionPane.showMessageDialog(null, "Error: no se encontraron direcciones DHCP libres. Por favor introdúzcala manualmente");
                    error = true;
                    staPanel.checkUtilizarDHCP.setSelected(false);
                    staPanel.activarConfigDHCP(false);
                    dhcpS = "Desactivado";
                }
            }
            if (!staPanel.checkUtilizarDHCP.isSelected())
            {
                dhcpS = "Desactivado";

                //verificación de la máscara del router con la de la estación
                verIp.octetosNum[0] = Integer.parseInt(staPanel.staMask1.getText());
                verIp.octetosNum[1] = Integer.parseInt(staPanel.staMask2.getText());
                verIp.octetosNum[2] = Integer.parseInt(staPanel.staMask3.getText());
                verIp.octetosNum[3] = Integer.parseInt(staPanel.staMask4.getText());
                verIp.ipFrom = "MáscaraSTA";
                
                if(!error)
                {
                    if(verIp.checkIP(verIp.ipFrom))
                    {
                        verIp.mask = router.getMask();
                        if(verIp.verificarMaskRouterSta()) mask = verIp.maskSTA;
                        else error = true;
                    }
                    else error = true;
                }

                //Verificación de la ip del router junto con la de la estación

                verIp.ipR = router.getIP();
                verIp.ipFrom = "Router";
                verIp.dividirIP(verIp.ipR, verIp.ipFrom);
                verIp.octetosNum = new int [4];
                verIp.octetosNum[0] = Integer.parseInt(staPanel.staIP1.getText());
                verIp.octetosNum[1] = Integer.parseInt(staPanel.staIP2.getText());
                verIp.octetosNum[2] = Integer.parseInt(staPanel.staIP3.getText());
                verIp.octetosNum[3] = Integer.parseInt(staPanel.staIP4.getText());
                verIp.ipFrom = "STA";
                
                if (!error) 
                {
                    if(verIp.checkIP(verIp.ipFrom))
                    {
                        if (verIp.verificarIPEstatica())
                        {
                            if (verIp.verificarIPRouterSTA()) ipSTA = verIp.ipS;
                            else error = true;
                        }
                        else error = true;
                    }else error = true;
                }
            }
            if ((router.getActDHCP().equals("Desactivado")) && (staPanel.checkUtilizarDHCP.isSelected()))
            {
                JOptionPane.showMessageDialog(null, "Error: No se encontraron servidores DHCP");
                error = true;
                staPanel.checkUtilizarDHCP.setSelected(false);
                staPanel.activarConfigDHCP(false);
                dhcpS = "Desactivado";
            }

            if (!tipoSeguridad.equals("Ninguna"))
            {
                if(!staPanel.passSTAField.getText().equals(router.getPassword()))
                {
                    JOptionPane.showMessageDialog(null, "Error: las contraseñas no coinciden");
                    error = true;
                }
                else clave = staPanel.passSTAField.getText();
            }
        }

        if (origen.equals("AP"))
        {
            /*Chequeo de la configuración IP por DHCP o por IP estática*/

            if ((ap.getActDHCP().equals("Activado")) && (staPanel.checkUtilizarDHCP.isSelected()))
            {
                for (int i=0; i<ap.direcciones.size();i++)
                {
                    if (ap.getUsadas(i).equals("No"))
                    {
                        ipSTA = ap.getDireccion(i);

                        ap.setUsadas("Si", i);
                        mask = ap.getMask();

                        dhcpS = "Activado";
                        libre = true;
                        break;
                    }
                    else libre = false;
                }
                if (!libre)
                {
                    //si es falso no encontró un espacio libre
                    JOptionPane.showMessageDialog(null, "Error: no se encontraron direcciones por DHCP libres. Por favor introdúzcala manualmente");
                    error = true;
                    staPanel.checkUtilizarDHCP.setSelected(false);
                    staPanel.activarConfigDHCP(false);
                    dhcpS = "Desactivado";
                }
            }
            if (!staPanel.checkUtilizarDHCP.isSelected())
            {
                dhcpS = "Desactivado";

                //verificación de la máscara del router con la de la estación
                verIp.octetosNum[0] = Integer.parseInt(staPanel.staMask1.getText());
                verIp.octetosNum[1] = Integer.parseInt(staPanel.staMask2.getText());
                verIp.octetosNum[2] = Integer.parseInt(staPanel.staMask3.getText());
                verIp.octetosNum[3] = Integer.parseInt(staPanel.staMask4.getText());
                verIp.ipFrom = "MáscaraSTA";
                
                if(!error)
                {
                    if(verIp.checkIP(verIp.ipFrom))
                    {
                        verIp.mask = ap.getMask();
                        if(verIp.verificarMaskRouterSta()) mask = verIp.maskSTA;
                        else error = true;
                    }else error = true;
                }

                //Verificación de la ip del router junto con la de la estación

                verIp.ipR = ap.getIP();
                verIp.ipFrom = "Router";
                verIp.dividirIP(verIp.ipR, verIp.ipFrom);
                verIp.octetosNum = new int [4];
                verIp.octetosNum[0] = Integer.parseInt(staPanel.staIP1.getText());
                verIp.octetosNum[1] = Integer.parseInt(staPanel.staIP2.getText());
                verIp.octetosNum[2] = Integer.parseInt(staPanel.staIP3.getText());
                verIp.octetosNum[3] = Integer.parseInt(staPanel.staIP4.getText());
                verIp.ipFrom = "STA";
                
                if (!error) 
                {
                    if(verIp.checkIP(verIp.ipFrom))
                    {
                        if (verIp.verificarIPEstatica())
                        {
                            if (verIp.verificarIPRouterSTA()) ipSTA = verIp.ipS;
                            else error = true;
                        }
                        else error = true;
                    }else error = true;
                }
            }
            if ((ap.getActDHCP().equals("Desactivado")) && (staPanel.checkUtilizarDHCP.isSelected()))
            {
                JOptionPane.showMessageDialog(null, "Error: No se encontraron servidores DHCP");
                error = true;
                staPanel.checkUtilizarDHCP.setSelected(false);
                staPanel.activarConfigDHCP(false);
                dhcpS = "Desactivado";
            }

            if (!tipoSeguridad.equals("Ninguna"))
            {
                if(!staPanel.passSTAField.getText().equals(ap.getPassword()))
                {
                    JOptionPane.showMessageDialog(null, "Error: las contraseñas no coinciden");
                    error = true;
                }
                else clave = staPanel.passSTAField.getText();
            }
        } 
    }
    
    public void guardarConfig()
    {
        sta.setID(nombre);
        sta.setSSID(ssid);
        sta.setDHCP(dhcpS);
        sta.setIP(ipSTA);
        sta.setMask(mask);
        sta.setSeguridad(tipoSeguridad);
        sta.setPassword(clave);
        sta.setOrigenSSID(origen);
        sta.setPosx(boton.getLocation().x);
        sta.setPosy(boton.getLocation().y);
        sta.setRx(arrayCirculoSTA.get(inBoton).r1);
        sta.setRy(arrayCirculoSTA.get(inBoton).r2);
        
        if ((stas.isEmpty())||(!stas.containsKey(nombre)))
        {
            if (origen.equals("Router")) router.setEstacion(nombre, sta);
            if (origen.equals("AP")) ap.setEstacion(nombre,sta);
            sta.setConfigurado(true);
        }
        else
        {
            if (sta.getConfigurado())
            {
                //la estación ya fue configurada
                if (origen.equals("Router")) router.setEstacion(nombre, sta);
                if (origen.equals("AP")) ap.setEstacion(nombre,sta);
                stas.remove(nombre);
                sta.setConfigurado(true);
            }
        }
        stas.put(nombre, sta);
       
        invocarAnimacion();
    }
   
    public void invocarAnimacion()
    {
        if (!botonesAP.isEmpty()) animacion.botonesAP = botonesAP.get(0);
        if (!botonesRouter.isEmpty()) animacion.botonesRouter = botonesRouter.get(0);
        
        animacion.botonesSTA = botonesSTA.get(0);
        
        animacion.setX1(boton.getLocation().x);
        animacion.setY1(boton.getLocation().y);
        animacion.setTipoAnimacion("Conexión");
        animacion.setTipoRed("Infraestructura");
        animacion.panel = panel;
  
        if (contenedor.isEmpty()||(!contenedor.containsKey(nombre)))
        {
            animacion.setVisible(true);
            animacion.boton = new JButton();
            animacion.setNombre(nombre);
        }
        else
        {
            if (contenedor.containsKey(nombre))
            {
                //la animacion ya fue configurada
                animacion = (Animacion) contenedor.get(nombre);
                animacion.boton = animacion.getBoton();
                contenedor.remove(nombre);
                animacion.setVisible(true);
            }
        }
        animacion.getNodo(routers, aps, stas, null);
        contenedor.put(nombre, animacion);
        
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground() throws Exception
            {
                animacion = new Animacion();
                return true;
            }
        };
        worker.execute();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
