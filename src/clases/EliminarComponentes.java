package clases;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;

public class EliminarComponentes
{
    /*Esta clase permite eliminar routers, aps y/o stas*/
    JButton boton = new JButton();
    
    ArrayList <String> nomSTAs = new ArrayList<>();
    
    HashMap routers = new HashMap();
    HashMap aps = new HashMap();
    HashMap stas = new HashMap();
    HashMap redes = new HashMap();
    
    ArrayList <JButton> botonesRouter = new ArrayList<>();
    ArrayList <JButton> botonesAP = new ArrayList<>();
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    ArrayList <JButton> botonesObst = new ArrayList<>();
    
    STA sta = new STA();
    boolean padre = false, found;    
    
    public void eliminar (String tipoRed, String tipoBoton)
    {
        Router aux = new Router();
        AccessPoint auxAP = new AccessPoint();
        HashMap estaciones = new HashMap();
        String nombre, origen = "", ssid = "";
        nombre = boton.getName();
        
        STA auxSTA = new STA();
        
        nomSTAs.clear();
        found = false;
        
        if (tipoRed.equals("Infraestructura"))
        {
            if (tipoBoton.equals("STA"))
            {
                if (stas.containsKey(nombre))
                {
                    sta = (STA) stas.get(nombre);
                    ssid = sta.getSSID();
                    origen = sta.getOrigenSSID();
                    found = true;
                }
                else found = false;
                
                if (origen.equals("Router"))
                {
                    for (Object key : routers.keySet())
                    {
                        aux = (Router) routers.get(key);
                        if (ssid.equals(aux.getSSID()))
                        {
                            estaciones = aux.getEstacion();
                            if (estaciones.containsKey(nombre)) estaciones.remove(nombre);
                        }
                    }
                }
                if (origen.equals("AP"))
                {
                    for (Object key : aps.keySet())
                    {
                        auxAP = (AccessPoint) aps.get(key);
                        if (ssid.equals(auxAP.getSSID()))
                        {
                            estaciones = auxAP.getEstacion();
                            if (estaciones.containsKey(nombre)) estaciones.remove(nombre);
                        }
                    }
                }
                
                if (!stas.isEmpty() && found) stas.remove(nombre);
            }
            if (tipoBoton.equals("Router"))
            {
                if (routers.containsKey(nombre))
                {
                    aux = (Router) routers.get(nombre);
                    estaciones = aux.getEstacion();
                    for (Object key : estaciones.keySet())
                    {
                        sta = (STA) estaciones.get(key);
                        nomSTAs.add(sta.getID());
                    }
                    aux.getEstacion().clear();
                    routers.remove(nombre);
                }
            }
            if (tipoBoton.equals("AP"))
            {
                if (aps.containsKey(nombre))
                {
                    auxAP = (AccessPoint) aps.get(nombre);
                    estaciones = aux.getEstacion();
                    for (Object key : estaciones.keySet())
                    {
                        sta = (STA) estaciones.get(key);
                        nomSTAs.add(sta.getID());
                    }
                    auxAP.getEstacion().clear();
                    aps.remove(nombre);
                }
            }
        }
        else
        {
            /*Modo Ad-Hoc*/
            if (stas.containsKey(nombre))
            {
                sta = (STA) stas.get(nombre);
                ssid = sta.getSSID();
                padre = sta.isNodoPadre();
                found = true;
            }
            else found = false;
            
            if (!stas.isEmpty())
            {
                if (padre)
                {
                    for (Object key : stas.keySet())
                    {
                        auxSTA = (STA) stas.get(key);
                        
                        if (ssid.equals(auxSTA.getSSID())) nomSTAs.add(auxSTA.getID());
                    }
                    redes.remove(auxSTA);
                }
                if(found) stas.remove(nombre);
            }
        }
    }
}
