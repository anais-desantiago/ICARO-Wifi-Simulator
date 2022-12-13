package clases;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;

public class Consola extends JPanel
{
    /*Esta clase permite mostrar información en la aplicación como una consola propia del programa*/
    static JTextPane textArea;
    private final static String newline = "\n";
    
    public Consola()
    {
        super(new GridBagLayout());
 
        textArea = new JTextPane();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
  
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }
    
    public static void mostrarTexto(String msj)
    {
        if (msj.equals("")) textArea.setText(msj);
        else
        {
            try
            {
                textArea.getStyledDocument().insertString(textArea.getStyledDocument().getLength(), msj+newline,  new SimpleAttributeSet());
            } catch (BadLocationException ex) {}
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
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
