package clases;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class ConfigRouter extends JDialog
{
    /*Clase que permite la configuración de un router*/
    JTabbedPane pestanasRouter;
    
    JPanel panelLAN = new JPanel();
    JPanel panelWLAN = new JPanel();
    JPanel panelWAN = new JPanel();
    JPanel panelStatus = new JPanel();
    
    VerificarIP verIp = new VerificarIP();
    AsigDHCP dhcp = new AsigDHCP();
    WLANRegion region1 = new WLANRegion();
    
    LANPanel lan;
    WLANPanel wlan;
    WANPanel wan;
    StatusPanel status;
    Revisiones revisar;
    Resetear reset;
    Router router;
    
    JButton boton = new JButton();
    JButton guardarRouter, cancelRouter;
    JDialog configRouterDialog;
    
    String nombre, claveR = "", tipoClave ="", banda, ssid, region, region5G, modo, mask, dhcpActivo,ip, ipWAN, maskWAN, gt, dns1, dns2, conexion;
    Boolean seguridad = false, error = false;

    int canal,indice = 0, cantDHCP, canal5G;
    
    HashMap routers = new HashMap();
    ArrayList <AddBotones.DrawCanvas> arrayCirculoRouter = new ArrayList<>();
    HashMap aps = new HashMap();
    
    ArrayList <String> dir = new ArrayList<>();
    ArrayList <String> uso = new ArrayList<>();
    
    public ConfigRouter(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        revisar = new Revisiones();
        reset = new Resetear();
        configRouterDialog = new JDialog(parent, modal);
        
        pestanasRouter = new JTabbedPane();
        lan = new LANPanel();
        wlan = new WLANPanel();
        wan = new WANPanel();
        status = new StatusPanel();
        
        this.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panelLAN.add(lan);
        panelWLAN.add(wlan);
        panelWAN.add(wan);
        panelStatus.add(status);
        
        pestanasRouter.addTab("LAN", panelLAN);
        pestanasRouter.addTab("WLAN",panelWLAN);
        pestanasRouter.addTab("WAN",panelWAN);
        pestanasRouter.addTab("Status", panelStatus);
        
        panel_superior.add(pestanasRouter);
        configRouterDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardarRouter = new JButton("Guardar");
        guardarRouter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!invocarRevisionVacio())
                {
                    //guardar
                    checkConfig();
                    if (!error) configRouterDialog.setVisible(false);
                }
            }
        });
        
        cancelRouter = new JButton("Cancelar");
        cancelRouter.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                configRouterDialog.setVisible(false);
            }
        });
        
        configRouterDialog.getRootPane().setDefaultButton(guardarRouter);
        
        guardarRouter.addKeyListener(new KeyListener()
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
        
        panel_inferior.add(guardarRouter);
        panel_inferior.add(cancelRouter);
        configRouterDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        configRouterDialog.pack();
                
        initComponents();
    }
    
    public void setTitulo (JButton boton, HashMap arrayRouter)
    {
        router = new Router();
        boolean nomEncontrado = false;
        nombre = boton.getName();
        configRouterDialog.setTitle("Configuración de Router - "+nombre);
        routers = arrayRouter;
        
        if (routers.isEmpty()) configInicial();
        else
        {
            if (routers.containsKey(nombre))
            {
                nomEncontrado = true;
                router = (Router) routers.get(nombre);
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
        
        fillCasillasIP("WAN", octetos);
        fillCasillasIP("Máscara WAN", octetos);
        
        fillCasillasIP("Gateway", octetos);
        fillCasillasIP("DNS1", octetos);
        fillCasillasIP("DNS2", octetos);
        
        wlan.comboModo.setSelectedIndex(0);
        wlan.comboCanal2GHz.setSelectedIndex(0);
        wlan.comboCanal2GHz.setEnabled(false);
        wlan.comboRegion2Ghz.setSelectedIndex(0);
        wlan.comboRegion2Ghz.setEnabled(false);
        wlan.comboRegion5Ghz.setSelectedIndex(0);
        wlan.comboRegion5Ghz.setEnabled(false);
        wlan.comboCanal5GHz.setSelectedIndex(0);
        wlan.comboCanal5GHz.setEnabled(false);
        
        wlan.ningunaSeg.setSelected(true);
        wlan.passRouter.setText("");
        wlan.passRouter.setEnabled(false);
        
        wan.wanComboBox.setSelectedIndex(0);
        wan.habilitarIPs(false);
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
        if (tipo.equals("WAN"))
        {
            wan.ipWAN1.setText(octetos[0]);
            wan.ipWAN2.setText(octetos[1]);
            wan.ipWAN3.setText(octetos[2]);
            wan.ipWAN4.setText(octetos[3]);
        }
        if (tipo.equals("Máscara WAN"))
        {
            wan.maskWAN1.setText(octetos[0]);
            wan.maskWAN2.setText(octetos[1]);
            wan.maskWAN3.setText(octetos[2]);
            wan.maskWAN4.setText(octetos[3]);
        }
        if (tipo.equals("Gateway"))
        {
            wan.gtWAN1.setText(octetos[0]);
            wan.gtWAN2.setText(octetos[1]);
            wan.gtWAN3.setText(octetos[2]);
            wan.gtWAN4.setText(octetos[3]);
        }
        if (tipo.equals("DNS1"))
        {
            wan.dnsPrim1.setText(octetos[0]);
            wan.dnsPrim2.setText(octetos[1]);
            wan.dnsPrim3.setText(octetos[2]);
            wan.dnsPrim4.setText(octetos[3]);
        }
        if (tipo.equals("DNS2"))
        {
            wan.dnsSec1.setText(octetos[0]);
            wan.dnsSec2.setText(octetos[1]);
            wan.dnsSec3.setText(octetos[2]);
            wan.dnsSec4.setText(octetos[3]);
        }
    }
    
    public void cargarConfiguracion()
    {
        ssid = router.getSSID();
        wlan.ssid.setText(ssid);
        String [] octetos = new String [4];
        
        ip = router.getIP();
        octetos = ip.split("\\.");
        fillCasillasIP("LAN", octetos);
        
        mask = router.getMask();
        octetos = mask.split("\\.");
        fillCasillasIP("Máscara", octetos);
        
        if ((router.getActDHCP()).equals("Activado"))
        {
            dhcpActivo = "Activado";
            lan.DHCPEnable.setSelected(true);
            cantDHCP = router.getCantDHCP();
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
        
        modo = router.getModo();
        banda = router.getBanda();
        
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
        
        tipoClave = router.getSeguridad();
        if (tipoClave.equals("Ninguna"))
        {
            wlan.ningunaSeg.setSelected(true);
            wlan.passRouter.setEnabled(false);
        }
        else
        {
            claveR = router.getPassword();
            if (tipoClave.equals("WEP")) wlan.wep.setSelected(true);
            if (tipoClave.equals("WPA")) wlan.wpa.setSelected(true);
            wlan.passRouter.setEnabled(true);
            wlan.passRouter.setText(claveR);
        }
        
        conexion = router.getConexion();
        if(conexion.equals("ISP"))
        {
            wan.wanComboBox.setSelectedIndex(1);
            wan.habilitarIPs(false);
        }
        if(conexion.equals("Estática"))
        {
            wan.wanComboBox.setSelectedIndex(2);
            wan.habilitarIPs(true);
        }
        ipWAN = router.getIPWAN();
        octetos = ipWAN.split("\\.");
        fillCasillasIP("WAN", octetos);
        
        maskWAN = router.getMaskWAN();
        octetos = maskWAN.split("\\.");
        fillCasillasIP("Máscara WAN", octetos);
        
        gt = router.getGateway();
        octetos = gt.split("\\.");
        fillCasillasIP("Gateway", octetos);
        
        dns1 = router.getPrimDNS();
        octetos = dns1.split("\\.");
        fillCasillasIP("DNS1", octetos);
        
        dns2 = router.getSecDNS();
        octetos = dns2.split("\\.");
        fillCasillasIP("DNS2", octetos);
    }
    
    public void configRegion2GHz()
    {
        region = router.getRegion();
        if (region.equals("USA")) wlan.comboRegion2Ghz.setSelectedIndex(1);
        if (region.equals("Europa")) wlan.comboRegion2Ghz.setSelectedIndex(2);
        if (region.equals("Japón")) wlan.comboRegion2Ghz.setSelectedIndex(3);
        
        canal = router.getCanal();
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
        region5G = router.getRegion5G();
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
        
        canal5G = router.getCanal5G();
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
        for (int i=0;i<pestanasRouter.getTabCount();i++)
        {
            pestanasRouter.setSelectedIndex(i);
            if (pestanasRouter.getSelectedIndex()==0)
            {
                if(revisar.CheckVacioTabbedPane(lan)) return true;
            }
            if (pestanasRouter.getSelectedIndex()==1)
            {
                if(revisar.CheckVacioTabbedPane(wlan)) return true;
            }
            if (pestanasRouter.getSelectedIndex()==2)
            {
                if(revisar.CheckVacioTabbedPane(wan)) return true;
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
        if (!repetido)
        {
            ssid = wlan.ssid.getText();
            error = false;
        }
        
        tipoClave = wlan.tipoSeguridad;
        claveR = wlan.passRouter.getText();
        
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
                else
                {
                    region = wlan.region1;
                    if(wlan.comboCanal2GHz.getSelectedIndex()==0)
                    {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un canal para la banda ISM de los 2.4 GHz");
                        error = true;
                    }
                    else canal = wlan.canal; 
                }
            }
            if (wlan.comboRegion5Ghz.isEnabled())
            {
                if (wlan.comboRegion5Ghz.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione una región para la banda ISM de los 5 GHz");
                    error = true;
                }
                else
                {
                    region5G = wlan.region5G;
                    if(wlan.comboCanal5GHz.getSelectedIndex()==0)
                    {
                        JOptionPane.showMessageDialog(null, "Por favor seleccione un canal para la banda ISM de los 5 GHz");
                        error = true;
                    }
                    else canal5G = wlan.canal5G;
                }
            }
        }
                        /*Configuración WAN*/
        
        if (wan.wanComboBox.getSelectedIndex()==0)
        {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un tipo de conexión");
            error = true;
        }
        else
        {
            //error = false;
            conexion = wan.conexion;
            if (conexion.equals("ISP"))
            {
                //el ISP es el que asigna la dirección
                dhcp.generarIPWAN();
                ipWAN = dhcp.ipWAN;
                maskWAN = dhcp.maskWAN;
                gt = dhcp.gt;
                dns1 = "8.8.8.8";
                dns2 = dns1;
            }
            else
            {
                //se le asigna la dirección WAN estáticamente
                verIp.octetosNum = new int [4];
                verIp.octetosNum[0] = Integer.parseInt(wan.maskWAN1.getText());
                verIp.octetosNum[1] = Integer.parseInt(wan.maskWAN2.getText());
                verIp.octetosNum[2] = Integer.parseInt(wan.maskWAN3.getText());
                verIp.octetosNum[3] = Integer.parseInt(wan.maskWAN4.getText());
                verIp.ipFrom = "Máscara";
                if (verIp.checkIP(verIp.ipFrom))
                {
                    maskWAN = verIp.mask;
                    
                    verIp.octetosNum[0] = Integer.parseInt(wan.ipWAN1.getText());
                    verIp.octetosNum[1] = Integer.parseInt(wan.ipWAN2.getText());
                    verIp.octetosNum[2] = Integer.parseInt(wan.ipWAN3.getText());
                    verIp.octetosNum[3] = Integer.parseInt(wan.ipWAN4.getText());
                    verIp.ipFrom = "Router";
                    if (verIp.checkIP(verIp.ipFrom)) ipWAN = verIp.ipR;
                    else error = true;
                    
                    verIp.octetosNum[0] = Integer.parseInt(wan.gtWAN1.getText());
                    verIp.octetosNum[1] = Integer.parseInt(wan.gtWAN2.getText());
                    verIp.octetosNum[2] = Integer.parseInt(wan.gtWAN3.getText());
                    verIp.octetosNum[3] = Integer.parseInt(wan.gtWAN4.getText());
                    verIp.ipFrom = "Gateway";
                    if (verIp.checkIP(verIp.ipFrom)) gt = verIp.gt;
                    else error = true;

                    verIp.octetosNum[0] = Integer.parseInt(wan.dnsPrim1.getText());
                    verIp.octetosNum[1] = Integer.parseInt(wan.dnsPrim2.getText());
                    verIp.octetosNum[2] = Integer.parseInt(wan.dnsPrim3.getText());
                    verIp.octetosNum[3] = Integer.parseInt(wan.dnsPrim4.getText());
                    verIp.ipFrom = "DNS";
                    if (verIp.checkIP(verIp.ipFrom)) dns1 = verIp.dns;
                    else error = true;

                    verIp.octetosNum[0] = Integer.parseInt(wan.dnsSec1.getText());
                    verIp.octetosNum[1] = Integer.parseInt(wan.dnsSec2.getText());
                    verIp.octetosNum[2] = Integer.parseInt(wan.dnsSec3.getText());
                    verIp.octetosNum[3] = Integer.parseInt(wan.dnsSec4.getText());
                    verIp.ipFrom = "DNS";
                    if (verIp.checkIP(verIp.ipFrom)) dns2 = verIp.dns;
                    else error = true;
                }
                else error = true;
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
        if (routers.containsKey(nombre))
        {
            status.campoIP.setText(router.getIP());
            status.campoMask.setText(router.getMask());
            status.campoSSID.setText(router.getSSID());
            status.campoDHCP.setText(router.getActDHCP());
            status.campoSeguridad.setText(router.getSeguridad());
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
       router.setNombre(nombre);
       router.setIP(ip);
       router.setMask(mask);
       router.setActDHCP(dhcpActivo);
       router.setCantDHCP(cantDHCP);
       router.setSSID(ssid);
       router.setSeguridad(tipoClave);
       router.setPassword(claveR);
       router.setRegion(region);
       router.setCanal(canal);
       router.setModo(modo);
       router.setDirecciones(dir, uso);
       router.setIPWAN(ipWAN);
       router.setMaskWAN(maskWAN);
       router.setGateway(gt);
       router.setPrimDNS(dns1);
       router.setSecDNS(dns2);
       router.setConexion(conexion);
       router.setBanda(banda);
       router.setPosx(boton.getLocation().x);
       router.setPosy(boton.getLocation().y);
       router.setCanal5G(canal5G);
       router.setRegion5G(region5G);
       router.setRx(arrayCirculoRouter.get(indice).r1);
       router.setRy(arrayCirculoRouter.get(indice).r2);
       
       if ((routers.isEmpty())||(!routers.containsKey(nombre))) router.setConfigurado(true);
       else
       {
           if (router.getConfigurado())
           {
               //ya fue previamente configurado 
               router.setConfigurado(true);
               routers.remove(nombre);
           }
       }
       routers.put(nombre, router);
    }
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