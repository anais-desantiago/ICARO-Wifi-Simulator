package clases;

import java.awt.*;
import javax.swing.*;

public class Revisiones 
{
    /*Clase que permite verificar si se llenaron todos los campos y/o filtrar que las teclas
     presionadas sean solo números*/
    JDialog dialog;
    JTabbedPane tabbedPane;
    
    public void CheckNumIntro(java.awt.event.KeyEvent evt)
    {
        /*Verifica si la tecla presionada es un número*/
        if(!Character.isDigit(evt.getKeyChar()) && !Character.isISOControl(evt.getKeyChar()))
        {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo se permiten números");
        }
    }
    
    boolean CheckVacio(javax.swing.JDialog dialog)
    {
        /*Chequea si existen campos vacíos en el JDialog, retorna true cuando hay vacio*/
        int vacioText = 0;
        int cantText = 0;
        Component [] componentes = dialog.getContentPane().getComponents();
        Component componente = null;
        
        for (Component componente1 : componentes)
        {
            componente = componente1;
            if (componente instanceof JTextField)
            {
                cantText++;
                if(((JTextField)componente).getText().trim().length()==0) vacioText++;
            }
        }
        if (cantText-vacioText!=cantText)
        {
            JOptionPane.showMessageDialog(null, "No pueden haber campos vacíos");
            return true;
        }
        else return false;
    }
    boolean CheckVacioTabbedPane(javax.swing.JPanel panel)
    {
        /*Chequea si existen campos vacíos en el JPanel, retorna true cuando hay vacío*/
        int vacioText = 0;
        int cantText = 0;
       
        for (int a =0; a<panel.getComponentCount();a++)
        {
             Component [] componentes = panel.getComponents();
             Component componente = null;
             for (Component componente1 : componentes)
             {
                componente = componente1;
                if (componente instanceof JTextField)
                {
                    if (componente.isEnabled())
                    {
                        cantText++;
                        if(((JTextField)componente).getText().trim().length()==0) vacioText++;
                    }
                }
            }
        }
        if (cantText-vacioText!=cantText)
        {
            JOptionPane.showMessageDialog(null, "No pueden haber campos vacíos");
            return true;
        }
        else return false;
    }
}