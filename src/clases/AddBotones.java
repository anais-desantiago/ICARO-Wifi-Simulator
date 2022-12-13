package clases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class AddBotones
{
    JPanel panelBoton = new JPanel();
   
    PingDialog ping = new PingDialog(null, true);
    SignalRange senal = new SignalRange();
    EliminarComponentes eliminar = new EliminarComponentes();
    
    ConfigRouter configR = new ConfigRouter(null, false);
    ConfigSTAInfra staInfra = new ConfigSTAInfra(null, true);
    ConfigAP configAP = new ConfigAP(null, false);
    ConfigSTAAdhoc adhoc = new ConfigSTAAdhoc(null, true);
    CrearSTAAdhoc crear = new CrearSTAAdhoc(null, true);
    Animacion animacion = new Animacion();
    Buscar buscar = new Buscar();
    
    HashMap contenedor = new HashMap();
    
    MoverBoton mover;
    DrawCanvas circulo;
    
    JPopupMenu popUp;
    JButton botn;
    
    Router router = new Router();
    AccessPoint ap = new AccessPoint();
    STA sta = new STA();
    
    String tipoBoton, tipoRed, id, ip = "", ssid = "", seguridad="", dhcp="", mask="", ipWAN="";
    int cant, tamPanelx,tamPanely,x, y, posx, posy, porc;
    
    ArrayList <JButton> botonesRouter = new ArrayList<>();
    ArrayList <JButton> botonesAP = new ArrayList<>();
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    
    HashMap arrayRouter = new HashMap();
    HashMap arraySTA = new HashMap();
    HashMap redesAdHoc = new HashMap();
    HashMap arrayAP = new HashMap();
    
    ArrayList <DrawCanvas> arrayCirculoRouter = new ArrayList <>();
    ArrayList <DrawCanvas> arrayCirculoAP = new ArrayList <>();
    ArrayList <DrawCanvas> arrayCirculoSTA = new ArrayList <>();
    
    ArrayList <String> puestos = new ArrayList<>();
    ArrayList <String> nombres = new ArrayList<>();
    
    String [] statusSTA = {"","",""};
    String [] statusRouter = {"","","","","","",""};
    String [] statusAP = {"","","","","",""};
    
    Debug debug = new Debug();
    boolean padre = false, noise, found = false, cargado = false;
    
    public AddBotones()
    {
        panelBoton.setLayout(null);
        panelBoton.setBorder(BorderFactory.createEtchedBorder());
        panelBoton.setBackground(Color.white);
    }

    public class DrawCanvas extends JPanel
    {
        int x, y, r1, r2, x2,y2;
        public DrawCanvas(int posx,int posy)
        {
            r1 = 300;
            r2 = 300;
            this.x = posx;
            setSize(tamPanelx, tamPanely);
            this.y = posy;
        }
        @Override
        public void paintComponent(Graphics g)
        {
           super.paintComponent(g);
           setBackground(Color.white);
           setOpaque(false);
           g.setColor(Color.BLACK);
           g.drawOval(x, y, r1, r2);
       }
    }
     
    public class MoverBoton extends MouseAdapter
    {
        /*Esta clase permite el movimiento en conjunto del boton junto con el círculo dibujado*/
        private Point startPoint;
        private Component draggedObject;
        Dimension size;
        DrawCanvas circle;
        JButton boton;
        int i,r1,r2, x1,x2;
        
        @Override
        public void mousePressed(MouseEvent mme)
        {
            draggedObject = (Component) mme.getSource();
            boton = (JButton) draggedObject;
            size = boton.getSize();
            
            if(boton.getName().startsWith("STA"))
            {
                i = botonesSTA.indexOf(draggedObject);
                circle = arrayCirculoSTA.get(i);
            }
            if(boton.getName().startsWith("AP"))
            {
                i = botonesAP.indexOf(draggedObject);
                circle = arrayCirculoAP.get(i);
            }
            if(boton.getName().startsWith("Router"))
            {
                i = botonesRouter.indexOf(draggedObject);
                circle = arrayCirculoRouter.get(i);
            }
            
            r1 = circle.r1;
            r2 = circle.r2;
            x1 = circle.x;
            x2 = circle.y;
            
            startPoint = SwingUtilities.convertPoint(draggedObject, mme.getPoint(), draggedObject.getParent());
        }
        @Override
        public void mouseDragged(MouseEvent mme)
        {
            Point location = SwingUtilities.convertPoint(draggedObject, mme.getPoint(), draggedObject.getParent());
            if (draggedObject.getParent().getBounds().contains(location))
            {
                Point newLocation = draggedObject.getLocation();
                newLocation.translate(location.x - startPoint.x, location.y - startPoint.y);
                newLocation.x = Math.max(newLocation.x, 0);
                newLocation.x = Math.min(newLocation.x, draggedObject.getParent().getWidth() - draggedObject.getWidth());
                newLocation.y = Math.max(newLocation.y, 0);
                newLocation.y = Math.min(newLocation.y, draggedObject.getParent().getHeight() - draggedObject.getHeight());
                draggedObject.setLocation(newLocation);
                startPoint = location;
                circle.setLocation(newLocation.x-x1-r1/2+size.width/2, newLocation.y-x2-r2/2+size.height/2);
            }
        }  
        @Override
        public void mouseMoved(MouseEvent mme) {}
       
        @Override
        public void mouseReleased(MouseEvent e)
        {
            startPoint = null;
            draggedObject = null;
        }
        public void makeDraggable(Component component)
        {
            component.addMouseListener(this);
            component.addMouseMotionListener(this);
        }
    }

    public void setEstado(final String tipoBoton, final MouseEvent e)
    {
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground() throws Exception
            {
                statusRouter = new String [7];
                Component c = e.getComponent();
                botn = (JButton) c;
                id = botn.getName();
                ip = "";
                ssid = "";
                mask = "";
                seguridad = "";
                dhcp = "";
                ipWAN = "";

                if (tipoBoton.equals("STA"))
                {
                    if (arraySTA.containsKey(id))
                    {
                        sta = (STA) arraySTA.get(id);
                        ip = sta.getIP();
                        ssid = sta.getSSID();
                        found = true;
                    }
                    else found = false;
                    for (int i=0; i<statusSTA.length;i++)
                    {
                        if (i==0) statusSTA[0] = "Estado de: "+id;
                        if (i==1) statusSTA[1] = "IP: "+ip;
                        if (i==2) statusSTA[2] = "SSID: " + ssid;
                    }
                }

                if (tipoBoton.equals("Router"))
                {
                    if (arrayRouter.containsKey(id))
                    {
                        router = (Router) arrayRouter.get(id);
                        ip = router.getIP();
                        mask = router.getMask();
                        ssid = router.getSSID();
                        seguridad = router.getSeguridad();
                        dhcp = router.getActDHCP();
                        ipWAN = router.getIPWAN();
                    }
                    for (int i=0; i<statusRouter.length;i++)
                    {
                        if (i==0) statusRouter[i] = "Estado de: "+id;
                        if (i==1) statusRouter[i] = "IP: "+ip;
                        if (i==2) statusRouter[i] = "Máscara: " + mask;
                        if (i==3) statusRouter[i] = "SSID: " + ssid;
                        if (i==4) statusRouter[i] = "Tipo de Seguridad: " +seguridad;
                        if (i==5) statusRouter[i] = "Configuración del DHCP: "+dhcp;
                        if (i==6) statusRouter[i] = "Configuración WAN: "+ipWAN;
                    }
                }
                if (tipoBoton.equals("AP"))
                {
                    if (arrayAP.containsKey(id))
                    {
                        ap = (AccessPoint) arrayAP.get(id);
                        ip = ap.getIP();
                        mask = ap.getMask();
                        ssid = ap.getSSID();
                        seguridad = ap.getSeguridad();
                        dhcp = ap.getActDHCP();
                    }
                    for (int i=0; i<statusAP.length;i++)
                    {
                        if (i==0) statusAP[i] = "Estado de: "+id;
                        if (i==1) statusAP[i] = "IP: "+ip;
                        if (i==2) statusAP[i] = "Máscara: " + mask;
                        if (i==3) statusAP[i] = "SSID: " + ssid;
                        if (i==4) statusAP[i] = "Tipo de Seguridad: " +seguridad;
                        if (i==5) statusAP[i] = "Configuración del DHCP: "+dhcp;
                    }
                }
                return true;
            }

            @Override
            protected void done()
            {
                if ((e.getClickCount()==1) && (e.getButton()!=3)) 
                {
                    if (tipoBoton.equals("STA"))
                    {
                        Consola.mostrarTexto("");
                        for (String statusSTA1 : statusSTA) {
                            Consola.mostrarTexto(statusSTA1);
                        }
                    }
                    if (tipoBoton.equals("Router"))
                    {
                        Consola.mostrarTexto("");
                        for (String statusRouter1 : statusRouter) {
                            Consola.mostrarTexto(statusRouter1);
                        }
                    }
                    if (tipoBoton.equals("AP"))
                    {
                        Consola.mostrarTexto("");
                        for (String statusAP1 : statusAP) {
                            Consola.mostrarTexto(statusAP1);
                        }
                    }
                }
                if (e.getClickCount()==2)
                {
                    if (tipoBoton.equals("STA"))
                    {
                        if (tipoRed.equals("Infraestructura")) staInfra.configSTAInfraDialog.setVisible(true);
                        
                        if (tipoRed.equals("AdHoc")) adhoc.configAdhocDialog.setVisible(true);
                        
                        Consola.mostrarTexto("");
                    }
                    if (tipoBoton.equals("Router"))
                    {
                        configR.configRouterDialog.setVisible(true);
                        Consola.mostrarTexto("");
                    }
                    if (tipoBoton.equals("AP"))
                    {
                        configAP.configAPDialog.setVisible(true);
                        Consola.mostrarTexto("");
                    }
                }
                if (e.getButton() == 3)
                {
                    // if right click
                    if (tipoBoton.equals("STA")) addPopUp ("STA",e);
                    if (tipoBoton.equals("Router")) addPopUp ("Router",e);
                    if (tipoBoton.equals("AP")) addPopUp ("AP",e);
                }
           }
        };
        worker.execute();
    }
    
    public void eliminarSTA(MouseEvent e)
    {
        String [] opciones = {"Si", "No"};
        int i = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este componente?", "Confirmar", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (i == JOptionPane.OK_OPTION)
        {
            eliminar.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));
            eliminar.stas = arraySTA;
            eliminar.aps = arrayAP;
            eliminar.routers = arrayRouter;
            eliminar.redes = redesAdHoc;
            eliminar.botonesSTA = botonesSTA;
            
            eliminar.eliminar(tipoRed, tipoBoton);
            padre = eliminar.padre;
            panelBoton.remove(arrayCirculoSTA.get(botonesSTA.indexOf(e.getSource())));
            panelBoton.remove(botonesSTA.get(botonesSTA.indexOf(e.getSource())));

            redesAdHoc = eliminar.redes;
            if (tipoRed.equals("AdHoc"))
            {
                if(padre)
                {
                    nombres = eliminar.nomSTAs;
                    redesAdHoc = eliminar.redes;
                    
                    for (String nombre : nombres)
                    {
                        if (arraySTA.containsKey(nombre)) eliminarAnimacion(nombre);
                    }
                }
            }
            
            if (eliminar.found && contenedor.containsKey(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName())) eliminarAnimacion(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
            
            arraySTA = eliminar.stas;
            panelBoton.repaint();
            arrayCirculoSTA.remove(botonesSTA.indexOf(e.getSource()));
            botonesSTA.remove(botonesSTA.indexOf(e.getSource()));
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void addPopUp (final String tipoBoton, final MouseEvent e)
    {
        popUp = new JPopupMenu();
        JMenuItem menuItem;
        final String [] staPopUp = {"Configurar", "Eliminar","Hacer Ping", "Estado"};
        final String [] routerPopUp = {"Configurar", "Eliminar", "Hacer Ping", "Estado"};
        final String [] adhocPopUp = {"Crear Red","Configurar", "Eliminar","Hacer Ping","Estado"};
        popUp.removeAll();
        
        /*pop-up sta*/
        if (tipoBoton.equals("STA"))
        {
            if (tipoRed.equals("Infraestructura"))
            {
                for (int i = 0; i < staPopUp.length; i++)
                {
                    menuItem = new JMenuItem(staPopUp[i]);
                    if (i==0)
                    {
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae)
                            {
                                staInfra.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));
                                staInfra.botonesAP = botonesAP;
                                staInfra.botonesRouter = botonesRouter;
                                staInfra.botonesSTA = botonesSTA;
                                staInfra.arrayCirculoAP = arrayCirculoAP;
                                staInfra.arrayCirculoRouter = arrayCirculoRouter;
                                staInfra.arrayCirculoSTA = arrayCirculoSTA;
                                staInfra.setTitulo(staInfra.boton, arrayRouter, arraySTA, arrayAP,botonesSTA.indexOf(e.getSource()), contenedor);
                                staInfra.configSTAInfraDialog.setVisible(true);
                                arraySTA = staInfra.stas;
                            }
                        });
                    }
                    if (i==1)
                    {
                        menuItem.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            eliminarSTA(e);
                        }
                        });
                    }
                    if (i==2)
                    {
                        menuItem.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            ping.panel = panelBoton;
                            ping.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));

                            buscar.nombre = ping.nombre = botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName();
                            buscar.tipoRed = ping.tipoRed = "Infraestructura";
                            buscar.nodo = ping.nodo = "STA";
                            ping.ip0 = ip;
                            buscar.setIPFuente(ping.ip0,arrayRouter, arraySTA, arrayAP);
                            ping.buscar = buscar;
                            ping.pingDialog.setVisible(true);
                        }
                        });
                    }
                    if (i==3)
                    {
                        menuItem.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent ae)
                            {
                                Consola.mostrarTexto("");
                                for (String statusSTA1 : statusSTA)
                                {
                                    Consola.mostrarTexto(statusSTA1);
                                }
                            }
                        });
                    }

                    popUp.add(menuItem);
                }
                popUp.show(e.getComponent(), e.getX(),e.getY());
            }
            else
            {
                for (int i = 0; i < adhocPopUp.length; i++)
                {
                    menuItem = new JMenuItem(adhocPopUp[i]);
                    if (i==0)
                    {
                        /*Crear red ad-hoc*/
                        menuItem.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent ae)
                            {
                                crear.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));
                                crear.botonesSTA = botonesSTA;
                                crear.arrayCirculoSTA = arrayCirculoSTA;
                                crear.setTitulo(crear.boton, redesAdHoc,botonesSTA.indexOf(e.getSource()));
                                crear.crearAdhocDialog.setVisible(true);
                                redesAdHoc = crear.stas;
                            }
                        });
                    }

                    if (i==1)
                    {
                        /*Configurar red ad-hoc*/
                        menuItem.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae)
                            {
                                adhoc.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));
                                adhoc.botonesSTA = botonesSTA;
                                adhoc.arrayCirculoSTA = arrayCirculoSTA;
                                adhoc.setTitulo(adhoc.boton, arraySTA,botonesSTA.indexOf(e.getSource()),redesAdHoc,contenedor);
                                
                                if (adhoc.error) adhoc.configAdhocDialog.setVisible(false);
                                else adhoc.configAdhocDialog.setVisible(true);
                                arraySTA = adhoc.stas;
                            }
                        });
                    }
                    if (i==2)
                    {
                        /*Eliminar STA en modo Ad-Hoc*/
                        menuItem.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            eliminarSTA(e);
                        }
                        });
                    }
                    if (i==3)
                    {
                        /*Ping ad-hoc*/
                        menuItem.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            ping.panel = panelBoton;
                            ping.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));

                            buscar.nombre = ping.nombre = botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName();
                            buscar.tipoRed = ping.tipoRed = "AdHoc";
                            buscar.nodo = ping.nodo = "STA";
                            ping.ip0 = ip;
                            buscar.setIPFuente(ip, null, arraySTA, null);
                            ping.buscar = buscar;
                            ping.pingDialog.setVisible(true);
                        }
                        });
                    }
                    if (i==4)
                    {
                        /*Status ad-hoc*/
                        menuItem.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent ae)
                            {
                                Consola.mostrarTexto("");
                                for (String statusSTA1 : statusSTA)
                                {
                                    Consola.mostrarTexto(statusSTA1);
                                }
                            }
                        });
                    }
                    popUp.add(menuItem);
                }
                popUp.show(e.getComponent(), e.getX(),e.getY());
            }
        }
        
        /*pop up router*/
        if(tipoBoton.equals("Router"))
        {
            for (int i = 0; i < routerPopUp.length; i++)
            {
                menuItem = new JMenuItem(routerPopUp[i]);
               
                if (i==0)
                {
                    /*Configurar router*/
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            configR.aps = arrayAP;
                            configR.boton = botn;
                            configR.arrayCirculoRouter = arrayCirculoRouter;
                            configR.setTitulo(configR.boton,arrayRouter);
                            arrayRouter = configR.routers;
                            configR.configRouterDialog.setVisible(true);
                        }
                    });
                }
                if (i==1)
                {
                    /*Eliminar router*/
                    menuItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        String [] opciones = {"Si", "No"};
                        int i = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este componente?", "Confirmar", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                        if (i == JOptionPane.OK_OPTION)
                        {
                            eliminar.boton = botonesRouter.get(botonesRouter.indexOf(e.getSource()));
                            eliminar.routers = arrayRouter;
                            eliminar.eliminar(tipoRed, tipoBoton);
                            panelBoton.remove(arrayCirculoRouter.get(botonesRouter.indexOf(e.getSource())));
                            panelBoton.remove(botonesRouter.get(botonesRouter.indexOf(e.getSource())));
                            nombres = eliminar.nomSTAs;
                            arrayRouter = eliminar.routers;
                            
                            for (String nombre : nombres)
                            {
                                if (arraySTA.containsKey(nombre))eliminarAnimacion(nombre);
                            }
                            
                            panelBoton.repaint();
                            arrayCirculoRouter.remove(botonesRouter.indexOf(e.getSource()));
                            botonesRouter.remove(botonesRouter.indexOf(e.getSource()));
                        }
                    }
                    });
                }
                if (i==2)
                {
                    menuItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        ping.panel = panelBoton;
                        buscar.nombre = ping.nombre = botonesRouter.get(botonesRouter.indexOf(e.getSource())).getName();
                        buscar.tipoRed = ping.tipoRed = "Infraestructura";
                        buscar.nodo = ping.nodo = "Router";
                        ping.ip0 = ip;
                        buscar.setIPFuente(ping.ip0,arrayRouter, arraySTA, arrayAP);
                        ping.buscar = buscar;
                        ping.pingDialog.setVisible(true);
                    }
                    });
                }
                if (i==3)
                {
                    menuItem.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            Consola.mostrarTexto("");
                            for (String statusRouter1 : statusRouter)
                            {
                                Consola.mostrarTexto(statusRouter1);
                            }
                        }
                    });
                }
                popUp.add(menuItem);
            }
            popUp.show(e.getComponent(), e.getX(),e.getY());
        }
        if (tipoBoton.equals("AP"))
        {
            //Si es un ap
            for (int i = 0; i < routerPopUp.length; i++)
            {
                menuItem = new JMenuItem(routerPopUp[i]);
                if (i==0)
                {
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae)
                        {
                            configAP.routers = arrayRouter;
                            configAP.boton = botn;
                            configAP.setTitulo(configAP.boton,arrayAP);
                            arrayAP = configAP.aps;
                            configAP.configAPDialog.setVisible(true);
                        }
                    });
                }
                if (i==1)
                {
                    menuItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        String [] opciones = {"Si", "No"};
                        int i = JOptionPane.showOptionDialog(null, "¿Está seguro que desea eliminar este componente?", "Confirmar", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                        if (i == JOptionPane.OK_OPTION)
                        {
                            eliminar.boton = botonesAP.get(botonesAP.indexOf(e.getSource()));
                            eliminar.aps = arrayAP;
                            eliminar.eliminar(tipoRed, tipoBoton);
                            panelBoton.remove(arrayCirculoAP.get(botonesAP.indexOf(e.getSource())));
                            panelBoton.remove(botonesAP.get(botonesAP.indexOf(e.getSource())));
                            
                            nombres = eliminar.nomSTAs;
                            arrayAP = eliminar.aps;
                            
                            for (String nombre : nombres)
                            {
                                if (arraySTA.containsKey(nombre)) eliminarAnimacion(nombre);
                            }
                            
                            panelBoton.repaint();
                            arrayCirculoAP.remove(botonesAP.indexOf(e.getSource()));
                            botonesAP.remove(botonesAP.indexOf(e.getSource()));
                        }
                    }
                    });
                }
                if (i==2)
                {
                    menuItem.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        ping.panel = panelBoton;
                        buscar.nombre = ping.nombre = botonesAP.get(botonesAP.indexOf(e.getSource())).getName();
                        buscar.tipoRed = ping.tipoRed = "Infraestructura";
                        buscar.nodo = ping.nodo = "AP";
                        ping.ip0 = ip;
                        buscar.setIPFuente(ping.ip0,arrayRouter, arraySTA, arrayAP);
                        ping.buscar = buscar;
                        ping.pingDialog.setVisible(true);
                    }
                    });
                }
                if (i==3)
                {
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            Consola.mostrarTexto("");
                            for (String statusRouter1 : statusRouter) {
                                Consola.mostrarTexto(statusRouter1);
                            }
                        }
                    });
                }
                popUp.add(menuItem);
            }
            popUp.show(e.getComponent(), e.getX(),e.getY());
        }
    }
   
    public void getIndice (ActionEvent e, String tipo)
    {
        if (tipo.equals("STA"))
        {
            found = arraySTA.containsKey(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
        }
        if (tipo.equals("Router"))
        {
            found = arrayRouter.containsKey(botonesRouter.get(botonesRouter.indexOf(e.getSource())).getName());
        }
        if (tipo.equals("AP"))
        {
            found = arrayAP.containsKey(botonesAP.get(botonesAP.indexOf(e.getSource())).getName());
        }
    }
    
    public void invocarAnimacion(final int e, final String invocador)
    {
        if (!cargado)
        {
            if (!botonesAP.isEmpty()) animacion.botonesAP = botonesAP.get(0);
            if (!botonesRouter.isEmpty()) animacion.botonesRouter = botonesRouter.get(0);

            animacion.botonesSTA = botonesSTA.get(0);
        }

        if (invocador.equals("STA"))
        {
            animacion = (Animacion) contenedor.get(sta.getID());

            if (cargado) animacion.setNombre(sta.getID());

            animacion.setX1(botonesSTA.get(e).getLocation().x);
            animacion.setY1(botonesSTA.get(e).getLocation().y);

            animacion.setTipoAnimacion("Conexión");
            animacion.setTipoRed(tipoRed);
            animacion.panel = panelBoton;

            if (tipoRed.equals("Infraestructura")) animacion.getNodo(arrayRouter, arrayAP, arraySTA, null);
            else animacion.getNodo(null, null, arraySTA, redesAdHoc);

            contenedor.put(sta.getID(),animacion);
        }
        else
        {
            animacion = (Animacion) contenedor.get(sta.getID());
            animacion.setNombre(sta.getID());
            animacion.setX1(sta.getPosx());
            animacion.setY1(sta.getPosy());
            animacion.setTipoAnimacion("Conexión");
            animacion.setTipoRed(tipoRed);

            if (invocador.equals("AP"))
            {
                animacion.setX2(botonesAP.get(e).getLocation().x);
                animacion.setY2(botonesAP.get(e).getLocation().y);
            }
            if (invocador.equals("Router"))
            {
                animacion.setX2(botonesRouter.get(e).getLocation().x);
                animacion.setY2(botonesRouter.get(e).getLocation().y);
            }
            if (invocador.equals("Padre"))
            {
                animacion.setX2(botonesSTA.get(e).getLocation().x);
                animacion.setY2(botonesSTA.get(e).getLocation().y);
            }
            animacion.calcPendiente();
            contenedor.put(sta.getID(),animacion);
        }
        
        if (cargado) animacion = (Animacion) contenedor.get(sta.getID());
        else
        {
            animacion = (Animacion) contenedor.get(sta.getID());
            panelBoton.add(animacion.getBoton());
            panelBoton.setVisible(true);
            panelBoton.revalidate();
            panelBoton.repaint();
        }
    }
    
    public void eliminarAnimacion(String quien)
    {
        animacion.panel = panelBoton;
        animacion = (Animacion) contenedor.get(quien);
        animacion.boton = animacion.getBoton();
        animacion.eliminar = true;
        animacion.eliminarAnimacion();
        contenedor.remove(quien);
        animacion.eliminar = false;
    }
    
    public void checkAdHoc (int ind)
    {
        /*Verificacion de que queden nodos conectados al nodo padre para así borrar o no la red*/
        senal.botonesSTA = botonesSTA;
        senal.stas = arraySTA;
        senal.redes = redesAdHoc;
        senal.arrayCirculoSTA = arrayCirculoSTA;
        
        if (!senal.checkRouter(ind, "STA"))
        {
            nombres = senal.nombres;

            for (String nombre : nombres)
            {
                if ((arraySTA.containsKey(nombre)) && (contenedor.containsKey(nombre)))
                {
                    eliminarAnimacion(nombre);
                    //arraySTA.remove(nombre);
                }
            }

            //JOptionPane.showMessageDialog(null, "Se perdió la configuración de la red");
            
            /*if (redesAdHoc.containsKey(botonesSTA.get(ind).getName()))
            {
                redesAdHoc.remove(botonesSTA.get(ind).getName());
                if (found) arraySTA.remove(botonesSTA.get(ind).getName());
            }*/
        }
        else
        {
            nombres = senal.nombres;

            for (String nombre : nombres)
            {
                if ((arraySTA.containsKey(nombre)) && (contenedor.containsKey(nombre)))
                {
                    sta = (STA) arraySTA.get(nombre);
                    invocarAnimacion(ind, "Padre");
                }
            }
        }
    }
    
    public void accionBoton(final ActionEvent e, final String tipo)
    {
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground() throws Exception
            {
                tipoBoton = tipo;
                animacion.panel = panelBoton;
                if((e.getSource() instanceof JButton)&&(tipoRed.equals("Infraestructura"))&&(tipo.equals("STA")))
                {
                    /*click en STA para infraestructura*/
                    staInfra.panel = panelBoton;
                    staInfra.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));
                    staInfra.botonesAP = botonesAP;
                    staInfra.botonesRouter = botonesRouter;
                    staInfra.botonesSTA = botonesSTA;
                    staInfra.arrayCirculoAP = arrayCirculoAP;
                    staInfra.arrayCirculoRouter = arrayCirculoRouter;
                    staInfra.arrayCirculoSTA = arrayCirculoSTA;
                    staInfra.setTitulo(staInfra.boton, arrayRouter, arraySTA, arrayAP,botonesSTA.indexOf(e.getSource()), contenedor);
                    arraySTA = staInfra.stas;
                    contenedor = staInfra.contenedor;

                    getIndice(e, "STA");

                    if (!arraySTA.isEmpty()&&found)
                    {
                        sta = (STA) arraySTA.get(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
                        sta.setPosx(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getLocation().x);
                        sta.setPosy(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getLocation().y);

                        senal.routers = arrayRouter;
                        senal.aps = arrayAP;
                        senal.botonesAP = botonesAP;
                        senal.botonesRouter = botonesRouter;
                        senal.botonesSTA = botonesSTA;
                        senal.arrayCirculoAP = arrayCirculoAP;
                        senal.arrayCirculoRouter = arrayCirculoRouter;
                        senal.arrayCirculoSTA = arrayCirculoSTA;

                        if (senal.checkAlcance(botonesSTA.indexOf(e.getSource()), "Infraestructura"))
                        {
                            if (contenedor.containsKey(sta.getID()))
                            {
                                animacion = (Animacion) contenedor.get(sta.getID());
                                invocarAnimacion(botonesSTA.indexOf(e.getSource()), "STA");
                            }
                        }
                        else
                        {
                            animacion.panel = panelBoton;
                            if (contenedor.containsKey(sta.getID()))
                            {
                                animacion = (Animacion) contenedor.get(sta.getID());
                                animacion.boton = animacion.getBoton();
                                animacion.eliminarAnimacion();
                            }
                        }
                    }
                }
                if((e.getSource() instanceof JButton)&&(tipoRed.equals("AdHoc"))&&(tipo.equals("STA")))
                {
                    /*click en STA para ad-hoc*/
                    STA auxSTA = new STA();
                    adhoc.panel = panelBoton;
                    adhoc.boton = botonesSTA.get(botonesSTA.indexOf(e.getSource()));
                    adhoc.botonesSTA = botonesSTA;
                    adhoc.arrayCirculoSTA = arrayCirculoSTA;
                    adhoc.setTitulo(adhoc.boton, arraySTA,botonesSTA.indexOf(e.getSource()),redesAdHoc, contenedor);
                    arraySTA = adhoc.stas;
                    padre = adhoc.nodoPadre;
                    contenedor = adhoc.contenedor;
                    getIndice(e,"STA");

                    if (!arraySTA.isEmpty())
                    {
                        if (!padre)
                        {
                            if (found)
                            {
                                sta = (STA) arraySTA.get(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
                                sta.setPosx(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getLocation().x);
                                sta.setPosy(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getLocation().y);

                                senal.botonesSTA = botonesSTA;
                                senal.redes = redesAdHoc;
                                senal.arrayCirculoSTA = arrayCirculoSTA;

                                if (senal.checkAlcance(botonesSTA.indexOf(e.getSource()), "AdHoc"))
                                {
                                    if (contenedor.containsKey(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName()))
                                    {
                                        sta = (STA) arraySTA.get(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
                                        animacion = (Animacion) contenedor.get(sta.getID());
                                        invocarAnimacion(botonesSTA.indexOf(e.getSource()), "STA");
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Se perdió la configuración de la red");

                                    if (contenedor.containsKey(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName()))
                                    {
                                        sta = (STA) arraySTA.get(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
                                        animacion = (Animacion) contenedor.get(sta.getID());
                                        animacion.boton = animacion.getBoton();
                                        animacion.eliminarAnimacion();
                                    }

                                    ssid = sta.getSSID();
                                    arraySTA.remove(sta.getID());

                                    for (int i=0; i<botonesSTA.size();i++)
                                    {
                                        for (Object key : redesAdHoc.keySet())
                                        {
                                            auxSTA = (STA) redesAdHoc.get(key);
                                            if (auxSTA.getSSID().equals(ssid)&&auxSTA.getID().equals(botonesSTA.get(i).getName()))
                                            {
                                                checkAdHoc(i);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            //si es padre
                            if (found)
                            {
                                sta = (STA) arraySTA.get(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getName());
                                sta.setPosx(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getLocation().x);
                                sta.setPosy(botonesSTA.get(botonesSTA.indexOf(e.getSource())).getLocation().y);
                            }

                            checkAdHoc(botonesSTA.indexOf(e.getSource()));
                        }
                    }
                }
                if((e.getSource() instanceof JButton)&&(tipo.equals("Router")))
                {
                    /*click en router*/
                    configR.aps = arrayAP;
                    configR.arrayCirculoRouter = arrayCirculoRouter;
                    configR.boton = botonesRouter.get(botonesRouter.indexOf(e.getSource()));
                    configR.setTitulo(configR.boton,arrayRouter);
                    arrayRouter = configR.routers;

                    getIndice(e, "Router");

                    if (!arrayRouter.isEmpty()&&found)
                    {
                        router = (Router) arrayRouter.get(botonesRouter.get(botonesRouter.indexOf(e.getSource())).getName());
                        router.setPosx(botonesRouter.get(botonesRouter.indexOf(e.getSource())).getLocation().x);
                        router.setPosy(botonesRouter.get(botonesRouter.indexOf(e.getSource())).getLocation().y);

                        if(!arraySTA.isEmpty())
                        {    
                            senal.routers = arrayRouter;
                            senal.botonesRouter = botonesRouter;
                            senal.botonesSTA = botonesSTA;
                            senal.arrayCirculoRouter = arrayCirculoRouter;
                            senal.arrayCirculoSTA = arrayCirculoSTA;
                            if (!senal.checkRouter(botonesRouter.indexOf(e.getSource()), "Router"))
                            {
                                nombres = senal.nombres;
                                
                                for (String nombre : nombres)
                                {
                                    if (router.getEstacion().containsKey(nombre) && arraySTA.containsKey(nombre))
                                    {
                                        sta = (STA) arraySTA.get(nombre);
                                        if (contenedor.containsKey(sta.getID()))
                                        {
                                            animacion.panel = panelBoton;
                                            animacion = (Animacion) contenedor.get(sta.getID());

                                            animacion.boton = animacion.getBoton();
                                            animacion.eliminarAnimacion();
                                        }
                                    }
                                }
                            }
                            else
                            {
                                nombres = senal.nombres;
                                for (String nombre : nombres)
                                {
                                    if (router.getEstacion().containsKey(nombre) && arraySTA.containsKey(nombre))
                                    {
                                        sta = (STA) arraySTA.get(nombre);
                                        invocarAnimacion(botonesRouter.indexOf(e.getSource()), "Router");
                                    }
                                }
                            }
                        }
                    }
                }

               if((e.getSource() instanceof JButton)&&(tipo.equals("AP")))
               {
                   /*click en AP*/
                   configAP.routers = arrayRouter;
                   configAP.arrayCirculoAP = arrayCirculoAP;
                   configAP.boton = botonesAP.get(botonesAP.indexOf(e.getSource()));
                   configAP.setTitulo(configAP.boton,arrayAP);
                   arrayAP = configAP.aps;

                   getIndice(e, "AP");
                    if (!arrayAP.isEmpty()&&found)
                    {
                        ap = (AccessPoint) arrayAP.get(botonesAP.get(botonesAP.indexOf(e.getSource())).getName());
                        ap.setPosx(botonesAP.get(botonesAP.indexOf(e.getSource())).getLocation().x);
                        ap.setPosy(botonesAP.get(botonesAP.indexOf(e.getSource())).getLocation().y);

                        if(!arraySTA.isEmpty())
                        {    
                            senal.aps = arrayAP;
                            senal.botonesAP = botonesAP;
                            senal.botonesSTA = botonesSTA;
                            senal.arrayCirculoAP = arrayCirculoAP;
                            senal.arrayCirculoSTA = arrayCirculoSTA;
                            if (!senal.checkRouter(botonesAP.indexOf(e.getSource()), "AP"))
                            {
                                nombres = senal.nombres;
                                for (String nombre : nombres)
                                {
                                    if (arraySTA.containsKey(nombre))
                                    {
                                        sta = (STA) arraySTA.get(nombre);
                                        if (contenedor.containsKey(nombre))
                                        {
                                            animacion.panel = panelBoton;
                                            animacion = (Animacion) contenedor.get(nombre);
                                            animacion.boton = animacion.getBoton();
                                            animacion.eliminarAnimacion();
                                        }
                                    }
                                }
                            }
                            else
                            {
                                nombres = senal.nombres;
                                for (String nombre : nombres)
                                {
                                    if (arraySTA.containsKey(nombre))
                                    {
                                        sta = (STA) arraySTA.get(nombre);
                                        invocarAnimacion(botonesAP.indexOf(e.getSource()), "AP");
                                    }
                                }
                            }
                        }
                    }
               }
                return true;
            }
        };
        worker.execute();
    }
            
    public void ponerNombre(final String tipo)
    {
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground() throws Exception
            {
                JButton botonNombre;
                int indiceN;
                ActionListener obtNombre;
                obtNombre = new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        accionBoton(e,tipo);
                    } 
                };

                if (tipo.equals("STA"))
                {
                    int cantSTA = botonesSTA.size();

                    for (int i=0; i<cantSTA; i++)
                    {   
                        botonNombre = null;
                        botonNombre = botonesSTA.get(i);

                        botonesSTA.remove(i);
                        indiceN = cantSTA-i;

                        botonNombre.setName("STA"+indiceN);
                        botonNombre.setText("STA"+Integer.toString(indiceN));

                        botonNombre.setHorizontalTextPosition(JButton.CENTER);
                        botonNombre.setVerticalTextPosition(JButton.BOTTOM);
                        botonNombre.addActionListener(obtNombre);

                        botonesSTA.add(i,botonNombre);
                    }
                }
                if (tipo.equals("Router"))
                {
                    int cantRouter = botonesRouter.size();

                    for (int i=0;i<cantRouter;i++)
                    {   
                        botonNombre = new JButton();

                        botonNombre = botonesRouter.get(i);

                        botonesRouter.remove(i);
                        indiceN = cantRouter-i;

                        botonNombre.setName("Router"+indiceN);
                        botonNombre.setText("R"+Integer.toString(indiceN));

                        botonNombre.setHorizontalTextPosition(JButton.CENTER);
                        botonNombre.addActionListener(obtNombre);
                        botonesRouter.add(i,botonNombre);
                    }
                }
                if (tipo.equals("AP"))
                {
                    int cantAP = botonesAP.size();

                    for (int i=0;i<cantAP;i++)
                    {   
                        botonNombre = new JButton();

                        botonNombre = botonesAP.get(i);

                        botonesAP.remove(i);
                        indiceN = cantAP -i;

                        botonNombre.setName("AP"+indiceN);
                        botonNombre.setText("AP"+Integer.toString(indiceN));

                        botonNombre.setHorizontalTextPosition(JButton.CENTER);
                        botonNombre.addActionListener(obtNombre);

                        botonNombre.setHorizontalTextPosition(JButton.CENTER);
                        botonNombre.addActionListener(obtNombre);
                        botonesAP.add(i,botonNombre);
                    }
                }
                return true;
            }

            @Override
            protected void done()
            {
                panelBoton.revalidate();
                panelBoton.repaint();
           }

        };
        worker.execute();
    }
         
     public void inicializarBotones(final int cant)
    {
        SwingWorker <Boolean, Void> worker = new SwingWorker<Boolean, Void>()
        {
            @Override
            protected Boolean doInBackground() throws Exception
            {
                int posx = 0, posy = 0,rx = 0,ry = 0;
                x = panelBoton.getX();
                y = panelBoton.getY();

                tamPanelx = panelBoton.getWidth();
                tamPanely = panelBoton.getHeight();
                ImageIcon icono;
                cargado = false;

                for(int i=0;i<cant;i++)
                {
                    posx = (int)(Math.random()*(tamPanelx-300)-1);
                    posy = (int)(Math.random()*(200-y)-1);

                    //Creamos los botones

                    JButton button = new JButton();
                    mover = new MoverBoton();

                    circulo = new DrawCanvas(posx, posy);
                    rx = posx + circulo.r1/2;
                    ry = posy + circulo.r2/2;

                    button.setActionCommand(""+i);

                    button.setOpaque(false);
                    button.setBorder(null);
                    button.setBorderPainted(false);
                    button.setBackground(null);
                    button.setContentAreaFilled(false);

                    if (tipoBoton.equals("STA"))
                    {
                        icono = new ImageIcon("src/Imagenes/STAoriginal.png");
                        button.setIcon(icono);
                        button.setPreferredSize(new Dimension(40,59));
                        Dimension size = button.getPreferredSize();

                        button.setBounds(rx-size.width/2, ry-size.height/2,size.width, size.height);
                        button.addMouseListener(new MouseAdapter()
                        {
                            @Override
                            public void mouseClicked(MouseEvent e) 
                            {
                                setEstado("STA", e);
                            }
                        });

                        mover.makeDraggable(button);
                        arrayCirculoSTA.add(i, circulo);
                        botonesSTA.add(i,button);

                        panelBoton.add(botonesSTA.get(i));
                        panelBoton.add(arrayCirculoSTA.get(i));
                    }
                    if (tipoBoton.equals("Router")||tipoBoton.equals("AP"))
                    {
                        icono = new ImageIcon("src/Imagenes/Router.png");
                        button.setIcon(icono);
                        button.setPreferredSize(new Dimension(60,50));
                        Dimension size = button.getPreferredSize();

                        button.setBounds(rx-size.width/2, ry-size.height/2,size.width, size.height);
                        configR = new ConfigRouter(null, true);
                        configAP = new ConfigAP(null, true);

                        button.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) 
                            {
                                if (tipoBoton.equals("Router")) setEstado("Router",e);
                                if (tipoBoton.equals("AP")) setEstado("AP",e);
                            }
                        });
                        mover.makeDraggable(button);
                        if (tipoBoton.equals("Router"))
                        {
                            arrayCirculoRouter.add(i, circulo);
                            botonesRouter.add(i,button);
                            panelBoton.add(botonesRouter.get(i));
                            panelBoton.add(arrayCirculoRouter.get(i));
                        }
                        if (tipoBoton.equals("AP"))
                        {
                            arrayCirculoAP.add(i, circulo);
                            botonesAP.add(i,button);
                            panelBoton.add(botonesAP.get(i));
                            panelBoton.add(arrayCirculoAP.get(i));
                        }
                    }
                }

                if (tipoBoton.equals("Router")) ponerNombre("Router");

                if (tipoBoton.equals("AP")) ponerNombre("AP");

                if (tipoBoton.equals("STA")) ponerNombre("STA");
                return true;
            }

            @Override
            protected void done()
            {
                panelBoton.setVisible(true);
                panelBoton.revalidate();
                panelBoton.repaint();
           }
        };
        worker.execute();   
    }

     public void cargarRouter()
     {
         tamPanelx = panelBoton.getWidth();
         tamPanely = panelBoton.getHeight();
        
         botonesRouter.clear();
         arrayCirculoRouter.clear();
         
         int posx, posy,rx,ry;
         
         ActionListener obtNombre;
         obtNombre = new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 accionBoton(e, "Router");
             }
         };
         
         for (Object key : arrayRouter.keySet())
        {
            router = (Router) arrayRouter.get(key);
            posx = router.getPosx();
            posy = router.getPosy();

            //Creamos los botones

            JButton button = new JButton();
            mover = new MoverBoton();
            button.setPreferredSize(new Dimension(60,50));

            rx = posx + button.getPreferredSize().width/2 - router.getRx()/2;
            ry = posy + button.getPreferredSize().height/2 - router.getRy()/2;

            circulo = new DrawCanvas(rx, ry);
            
            button.setActionCommand("");

            button.setOpaque(false);
            button.setBorder(null);
            button.setBorderPainted(false);
            button.setBackground(null);
            button.setContentAreaFilled(false);

            ImageIcon icono = new ImageIcon("src/Imagenes/Router.png");
            button.setIcon(icono);
            
            Dimension size = button.getPreferredSize();

            button.setBounds(posx, posy,size.width, size.height);
            configR = new ConfigRouter(null, true);

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    setEstado("Router",e);
                    if ((e.getClickCount()==1) && (e.getButton()!=3))
                    {   //si es un solo click pero no con el botón derecho
                        Consola.mostrarTexto("");
                        for (String statusRouter1 : statusRouter) {
                            Consola.mostrarTexto(statusRouter1);
                        }
                    }
                    if (e.getClickCount()==2)
                    {
                        configR.arrayCirculoRouter = arrayCirculoRouter;
                        configR.configRouterDialog.setVisible(true);
                        Consola.mostrarTexto("");
                    }
                    if (e.getButton() == 3)
                    {
                        // if right click
                        addPopUp ("Router",e);
                    }
                }
            });
            button.setName(router.getNombre());
            button.setText("R"+router.getNombre().substring(6));
            button.setHorizontalTextPosition(JButton.CENTER);
            button.addActionListener(obtNombre);
            mover.makeDraggable(button);
            arrayCirculoRouter.add(circulo);
            botonesRouter.add(button);
        }
         
         for (int i=0;i<botonesRouter.size();i++)
         {
             panelBoton.add(botonesRouter.get(i));
             panelBoton.add(arrayCirculoRouter.get(i));
         }

         panelBoton.setVisible(true);
         panelBoton.revalidate();
         panelBoton.repaint();
     }
     
     public void cargarAP()
     {
         tamPanelx = panelBoton.getWidth();
         tamPanely = panelBoton.getHeight();
        
         botonesAP.clear();
         arrayCirculoAP.clear();
         
         int posx, posy,rx,ry;
         
         ActionListener obtNombre;
         obtNombre = new ActionListener()
         {
             @Override
             public void actionPerformed(ActionEvent e)
             {
                 accionBoton(e, "AP");
             }
         };
         
        for (Object key : arrayAP.keySet())
        {
            ap = (AccessPoint) arrayAP.get(key);
            posx = ap.getPosx();
            posy = ap.getPosy();

            //Creamos los botones

            JButton button = new JButton();
            mover = new MoverBoton();
            button.setPreferredSize(new Dimension(60,50));

            rx = posx + button.getPreferredSize().width/2 - ap.getRx()/2;
            ry = posy + button.getPreferredSize().height/2 - ap.getRy()/2;

            circulo = new DrawCanvas(rx, ry);
            
            button.setActionCommand("");

            button.setOpaque(false);
            button.setBorder(null);
            button.setBorderPainted(false);
            button.setBackground(null);
            button.setContentAreaFilled(false);

            ImageIcon icono = new ImageIcon("src/Imagenes/Router.png");
            button.setIcon(icono);
            
            Dimension size = button.getPreferredSize();

            button.setBounds(posx, posy,size.width, size.height);
            configAP = new ConfigAP(null, true);

            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) 
                {
                    setEstado("AP",e);
                    if ((e.getClickCount()==1) && (e.getButton()!=3))
                    {   //si es un solo click pero no con el botón derecho
                        Consola.mostrarTexto("");
                        for (String statusAP1 : statusAP) {
                            Consola.mostrarTexto(statusAP1);
                        }
                    }
                    if (e.getClickCount()==2)
                    {
                        configAP.arrayCirculoAP = arrayCirculoAP;
                        configAP.configAPDialog.setVisible(true);
                        Consola.mostrarTexto("");
                    }
                    if (e.getButton() == 3)
                    {
                        // if right click
                        addPopUp ("AP",e);
                    }
                }
            });
            button.setName(ap.getNombre());
            button.setText(ap.getNombre());
            button.setHorizontalTextPosition(JButton.CENTER);
            button.addActionListener(obtNombre);
            mover.makeDraggable(button);
            arrayCirculoAP.add(circulo);
            botonesAP.add(button);
        }
         
         for (int i=0;i<botonesAP.size();i++)
         {
             panelBoton.add(botonesAP.get(i));
             panelBoton.add(arrayCirculoAP.get(i));
         }

         panelBoton.setVisible(true);
         panelBoton.revalidate();
         panelBoton.repaint();
     }
     
     public void cargarSTA()
     {
         boolean papa = false;
         STA aux = new STA();
        sta = new STA();
        tamPanelx = panelBoton.getWidth();
        tamPanely = panelBoton.getHeight();

        botonesSTA.clear();
        arrayCirculoSTA.clear();
        contenedor = new HashMap();
        animacion = new Animacion();

        cargado = true;

        int posx, posy,rx,ry;

        String origen;

        ActionListener obtNombre;
        obtNombre = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                accionBoton(e, "STA");
            }
        };

       for (Object key : arraySTA.keySet())
       {
           sta = (STA) arraySTA.get(key);
           posx = sta.getPosx();
           posy = sta.getPosy();
           papa = sta.isNodoPadre();
           System.out.println("papa: "+papa);

           //Creamos los botones

           JButton button = new JButton();
           mover = new MoverBoton();
           button.setPreferredSize(new Dimension(40,59));

           rx = posx + button.getPreferredSize().width/2 - sta.getRx()/2;
           ry = posy + button.getPreferredSize().height/2 - sta.getRy()/2;

           circulo = new DrawCanvas(rx, ry);

           button.setActionCommand("");

           button.setOpaque(false);
           button.setBorder(null);
           button.setBorderPainted(false);
           button.setBackground(null);
           button.setContentAreaFilled(false);

           ImageIcon icono = new ImageIcon("src/Imagenes/STAoriginal.png");
           button.setIcon(icono);

           Dimension size = button.getPreferredSize();

           button.setBounds(posx, posy,size.width, size.height);
           configAP = new ConfigAP(null, true);

           staInfra = new ConfigSTAInfra(null, true);
           adhoc = new ConfigSTAAdhoc(null, true);
           crear = new CrearSTAAdhoc(null, true);

           button.addMouseListener(new MouseAdapter()
           {
               @Override
               public void mouseClicked(MouseEvent e) 
               {
                   setEstado("STA",e);
                   if ((e.getClickCount()==1) && (e.getButton()!=3))
                   {   //si es un solo click pero no con el botón derecho
                       Consola.mostrarTexto("");
                       for (String statusSTA1 : statusSTA)
                       {
                           Consola.mostrarTexto(statusSTA1);
                       }
                   }
                   if (e.getClickCount()==2)
                   {
                       if (tipoRed.equals("Infraestructura"))
                       {
                           staInfra.configSTAInfraDialog.setVisible(true);
                           Consola.mostrarTexto("");
                       }
                       else
                       {
                           adhoc.configAdhocDialog.setVisible(true);
                           Consola.mostrarTexto("");
                       }
                   }
                   if (e.getButton() == 3)
                   {
                       // if right click
                       addPopUp ("STA",e);
                   }
               }
           });
           button.setName(sta.getID());
           button.setText(sta.getID());

           button.setHorizontalTextPosition(JButton.CENTER);
           button.setVerticalTextPosition(JButton.BOTTOM);
           button.addActionListener(obtNombre);
           mover.makeDraggable(button);
           arrayCirculoSTA.add(circulo);
           botonesSTA.add(button);

           origen = sta.getOrigenSSID();
           ssid = sta.getSSID();
          // System.out.println("who: "+sta.getID()+" padre: "+sta.isNodoPadre());
          
           setConexiones(origen);
           if (tipoRed.equals("Infraestructura")) contenedor.put(sta.getID(),new Animacion());
           else if (!sta.isNodoPadre()) contenedor.put(sta.getID(),new Animacion());
       }
       
       if (tipoRed.equals("Infraestructura"))
       {
            for (int i=0;i<botonesSTA.size();i++)
            {
                panelBoton.add(botonesSTA.get(i));
                panelBoton.add(arrayCirculoSTA.get(i));
                sta = (STA) arraySTA.get(botonesSTA.get(i).getName());
                invocarAnimacion(i, "STA");
                panelBoton.add(animacion.getBoton());
            }
       }
       else
       {
           for (int i=0;i<botonesSTA.size();i++)
            {
                panelBoton.add(botonesSTA.get(i));
                panelBoton.add(arrayCirculoSTA.get(i));
                sta = (STA) arraySTA.get(botonesSTA.get(i).getName());
                if (!sta.isNodoPadre())
                {
                    invocarAnimacion(i, "STA");
                    panelBoton.add(animacion.getBoton());
                }
            }
       }
        panelBoton.setVisible(true);
        panelBoton.revalidate();
        panelBoton.repaint();
     }
     
     public void setConexiones(String origen)
     {
         Router aux = new Router();
         AccessPoint auxAP = new AccessPoint();
         STA auxSTA = new STA();
         STA padreSTA = new STA();
         if (tipoRed.equals("Infraestructura"))
         {
             if (origen.equals("Router"))
            {
                for (Object key : arrayRouter.keySet())
                {
                    aux = (Router) arrayRouter.get(key);
                    if (ssid.equals(aux.getSSID()))
                    {
                        router = (Router) arrayRouter.get(key);
                        break;
                    }
                }
                router.setEstacion(sta.getID(), sta);
            }
            if (origen.equals("AP"))
            {
                for (Object key : arrayAP.keySet())
                {
                    auxAP = (AccessPoint) arrayAP.get(key);
                    if (ssid.equals(auxAP.getSSID()))
                    {
                        ap = (AccessPoint) arrayAP.get(key);
                        break;
                    }
                }
                ap.setEstacion(sta.getID(), sta);
            }
         }
         else
         {
            if (arraySTA.containsKey(origen))
            {
                padreSTA = (STA) arraySTA.get(origen);
                //System.out.println("who is padre: "+padreSTA.getID());
                padreSTA.setNodoPadre(true);
                System.out.println("id: "+sta.getID()+ " padre: "+sta.isNodoPadre());
                arraySTA.put(padreSTA.getID(), padreSTA);
                if (!sta.isNodoPadre())
                {
                    for (Object key : arraySTA.keySet())
                   {
                       auxSTA = (STA) arraySTA.get(key);
                       if (ssid.equals(auxSTA.getSSID())&&origen.equals(auxSTA.getID()))
                       {
                           auxSTA.setNombres(sta.getID());
                          //System.out.println("por aqui: "+auxSTA.getNombres().size()+"padre: "+origen);
                           
                           redesAdHoc.put(auxSTA.getID(), auxSTA);
                           break;
                       }
                   }         
                }
                else
                {
                    auxSTA.setNombres(sta.getID());
                    System.out.println("yuju: "+auxSTA.getNombres().size());
                    redesAdHoc.put(sta.getID(), sta);
                }
            }
         }
     }
}