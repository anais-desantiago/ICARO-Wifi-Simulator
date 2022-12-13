package clases;

import java.awt.Toolkit;
import javax.swing.JOptionPane;


public class CrearSTAAdhocPanel extends javax.swing.JPanel
{
    Revisiones revisar = new Revisiones();
    String tipoSeguridad = "Ninguna";
    boolean seguridad, error = false;
    
    public CrearSTAAdhocPanel()
    {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipoSeguridad = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        ssidAdHoc = new javax.swing.JTextField();
        passRouter = new javax.swing.JPasswordField();
        wep = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        ningunaSeg = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        wpa = new javax.swing.JRadioButton();

        jLabel1.setText("Introduzca el nombre de  la red:");

        passRouter.setEnabled(false);
        passRouter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passRouterKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passRouterKeyTyped(evt);
            }
        });

        TipoSeguridad.add(wep);
        wep.setText("WEP");
        wep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wepActionPerformed(evt);
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

        jLabel3.setText("Contraseña de red:");

        TipoSeguridad.add(wpa);
        wpa.setText("WPA/WPA2");
        wpa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wpaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(88, 88, 88)
                .addComponent(ssidAdHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ningunaSeg)
                        .addGap(97, 97, 97)
                        .addComponent(wep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wpa))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(passRouter, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ssidAdHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ningunaSeg)
                    .addComponent(wep)
                    .addComponent(wpa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passRouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

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
        if (error)
        {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Sólo se permiten caracteres hexadecimales (0-9) y (A-F)");
            error = false;
        }
        else error = false;
    }//GEN-LAST:event_passRouterKeyTyped

    private void wepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wepActionPerformed
        seguridad = true;
        passRouter.setEnabled(true);
        tipoSeguridad = "WEP";
    }//GEN-LAST:event_wepActionPerformed

    private void ningunaSegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ningunaSegActionPerformed
        // Ninguna seguridad
        seguridad = false;
        tipoSeguridad = "Ninguna";
        passRouter.setEnabled(false);
    }//GEN-LAST:event_ningunaSegActionPerformed

    private void wpaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wpaActionPerformed
        //clave normal
        seguridad = true;
        passRouter.setEnabled(true);
        tipoSeguridad = "WPA";
    }//GEN-LAST:event_wpaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.ButtonGroup TipoSeguridad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    public javax.swing.JRadioButton ningunaSeg;
    public javax.swing.JPasswordField passRouter;
    public javax.swing.JTextField ssidAdHoc;
    public javax.swing.JRadioButton wep;
    public javax.swing.JRadioButton wpa;
    // End of variables declaration//GEN-END:variables
}
