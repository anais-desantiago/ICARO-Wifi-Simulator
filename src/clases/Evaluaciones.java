package clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Evaluaciones extends javax.swing.JDialog
{
    /*Esta clase permite crear las evaluaciones según sean requeridas*/
    /*hacer las funcionalidades de las tareas y del troubleshooting*/
    CuestionarioPanel cuestionario = new CuestionarioPanel();
    TareaPanel tarea = new TareaPanel();
    Resetear reset = new Resetear();
    Revisiones check = new Revisiones();
    //ConvertPDF pdf = new ConvertPDF();
    
    JDialog evaluacionesDialog;
    JButton guardar, cancelar;
    JPanel division;
    JTextField ciField;
    JLabel ci;
    
    String [] div;
    boolean ev = false;
    int ced, cod, id;
    String tit;
    
    public Evaluaciones(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }
    
    public static void eliminar(String pArchivo) throws Exception
    {
        try
        {
            File fichero = new File(pArchivo);
            if (!fichero.delete()) throw new Exception("El archivo " + pArchivo+ " no puede ser borrado!");
        } catch (Exception e) {throw new Exception(e);}
    }
    
    public void mostrarPreguntas(String titulo) throws FileNotFoundException
    {
        if (titulo.equals("Cuestionario"))
        {
            String line;
            try (Scanner scanner = new Scanner(new File("src/evaluaciones/numCuestionario.txt"))) {
                scanner.useDelimiter("\r");
                line = scanner.next();
            }
            div = line.split(" ");
        }
        if (titulo.equals("Tarea"))
        {
            String line;
            try (Scanner scanner = new Scanner(new File("src/evaluaciones/numTarea.txt"))) {
                scanner.useDelimiter("\r");
                line = scanner.next();
            }
            div = line.split(" ");
        }
    }

    public void llenarEvaluacion(final String titulo) throws FileNotFoundException
    {
        evaluacionesDialog = new JDialog();
        division = new JPanel();
        ci = new JLabel("Introduzca su cédula:          ");
        ciField = new JTextField();
        ciField.setColumns(10);

        this.setPreferredSize(new Dimension(1200,300));
        this.setLayout(new BorderLayout());
        
        JPanel subpanel = new JPanel();
        this.add(subpanel, BorderLayout.CENTER);
        
        JPanel ciPanel = new JPanel();
        ciField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e)
            {
                check.CheckNumIntro(e);
            }

            @Override
            public void keyPressed(KeyEvent e){}

            @Override
            public void keyReleased(KeyEvent e){}
        });
        
        ciPanel.add(ci);
        ciPanel.add(ciField);
        evaluacionesDialog.add(new JScrollPane(ciPanel),BorderLayout.NORTH);
        
        subpanel.setLayout(new BorderLayout());
        JPanel panel_superior = new JPanel();
        mostrarPreguntas(titulo);
        
        if (titulo.equals("Cuestionario"))
        {
            panel_superior.add(cuestionario);
            evaluacionesDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
            try {
                cuestionario.mostrarPreguntas();
            } catch (FileNotFoundException ex){}
        }
        if (titulo.equals("Tarea"))
        {
            panel_superior.add(tarea);
            evaluacionesDialog.add(new JScrollPane(panel_superior), BorderLayout.CENTER);
            try {
                tarea.mostrarPreguntas();
            } catch (FileNotFoundException ex){}
        }
        evaluacionesDialog.setTitle("["+div[1]+"] "+titulo+" "+div[0]);//codigo+titulo+id
        
        subpanel.setLayout(new BorderLayout());
        
        JPanel panel_inferior = new JPanel();
        guardar = new JButton("Guardar");
        guardar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean found = false;
                int indice=0;
                PersonasDAL objPersDAL= new PersonasDAL();
                ArrayList <Personas> listPers = objPersDAL.obtenerPersonas();
                int texto;
                for (int i=0;i<listPers.size();i++)
                {
                    texto = listPers.get(i).getCedula();
                    if (texto==Integer.parseInt(ciField.getText()))
                    {
                        indice = i;
                        found = true;
                        if (titulo.equals("Cuestionario"))
                        {
                            File archivo = new File("src/evaluaciones/"+titulo+div[0]+"-"+div[1]+" "+ciField.getText()+".txt");//tipo de evaluacion, número evaluación, código materia, ci
                            BufferedWriter writer;
                            try
                            {
                                writer = new BufferedWriter(new FileWriter(archivo));
                                writer.write(cuestionario.respuestasArea.getText());
                                writer.close();
                            } catch (IOException ex) {
                                Logger.getLogger(GUIppal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            //hacer la conversion para que lo guarde como pdf y el estudiante lo envie desde su correo
                            //pdf.crearPDF(titulo+div[0]+"-"+div[1]+" "+ciField.getText(), "src/evaluaciones/"+titulo+div[0]+"-"+div[1]+" "+ciField.getText()+".pdf");
                            
                            evaluacionesDialog.setVisible(false);
                            reset.ResetValores(evaluacionesDialog);
                            try {
                                eliminar("src/evaluaciones/"+titulo+div[0]+"-"+div[1]+" "+ciField.getText()+".txt");
                            } catch (Exception ex) {
                                Logger.getLogger(Evaluaciones.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        if (titulo.equals("Tarea"))
                        {
                            ev = true;
                            tit = titulo;
                            ced = Integer.parseInt(ciField.getText());
                            cod = Integer.parseInt(div[1]);
                            id = Integer.parseInt(div[0]);
                        }
                        break;
                    }
                }
  
                if (!found)
                {
                    JOptionPane.showMessageDialog(null, "No se encontró esta cédula, por favor regístrese (Archivo->Registrar Usuario)");
                    evaluacionesDialog.setVisible(false);
                }
            }
        });
        
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                evaluacionesDialog.setVisible(false);
                reset.ResetValores(evaluacionesDialog);
            }
        });
        
        panel_inferior.add(guardar);
        panel_inferior.add(cancelar);
        evaluacionesDialog.add(new JScrollPane(panel_inferior),BorderLayout.SOUTH);
        
        evaluacionesDialog.pack();
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
