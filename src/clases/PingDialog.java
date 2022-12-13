package clases;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class PingDialog extends JDialog
{
    JPanel panel = new JPanel();
    PingPanel pingPanel = new PingPanel();
    SignalRange senal = new SignalRange();
    Ping ping, ping1, ping2;

    Revisiones revisar = new Revisiones();
    Resetear reset = new Resetear();
    VerificarIP verIP = new VerificarIP();
    
    HashMap routers = new HashMap();
    HashMap stas =  new HashMap();
    HashMap aps = new HashMap();
    
    JDialog pingDialog;
    JButton pingB, cancelar, boton;
    String ip="", ip0="", error="", tipoRed="", nodo="", nombre="", ssid="", origen="";
    int x1 = 0,y1 =0 ,x2= 0,y2=0, x3=0,y3=0, r1_x=0, r1_y=0, r2_x=0,r2_y=0,r3_x=0,r3_y=0;
    boolean encontrado=false, padre=false, borrar = false, errorB=false;
    Buscar buscar = new Buscar();
    
    STA sta = new STA();
    Router router = new Router();
    AccessPoint ap = new AccessPoint();
    Animacion animacion, animacion2;
    
    public PingDialog(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        
        pingDialog = new JDialog(parent, modal);
        pingPanel = new PingPanel();
        
        ping = new Ping("");
        
        this.setSize(50,50);
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel panel_superior = new JPanel();
        
        panel_superior.add(pingPanel);
        pingDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        pingB = new JButton("Ping");
        pingB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!revisar.CheckVacioTabbedPane(pingPanel))
                {
                    pingDialog.setVisible(false);
                    checkIP();
                    buscar.ip = ip;
                    buscar.buscarIP();
                    
                    reset.ResetValoresTabbedPane(pingPanel);
                    
                    ping1 = new Ping("iniciar");
                    
                    ping1.fuente = ip0;
                    ping1.destino = ip;
                    
                    try {
                        ping1.hilo.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PingDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ping.continuar = buscar.continuar;
                    ping.x1 = buscar.x1;
                    ping.x2 = buscar.x2;
                    ping.y1 = buscar.y1;
                    ping.y2 = buscar.y2;
                    ping.x3 = buscar.x3;
                    ping.y3 = buscar.y3;
                    ping.panel = panel;
                    ping.nodosAlc = buscar.nodosAlc;
                    
                    ping.hacerPing(buscar.ip0, buscar.ip, buscar.encontrado, buscar.error);
                }
            }
        });
        
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                reset.ResetValoresTabbedPane(pingPanel);
                pingDialog.setVisible(false);
            }
        });
        
        pingDialog.getRootPane().setDefaultButton(pingB);
        
        pingB.addKeyListener(new KeyListener()
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
        panel_inferior.add(pingB);
        panel_inferior.add(cancelar);
        pingDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        pingDialog.setTitle("Ping");
        pingDialog.pack();  
        
        initComponents();
    }
    
    public void checkIP()
    {
        verIP.octetosNum = new int[4];
        verIP.octetosNum[0] = Integer.parseInt(pingPanel.pingIP1.getText());
        verIP.octetosNum[1] = Integer.parseInt(pingPanel.pingIP2.getText());
        verIP.octetosNum[2] = Integer.parseInt(pingPanel.pingIP3.getText());
        verIP.octetosNum[3] = Integer.parseInt(pingPanel.pingIP4.getText());
        verIP.ipFrom = "bla";
        if (verIP.checkIP(verIP.ipFrom)) ip = verIP.ip; //ip a la que se le quiere hacer ping
        else errorB = true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
