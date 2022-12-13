package clases;
import java.awt.Toolkit;
import javax.swing.*;

public class WLANPanel extends javax.swing.JPanel
{
    /*Clase que muestra los campos para la configuración WLAN*/
    WLANRegion region = new WLANRegion();
    String modo = "<Modo>", region1 = "<Región>", banda = "", tipoSeguridad = "Ninguna", region5G = "<Región>";
    boolean seguridad, error = false;
    int canal = 0, canal5G = 0;
    
    public WLANPanel()
    {
        initComponents();
        passRouter.setEnabled(false);
        ningunaSeg.setSelected(true);
        comboCanal2GHz.setEnabled(false);
        comboRegion2Ghz.setEnabled(false);
        comboCanal5GHz.setEnabled(false);
        comboRegion5Ghz.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipoSeguridad = new javax.swing.ButtonGroup();
        comboCanal5GHz = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ssid = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        comboRegion2Ghz = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        comboCanal2GHz = new javax.swing.JComboBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        comboModo = new javax.swing.JComboBox();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        ningunaSeg = new javax.swing.JRadioButton();
        wep = new javax.swing.JRadioButton();
        wpa = new javax.swing.JRadioButton();
        dosGHz = new javax.swing.JCheckBox();
        cincoGHz = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        passRouter = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        comboRegion5Ghz = new javax.swing.JComboBox();
        jLabel32 = new javax.swing.JLabel();

        comboCanal5GHz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Canal>" }));
        comboCanal5GHz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCanal5GHzActionPerformed(evt);
            }
        });

        jLabel14.setText("Introduzca el nombre la red");

        jLabel15.setText("SSID:");

        jLabel16.setText("Región 2.4 GHz:");

        jLabel27.setText("Seleccione la Región donde se encuentra y el canal a utilizar");

        comboRegion2Ghz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Región>", "USA", "Europa", "Japón" }));
        comboRegion2Ghz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRegion2GhzActionPerformed(evt);
            }
        });

        jLabel28.setText("Canal 2.4 GHz:");

        comboCanal2GHz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Canal>" }));
        comboCanal2GHz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCanal2GHzActionPerformed(evt);
            }
        });

        jLabel29.setText("Seleccione el Modo y la Banda de operación");

        jLabel30.setText("Modo:");

        comboModo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Modo>", "802.11a", "802.11b", "802.11g", "802.11b/g/n", "802.11n" }));
        comboModo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboModoActionPerformed(evt);
            }
        });

        jLabel31.setText("Seleccione el mecanismo de seguridad");

        TipoSeguridad.add(ningunaSeg);
        ningunaSeg.setSelected(true);
        ningunaSeg.setText("Ninguna");
        ningunaSeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ningunaSegActionPerformed(evt);
            }
        });

        TipoSeguridad.add(wep);
        wep.setText("WEP");
        wep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wepActionPerformed(evt);
            }
        });

        TipoSeguridad.add(wpa);
        wpa.setText("WPA/WPA2");
        wpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wpaActionPerformed(evt);
            }
        });

        dosGHz.setText("2.4 GHz");
        dosGHz.setEnabled(false);
        dosGHz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosGHzActionPerformed(evt);
            }
        });

        cincoGHz.setText("5 GHz");
        cincoGHz.setEnabled(false);
        cincoGHz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoGHzActionPerformed(evt);
            }
        });

        jLabel1.setText("Contraseña de red:");

        passRouter.setEnabled(false);
        passRouter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passRouterKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passRouterKeyTyped(evt);
            }
        });

        jLabel17.setText("Región 5 GHz:");

        comboRegion5Ghz.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Región>" }));
        comboRegion5Ghz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRegion5GhzActionPerformed(evt);
            }
        });

        jLabel32.setText("Canal 5 GHz:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ningunaSeg)
                        .addGap(97, 97, 97)
                        .addComponent(wep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wpa))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboRegion2Ghz, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel32))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboCanal5GHz, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboRegion5Ghz, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(104, 104, 104))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dosGHz)
                        .addGap(46, 46, 46)
                        .addComponent(cincoGHz)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(133, 133, 133))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(102, 102, 102))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(55, 55, 55))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ssid, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE))
                                    .addComponent(jSeparator3)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(passRouter, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboCanal2GHz, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(ssid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboModo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(dosGHz)
                    .addComponent(cincoGHz))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRegion2Ghz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(comboRegion5Ghz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(comboCanal2GHz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(comboCanal5GHz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ningunaSeg)
                    .addComponent(wep)
                    .addComponent(wpa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(passRouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboModoActionPerformed
        //combo box del modo de operación (802.11a, 802.11b, etc)
        int indice = comboModo.getSelectedIndex();
        if (indice == 0)
        {
            comboCanal2GHz.setEnabled(false);
            comboRegion2Ghz.setEnabled(false);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
        }
        if (indice == 1)
        {
            modo = "802.11a";
            cincoGHz.setSelected(true);
            banda = "5 GHz";
            cincoGHz.setEnabled(false);
            dosGHz.setEnabled(false);
            dosGHz.setSelected(false);
            comboCanal2GHz.setEnabled(false);
            comboRegion2Ghz.setEnabled(false);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
            setRegion5Ghz(modo);
        }
        if (indice == 2)
        {
            modo = "802.11b";
            dosGHz.setSelected(true);
            banda = "2.4 GHz";
            dosGHz.setEnabled(false);
            cincoGHz.setEnabled(false);
            cincoGHz.setSelected(false);
            comboCanal2GHz.setEnabled(true);
            comboRegion2Ghz.setEnabled(true);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
        }
        if (indice == 3)
        {
            modo = "802.11g";
            dosGHz.setSelected(true);
            dosGHz.setEnabled(false);
            banda = "2.4 GHz";
            cincoGHz.setEnabled(false);
            cincoGHz.setSelected(false);
            comboCanal2GHz.setEnabled(true);
            comboRegion2Ghz.setEnabled(true);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
        }
        if (indice == 4)
        {
            modo = "802.11b/g/n";
            dosGHz.setSelected(false);
            dosGHz.setEnabled(true);
            cincoGHz.setEnabled(true);
            cincoGHz.setSelected(false);
            comboCanal2GHz.setEnabled(false);
            comboRegion2Ghz.setEnabled(false);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
        }
        if (indice == 5)
        {
            modo = "802.11n";
            dosGHz.setSelected(false);
            dosGHz.setEnabled(true);
            cincoGHz.setEnabled(true);
            cincoGHz.setSelected(false);
            comboRegion2Ghz.setEnabled(false);
            comboCanal2GHz.setEnabled(false);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
        }
    }//GEN-LAST:event_comboModoActionPerformed

    private void ningunaSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ningunaSegActionPerformed
        // sin clave
        seguridad = false;
        tipoSeguridad = "Ninguna";
        passRouter.setEnabled(false);
    }//GEN-LAST:event_ningunaSegActionPerformed

    private void wepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wepActionPerformed
        //clave WEP
        seguridad = true;
        passRouter.setEnabled(true);
        tipoSeguridad = "WEP";
    }//GEN-LAST:event_wepActionPerformed

    private void wpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wpaActionPerformed
        //clave WPA
        seguridad = true;
        passRouter.setEnabled(true);
        tipoSeguridad = "WPA";
    }//GEN-LAST:event_wpaActionPerformed

    private void comboRegion2GhzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRegion2GhzActionPerformed
        // TODO add your handling code here:
        region.indice = comboRegion2Ghz.getSelectedIndex();
        region1 = region.getRegion(region.indice);
        setCanal(region1);
    }//GEN-LAST:event_comboRegion2GhzActionPerformed

    public void setCanal(String region)
    {
        comboCanal2GHz.removeAllItems();
        String [] canal = new String [15];
        
        for (int i=0; i<15;i++)
        {
            if (i==0) canal[0] = "<Canal>";
            else canal[i] = Integer.toString(i);
        }
        if (region.equals("USA"))
        {
            for (int i=0; i<12;i++)
            {
                comboCanal2GHz.addItem(canal[i]);
            }
        }
        if (region.equals("Europa"))
        {
            for (int i=0; i<14;i++)
            {
                comboCanal2GHz.addItem(canal[i]);
            }
        }
        if (region.equals("Japón"))
        {
            for (int i=0; i<15;i++)
            {
                comboCanal2GHz.addItem(canal[i]);
            }
        }
    }
    
    private void comboCanal2GHzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCanal2GHzActionPerformed
        // TODO add your handling code here:
        canal = comboCanal2GHz.getSelectedIndex();
    }//GEN-LAST:event_comboCanal2GHzActionPerformed

    private void dosGHzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosGHzActionPerformed
        // TODO add your handling code here:
        if ((dosGHz.isSelected())&&(!cincoGHz.isSelected()))
        {
            banda = "2.4 GHz";
            comboRegion2Ghz.setEnabled(true);
            comboCanal2GHz.setEnabled(true);
            comboCanal5GHz.setEnabled(false);
            comboRegion5Ghz.setEnabled(false);
        }
        if ((dosGHz.isSelected())&&(cincoGHz.isSelected()))
        {
            comboRegion2Ghz.setEnabled(true);
            comboCanal2GHz.setEnabled(true);
            comboCanal5GHz.setEnabled(true);
            comboRegion5Ghz.setEnabled(true);
            banda = "Dual-Band";
            setRegion5Ghz(modo);
        }
        if (!dosGHz.isSelected())
        {
            comboRegion2Ghz.setEnabled(false);
            comboCanal2GHz.setEnabled(false);
        }
    }//GEN-LAST:event_dosGHzActionPerformed

    private void cincoGHzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincoGHzActionPerformed
        // TODO add your handling code here:
        if ((!dosGHz.isSelected())&&(cincoGHz.isSelected()))
        {
            comboRegion2Ghz.setEnabled(false);
            comboCanal2GHz.setEnabled(false);
            comboCanal5GHz.setEnabled(true);
            comboRegion5Ghz.setEnabled(true);
            banda = "5 GHz";
            setRegion5Ghz(modo);
        }
        if ((dosGHz.isSelected())&&(cincoGHz.isSelected()))
        {
            comboRegion2Ghz.setEnabled(true);
            comboCanal2GHz.setEnabled(true);
            comboCanal5GHz.setEnabled(true);
            comboRegion5Ghz.setEnabled(true);
            banda = "Dual-Band";
            setRegion5Ghz(modo);
        }
        
        if (!cincoGHz.isSelected())
        {
            comboRegion5Ghz.setEnabled(false);
            comboCanal5GHz.setEnabled(false);
        }
    }//GEN-LAST:event_cincoGHzActionPerformed

    private void passRouterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passRouterKeyPressed
        // TODO add your handling code here:
         if (tipoSeguridad.equals("WEP"))
        {    
            int letra = evt.getKeyCode();
            if (Character.isDigit(evt.getKeyChar()) || (((letra>=65) && (letra <= 70)) || (letra >= 97) && (letra<=102))||(letra==8)) error = false;
            else
            {
                error = true;
                passRouterKeyTyped(evt);
            }
        }
    }//GEN-LAST:event_passRouterKeyPressed

    private void passRouterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passRouterKeyTyped
        // TODO add your handling code here:
        if (error == true)
        {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten caracteres hexadecimales (0-9) y (A-F)");
            error = false;
        }
        else error = false;
    }//GEN-LAST:event_passRouterKeyTyped

    public void setRegion5Ghz(String modo)
    {
        comboRegion5Ghz.removeAllItems();
        String [] modoA = {"<Región>","América", "Australia", "China", "Europa", "Japón"};
        String [] modoN = {"<Región>","América", "China", "Europa", "Israel", "Japón"};
        
        if (modo.equals("802.11a"))
        {            
            for (String modoA1 : modoA)
            {
                comboRegion5Ghz.addItem(modoA1);
            }
        }
        if ((modo.equals("802.11n"))||(modo.equals("802.11b/g/n")))
        {
            for (String modoN1 : modoN)
            {
                comboRegion5Ghz.addItem(modoN1);
            }
        }
        comboRegion5Ghz.setEnabled(true);
    }
    
    public void setCanal5Ghz(String region)
    {
        comboCanal5GHz.removeAllItems();
        
        String [] usa = {"<Canal>","36","40","44","52","56","60","64","149","153","157","161"}; // válido tanto para América como para Australia
        String [] euro = {"<Canal>","36","40","44","52","56","60","64","100","104","108","112","116","120","124","128","132","136","140"};
        String [] jap = {"<Canal>","36","40","44","52","56","60","64"};//Válido tanto para Japón como para Israel
        String [] chi = {"<Canal>","149","153","157","161"};
        
        if (region.equals("América")|| region.equals("Australia"))
        {
            for (String usa1 : usa)
            {
                comboCanal5GHz.addItem(usa1);
            }
        }
        if (region.equals("Europa"))
        {
            for (String euro1 : euro)
            {
                comboCanal5GHz.addItem(euro1);
            }
        }
        if (region.equals("Japón")|| region.equals("Israel"))
        {
            for (String jap1 : jap)
            {
                comboCanal5GHz.addItem(jap1);
            }
        }
        if (region.equals("China"))
        {
            for (String chi1 : chi)
            {
                comboCanal5GHz.addItem(chi1);
            }
        }
    }
    
    private void comboRegion5GhzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRegion5GhzActionPerformed
        region.indice = comboRegion5Ghz.getSelectedIndex();
        region5G = region.getRegion5GHz(region.indice, modo);
        
        if (comboRegion5Ghz.getSelectedIndex()!=0)
        {
            comboCanal5GHz.setEnabled(true);
            setCanal5Ghz(region5G);
        }
    }//GEN-LAST:event_comboRegion5GhzActionPerformed

    private void comboCanal5GHzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCanal5GHzActionPerformed
        // TODO add your handling code here:
        if ((!region5G.equals(""))&&(comboCanal5GHz.getSelectedIndex()>0))
            canal5G = Integer.parseInt((String)comboCanal5GHz.getSelectedItem());
    }//GEN-LAST:event_comboCanal5GHzActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup TipoSeguridad;
    public javax.swing.JCheckBox cincoGHz;
    public javax.swing.JComboBox comboCanal2GHz;
    public javax.swing.JComboBox comboCanal5GHz;
    public javax.swing.JComboBox comboModo;
    public javax.swing.JComboBox comboRegion2Ghz;
    public javax.swing.JComboBox comboRegion5Ghz;
    public javax.swing.JCheckBox dosGHz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JRadioButton ningunaSeg;
    public javax.swing.JPasswordField passRouter;
    public javax.swing.JTextField ssid;
    public javax.swing.JRadioButton wep;
    public javax.swing.JRadioButton wpa;
    // End of variables declaration//GEN-END:variables
}
