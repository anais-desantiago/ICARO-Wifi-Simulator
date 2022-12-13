package clases;

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class Animacion
{
    JButton boton = new JButton(), botonesRouter = new JButton(), botonesAP = new JButton(), botonesSTA = new JButton(),otro;
    JPanel panel = new JPanel();
    String nombre ="", tipoAnimacion="", tipoRed="";
    String como ="", direccion = "";
    
    HashMap arrayRouter = new HashMap();
    HashMap arrayAP = new HashMap();
    HashMap arraySTA = new HashMap();
    HashMap redesAdHoc = new HashMap();
    
    STA sta = new STA();
    
    Image dibujo;
    boolean visible=false, eliminar = false;
    int x1=0, y1=0, x2=0, y2=0, x3 =0,y3=0;
    Dimension size = new Dimension(), size1 = new Dimension(),size0 = new Dimension();
    String  [] conexiones = {"src/Imagenes/vertical.gif","src/Imagenes/vertical1.gif","src/Imagenes/horizontal.gif", 
        "src/Imagenes/horizontal1.gif", "src/Imagenes/diagonal.gif","src/Imagenes/diagonal1.gif",
        "src/Imagenes/diagonal2.gif","src/Imagenes/diagonal3.gif"};
    String [] ping = {"src/Imagenes/ping_vertical1.gif","src/Imagenes/ping_vertical2.gif","src/Imagenes/ping_horizontal1.gif", 
        "src/Imagenes/ping_horizontal2.gif", "src/Imagenes/ping_diagonal1.gif","src/Imagenes/ping_diagonal2.gif",
        "src/Imagenes/ping_diagonal3.gif","src/Imagenes/ping_diagonal4.gif"};
    String [] paquetes= {"src/Imagenes/datos_vertical1.gif","src/Imagenes/datos_vertical2.gif","src/Imagenes/datos_horizontal1.gif", 
        "src/Imagenes/datos_horizontal2.gif", "src/Imagenes/datos_diagonal1.gif","src/Imagenes/datos_diagonal2.gif",
        "src/Imagenes/datos_diagonal3.gif","src/Imagenes/datos_diagonal4.gif"};
    
    public Animacion()
    {
        setTipoRed("");
        setTipoAnimacion("");
        setBoton(new JButton());
        setNombre("");
        setVisible(false);
    }
    
    public int getX3()
    {
        return x3;
    }

    public void setX3(int x3)
    {
        this.x3 = x3;
    }

    public int getY3()
    {
        return y3;
    }

    public void setY3(int y3)
    {
        this.y3 = y3;
    }
    
    public String getTipoRed()
    {
        return tipoRed;
    }

    public void setTipoRed(String tipoRed)
    {
        this.tipoRed = tipoRed;
    }

    public JButton getBoton()
    {
        return boton;
    }

    public void setBoton(JButton boton)
    {
        this.boton = boton;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getTipoAnimacion()
    {
        return tipoAnimacion;
    }

    public void setTipoAnimacion(String tipoAnimacion)
    {
        this.tipoAnimacion = tipoAnimacion;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public int getX1()
    {
        return x1;
    }

    public void setX1(int x1)
    {
        this.x1 = x1;
    }

    public int getY1()
    {
        return y1;
    }

    public void setY1(int y1)
    {
        this.y1 = y1;
    }

    public int getX2()
    {
        return x2;
    }

    public void setX2(int x2)
    {
        this.x2 = x2;
    }

    public int getY2()
    {
        return y2;
    }

    public void setY2(int y2)
    {
        this.y2 = y2;
    }
    
    public void addAnimacion ()
    {
        panel.add(boton);
        panel.setVisible(true);
        panel.revalidate();
        panel.repaint();
    }
    
    public void setPos(String tipo)
    {
        Router aux = new Router ();
        AccessPoint auxAP = new AccessPoint();
        STA auxSTA = new STA();
        if (tipo.equals("Suma"))
        {
            if (tipoRed.equals("Infraestructura"))
            {
                if (!arrayRouter.isEmpty())
                {
                    for (Object key : arrayRouter.keySet())
                    {
                        aux = (Router) arrayRouter.get(key);
                        if (aux.getEstacion().containsKey(sta.getID()))
                        {
                            size0 = botonesRouter.getPreferredSize();
                            x2 = aux.getPosx()+size0.width/2;
                            y2 = aux.getPosy()+size0.height/2;
                            break;
                        }
                    }
                }
                if (!arrayAP.isEmpty())
                {
                    for (Object key : arrayAP.keySet())
                    {
                        auxAP = (AccessPoint) arrayAP.get(key);
                        if (auxAP.getEstacion().containsKey(sta.getID()))
                        {
                            size0 = botonesAP.getPreferredSize();
                            x2 = auxAP.getPosx()+size0.width/2;
                            y2 = auxAP.getPosy()+size0.height/2;
                            break;
                        }
                    }
                    size = botonesSTA.getPreferredSize();
                    x1 = x1+size.width/2;
                    y1 = y1+size.height/2;
                }
            }
            else
            {
                for (Object key : redesAdHoc.keySet())
                {
                    auxSTA = (STA) redesAdHoc.get(key);
                    if (sta.getSSID().equals(auxSTA.getSSID()))
                    {
                        size0 = botonesSTA.getPreferredSize();
                        x2 = auxSTA.getPosx();
                        y2 = auxSTA.getPosy();
                        break;
                    }
                }
                size = botonesSTA.getPreferredSize();
            }
        }
        else
        {
            if (tipoRed.equals("Infraestructura"))
            {
                for (int j=0; j<arrayRouter.size();j++)
                {
                    for (Object key : arrayRouter.keySet())
                    {
                        aux = (Router) arrayRouter.get(key);
                        if (aux.getEstacion().containsKey(sta.getID()))
                        {
                            size0 = botonesRouter.getPreferredSize();
                            x2 = aux.getPosx()-size0.width/2;
                            y2 = aux.getPosy()-size0.height/2;
                            break;
                        }
                    }
                }
                
                for (int j=0; j<arrayAP.size();j++)
                {
                    for (Object key : arrayAP.keySet())
                    {
                        auxAP = (AccessPoint) arrayAP.get(key);
                        if (auxAP.getEstacion().containsKey(sta.getID()))
                        {
                            size0 = botonesAP.getPreferredSize();
                            x2 = auxAP.getPosx()-size0.width/2;
                            y2 = auxAP.getPosy()-size0.height/2;
                            break;
                        }
                    }
                }
                
                size = botonesSTA.getPreferredSize();
                x1 = x1-size.width/2;
                y1 = y1-size.height/2;
            }
            else
            {
                for (Object key : redesAdHoc.keySet())
                {
                    auxSTA = (STA) redesAdHoc.get(key);
                    if (sta.getSSID().equals(auxSTA.getSSID()))
                    {
                        size0 = botonesSTA.getPreferredSize();
                        x2 = auxSTA.getPosx();
                        y2 = auxSTA.getPosy();
                        break;
                    }
                }
                size = botonesSTA.getPreferredSize();
            }
        }
    }
    
    public void crearAnimacion(final String como, final String direccion)
    {
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground() throws Exception
            {
                eliminarAnimacion();
                
                boton = new JButton();
                boton.setOpaque(false);
                boton.setBorder(null);
                boton.setBorderPainted(false);
                boton.setBackground(null);
                boton.setContentAreaFilled(false);
                
                /*Conexión*/
                if (tipoAnimacion.equals("Conexión"))
                {
                    if (como.equals("Vertical"))
                    {
                        if (direccion.equals("Arriba"))
                        {
                            boton.setIcon(new ImageIcon(conexiones[0]));
                            boton.setPreferredSize(new Dimension(70, 102));
                            size1 = boton.getPreferredSize();
                            boton.setBounds(x2-30, (y1+y2)/2,size1.width, size1.height);
                        }
                        else
                        {
                            boton.setIcon(new ImageIcon(conexiones[1]));
                            boton.setPreferredSize(new Dimension(70, 102));
                            size1 = boton.getPreferredSize();
                            boton.setBounds(x2-30, (y1+y2+size0.height)/2,size1.width, size1.height);
                        }
                    }
                    if (como.equals("Horizontal"))
                    {
                        if (direccion.equals("Izquierda"))
                        {
                            boton.setIcon(new ImageIcon(conexiones[3]));
                            boton.setPreferredSize(new Dimension(90, 31));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x1+x2)/2, y2+size0.height/2,size1.width, size1.height);
                        }
                        else
                        {
                            boton.setIcon(new ImageIcon(conexiones[2]));
                            boton.setPreferredSize(new Dimension(70, 56));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x1+x2)/2, y2+size0.height/2,size1.width, size1.height);
                        }
                    }
                    if (como.equals("Diagonal"))
                    {
                        if (direccion.equals("ArribaDerecha"))
                        {
                            boton.setIcon(new ImageIcon(conexiones[7]));
                            boton.setPreferredSize(new Dimension(91, 102));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y2-size1.height/2,size1.width, size1.height);
                        }
                        if (direccion.equals("ArribaIzquierda"))
                        {
                            boton.setIcon(new ImageIcon(conexiones[5]));
                            boton.setPreferredSize(new Dimension(91, 102));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y1+size1.height/2,size1.width, size1.height);
                        }
                        if (direccion.equals("AbajoDerecha"))
                        {
                            boton.setIcon(new ImageIcon(conexiones[4]));
                            boton.setPreferredSize(new Dimension(91, 102));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, (y2+y1)/2,size1.width, size1.height);
                        }
                        if (direccion.equals("AbajoIzquierda"))
                        {
                            boton.setIcon(new ImageIcon(conexiones[6]));
                            boton.setPreferredSize(new Dimension(91, 102));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y2+size1.height/2,size1.width, size1.height);
                        }
                    }
                }
                
                /*Ping*/
                if (tipoAnimacion.equals("Ping"))
                {
                    if (como.equals("Vertical"))
                    {
                        if (direccion.equals("Arriba"))
                        {
                            boton.setIcon(new ImageIcon(ping[0]));
                            boton.setPreferredSize(new Dimension(112, 139));
                            size1 = boton.getPreferredSize();
                            boton.setBounds(x2-30, (y1+y2)/2,size1.width, size1.height);
                        }
                        else
                        {
                            boton.setIcon(new ImageIcon(ping[1]));
                            boton.setPreferredSize(new Dimension(112, 139));
                            size1 = boton.getPreferredSize();
                            boton.setBounds(x2-30, (y1+y2+size0.height)/2,size1.width, size1.height);
                        }
                    }
                    if (como.equals("Horizontal"))
                    {
                        if (direccion.equals("Izquierda"))
                        {
                            boton.setIcon(new ImageIcon(ping[3]));
                            boton.setPreferredSize(new Dimension(160, 46));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x1+x2)/2, y2+size0.height/2,size1.width, size1.height);
                        }
                        else
                        {
                            boton.setIcon(new ImageIcon(ping[2]));
                            boton.setPreferredSize(new Dimension(160, 46));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x1+x2)/2, y2+size0.height/2,size1.width, size1.height);
                        }
                    }
                    if (como.equals("Diagonal"))
                    {
                        if (direccion.equals("ArribaDerecha"))
                        {
                            boton.setIcon(new ImageIcon(ping[4]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y2-size1.height/2,size1.width, size1.height);
                        }
                        if (direccion.equals("ArribaIzquierda"))
                        {
                            boton.setIcon(new ImageIcon(ping[7]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y1+size1.height/2,size1.width, size1.height);
                        }
                        if (direccion.equals("AbajoDerecha"))
                        {
                            boton.setIcon(new ImageIcon(ping[6]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, (y2+y1)/2,size1.width, size1.height);
                        }
                        if (direccion.equals("AbajoIzquierda"))
                        {
                            boton.setIcon(new ImageIcon(ping[5]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y2+size1.height/2,size1.width, size1.height);
                        }
                    }
                }
                
                /*Envío de paquetes*/
                if (tipoAnimacion.equals("Paquetes"))
                {
                    if (como.equals("Vertical"))
                    {
                        if (direccion.equals("Arriba"))
                        {
                            boton.setIcon(new ImageIcon(paquetes[0]));
                            boton.setPreferredSize(new Dimension(112, 139));
                            size1 = boton.getPreferredSize();
                            boton.setBounds(x2-30, (y1+y2)/2,size1.width, size1.height);
                        }
                        else
                        {
                            boton.setIcon(new ImageIcon(paquetes[1]));
                            boton.setPreferredSize(new Dimension(112, 139));
                            size1 = boton.getPreferredSize();
                            boton.setBounds(x2-30, (y1+y2+size0.height)/2,size1.width, size1.height);
                        }
                    }
                    if (como.equals("Horizontal"))
                    {
                        if (direccion.equals("Izquierda"))
                        {
                            boton.setIcon(new ImageIcon(paquetes[3]));
                            boton.setPreferredSize(new Dimension(160, 46));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x1+x2)/2, y2+size0.height/2,size1.width, size1.height);
                        }
                        else
                        {
                            boton.setIcon(new ImageIcon(paquetes[2]));
                            boton.setPreferredSize(new Dimension(160, 46));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x1+x2)/2, y2+size0.height/2,size1.width, size1.height);
                        }
                    }
                    if (como.equals("Diagonal"))
                    {
                        if (direccion.equals("ArribaDerecha"))
                        {
                            boton.setIcon(new ImageIcon(paquetes[4]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y2-size1.height/2,size1.width, size1.height);
                        }
                        if (direccion.equals("ArribaIzquierda"))
                        {
                            boton.setIcon(new ImageIcon(paquetes[7]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y1+size1.height/2,size1.width, size1.height);
                        }
                        if (direccion.equals("AbajoDerecha"))
                        {
                            boton.setIcon(new ImageIcon(paquetes[6]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, (y2+y1)/2,size1.width, size1.height);
                        }
                        if (direccion.equals("AbajoIzquierda"))
                        {
                            boton.setIcon(new ImageIcon(paquetes[5]));
                            boton.setPreferredSize(new Dimension(72, 72));
                            size1 = boton.getPreferredSize();
                            boton.setBounds((x2+x1)/2, y2+size1.height/2,size1.width, size1.height);
                        }
                    }
                }
                setBoton(boton);
                return true;
            }

            @Override
            protected void done()
            {
               addAnimacion();
            }

        };
        worker.execute();
    }
    
    public void eliminarAnimacion()
    {        
        panel.remove(boton);
        panel.revalidate();
        panel.repaint();
    }
    
    public void mover()
    {
        double m = 0.0;
            
        if ((-30<=x2-x1)&&(x2-x1<=30))
        {
            //linea vertical |
            if (y1>y2)
            {
                //se envía en vertical hacia arriba
                como = "Vertical";
                direccion = "Arriba";
            }
            if (y1<y2)
            {
                //se envía en vertical hacia abajo
                como = "Vertical";
                direccion = "Abajo";
            }
        }
        else
        {
            m = (double)(y1-y2)/(double)(x1-x2);
            if(m<0.2)
            {
                if (m<-0.25)
                {
                    //pendiente positiva /
                    if (y1>y2)
                    {
                        //se envía en diagonal hacia arriba y hacia la derecha
                        como = "Diagonal";
                        direccion = "ArribaDerecha";
                    }
                    if (y1<y2)
                    {
                        //se envía en diagonal hacia abajo y hacia la izquierda
                        como = "Diagonal";
                        direccion = "AbajoIzquierda";
                    }
                }
            }
            if ((m>-0.25)||(m==0.0))
            {
                //recta horizontal -
                if (x2<x1)
                {
                    // <-
                    como = "Horizontal";
                    direccion = "Izquierda";
                }
                if (x2>x1)
                {
                    // ->
                    como = "Horizontal";
                    direccion = "Derecha";
                }
            }

            if (m>0.25)
            {
                //pendiente negativa \
                if (y1>y2)
                {
                    //se envía en diagonal hacia arriba y hacia la izquierda
                    como = "Diagonal";
                    direccion = "ArribaIzquierda";
                }
                if (y1<y2)
                {
                    //se envía en diagonal hacia abajo y hacia la derecha
                    como = "Diagonal";
                    direccion = "AbajoDerecha";
                }
            }
        }
        crearAnimacion(como, direccion);
    }
    
    public void calcPendiente()
    {   
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            String como ="", direccion = "";
            double m = 0.0;
            
            @Override
            protected Boolean doInBackground() throws Exception
            {
                if ((-30<=x2-x1)&&(x2-x1<=30))
                {
                    //linea vertical |
                    if (y1<y2)
                    {
                        //se envía en vertical hacia arriba
                        como = "Vertical";
                        direccion = "Arriba";
                    }
                    if (y1>y2)
                    {
                        //se envía en vertical hacia abajo
                        como = "Vertical";
                        direccion = "Abajo";
                    }
                }
                else
                {
                    if (tipoAnimacion.equals("Conexión")) setPos("Resta");
                    m = (double)(y1-y2)/(double)(x1-x2);
                    if(m<0.2)
                    {
                        if (m<-0.25)
                        {
                            //pendiente positiva /
                            if (y1<y2)
                            {
                                //se envía en diagonal hacia arriba y hacia la derecha
                                como = "Diagonal";
                                direccion = "ArribaDerecha";
                            }
                            if (y1>y2)
                            {
                                //se envía en diagonal hacia abajo y hacia la izquierda
                                como = "Diagonal";
                                direccion = "AbajoIzquierda";
                            }
                        }
                    }
                    if ((m>-0.25)||(m==0.0))
                    {
                        //recta horizontal -
                        if (x2>x1)
                        {
                            // <-
                            como = "Horizontal";
                            direccion = "Izquierda";
                        }
                        if (x2<x1)
                        {
                            // ->
                            como = "Horizontal";
                            direccion = "Derecha";
                        }
                    }

                    if (m>0.25)
                    {
                        //pendiente negativa \
                        if (y1<y2)
                        {
                            //se envía en diagonal hacia arriba y hacia la izquierda
                            como = "Diagonal";
                            direccion = "ArribaIzquierda";
                        }
                        if (y1>y2)
                        {
                            //se envía en diagonal hacia abajo y hacia la derecha
                            como = "Diagonal";
                            direccion = "AbajoDerecha";
                        }
                    }
                }
                return true;
            }

            @Override
            protected void done()
            {
                crearAnimacion(como, direccion);
            }
        };
        worker.execute();
    }
    
    public void getNodo(HashMap arrayRouter, HashMap arrayAP, HashMap arraySTA, HashMap redesAdHoc) 
    {
        this.arrayRouter = arrayRouter;
        this.arrayAP = arrayAP;
        this.arraySTA = arraySTA;
        this.redesAdHoc = redesAdHoc;
        sta = new STA();

        if (arraySTA.containsKey(nombre))
        {
            sta = (STA) arraySTA.get(nombre);
            setPos("Suma");
            calcPendiente();
        }
    }
}