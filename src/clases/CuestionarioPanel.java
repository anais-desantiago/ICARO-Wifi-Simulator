package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CuestionarioPanel extends javax.swing.JPanel
{
    /*Esta clase permite plantear preguntas en formato de cuestionario*/
    ArrayList <String> texto = new ArrayList <> ();
    
    public CuestionarioPanel()
    {
        initComponents();
    }

    public void mostrarPreguntas() throws FileNotFoundException
    {
        try (Scanner scanner = new Scanner(new File("src/evaluaciones/Cuestionario.txt"))) {
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
        jScrollPane2 = new javax.swing.JScrollPane();
        respuestasArea = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(900, 500));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preguntas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        preguntasArea.setEditable(false);
        preguntasArea.setBackground(new java.awt.Color(234, 234, 234));
        preguntasArea.setColumns(20);
        preguntasArea.setRows(5);
        jScrollPane1.setViewportView(preguntasArea);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Respuestas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        respuestasArea.setColumns(20);
        respuestasArea.setRows(5);
        respuestasArea.setAutoscrolls(false);
        jScrollPane2.setViewportView(respuestasArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea preguntasArea;
    public javax.swing.JTextArea respuestasArea;
    // End of variables declaration//GEN-END:variables
}
