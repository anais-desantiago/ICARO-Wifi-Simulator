package clases;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class CrearSTAAdhoc extends JDialog
{
    /*Esta clase permite crear la red ad-hoc desde una estación cualquiera*/
    JDialog crearAdhocDialog;
    
    HashMap stas = new HashMap();
    
    Revisiones revisar = new Revisiones();
    VerificarIP verIp = new VerificarIP();
    
    CrearSTAAdhocPanel adHoc;
    STA sta;
    SignalRange senal = new SignalRange();
    
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    
    ArrayList <AddBotones.DrawCanvas> arrayCirculoSTA = new ArrayList<>();
    
    JButton boton = new JButton();
    JButton crearSTA, cancelSTA;
    
    String nombre = "", seguridad= "", clave="";
    
    int indice, inBoton;
    boolean error = false;

    public CrearSTAAdhoc(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        crearAdhocDialog = new JDialog(parent, modal);
        adHoc = new CrearSTAAdhocPanel();
        
        this.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(adHoc);
        crearAdhocDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        crearSTA = new JButton("Crear Red");
        crearSTA.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!revisar.CheckVacioTabbedPane(adHoc))
                {
                    error = false;
                    checkConfig();
                    if (!error) {
                        crearAdhocDialog.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Configuración exitosa: Por favor haga doble clic en el nodo para configurarle la IP");
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
                crearAdhocDialog.setVisible(false);
            }
        });
        
        crearAdhocDialog.getRootPane().setDefaultButton(crearSTA);
        
        crearSTA.addKeyListener(new KeyListener()
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
        
        panel_inferior.add(crearSTA);
        panel_inferior.add(cancelSTA);
        crearAdhocDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        crearAdhocDialog.pack();
        initComponents();
    }

    public void setTitulo (JButton boton, HashMap auxSTA,int inBoton)
    {
        this.inBoton = inBoton;

        sta = new STA();
        stas = auxSTA;
     
        boolean nomEncontrado = false;
        
        nombre = boton.getName();
        crearAdhocDialog.setTitle("Creación de red desde la " + nombre);
        
        if (stas.isEmpty()) configInicial();
        else
        {
            if (stas.containsKey(nombre))
            {
                sta = (STA) stas.get(nombre);
                nomEncontrado = true;
            }
            else nomEncontrado = false;
        }
        
        if(!nomEncontrado) configInicial();
        else cargarConfiguracion();
    }

    public void configInicial()
    {
        adHoc.ssidAdHoc.setText("");
        adHoc.ningunaSeg.setSelected(true);
        adHoc.passRouter.setText("");
        adHoc.passRouter.setEnabled(false);
    }
    
    public void cargarConfiguracion()
    {
        adHoc.ssidAdHoc.setText(sta.getSSID());
        seguridad = sta.getSeguridad();
        clave = sta.getPassword();
        
        if (seguridad.equals("Ninguna")) adHoc.ningunaSeg.setSelected(true);
        else
        {
            adHoc.passRouter.setText(clave);
            if (seguridad.equals("WEP")) adHoc.wep.setSelected(true);
            if (seguridad.equals("WPA")) adHoc.wpa.setSelected(true);
        }
    }
    
    public void checkConfig()
    {
        STA aux = new STA();
        boolean repetido = false;
        seguridad = adHoc.tipoSeguridad;
        if (!seguridad.equals("Ninguna")) clave = adHoc.passRouter.getText();
        
        for (Object key :stas.keySet())
        {
            aux = (STA) stas.get(key);
            if (adHoc.ssidAdHoc.getText().equals(aux.getSSID())&&(!aux.getID().equals(nombre)))
            {
                JOptionPane.showMessageDialog(null, "Error: no pueden existir nodos con el mismo SSID");
                repetido = true;
                error = true;
                break;
            }else repetido = false;
        }
        if (!error) guardarConfig();
    }
    
    public void guardarConfig()
    {
        sta.setID(nombre);
        sta.setSSID(adHoc.ssidAdHoc.getText());
        sta.setSeguridad(seguridad);
        sta.setPassword(clave);
        sta.setNodoPadre(true);
        
        sta.setPosx(boton.getLocation().x);
        sta.setPosy(boton.getLocation().y);
        sta.setRx(arrayCirculoSTA.get(inBoton).r1);
        sta.setRy(arrayCirculoSTA.get(inBoton).r2);
        
        if (stas.isEmpty() || !stas.containsKey(nombre)) sta.setConfigurado(true);
        else
        {
            if (sta.getConfigurado())
            {
                //la estación ya fue configurada
                stas.remove(indice);
                sta.setConfigurado(true);
            }
        }
        stas.put(nombre, sta);
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
