package clases;

import javax.swing.SwingUtilities;

public class WANPanel extends javax.swing.JPanel
{
    /*Clase que muestra los campos para la configuración WAN*/
    Revisiones revisar = new Revisiones();
    String conexion;
    
    public WANPanel()
    {
        initComponents();
    }

    public void habilitarIPs(boolean activo)
    {
        ipWAN1.setEnabled(activo);
        ipWAN2.setEnabled(activo);
        ipWAN3.setEnabled(activo);
        ipWAN4.setEnabled(activo);
        maskWAN1.setEnabled(activo);
        maskWAN2.setEnabled(activo);
        maskWAN3.setEnabled(activo);
        maskWAN4.setEnabled(activo);
        gtWAN1.setEnabled(activo);
        gtWAN2.setEnabled(activo);
        gtWAN3.setEnabled(activo);
        gtWAN4.setEnabled(activo);
        dnsPrim1.setEnabled(activo);
        dnsPrim2.setEnabled(activo);
        dnsPrim3.setEnabled(activo);
        dnsPrim4.setEnabled(activo);
        dnsSec1.setEnabled(activo);
        dnsSec2.setEnabled(activo);
        dnsSec3.setEnabled(activo);
        dnsSec4.setEnabled(activo);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        wanComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ipWAN1 = new javax.swing.JTextField();
        maskWAN1 = new javax.swing.JTextField();
        gtWAN1 = new javax.swing.JTextField();
        dnsPrim1 = new javax.swing.JTextField();
        dnsSec1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ipWAN2 = new javax.swing.JTextField();
        maskWAN2 = new javax.swing.JTextField();
        gtWAN2 = new javax.swing.JTextField();
        dnsPrim2 = new javax.swing.JTextField();
        dnsSec2 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        ipWAN3 = new javax.swing.JTextField();
        maskWAN3 = new javax.swing.JTextField();
        gtWAN3 = new javax.swing.JTextField();
        dnsPrim3 = new javax.swing.JTextField();
        dnsSec3 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ipWAN4 = new javax.swing.JTextField();
        maskWAN4 = new javax.swing.JTextField();
        gtWAN4 = new javax.swing.JTextField();
        dnsPrim4 = new javax.swing.JTextField();
        dnsSec4 = new javax.swing.JTextField();

        jLabel29.setText("jLabel29");

        jLabel1.setText("Seleccione el tipo de conexión WAN:");

        wanComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Conexión>", "IP Asignada por el ISP", "IP Estática" }));
        wanComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wanComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Dirección IP:");

        jLabel3.setText("Máscara de Subred:");

        jLabel4.setText("Puerta de enlace predeterminada:");

        jLabel5.setText("Servidor DNS Primario:");

        jLabel6.setText("Servidor DNS Secundario:");

        ipWAN1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ipWAN1FocusGained(evt);
            }
        });
        ipWAN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ipWAN1KeyTyped(evt);
            }
        });

        maskWAN1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskWAN1FocusGained(evt);
            }
        });
        maskWAN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskWAN1KeyTyped(evt);
            }
        });

        gtWAN1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gtWAN1FocusGained(evt);
            }
        });
        gtWAN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gtWAN1KeyTyped(evt);
            }
        });

        dnsPrim1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsPrim1FocusGained(evt);
            }
        });
        dnsPrim1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsPrim1KeyTyped(evt);
            }
        });

        dnsSec1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsSec1FocusGained(evt);
            }
        });
        dnsSec1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsSec1KeyTyped(evt);
            }
        });

        jLabel7.setText(".");

        jLabel20.setText(".");

        jLabel21.setText(".");

        jLabel22.setText(".");

        jLabel23.setText(".");

        ipWAN2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ipWAN2FocusGained(evt);
            }
        });
        ipWAN2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ipWAN2KeyTyped(evt);
            }
        });

        maskWAN2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskWAN2FocusGained(evt);
            }
        });
        maskWAN2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskWAN2KeyTyped(evt);
            }
        });

        gtWAN2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gtWAN2FocusGained(evt);
            }
        });
        gtWAN2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gtWAN2KeyTyped(evt);
            }
        });

        dnsPrim2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsPrim2FocusGained(evt);
            }
        });
        dnsPrim2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsPrim2KeyTyped(evt);
            }
        });

        dnsSec2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsSec2FocusGained(evt);
            }
        });
        dnsSec2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsSec2KeyTyped(evt);
            }
        });

        jLabel24.setText(".");

        jLabel25.setText(".");

        jLabel26.setText(".");

        jLabel27.setText(".");

        jLabel28.setText(".");

        ipWAN3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ipWAN3FocusGained(evt);
            }
        });
        ipWAN3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ipWAN3KeyTyped(evt);
            }
        });

        maskWAN3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskWAN3FocusGained(evt);
            }
        });
        maskWAN3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskWAN3KeyTyped(evt);
            }
        });

        gtWAN3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gtWAN3FocusGained(evt);
            }
        });
        gtWAN3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gtWAN3KeyTyped(evt);
            }
        });

        dnsPrim3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsPrim3FocusGained(evt);
            }
        });
        dnsPrim3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsPrim3KeyTyped(evt);
            }
        });

        dnsSec3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsSec3FocusGained(evt);
            }
        });
        dnsSec3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsSec3KeyTyped(evt);
            }
        });

        jLabel30.setText(".");

        jLabel31.setText(".");

        jLabel32.setText(".");

        jLabel33.setText(".");

        jLabel34.setText(".");

        ipWAN4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ipWAN4FocusGained(evt);
            }
        });
        ipWAN4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ipWAN4KeyTyped(evt);
            }
        });

        maskWAN4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maskWAN4FocusGained(evt);
            }
        });
        maskWAN4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                maskWAN4KeyTyped(evt);
            }
        });

        gtWAN4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                gtWAN4FocusGained(evt);
            }
        });
        gtWAN4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                gtWAN4KeyTyped(evt);
            }
        });

        dnsPrim4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsPrim4FocusGained(evt);
            }
        });
        dnsPrim4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsPrim4KeyTyped(evt);
            }
        });

        dnsSec4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dnsSec4FocusGained(evt);
            }
        });
        dnsSec4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dnsSec4KeyTyped(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(dnsSec1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel23)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dnsSec2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dnsSec3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dnsSec4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(dnsPrim1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel22)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dnsPrim2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel27)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dnsPrim3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(dnsPrim4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(gtWAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel21)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gtWAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gtWAN3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel32)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(gtWAN4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(ipWAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ipWAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ipWAN3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ipWAN4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(maskWAN1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(maskWAN2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(maskWAN3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(maskWAN4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(wanComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(wanComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ipWAN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(ipWAN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(ipWAN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(ipWAN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(maskWAN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(maskWAN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(maskWAN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(maskWAN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(gtWAN1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(gtWAN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(gtWAN3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(gtWAN4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(dnsPrim1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(dnsPrim2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(dnsPrim3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(dnsPrim4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dnsSec1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(dnsSec2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(dnsSec3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(dnsSec4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void wanComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wanComboBoxActionPerformed
        if (wanComboBox.getSelectedIndex()==1)
        {
            //IP automatica por ISP
            habilitarIPs(false);
            conexion = "ISP";
        }
        if (wanComboBox.getSelectedIndex()==2)
        {
            //ip estatica
            habilitarIPs(true);
            conexion = "Estática";
        }
    }//GEN-LAST:event_wanComboBoxActionPerformed

    private void ipWAN1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipWAN1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_ipWAN1KeyTyped

    private void ipWAN2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipWAN2KeyTyped
         revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_ipWAN2KeyTyped

    private void ipWAN3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipWAN3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_ipWAN3KeyTyped

    private void ipWAN4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipWAN4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_ipWAN4KeyTyped

    private void maskWAN1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskWAN1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskWAN1KeyTyped

    private void maskWAN2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskWAN2KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskWAN2KeyTyped

    private void maskWAN3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskWAN3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskWAN3KeyTyped

    private void maskWAN4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maskWAN4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_maskWAN4KeyTyped

    private void gtWAN1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gtWAN1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_gtWAN1KeyTyped

    private void gtWAN2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gtWAN2KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_gtWAN2KeyTyped

    private void gtWAN3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gtWAN3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_gtWAN3KeyTyped

    private void gtWAN4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gtWAN4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_gtWAN4KeyTyped

    private void dnsPrim1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsPrim1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsPrim1KeyTyped

    private void dnsPrim2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsPrim2KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsPrim2KeyTyped

    private void dnsPrim3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsPrim3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsPrim3KeyTyped

    private void dnsPrim4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsPrim4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsPrim4KeyTyped

    private void dnsSec1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsSec1KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsSec1KeyTyped

    private void dnsSec2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsSec2KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsSec2KeyTyped

    private void dnsSec3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsSec3KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsSec3KeyTyped

    private void dnsSec4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dnsSec4KeyTyped
        revisar.CheckNumIntro(evt);
    }//GEN-LAST:event_dnsSec4KeyTyped

    private void ipWAN1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipWAN1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipWAN1.selectAll();
            }
        });
    }//GEN-LAST:event_ipWAN1FocusGained

    private void ipWAN2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipWAN2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipWAN2.selectAll();
            }
        });
    }//GEN-LAST:event_ipWAN2FocusGained

    private void ipWAN3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipWAN3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipWAN3.selectAll();
            }
        });
    }//GEN-LAST:event_ipWAN3FocusGained

    private void ipWAN4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipWAN4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipWAN4.selectAll();
            }
        });
    }//GEN-LAST:event_ipWAN4FocusGained

    private void maskWAN1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskWAN1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskWAN1.selectAll();
            }
        });
    }//GEN-LAST:event_maskWAN1FocusGained

    private void maskWAN2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskWAN2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskWAN2.selectAll();
            }
        });
    }//GEN-LAST:event_maskWAN2FocusGained

    private void maskWAN3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskWAN3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskWAN3.selectAll();
            }
        });
    }//GEN-LAST:event_maskWAN3FocusGained

    private void maskWAN4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maskWAN4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                maskWAN4.selectAll();
            }
        });
    }//GEN-LAST:event_maskWAN4FocusGained

    private void gtWAN1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gtWAN1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gtWAN1.selectAll();
            }
        });
    }//GEN-LAST:event_gtWAN1FocusGained

    private void gtWAN2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gtWAN2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gtWAN2.selectAll();
            }
        });
    }//GEN-LAST:event_gtWAN2FocusGained

    private void gtWAN3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gtWAN3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gtWAN3.selectAll();
            }
        });
    }//GEN-LAST:event_gtWAN3FocusGained

    private void gtWAN4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_gtWAN4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gtWAN4.selectAll();
            }
        });
    }//GEN-LAST:event_gtWAN4FocusGained

    private void dnsPrim1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsPrim1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsPrim1.selectAll();
            }
        });
    }//GEN-LAST:event_dnsPrim1FocusGained

    private void dnsPrim2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsPrim2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsPrim2.selectAll();
            }
        });
    }//GEN-LAST:event_dnsPrim2FocusGained

    private void dnsPrim3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsPrim3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsPrim3.selectAll();
            }
        });
    }//GEN-LAST:event_dnsPrim3FocusGained

    private void dnsPrim4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsPrim4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsPrim4.selectAll();
            }
        });
    }//GEN-LAST:event_dnsPrim4FocusGained

    private void dnsSec1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsSec1FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsSec1.selectAll();
            }
        });
    }//GEN-LAST:event_dnsSec1FocusGained

    private void dnsSec2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsSec2FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsSec2.selectAll();
            }
        });
    }//GEN-LAST:event_dnsSec2FocusGained

    private void dnsSec3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsSec3FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsSec3.selectAll();
            }
        });
    }//GEN-LAST:event_dnsSec3FocusGained

    private void dnsSec4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dnsSec4FocusGained
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                dnsSec4.selectAll();
            }
        });
    }//GEN-LAST:event_dnsSec4FocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField dnsPrim1;
    public javax.swing.JTextField dnsPrim2;
    public javax.swing.JTextField dnsPrim3;
    public javax.swing.JTextField dnsPrim4;
    public javax.swing.JTextField dnsSec1;
    public javax.swing.JTextField dnsSec2;
    public javax.swing.JTextField dnsSec3;
    public javax.swing.JTextField dnsSec4;
    public javax.swing.JTextField gtWAN1;
    public javax.swing.JTextField gtWAN2;
    public javax.swing.JTextField gtWAN3;
    public javax.swing.JTextField gtWAN4;
    public javax.swing.JTextField ipWAN1;
    public javax.swing.JTextField ipWAN2;
    public javax.swing.JTextField ipWAN3;
    public javax.swing.JTextField ipWAN4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField3;
    public javax.swing.JTextField maskWAN1;
    public javax.swing.JTextField maskWAN2;
    public javax.swing.JTextField maskWAN3;
    public javax.swing.JTextField maskWAN4;
    public javax.swing.JComboBox wanComboBox;
    // End of variables declaration//GEN-END:variables
}
