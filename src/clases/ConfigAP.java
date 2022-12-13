package clases;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ConfigAP extends JDialog
{
    /*Clase que permite la configuración de un router*/
    JTabbedPane pestanasAP;
    
    JPanel panelLAN = new JPanel();
    JPanel panelWLAN = new JPanel();
    JPanel panelStatus = new JPanel();
    
    VerificarIP verIp = new VerificarIP();
    AsigDHCP dhcp = new AsigDHCP();
    WLANRegion region1 = new WLANRegion();
    
    LANPanel lan;
    WLANPanel wlan;
    StatusPanel status;
    Revisiones revisar;
    Resetear reset;
    AccessPoint ap;
    Router router;
    
    JButton boton = new JButton();
    JButton guardarAP, cancelAP;
    JDialog configAPDialog;
    
    String nombre, clave = "", tipoClave ="", banda, ssid, region, region5G, modo, mask, dhcpActivo,ip;
    Boolean seguridad = false, error = false;

    int canal,indice = 0, cantDHCP, canal5G;
    
    HashMap routers = new HashMap();
    ArrayList <AddBotones.DrawCanvas> arrayCirculoAP = new ArrayList<>();
    HashMap aps = new HashMap();
    
    ArrayList <String> dir = new ArrayList<>();
    ArrayList <String> uso = new ArrayList<>();
    
    public ConfigAP(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        revisar = new Revisiones();
        reset = new Resetear();
        configAPDialog = new JDialog(parent, modal);
        
        pestanasAP = new JTabbedPane();
        lan = new LANPanel();
        wlan = new WLANPanel();
        status = new StatusPanel();
        
        this.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panelLAN.add(lan);
        panelWLAN.add(wlan);
        panelStatus.add(status);
        
        pestanasAP.addTab("LAN", panelLAN);
        pestanasAP.addTab("WLAN",panelWLAN);
        pestanasAP.addTab("Status", panelStatus);
        
        panel_superior.add(pestanasAP);
        configAPDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarAP = new JButton("Guardar");
        guardarAP.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!invocarRevisionVacio())
                {
                    //guardar
                    error = false;
                    checkConfig();
                    if (!error) configAPDialog.setVisible(false);
                }
            }
        });
        
        cancelAP = new JButton("Cancelar");
        cancelAP.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                configAPDialog.setVisible(false);
            }
        });
        
        configAPDialog.getRootPane().setDefaultButton(guardarAP);
        
        guardarAP.addKeyListener(new KeyListener()
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
        
        panel_inferior.add(guardarAP);
        panel_inferior.add(cancelAP);
        configAPDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        configAPDialog.pack();
                
        initComponents();
    }
    
    public void setTitulo (JButton boton, HashMap arrayAP)
    {
        ap = new AccessPoint();
        boolean nomEncontrado = false;
        nombre = boton.getName();
        configAPDialog.setTitle("Configuración de AP - "+nombre);
        aps = arrayAP;
        
        if (aps.isEmpty()) configInicial();
        else
        {
            if (aps.containsKey(nombre))
            {
                nomEncontrado = true;
                ap = (AccessPoint) aps.get(nombre);
            }
            else nomEncontrado = false;
        }
        if(!nomEncontrado)
        {
            configInicial();
            setStatus();
        }
        else
        {
            cargarConfiguracion();
            setStatus();
        }
    }

    public void configInicial()
    {
        String [] octetos = new String [4];
        octetos = "0.0.0.0".split("\\.");
        
        fillCasillasIP("LAN", octetos);
        fillCasillasIP("Máscara", octetos);
        
        lan.DHCPEnable.setSelected(false);
        lan.cantDHCP.setText("");
        lan.cantDHCP.setEnabled(false);
        
        wlan.ssid.setText("");
        wlan.cincoGHz.setSelected(false);
        wlan.cincoGHz.setEnabled(true);
        wlan.dosGHz.setSelected(false);
        wlan.dosGHz.setEnabled(true);
        
        wlan.comboModo.setSelectedIndex(0);
        wlan.comboCanal2GHz.setEnabled(false);
        wlan.comboRegion2Ghz.setEnabled(false);
        wlan.comboRegion5Ghz.setEnabled(false);
        wlan.comboCanal5GHz.setEnabled(false);
        
        wlan.ningunaSeg.setSelected(true);
        wlan.passRouter.setEnabled(false);
    }
    
    public void fillCasillasIP(String tipo, String [] octeto)
    {
        String [] octetos = new String [4];
        octetos = octeto;
        if (tipo.equals("LAN"))
        {
            lan.lanIP1.setText(octetos[0]);
            lan.lanIP2.setText(octetos[1]);
            lan.lanIP3.setText(octetos[2]);
            lan.lanIP4.setText(octetos[3]);
        }
        if (tipo.equals("Máscara"))
        {
            lan.maskIP1.setText(octetos[0]);
            lan.maskIP2.setText(octetos[1]);
            lan.maskIP3.setText(octetos[2]);
            lan.maskIP4.setText(octetos[3]);
        }
    }
    
    public void cargarConfiguracion()
    {
        ssid = ap.getSSID();
        wlan.ssid.setText(ssid);
        String [] octetos = new String [4];
        
        ip = ap.getIP();
        octetos = ip.split("\\.");
        fillCasillasIP("LAN", octetos);
        
        mask = ap.getMask();
        octetos = mask.split("\\.");
        fillCasillasIP("Máscara", octetos);
        
        if ((ap.getActDHCP()).equals("Activado"))
        {
            dhcpActivo = "Activado";
            lan.DHCPEnable.setSelected(true);
            cantDHCP = ap.getCantDHCP();
            lan.cantDHCP.setEnabled(true);
            lan.cantDHCP.setText(Integer.toString(cantDHCP));
        }
        else
        {
            dhcpActivo = "Desactivado";
            lan.DHCPEnable.setSelected(false);
            lan.cantDHCP.setEnabled(false);
            lan.cantDHCP.setText("");
        }
        
        modo = ap.getModo();
        banda = ap.getBanda();
        
        if (modo.equals("802.11a"))
        {
            wlan.comboModo.setSelectedIndex(1);
            wlan.cincoGHz.setSelected(true);
            wlan.cincoGHz.setEnabled(false);
            wlan.dosGHz.setSelected(false);
            wlan.dosGHz.setEnabled(false);
            configRegion5GHz(modo);
        }
        if (modo.equals("802.11b"))
        {
            wlan.comboModo.setSelectedIndex(2);
            wlan.cincoGHz.setSelected(false);
            wlan.cincoGHz.setEnabled(false);
            wlan.dosGHz.setSelected(true);
            wlan.dosGHz.setEnabled(false);
            configRegion2GHz();
        }
        if (modo.equals("802.11g"))
        {
            wlan.comboModo.setSelectedIndex(3);
            wlan.cincoGHz.setSelected(false);
            wlan.cincoGHz.setEnabled(false);
            wlan.dosGHz.setSelected(true);
            wlan.dosGHz.setEnabled(false);
            configRegion2GHz();
        }
        if (modo.equals("802.11b/g/n"))
        {
            wlan.comboModo.setSelectedIndex(4);
            if (banda.equals("2.4 GHz"))
            {
                wlan.dosGHz.setSelected(true);
                configRegion2GHz();
            }
            if (banda.equals("5 GHz"))
            {
                wlan.cincoGHz.setSelected(true);
                configRegion5GHz(modo);
            }
            if (banda.equals("Dual-Band"))
            {
                wlan.dosGHz.setSelected(true);
                wlan.cincoGHz.setSelected(true);
                configRegion2GHz();
                configRegion5GHz(modo);
            }
            wlan.cincoGHz.setEnabled(true);
            wlan.dosGHz.setEnabled(true);
        }
        if (modo.equals("802.11n"))
        {
            wlan.comboModo.setSelectedIndex(5);
            if (banda.equals("2.4 GHz"))
            {
                wlan.dosGHz.setSelected(true);
                configRegion2GHz();
            }
            if (banda.equals("5 GHz"))
            {
                wlan.cincoGHz.setSelected(true);
                configRegion5GHz(modo);
            }
            if (banda.equals("Dual-Band"))
            {
                wlan.dosGHz.setSelected(true);
                wlan.cincoGHz.setSelected(true);
                configRegion2GHz();
                configRegion5GHz(modo);
            }
            wlan.cincoGHz.setEnabled(true);
            wlan.dosGHz.setEnabled(true);
        }
        
        tipoClave = ap.getSeguridad();
        if (tipoClave.equals("Ninguna"))
        {
            wlan.ningunaSeg.setSelected(true);
            wlan.passRouter.setEnabled(false);
        }
        else
        {
            clave = ap.getPassword();
            if (tipoClave.equals("WEP")) wlan.wep.setSelected(true);
            if (tipoClave.equals("WPA")) wlan.wpa.setSelected(true);
            wlan.passRouter.setEnabled(true);
            wlan.passRouter.setText(clave);
        }
    }
    
    public void configRegion2GHz()
    {
        region = ap.getRegion();
        if (region.equals("USA")) wlan.comboRegion2Ghz.setSelectedIndex(1);
        if (region.equals("Europa")) wlan.comboRegion2Ghz.setSelectedIndex(2);
        if (region.equals("Japón")) wlan.comboRegion2Ghz.setSelectedIndex(3);
        
        canal = ap.getCanal();
        wlan.setCanal(region);
        
        for (int i = 0; i<wlan.comboCanal2GHz.getItemCount();i++)
        {
            if (canal==i)
            {
                wlan.comboCanal2GHz.setSelectedIndex(i);
                break;
            }
        }
    }
    
    public void configRegion5GHz(String modo)
    {
        region5G = ap.getRegion5G();
        if (modo.equals("802.11a"))
        {
            if (region5G.equals("América")) wlan.comboRegion5Ghz.setSelectedIndex(1);
            if (region5G.equals("Australia")) wlan.comboRegion5Ghz.setSelectedIndex(2);
            if (region5G.equals("China")) wlan.comboRegion5Ghz.setSelectedIndex(3);
            if (region5G.equals("Europa")) wlan.comboRegion5Ghz.setSelectedIndex(4);
            if (region5G.equals("Japón")) wlan.comboRegion5Ghz.setSelectedIndex(5);
        }
        else
        {
            if (region5G.equals("América")) wlan.comboRegion5Ghz.setSelectedIndex(1);
            if (region5G.equals("China")) wlan.comboRegion5Ghz.setSelectedIndex(2);
            if (region5G.equals("Europa")) wlan.comboRegion5Ghz.setSelectedIndex(3);
            if (region5G.equals("Israel")) wlan.comboRegion5Ghz.setSelectedIndex(4);
            if (region5G.equals("Japón")) wlan.comboRegion5Ghz.setSelectedIndex(5);
        }
        
        canal5G = ap.getCanal5G();
        wlan.setCanal5Ghz(region5G);
        for (int i = 0; i<wlan.comboCanal5GHz.getItemCount();i++)
        {
            if (Integer.toString(canal5G).equals((String)wlan.comboCanal5GHz.getItemAt(i)))
            {
                wlan.comboCanal5GHz.setSelectedIndex(i);
                break;
            }
        }
    }
    
    boolean invocarRevisionVacio()
    {
        for (int i=0;i<pestanasAP.getTabCount();i++)
        {
            pestanasAP.setSelectedIndex(i);
            if (pestanasAP.getSelectedIndex()==0)
            {
                if(revisar.CheckVacioTabbedPane(lan)) return true;
            }
            if (pestanasAP.getSelectedIndex()==1)
            {
                if(revisar.CheckVacioTabbedPane(wlan)) return true;
            }
        }
        return false;
    }
    
    public void checkConfig()
    {
        Router aux = new Router();
        AccessPoint auxAP = new AccessPoint();
        boolean repetido = false;
        
                            /*Configuración LAN*/
        verIp.octetosNum = new int [4];
        verIp.octetosNum[0] = Integer.parseInt(lan.maskIP1.getText());
        verIp.octetosNum[1] = Integer.parseInt(lan.maskIP2.getText());
        verIp.octetosNum[2] = Integer.parseInt(lan.maskIP3.getText());
        verIp.octetosNum[3] = Integer.parseInt(lan.maskIP4.getText());
        verIp.ipFrom = "Máscara";
        
        if(verIp.checkIP(verIp.ipFrom))
        {
            mask = verIp.mask;
            verIp.octetosNum[0] = Integer.parseInt(lan.lanIP1.getText());
            verIp.octetosNum[1] = Integer.parseInt(lan.lanIP2.getText());
            verIp.octetosNum[2] = Integer.parseInt(lan.lanIP3.getText());
            verIp.octetosNum[3] = Integer.parseInt(lan.lanIP4.getText());
            verIp.ipFrom = "Router";

            if (verIp.checkIP(verIp.ipFrom)) ip = verIp.ipR;
            else error = true;
        }
        else error = true;
        
        if (lan.DHCPEnable.isSelected())
        {
            verIp.obtenerClase();
            dhcpActivo = "Activado";
            cantDHCP = Integer.parseInt(lan.cantDHCP.getText());
            dhcp.cantdhcp = cantDHCP;
            dhcp.mask = mask;
            dhcp.ipR = ip;
            dhcp.clase = verIp.clase;
            dhcp.generarAleatorio(dhcp.cantdhcp);
            
            for (int i=0; i<dhcp.direcciones.size();i++)
            {
                dir.add(i, dhcp.direcciones.get(i));
                uso.add(i, dhcp.usadas.get(i));
            }
        }
        else dhcpActivo = "Desactivado";
                            /*Configuración WLAN*/
        for (Object key : aps.keySet())
        {
            auxAP = (AccessPoint) aps.get(key);
            if (wlan.ssid.getText().equals(auxAP.getSSID())&&(!auxAP.getNombre().equals(nombre)))
            {
                JOptionPane.showMessageDialog(null, "Error: no pueden existir nodos con el mismo SSID");
                repetido = true;
                error = true;
                break;
            }else repetido = false;
        }
        
        for (Object key : routers.keySet())
        {
            aux = (Router) routers.get(key);
            if (wlan.ssid.getText().equals(aux.getSSID())&&(!aux.getNombre().equals(nombre)))
            {
                JOptionPane.showMessageDialog(null, "Error: no pueden existir nodos con el mismo SSID");
                repetido = true;
                error = true;
                break;
            }else repetido = false;
        }
        if (!repetido) ssid = wlan.ssid.getText();
        
        tipoClave = wlan.tipoSeguridad;
        clave = wlan.passRouter.getText();
        
        if (wlan.modo.equals("<Modo>"))
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un modo de operación");
            error = true;
        }
        else
        {
            modo = wlan.modo;
            banda = wlan.banda;
            if (wlan.comboRegion2Ghz.isEnabled())
            {
                if (wlan.comboRegion2Ghz.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una región para la banda ISM de los 2.4 GHz");
                    error = true;
                }
                else region = wlan.region1;

                if(wlan.comboCanal2GHz.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un canal para la banda ISM de los 2.4 GHz");
                    error = true;
                }
                else canal = wlan.canal;
            }
            if (wlan.comboRegion5Ghz.isEnabled())
            {
                if (wlan.comboRegion5Ghz.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una región para la banda ISM de los 5 GHz");
                    error = true;
                }
                else region5G = wlan.region5G;

                if(wlan.comboCanal5GHz.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un canal para la banda ISM de los 5 GHz");
                    error = true;
                }
                else canal5G = wlan.canal5G;
            }
        }
                        /*Guardar Configuración en el Router*/
        if(verIp.claseIP()&&!error)
        {
            setConfig();
            setStatus();
        }
    }
     
    public void setStatus()
    {
        if (aps.containsKey(nombre))
        {
            status.campoIP.setText(ap.getIP());
            status.campoMask.setText(ap.getMask());
            status.campoSSID.setText(ap.getSSID());
            status.campoDHCP.setText(ap.getActDHCP());
            status.campoSeguridad.setText(ap.getSeguridad());
        }
        else
        {
            status.campoIP.setText("0.0.0.0");
            status.campoMask.setText("0.0.0.0");
            status.campoSSID.setText("");
            status.campoDHCP.setText("");
            status.campoSeguridad.setText("Ninguna");
        }
    }
     
     public void setConfig()
    {
       ap.setNombre(nombre);
       ap.setIP(ip);
       ap.setMask(mask);
       ap.setActDHCP(dhcpActivo);
       ap.setCantDHCP(cantDHCP);
       ap.setSSID(ssid);
       ap.setSeguridad(tipoClave);
       ap.setPassword(clave);
       ap.setRegion(region);
       ap.setCanal(canal);
       ap.setModo(modo);
       ap.setDirecciones(dir, uso);
       ap.setBanda(banda);
       ap.setPosx(boton.getLocation().x);
       ap.setPosy(boton.getLocation().y);
       ap.setCanal5G(canal5G);
       ap.setRegion5G(region5G);
       ap.setRx(arrayCirculoAP.get(indice).r1);
       ap.setRy(arrayCirculoAP.get(indice).r2);
       
       if ((aps.isEmpty())||(!aps.containsKey(nombre))) ap.setConfigurado(true);
       else
       {
           if (ap.getConfigurado())
           {
               //ya fue previamente configurado 
               ap.setConfigurado(true);
               aps.remove(nombre);
           }
       }
       aps.put(nombre, ap);
    }    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
