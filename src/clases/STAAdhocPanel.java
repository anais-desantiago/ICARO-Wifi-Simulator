package clases;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class STAAdhocPanel extends javax.swing.JPanel
{
    Revisiones revisar = new Revisiones();
    
    String ipSTA = "", tipoSeguridad = "", mask = "", id = "", ssid ="";
    boolean error;
    int indice = 0, vacioR=0, foundR=0; //si hay 0 es que los ssids están configurados en todo el arreglo;
    
    HashMap ssids = new HashMap();
    ArrayList <String> indices = new ArrayList<>();
    HashMap seguridades = new HashMap();
    ArrayList <String> inVacio = new ArrayList<>();
    
    public STAAdhocPanel()
    {
        initComponents();
    }
    
    public void cargarSSID(ArrayList <String> ssids, ArrayList <String> seguridades, boolean redes)
    {
        this.ssids = new HashMap();
        this.seguridades = new HashMap();
        inVacio.clear();
        
        comboBoxSSID.removeAllItems();
        
        if (!redes)
        {
            comboBoxSSID.addItem ("No hay redes disponibles");
            comboBoxSSID.setEnabled(false);
            activarConfig(false);
            passSTAField.setEnabled(false);
        }
        else
        {
            if (!ssids.isEmpty())
            {
                for (int i = 0; i<ssids.size();i++)
                {
                    this.ssids.put(ssids.get(i),ssids.get(i));
                    this.seguridades.put(ssids.get(i),seguridades.get(i));

                    comboBoxSSID.addItem(ssids.get(i));
                    foundR++;
                }
            }
            comboBoxSSID.setEnabled(true);
            activarConfig(true);
            passSTAField.setEnabled(true);

            if((vacioR==ssids.size())||(foundR==0))
            {
                comboBoxSSID.addItem ("No hay redes disponibles");
                comboBoxSSID.setEnabled(false);
                activarConfig(false);
                passSTAField.setEnabled(false);
            }
        }
    }
    
    public void cargarSeguridad()
    {
        String seg;
        
        if (seguridades.containsKey(ssid))
        {
            seg = (String) seguridades.get(ssid);
            if (seg.equals("Ninguna"))
            {
                passSTAField.setEnabled(false);
                tipoSeguridad = "Ninguna";
            }
            else
            {
                if (seg.equals("WEP")) tipoSeguridad = "WEP";
                if (seg.equals("WPA")) tipoSeguridad = "WPA";
                passSTAField.setEnabled(true);
            }
        }
    }

     public void activarConfig(Boolean activo)
    {
        /*Función que permite habilitar o deshabilitar la configuración IP de las STAs*/
        /*Si activo es true se habilitarán los campos*/
        staIP1.setEnabled(activo);
        staIP2.setEnabled(activo);
        staIP3.setEnabled(activo);
        staIP4.setEnabled(activo);
        staMask1.setEnabled(activo);
        staMask2.setEnabled(activo);
        staMask3.setEnabled(activo);
        staMask4.setEnabled(activo);
    }                   
                 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPunto3 = new javax.swing.JLabel();
        staIP4 = new javax.swing.JTextField();
        labelPunto2 = new javax.swing.JLabel();
        staIP3 = new javax.swing.JTextField();
        staIP2 = new javax.swing.JTextField();
        ipSTALabel = new javax.swing.JLabel();
        labelPunto1 = new javax.swing.JLabel();
        staIP1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        staMask1 = new javax.swing.JTextField();
        staMask2 = new javax.swing.JTextField();
        staMask3 = new javax.swing.JTextField();
        staMask4 = new javax.swing.JTextField();
        labelPunto4 = new javax.swing.JLabel();
        labelPunto5 = new javax.swing.JLabel();
        labelPunto7 = new javax.swing.JLabel();
        comboBoxSSID = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        passSTALabel = new javax.swing.JLabel();
        passSTAField = new javax.swing.JPasswordField();

        labelPunto3.setText(".");

        staIP4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staIP4FocusGained(evt);
            }
        });
        staIP4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staIP4KeyTyped(evt);
            }
        });

        labelPunto2.setText(".");

        staIP3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staIP3FocusGained(evt);
            }
        });
        staIP3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staIP3KeyTyped(evt);
            }
        });

        staIP2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staIP2FocusGained(evt);
            }
        });
        staIP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staIP2KeyTyped(evt);
            }
        });

        ipSTALabel.setText("Introduzca la dirección IP que tendrá su máquina:");

        labelPunto1.setText(".");

        staIP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staIP1FocusGained(evt);
            }
        });
        staIP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staIP1KeyTyped(evt);
            }
        });

        jLabel2.setText("Introduzca la máscara de red:");

        staMask1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staMask1FocusGained(evt);
            }
        });
        staMask1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staMask1KeyTyped(evt);
            }
        });

        staMask2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staMask2FocusGained(evt);
            }
        });
        staMask2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staMask2KeyTyped(evt);
            }
        });

        staMask3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staMask3FocusGained(evt);
            }
        });
        staMask3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staMask3KeyTyped(evt);
            }
        });

        staMask4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                staMask4FocusGained(evt);
            }
        });
        staMask4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                staMask4KeyTyped(evt);
            }
        });

        labelPunto4.setText(".");

        labelPunto5.setText(".");

        labelPunto7.setText(".");

        comboBoxSSID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<SSID>" }));
        comboBoxSSID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSSIDActionPerformed(evt);
            }
        });

        jLabel1.setText("Seleccione la red a la que se quiere conectar:");

        passSTALabel.setText("Introduzca la contraseña de la red:");

        passSTAField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passSTAFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passSTAFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ipSTALabel)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(passSTAField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(staMask1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelPunto4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(staMask2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelPunto5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(staMask3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelPunto7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(staMask4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(staIP1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelPunto1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(staIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelPunto2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(staIP3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelPunto3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(staIP4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(comboBoxSSID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(passSTALabel)
                    .addContainerGap(229, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxSSID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passSTAField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipSTALabel)
                    .addComponent(staIP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPunto1)
                    .addComponent(staIP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPunto2)
                    .addComponent(staIP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPunto3)
                    .addComponent(staIP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(staMask1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPunto4)
                    .addComponent(staMask2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPunto5)
                    .addComponent(staMask3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPunto7)
                    .addComponent(staMask4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(passSTALabel)
                    .addContainerGap(84, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void staIP4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staIP4KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staIP4KeyTyped

    private void staIP3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staIP3KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staIP3KeyTyped

    private void staIP2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staIP2KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staIP2KeyTyped

    private void staIP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staIP1KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staIP1KeyTyped

    private void staMask1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staMask1KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staMask1KeyTyped

    private void staMask2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staMask2KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staMask2KeyTyped

    private void staMask3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staMask3KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staMask3KeyTyped

    private void staMask4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_staMask4KeyTyped
        // TODO add your handling code here:
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_staMask4KeyTyped

    private void comboBoxSSIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSSIDActionPerformed
        Object aux;
        aux = comboBoxSSID.getSelectedItem();
        if (ssids.containsKey(aux))
        {
            ssid = (String) aux;
            cargarSeguridad();
        }
    }//GEN-LAST:event_comboBoxSSIDActionPerformed

    private void passSTAFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passSTAFieldKeyReleased
        // TODO add your handling code here:
        if (tipoSeguridad.equals("WEP"))
        {
            int letra = evt.getKeyCode();
            if (Character.isDigit(evt.getKeyChar()) || (((letra>=65) && (letra <= 70)) || (letra >= 97) && (letra<=102))||(letra==8)) error = false;
            else
            {
                error = true;
                passSTAFieldKeyTyped(evt);
            }
        }
    }//GEN-LAST:event_passSTAFieldKeyReleased

    private void passSTAFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passSTAFieldKeyTyped
        // TODO add your handling code here:
        if (error == true)
        {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten caracteres hexadecimales (0-9) y (A-F)");
            error = false;
        }
        else error = false;
    }//GEN-LAST:event_passSTAFieldKeyTyped

    private void staIP1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staIP1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staIP1.selectAll();
            }
        });
    }//GEN-LAST:event_staIP1FocusGained

    private void staIP2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staIP2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staIP2.selectAll();
            }
        });
    }//GEN-LAST:event_staIP2FocusGained

    private void staIP3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staIP3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staIP3.selectAll();
            }
        });
    }//GEN-LAST:event_staIP3FocusGained

    private void staIP4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staIP4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staIP4.selectAll();
            }
        });
    }//GEN-LAST:event_staIP4FocusGained

    private void staMask1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staMask1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staMask1.selectAll();
            }
        });
    }//GEN-LAST:event_staMask1FocusGained

    private void staMask2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staMask2FocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staMask2.selectAll();
            }
        });
    }//GEN-LAST:event_staMask2FocusGained

    private void staMask3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staMask3FocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staMask3.selectAll();
            }
        });
    }//GEN-LAST:event_staMask3FocusGained

    private void staMask4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_staMask4FocusGained
         SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                staMask4.selectAll();
            }
        });
    }//GEN-LAST:event_staMask4FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox comboBoxSSID;
    public javax.swing.JLabel ipSTALabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel labelPunto1;
    public javax.swing.JLabel labelPunto2;
    public javax.swing.JLabel labelPunto3;
    public javax.swing.JLabel labelPunto4;
    public javax.swing.JLabel labelPunto5;
    public javax.swing.JLabel labelPunto7;
    public javax.swing.JPasswordField passSTAField;
    public javax.swing.JLabel passSTALabel;
    public javax.swing.JTextField staIP1;
    public javax.swing.JTextField staIP2;
    public javax.swing.JTextField staIP3;
    public javax.swing.JTextField staIP4;
    public javax.swing.JTextField staMask1;
    public javax.swing.JTextField staMask2;
    public javax.swing.JTextField staMask3;
    public javax.swing.JTextField staMask4;
    // End of variables declaration//GEN-END:variables
}
