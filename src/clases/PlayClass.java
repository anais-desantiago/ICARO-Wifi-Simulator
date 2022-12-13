package clases;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PlayClass
{
    JPanel panel = new JPanel();  
    JButton boton = new JButton();
    
    HashMap arrayRouter = new HashMap();
    HashMap arraySTA = new HashMap();
    HashMap arrayAP = new HashMap();
    
    VerificarIP ip = new VerificarIP();
    Buscar buscar = new Buscar();
    
    String ipFuente, ipCentral,ipDestino, tipoRed="", auxFuente, auxDestino, ssid, nodo;
    int indice, saltos = 10,sent = 0, rec = 0, x1, x2, x3, y1,y2,y3;
    long time_start, time_end;
    boolean continuar = false;
    
    STA sta = new STA();
    STA sta1 = new STA();
    Router router = new Router();
    AccessPoint ap = new AccessPoint();
    Animacion animacion, animacion2;
    
    public void selectFuente(HashMap arrayRouter, HashMap arraySTA, HashMap arrayAP)
    {
        Random generator = new Random();
        Object[] values = arraySTA.values().toArray();
        Object randomValue = values[generator.nextInt(values.length)];
        time_start = System.currentTimeMillis();
        sent = rec = 0;
        this.arrayRouter = arrayRouter;
        this.arraySTA = arraySTA;
        this.arrayAP = arrayAP;
        
        /*int i = (int)(Math.random()*arraySTA.size()-1);
        
        sta = (STA) arraySTA.get("STA"+(i+1));*/
        sta = (STA) randomValue;
        System.out.println("who: "+sta.getID());
        buscar.nombre = sta.getID();
        ipFuente = sta.getIP();
        ssid = sta.getSSID();
        
        buscar.tipoRed = tipoRed;
        buscar.nodo = "STA";
        //bu
        
        if (tipoRed.equals("Infraestructura")) buscar.setIPFuente(ipFuente, arrayRouter, arraySTA, arrayAP);
        else buscar.setIPFuente(ipFuente, null, arraySTA, null);
        buscar.buscarIP();
        
        nodo = buscar.origen;
        System.out.println(buscar.auxSTA.getID());
        sta1 = buscar.auxSTA;
        router = buscar.aux;
        ap = buscar.auxAP;

        //System.out.println("quien: "+sta1.getID() +"ip: "+sta1.getIP());
        selectDestino();
    }
    
    public void selectDestino()
    {
        System.out.println("quien: "+sta1.getID() +"ip: "+sta1.getIP());
        int i;
        HashMap estaciones = new HashMap();
        
        Random generator = new Random();
        Object[] values;
        Object randomValue;
        STA stas = new STA();
        System.out.println(sta1.getNombres().size());
        
        if (nodo.equals("Router")) {System.out.println("Router");estaciones = router.getEstacion();}
        if (nodo.equals("AP")) {System.out.println("AP");estaciones = ap.getEstacion();}
        if (nodo.equals("Adhoc")) {System.out.println("adhoc");estaciones = sta1.getNombres();}
        
        System.out.println(estaciones);
        values = estaciones.values().toArray();
        do
        {
           randomValue = values[generator.nextInt(values.length)];
            /*i = (int)(Math.random()*estaciones.size());
            System.out.println("i: "+i);
            stas = (STA) arraySTA.get("STA"+i);
            System.out.println(stas.getID());*/
            if (tipoRed.equals("Infraestructura")) stas = (STA) randomValue;
            else stas = (STA) arraySTA.get(randomValue);
           System.out.println(stas.getID());
        }while(stas.getIP().equals(ipFuente));
        
        ipDestino = stas.getIP();
        buscar.ip = ipDestino;
        buscar.buscarIP();
        continuar = buscar.continuar;
        x1 = buscar.x1;
        y1 = buscar.y1;
        x2 = buscar.x2;
        y2 = buscar.y2;
        x3 = buscar.x3;
        y3 = buscar.y3;
    }
    
    public void animarPaquete(int x1, int x2, int y1, int y2)
    {
        animacion = new Animacion();
        animacion.panel = panel;
        animacion.setX1(x1);
        animacion.setX2(x2);
        animacion.setY1(y1);
        animacion.setY2(y2);
        animacion.setTipoAnimacion("Paquetes");
        animacion.mover();
    }
    
    public void continuarPaquete(int x2,int x3, int y2,int y3)
    {
        animacion2 = new Animacion();
        animacion2.panel = panel;
        animacion2.setX1(x2);
        animacion2.setX2(x3);
        animacion2.setY1(y2);
        animacion2.setY2(y3);
        animacion2.setTipoAnimacion("Paquetes");
        animacion2.mover();
    }
    
    public void enviarPaquete()
    {
        String msj1 = "Enviando desde: "+ipFuente +" hasta: "+ipDestino;
        Consola.mostrarTexto(msj1);
        String msj;
        
        animarPaquete(x1,x2,y1,y2);
        try {
            Thread.sleep(2000);
            animacion.eliminarAnimacion();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (continuar)
        {
            continuarPaquete(x2,x3,y2,y3);
            try {
                Thread.sleep(2000);
                animacion2.eliminarAnimacion();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        sent++;
        time_end = System.currentTimeMillis();
        msj = (sent)+" "+ (time_end-time_start)+"ms "+ipDestino;
        Consola.mostrarTexto(msj);
    }
    
    public void recibirPaquete()
    {
        if (continuar)
        {
            continuarPaquete(x3,x2,y3,y2);
            try {
                Thread.sleep(1500);
                animacion2.eliminarAnimacion();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        animarPaquete(x2,x1,y2,y1);
        try {
            Thread.sleep(1500);
            animacion.eliminarAnimacion();
            String msj1 = "Respondiendo desde: "+ipDestino +" a: "+ipFuente;
            String msj;
            Consola.mostrarTexto(msj1);
            rec++;

            time_end = System.currentTimeMillis();
            msj =(time_end-time_start)+"ms "+ipFuente;
            Consola.mostrarTexto(msj);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
