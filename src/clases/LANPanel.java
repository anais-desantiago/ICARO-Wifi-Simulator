package clases;
import javax.swing.*;

public class LANPanel extends JPanel
{
    Revisiones revisar;

    public LANPanel()
    {
        initComponents();
        revisar = new Revisiones();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lanIP1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lanIP2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        lanIP3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        lanIP4 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        maskIP1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        maskIP2 = new javax.swing.JTextField();
        maskIP3 = new javax.swing.JTextField();
        maskIP4 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        cantDHCP = new javax.swing.JTextField();
        DHCPEnable = new javax.swing.JCheckBox();

        setMinimumSize(new java.awt.Dimension(400, 300));
        setName("PanelLAN"); // NOI18N

        jLabel17.setText("Introduzca la IP del Dispositivo");

        jLabel18.setText("IP:");

        lanIP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lanIP1FocusGained(evt);
            }
        });
        lanIP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lanIP1KeyTyped(evt);
            }
        });

        jLabel19.setText(".");

        lanIP2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lanIP2FocusGained(evt);
            }
        });
        lanIP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lanIP2KeyTyped(evt);
            }
        });

        jLabel20.setText(".");

        lanIP3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lanIP3FocusGained(evt);
            }
        });
        lanIP3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lanIP3KeyTyped(evt);
            }
        });

        jLabel21.setText(".");

        lanIP4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lanIP4FocusGained(evt);
            }
        });
        lanIP4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lanIP4KeyTyped(evt);
            }
        });

        jLabel22.setText("Máscara de subred:");

        maskIP1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskIP1FocusGained(evt);
            }
        });
        maskIP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskIP1KeyTyped(evt);
            }
        });

        jLabel23.setText(".");

        jLabel24.setText(".");

        jLabel25.setText(".");

        maskIP2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskIP2FocusGained(evt);
            }
        });
        maskIP2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskIP2KeyTyped(evt);
            }
        });

        maskIP3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskIP3FocusGained(evt);
            }
        });
        maskIP3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskIP3KeyTyped(evt);
            }
        });

        maskIP4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskIP4FocusGained(evt);
            }
        });
        maskIP4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskIP4KeyTyped(evt);
            }
        });

        jLabel26.setText("Configuración DHCP");

        jLabel13.setText("Cantidad de direcciones a asignar:");

        cantDHCP.setEnabled(false);
        cantDHCP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantDHCPKeyTyped(evt);
            }
        });

        DHCPEnable.setText("Habilitar DHCP");
        DHCPEnable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DHCPEnableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(13, 13, 13))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel18))
                                        .addGap(83, 83, 83)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lanIP1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lanIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lanIP3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lanIP4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(maskIP1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maskIP2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maskIP3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(maskIP4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cantDHCP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(76, 76, 76)))
                        .addContainerGap(72, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DHCPEnable)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel26))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lanIP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel21)
                    .addComponent(lanIP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(lanIP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(lanIP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(maskIP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(maskIP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(maskIP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(maskIP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(DHCPEnable)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cantDHCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("PanelLAN");
    }// </editor-fold>//GEN-END:initComponents

    private void lanIP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lanIP1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_lanIP1KeyTyped

    private void lanIP2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lanIP2KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_lanIP2KeyTyped

    private void lanIP3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lanIP3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_lanIP3KeyTyped

    private void lanIP4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lanIP4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_lanIP4KeyTyped

    private void maskIP1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskIP1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskIP1KeyTyped

    private void maskIP2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskIP2KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskIP2KeyTyped

    private void maskIP3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskIP3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskIP3KeyTyped

    private void maskIP4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskIP4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskIP4KeyTyped

    private void cantDHCPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantDHCPKeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_cantDHCPKeyTyped

    private void DHCPEnableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DHCPEnableActionPerformed
        if (DHCPEnable.isSelected()) cantDHCP.setEnabled(true);
        else cantDHCP.setEnabled(false);
    }//GEN-LAST:event_DHCPEnableActionPerformed

    private void lanIP1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lanIP1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lanIP1.selectAll();
            }
        });
    }//GEN-LAST:event_lanIP1FocusGained

    private void lanIP2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lanIP2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lanIP2.selectAll();
            }
        });
    }//GEN-LAST:event_lanIP2FocusGained

    private void lanIP3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lanIP3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lanIP3.selectAll();
            }
        });
    }//GEN-LAST:event_lanIP3FocusGained

    private void lanIP4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lanIP4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                lanIP4.selectAll();
            }
        });
    }//GEN-LAST:event_lanIP4FocusGained

    private void maskIP1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskIP1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskIP1.selectAll();
            }
        });
    }//GEN-LAST:event_maskIP1FocusGained

    private void maskIP2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskIP2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskIP2.selectAll();
            }
        });
    }//GEN-LAST:event_maskIP2FocusGained

    private void maskIP3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskIP3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskIP3.selectAll();
            }
        });
    }//GEN-LAST:event_maskIP3FocusGained

    private void maskIP4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskIP4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskIP4.selectAll();
            }
        });
    }//GEN-LAST:event_maskIP4FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox DHCPEnable;
    public javax.swing.JTextField cantDHCP;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTextField lanIP1;
    public javax.swing.JTextField lanIP2;
    public javax.swing.JTextField lanIP3;
    public javax.swing.JTextField lanIP4;
    public javax.swing.JTextField maskIP1;
    public javax.swing.JTextField maskIP2;
    public javax.swing.JTextField maskIP3;
    public javax.swing.JTextField maskIP4;
    // End of variables declaration//GEN-END:variables
}
