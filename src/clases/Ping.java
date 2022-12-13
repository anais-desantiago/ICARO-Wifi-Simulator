package clases;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ping implements Runnable
{
    /*Clase para hacer ping desde un nodo a otro*/
    JPanel panel = new JPanel();  
    JButton boton = new JButton();
    
    String fuente, destino, mensaje, id, pinging, error, como, direccion;
    final String newLine = "\n";
    boolean found = false, noise = false, nodosAlc = false, continuar = false;
    int por100 = 0, prom = 0,x1, x2, y1, y2, x3, y3;
    double time_end;
    
    Thread hilo;
    
    Animacion animacion, animacion2;
    
    public Ping (String nombre)
    {
        hilo = new Thread(this, nombre);
        hilo.start();
    }
    
    public void animarPing(int x1, int x2, int y1, int y2)
    {
        animacion = new Animacion();
        animacion.panel = panel;
        animacion.setX1(x1);
        animacion.setX2(x2);
        animacion.setY1(y1);
        animacion.setY2(y2);
        animacion.setTipoAnimacion("Ping");
        animacion.mover();
    }
    
    public void continuarPing(int x2,int x3, int y2,int y3)
    {
        animacion2 = new Animacion();
        animacion2.panel = panel;
        animacion2.setX1(x2);
        animacion2.setX2(x3);
        animacion2.setY1(y2);
        animacion2.setY2(y3);
        animacion2.setTipoAnimacion("Ping");
        animacion2.mover();
    }
    
    public void iniciarPing()
    {
        Consola.mostrarTexto("");
        pinging = "Haciendo ping a: "+destino+" desde: "+fuente;
        Consola.mostrarTexto(pinging);
    }
    
    public void hacerPing(String ip0, final String ip1, boolean found, String msj)
    {
        this.mensaje = msj;
        prom = 0;
        time_end = 0.0;
        
        if ((found)&&(nodosAlc))
        {
            Timer timer = new Timer();
            timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    boolean responder = false;
                    if (mensaje.equals("Sin error"))
                    {
                        for (int i=0;i<8;i++)
                        {                            
                            if (!responder)
                            {
                                animarPing(x1,x2,y1,y2);
                                try {
                                    Thread.sleep(1550);
                                    animacion.eliminarAnimacion();
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                if (continuar)
                                {
                                    continuarPing(x2,x3,y2,y3);
                                    try {
                                        Thread.sleep(1550);
                                        animacion2.eliminarAnimacion();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                responder = true;
                            }
                            else
                            {
                                if (continuar)
                                {
                                    continuarPing(x3,x2,y3,y2);
                                    try {
                                        Thread.sleep(1500);
                                        animacion2.eliminarAnimacion();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                                time_end = (int)(Math.random()*(300)+200);
                            
                                if (noise) time_end = time_end + time_end*((double)por100/(double)100);
                                
                                animarPing(x2,x1,y2,y1);
                                try {
                                    Thread.sleep(1500);
                                    animacion.eliminarAnimacion();
                                    mensaje = "Respondiendo desde: "+ip1+" en "+(time_end)+" ms";
                                    Consola.mostrarTexto(mensaje);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                responder = false;
                                prom+=time_end;
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Ping exitoso: Respuesta desde: "+ip1+" en "+(prom/4)+" ms");
                    }
                }
            }, 400);
        }
        else
        {
            if (!found)
            {
                Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        for (int i=0;i<4;i++)
                        {
                            mensaje = "Tiempo de espera agotado";
                            Consola.mostrarTexto(mensaje);
                            
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Ping no exitoso: Tiempo de espera agotado");
                    }
                }, 3000);
            }
            if (!nodosAlc)
            {
                Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        for (int i=0;i<4;i++)
                        {
                            mensaje = "Nodo inalcanzable";
                            Consola.mostrarTexto(mensaje);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Ping no exitoso: Nodo inalcanzable");
                    }
                }, 3000);
            }
        }
    }

    @Override
    public void run()
    {
        if (hilo.getName().equals("iniciar")) iniciarPing();
    }
}