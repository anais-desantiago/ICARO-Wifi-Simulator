package clases;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class ConfigSTAAdhoc extends JDialog
{
    JDialog configAdhocDialog;
    JPanel panel = new JPanel();
    
    HashMap stas = new HashMap();
    HashMap redesAdHoc = new HashMap();
    
    Revisiones revisar = new Revisiones();
    VerificarIP verIp = new VerificarIP();
    
    STAAdhocPanel adHoc;
    STA sta;
    STA auxSTA = new STA();
    STA padreSTA = new STA();
    SignalRange senal = new SignalRange();
    Animacion animacion = new Animacion();
    
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    
    ArrayList <AddBotones.DrawCanvas> arrayCirculoSTA = new ArrayList<>();
    
    ArrayList <String> indices = new ArrayList<>();
    ArrayList <String> ssids = new ArrayList<>();
    ArrayList <String> seguridades = new ArrayList<>();
    
    HashMap contenedor = new HashMap();
    
    JButton boton = new JButton();
    JButton conectarSTA,cancelSTA;
    
    String ipSTA = "", mask = "", nombre = "",tipoSeguridad = "", ssid = "", clave = "";
    int indice, indiceN, inBoton, x,y;
    boolean error = false, redes = false, nodoPadre = false;

    public ConfigSTAAdhoc(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        configAdhocDialog = new JDialog(parent, modal);
        adHoc = new STAAdhocPanel();
        
        this.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(adHoc);
        configAdhocDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        conectarSTA = new JButton("Conectar");
        conectarSTA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                error = false;
                /*if ((nodoPadre)&&(stas.isEmpty()))
                        JOptionPane.showMessageDialog(null, "Se debe conectar primero otra STA antes que esta se conecte a la red");
                else*/
                if (!revisar.CheckVacioTabbedPane(adHoc))
                {
                    checkConfig();
                    if (!error)
                    {
                        guardarConfig();
                        configAdhocDialog.setVisible(false);
                    }
                }
            }
        });
        
        cancelSTA = new JButton("Cancelar");
        cancelSTA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                configAdhocDialog.setVisible(false);
            }
        });
        
        configAdhocDialog.getRootPane().setDefaultButton(conectarSTA);
        
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
        configAdhocDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        configAdhocDialog.pack();
        initComponents();
    }

    public void setTitulo (JButton boton, HashMap auxSTA,int inBoton, HashMap redesA, HashMap contenedor)
    {
        this.inBoton = inBoton;
        nodoPadre = false;
        sta = new STA();
        stas = auxSTA;
        redes = false;
        redesAdHoc = new HashMap();
        ssids.clear();
        indices.clear();
     
        boolean nomEncontrado = false;
        this.contenedor = contenedor;
        nombre = boton.getName();
        configAdhocDialog.setTitle("Configuración de Estación - " + nombre);
        redesAdHoc = redesA;
        
        if (stas.isEmpty()) configInicial();
        else
        {
            if (stas.containsKey(nombre))
            {
                nomEncontrado = true;
                sta = (STA) stas.get(nombre);
            }
            else nomEncontrado = false;
        }
        if (!redesAdHoc.isEmpty())
        {
            if (redesAdHoc.containsKey(nombre))
            {
                padreSTA = (STA) redesAdHoc.get(nombre);
                nodoPadre = true;
            }
            else nodoPadre = false;
            
            if(!nodoPadre)
            {
                senal.botonesSTA = botonesSTA;
                senal.redes = redesAdHoc;
                senal.arrayCirculoSTA = arrayCirculoSTA;

                if (senal.checkAlcance(inBoton, "Ad-Hoc"))
                {
                    indices = senal.indicesSTA;
                    redes = true;
                    cargarValores();
                }
                else
                {
                    redes = false;
                    adHoc.cargarSSID(null, null, false);
                }
            }
            else
            {
                cargarValores();
                //if (!stas.isEmpty()) cargarValores();
                /*else
                {
                    JOptionPane.showMessageDialog(null, "Se debe conectar primero otra STA antes que ésta se conecte a la red");
                    error = true;
                }*/
            }
            if(!nomEncontrado) configInicial();
            else cargarConfiguracion();
        }
        else
        {
            ssids.clear();
            redes = false;
            adHoc.cargarSSID(null, null, false);
        }
    }

    public void configInicial()
    {
        String [] octetos = new String [4];
        octetos = "0.0.0.0".split("\\.");
        fillCasillasIP("LAN", octetos);

        octetos = "0.0.0.0".split("\\.");
        fillCasillasIP("MáscaraSTA", octetos);
        adHoc.passSTAField.setText("");
    }
    
    public void cargarValores()
    {
        if (!nodoPadre)
        {
            for (int i =0; i<indices.size();i++)
            {
                if (redesAdHoc.containsKey(indices.get(i)))
                {
                    auxSTA = (STA) redesAdHoc.get(indices.get(i));
                    ssids.add(auxSTA.getSSID());
                    seguridades.add(auxSTA.getSeguridad());
                }
            }

            adHoc.indices = indices;
            adHoc.cargarSSID(ssids, seguridades, redes);
            ssid = adHoc.ssid;
            tipoSeguridad = adHoc.tipoSeguridad;

            for (Object key : redesAdHoc.keySet())
            {
                padreSTA = (STA) redesAdHoc.get(key);
                if (ssid.equals(padreSTA.getSSID()))
                {
                    x = padreSTA.getPosx();
                    y = padreSTA.getPosy();
                    break;
                }
            }
        }
        else
        {
            ssid = padreSTA.getSSID();
            tipoSeguridad = padreSTA.getSeguridad();
            
            if (!tipoSeguridad.equals("Ninguna"))
            {
                clave = padreSTA.getPassword();
                adHoc.passSTAField.setText(clave);
                adHoc.passSTAField.setEnabled(false);
            }
            else
            {
                adHoc.passSTAField.setText("");
                adHoc.passSTAField.setEnabled(false);
            }
            adHoc.comboBoxSSID.removeAllItems();
            adHoc.comboBoxSSID.addItem(ssid);
            adHoc.comboBoxSSID.setEnabled(false);
            adHoc.activarConfig(true);
            x = padreSTA.getPosx();
            y = padreSTA.getPosy();
        }
    }
    
    public void fillCasillasIP(String tipo, String [] octeto)
    {
        String [] octetos = new String [4];
        octetos = octeto;
        if (tipo.equals("LAN"))
        {
            adHoc.staIP1.setText(octetos[0]);
            adHoc.staIP2.setText(octetos[1]);
            adHoc.staIP3.setText(octetos[2]);
            adHoc.staIP4.setText(octetos[3]);
        }
        if (tipo.equals("MáscaraSTA"))
        {
            adHoc.staMask1.setText(octetos[0]);
            adHoc.staMask2.setText(octetos[1]);
            adHoc.staMask3.setText(octetos[2]);
            adHoc.staMask4.setText(octetos[3]);
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
        
        octetos = ipSTA.split("\\.");
        fillCasillasIP("LAN", octetos);

        octetos = mask.split("\\.");
        fillCasillasIP("MáscaraSTA", octetos);
        
        if (!nodoPadre)
        {
            if (tipoSeguridad.equals("Ninguna")) adHoc.passSTAField.setEnabled(false);
            else
            {
                adHoc.passSTAField.setEnabled(true);
                clave = sta.getPassword();
                adHoc.passSTAField.setText(clave);
            }

            if (!ssids.isEmpty())
            {
                for (int i=0;i<ssids.size();i++)
                {
                    if (ssid.equals(ssids.get(i)))
                    {
                        encontrado = true;
                        adHoc.comboBoxSSID.setSelectedItem(ssid);
                        break;
                    }
                }

                if (!encontrado) cargarValores(); 
            }
        }
    }
    
    public void checkConfig()
    {
        //verificación de la máscara del router con la de la estación
        verIp.octetosNum[0] = Integer.parseInt(adHoc.staMask1.getText());
        verIp.octetosNum[1] = Integer.parseInt(adHoc.staMask2.getText());
        verIp.octetosNum[2] = Integer.parseInt(adHoc.staMask3.getText());
        verIp.octetosNum[3] = Integer.parseInt(adHoc.staMask4.getText());
        verIp.ipFrom = "MáscaraSTA";
        
        if (!verIp.checkIP(verIp.ipFrom)) error = true;
        else
        {
            mask = verIp.maskSTA;
            verIp.octetosNum = new int [4];
            verIp.octetosNum[0] = Integer.parseInt(adHoc.staIP1.getText());
            verIp.octetosNum[1] = Integer.parseInt(adHoc.staIP2.getText());
            verIp.octetosNum[2] = Integer.parseInt(adHoc.staIP3.getText());
            verIp.octetosNum[3] = Integer.parseInt(adHoc.staIP4.getText());
            verIp.ipFrom = "STA";

            if (!verIp.checkIP(verIp.ipFrom)) error = true;
            else ipSTA = verIp.ipS;
        }
        
        if (!nodoPadre)
        {
            if (!tipoSeguridad.equals("Ninguna"))
            {
                if(!adHoc.passSTAField.getText().equals(sta.getPassword()))
                {
                    JOptionPane.showMessageDialog(null, "Error: las contraseñas no coinciden");
                    error = true;
                }
                else clave = adHoc.passSTAField.getText();
            }
        }
        
        if(verIp.claseIP()) guardarConfig();
        else error = true;
    }
    
    public void guardarConfig()
    {
        boolean found = false;
        String aux;
        
        sta.setID(nombre);
        sta.setSSID(ssid);
        sta.setIP(ipSTA);
        sta.setMask(mask);
        sta.setPosx(boton.getLocation().x);
        sta.setPosy(boton.getLocation().y);
        sta.setSeguridad(tipoSeguridad);
        sta.setPassword(clave);
        sta.setRx(arrayCirculoSTA.get(inBoton).r1);
        sta.setRy(arrayCirculoSTA.get(inBoton).r2);
        sta.setNodoPadre(nodoPadre);
        sta.setOrigenSSID(padreSTA.getID());
        
        /*if(!padreSTA.getNombres().isEmpty())
        {
            found = padreSTA.getNombres().containsKey(nombre);
            if (!found) padreSTA.setNombres(nombre);
        }
        else padreSTA.setNombres(nombre);*/
        
        padreSTA.setNombres(nombre);
        System.out.println("padre estaciones: "+padreSTA.getNombres());
        
        if ((stas.isEmpty())||(!stas.containsKey(nombre))) sta.setConfigurado(true);
        else
        {
            if (sta.getConfigurado())
            {
                //la estación ya fue configurada
                stas.remove(nombre);
                sta.setConfigurado(true);
            }
        }
        stas.put(nombre, sta);
        if (nodoPadre) stas.put(nombre, padreSTA);
        else
        {
            animacion = new Animacion();
            invocarAnimacion();
        }
    }
   
    public void invocarAnimacion()
    {
        animacion.botonesSTA = botonesSTA.get(0);
        animacion.setNombre(nombre);
        animacion.setX1(boton.getLocation().x);
        animacion.setY1(boton.getLocation().y);
        animacion.setTipoAnimacion("Conexión");
        animacion.setTipoRed("Ad-Hoc");
        animacion.panel = panel;
  
        if (contenedor.isEmpty()||(!contenedor.containsKey(nombre)))
        {
            animacion.setVisible(true);
            animacion.boton = new JButton();
        }
        else
        {
            if (contenedor.containsKey(nombre))
            {
                //la animacion ya fue configurada
                animacion = (Animacion) contenedor.get(nombre);
                animacion.nombre = nombre;
                animacion.boton = animacion.getBoton();
                contenedor.remove(nombre);
                animacion.setVisible(true);
            }
        }
        animacion.getNodo(null, null, stas, redesAdHoc);
        contenedor.put(nombre, animacion);
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
