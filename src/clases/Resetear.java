package clases;

import java.awt.*;
import javax.swing.*;

public class Resetear
{
    /*Clase que permite vaciar los campos de texto de c/ventana*/
    JDialog dialog;
    
    public void ResetValores(JDialog dialog)
    {
        //resetea los campos de texto y cierra esa ventana
        Component [] componentes = dialog.getContentPane().getComponents();
        Component componente = null;
        for (Component componente1 : componentes)
        {
            componente = componente1;
            if (componente instanceof JTextField) ((JTextField)componente).setText("");
        }
        dialog.setVisible(false);
    }
    
    public void ResetValoresTabbedPane(JPanel panel)
    {
        //resetea los campos de texto y cierra esa ventana
        Component [] componentes = panel.getComponents();
        Component componente = null;
        for (Component componente1 : componentes)
        {
            componente = componente1;
            if (componente instanceof JTextField) ((JTextField)componente).setText("");
        }
    }
}
