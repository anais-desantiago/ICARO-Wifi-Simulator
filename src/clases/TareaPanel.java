package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TareaPanel extends javax.swing.JPanel
{
    /*Clase que carga las tareas*/
    ArrayList <String> texto = new ArrayList <> ();
    
    public TareaPanel()
    {
        initComponents();
    }

    public void mostrarPreguntas() throws FileNotFoundException
    {
        try (Scanner scanner = new Scanner(new File("src/evaluaciones/Tarea.txt"))) {
            scanner.useDelimiter("\r");
            while (scanner.hasNext())
            {
                String line = scanner.next();
                texto.add(texto.size(),line);
            }
            for (int i=0;i<texto.size();i++)
            {
                preguntasArea.append(texto.get(i));
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        preguntasArea = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(900, 500));

        jScrollPane1.setBorder(null);

        preguntasArea.setEditable(false);
        preguntasArea.setBackground(new java.awt.Color(234, 234, 234));
        preguntasArea.setColumns(20);
        preguntasArea.setRows(5);
        jScrollPane1.setViewportView(preguntasArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea preguntasArea;
    // End of variables declaration//GEN-END:variables
}
