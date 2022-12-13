package clases;
import java.io.File;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Abrir extends javax.swing.JDialog
{
    JFileChooser abrirDialog = new JFileChooser();
    
    CargarSim cargar = new CargarSim();
    
    HashMap routers = new HashMap();
    HashMap aps = new HashMap();
    HashMap stas = new HashMap();
    
    String tipoRed;
    
    public Abrir(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }
    
    public void abrirArchivo(String titulo, String tipoBoton, String fuente)
    {
        abrirDialog.setDialogTitle(titulo);
        abrirDialog.setApproveButtonText(tipoBoton);
        
        int status = abrirDialog.showOpenDialog(null);// Da un entero
        
        // Si apretamos en aceptar ocurrirá esto
        if (status == JFileChooser.APPROVE_OPTION)
        {
            File archivo = abrirDialog.getSelectedFile();
            
            //funciones para cargar los datos de configuración
            cargar.tipoRed = tipoRed;
            cargar.setFunctionsRouter(Router.class);
            cargar.setFunctionsSTA(STA.class);
            cargar.setFunctionsAP(AccessPoint.class);
            cargar.leerArchivo(archivo.getPath());
            routers = cargar.routers;
            aps = cargar.aps;
            stas = cargar.stas;
            abrirDialog.setVisible(false);
        }
        else if (status == JFileChooser.CANCEL_OPTION) abrirDialog.hide();
    }
    @SuppressWarnings("unchecked")
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
