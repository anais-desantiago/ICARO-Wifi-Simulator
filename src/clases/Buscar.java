package clases;

import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class Buscar
{
    JPanel panel = new JPanel();
    PingPanel pingPanel = new PingPanel();
    SignalRange senal = new SignalRange();
    Ping ping, ping1, ping2;

    Revisiones revisar = new Revisiones();
    Resetear reset = new Resetear();
    VerificarIP verIP = new VerificarIP();
    
    HashMap routers = new HashMap();
    HashMap stas =  new HashMap();
    HashMap aps = new HashMap();
    
    JDialog pingDialog;
    JButton pingB, cancelar, boton;
    String ip="", ip0="", error="", tipoRed="", nodo="", nombre="", ssid="", origen="", papa="";
    int por100 = 0, x1 = 0,y1 =0 ,x2= 0,y2=0, x3=0,y3=0, r1_x=0, r1_y=0, r2_x=0,r2_y=0,r3_x=0,r3_y=0;
    boolean encontrado = false, noise = false, padre = false, continuar = false, nodosAlc = false, play = false;
    
    STA sta = new STA();
    Router router = new Router();
    AccessPoint ap = new AccessPoint();
    
    Router aux = new Router();
    AccessPoint auxAP = new AccessPoint();
    STA auxSTA = new STA();
    STA auxSTA1 = new STA();
    
    public void setIPFuente(String ip0, HashMap auxR, HashMap auxS, HashMap auxAP)
    {
        this.ip0 = ip0;
        this.routers = auxR;
        this.aps = auxAP;
        this.stas = auxS;
        
        if (tipoRed.equals("Infraestructura"))
        {
            if (nodo.equals("STA"))
            {
                if (stas.containsKey(nombre))
                {
                    sta = (STA) stas.get(nombre);
                    if (sta.getIP().equals(ip0))
                    {
                        ssid = sta.getSSID();
                        origen = sta.getOrigenSSID();
                        senal.x1 = x1 = sta.getPosx();
                        senal.y1 = y1 = sta.getPosy();
                        senal.r1_x = r1_x = sta.getRx();
                        senal.r1_y = r1_y = sta.getRy();
                    }
                }
            }
            if (nodo.equals("Router"))
            {
                if (routers.containsKey(nombre))
                {
                    router = (Router) routers.get(nombre);
                    if (router.getIP().equals(ip0))
                    {
                        ssid = router.getSSID();
                        senal.x1 = x1 = router.getPosx();
                        senal.y1 = y1 = router.getPosy();
                        senal.r1_x = r1_x = router.getRx();
                        senal.r1_y = r1_y = router.getRy();
                    }
                }
            }
            if (nodo.equals("AP"))
            {
                if (aps.containsKey(nombre))
                {
                    ap = (AccessPoint) aps.get(nombre);
                    if (ap.getIP().equals(ip0))
                    {
                        ssid = ap.getSSID();
                        senal.x1 = x1 = ap.getPosx();
                        senal.y1 = y1 = ap.getPosy();
                        senal.r1_x = r1_x = ap.getRx();
                        senal.r1_y = r1_y = ap.getRy();
                    }
                }
            }
        }
        else
        {
            if (stas.containsKey(nombre))
            {
                origen = "Adhoc";
                sta = (STA) stas.get(nombre);
                if (sta.getIP().equals(ip0))
                {
                    papa = sta.getOrigenSSID();
                    ssid = sta.getSSID();
                    senal.x1 = x1 = sta.getPosx();
                    senal.y1 = y1 = sta.getPosy();
                    senal.r1_x = r1_x = sta.getRx();
                    senal.r1_y = r1_y = sta.getRy();
                }
            }
            if (papa.equals(nombre)) auxSTA = sta;
            else auxSTA = (STA) stas.get(papa);
        }
    }
    
    public void buscarIP()
    {
        STA auxS;
        String sta1;
        int cant = 0, sta = 0, ap = 0;
     
        if (tipoRed.equals("Infraestructura"))
        {
            if (nodo.equals("STA"))
            {
                //obtener las coordenadas del boton
                if (origen.equals("Router"))
                {
                    for (Object key : routers.keySet())
                    {
                        aux = (Router) routers.get(key);
                        if (ssid.equals(aux.getSSID()))
                        {
                            senal.x2 = x2 = aux.getPosx();
                            senal.y2 = y2 = aux.getPosy();
                            senal.r2_x = r2_x = aux.getRx();
                            senal.r2_y = r2_y = aux.getRy();
                            break;
                        }
                    }
                    if (!aux.getIP().equals(ip))
                    {
                        for (Object key: aux.getEstacion().keySet())
                        {
                            auxSTA = (STA) aux.getEstacion().get(key);
                            if (auxSTA.getIP().equals(ip))
                            {
                                x3 = auxSTA.getPosx();
                                y3 = auxSTA.getPosy();
                                r3_x = auxSTA.getRx();
                                r3_y = auxSTA.getRy();
                                encontrado = true;
                                break;
                            }
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
                            senal.x2 = x2 = auxAP.getPosx();
                            senal.y2 = y2 = auxAP.getPosy();
                            senal.r2_x = r2_x = auxAP.getRx();
                            senal.r2_y = r2_y =auxAP.getRy();
                            break;
                        }
                    }

                    if (!auxAP.getIP().equals(ip))
                    {
                        for (Object key: auxAP.getEstacion().keySet())
                        {
                            auxSTA = (STA) auxAP.getEstacion().get(key);
                            if (auxSTA.getIP().equals(ip))
                            {
                                encontrado = true;
                                x3 = auxSTA.getPosx();
                                y3 = auxSTA.getPosy();
                                r3_x = auxSTA.getRx();
                                r3_y = auxSTA.getRy();
                                break;
                            }
                        }
                    }
                }
            }
            
            if (nodo.equals("Router"))
            {
                aux = (Router) routers.get(nombre);
                for (Object key: aux.getEstacion().keySet())
                {
                    auxSTA = (STA) aux.getEstacion().get(key);
                    
                    if (auxSTA.getIP().equals(ip))
                    {
                        senal.x2 = x2 = auxSTA.getPosx();
                        senal.y2 = y2 = auxSTA.getPosy();
                        senal.r2_x = r2_x = auxSTA.getRx();
                        senal.r2_y = r2_y = auxSTA.getRy();
                        encontrado = true;
                        break;
                    }
                }
            }
            if (nodo.equals("AP"))
            {
                auxAP = (AccessPoint) aps.get(nombre);
                for (Object key: auxAP.getEstacion().keySet())
                {
                    auxSTA = (STA) auxAP.getEstacion().get(key);
                    if (auxSTA.getIP().equals(ip))
                    {
                        senal.x2 = x2 = auxSTA.getPosx();
                        senal.y2 = y2 = auxSTA.getPosy();
                        senal.r2_x = r2_x = auxSTA.getRx();
                        senal.r2_y = r2_y = auxSTA.getRy();
                        encontrado = true;
                        break;
                    }
                }
            }
        }
        else
        {
            for (Object key : stas.keySet())
            {
                auxSTA = (STA) stas.get(key);
                if (ssid.equals(auxSTA.getSSID())&& auxSTA.isNodoPadre())
                {
                    System.out.println("padre: "+auxSTA.getID()+ "tam: "+auxSTA.getNombres());
                    senal.x2 = x2 = auxSTA.getPosx();
                    senal.y2 = y2 = auxSTA.getPosy();
                    senal.r2_x = r2_x = auxSTA.getRx();
                    senal.r2_y = r2_y = auxSTA.getRy();
                    break;
                }
            }
            
            if (!auxSTA.getIP().equals(ip)&&!play)
            {
               for (Object key: auxSTA.getNombres().keySet())
                {
                    sta1 = (String) auxSTA.getNombres().get(key);
                    
                    auxSTA1 = (STA) stas.get(sta1);
                    
                    if (auxSTA1.getIP().equals(ip))
                    {
                        encontrado = true;
                        x3 = auxSTA1.getPosx();
                        y3 = auxSTA1.getPosy();
                        r3_x = auxSTA1.getRx();
                        r3_y = auxSTA1.getRy();
                        break;
                    }
                }
            }
        }
        
        senal.detAlcance("",""); 
        
        if ((x3!=0) ||(y3!=0))
        {
            senal.x1 = x2;
            senal.y1 = y2;
            senal.r1_x = r2_x;
            senal.r1_y = r2_y;
            
            senal.x2 = x3;
            senal.y2 = y3;
            senal.r2_x = r3_x;
            senal.r2_y = r3_y;
            senal.detAlcance("","");
            continuar = true;
        }
        
        if (!senal.junto)
        {
            //no se puede establecer la comunicacion con el nodo central
            nodosAlc = false;
            error = "Nodo inalcanzable";
        }
        else
        {
            nodosAlc = true;
            if (tipoRed.equals("Infraestructura"))
            {
                if (!routers.isEmpty())
                {
                    for (Object key : routers.keySet())
                    {
                        aux = (Router) routers.get(key);
                        if (ip.equals(aux.getIP())&&(ssid.equals(aux.getSSID()))) cant++;
                    }
                }
            
                if (!aps.isEmpty())
                {
                    for (Object key : aps.keySet())
                    {
                        auxAP = (AccessPoint) aps.get(key);
                        if (ip.equals(auxAP.getIP())&&(ssid.equals(auxAP.getSSID()))) ap++;
                    }
                }
            }
            for (Object key : stas.keySet())
            {
                auxS = (STA) stas.get(key);
                if (ip.equals(auxS.getIP())&&(ssid.equals(auxS.getSSID()))) cant++;
            }

            if ((cant==1) || (sta==1) ||(ap==1))
            {
                encontrado = true;
                error = "Sin error";
            }
            if ((cant==0)&&(sta==0)&&(ap==0))
            {
                encontrado = false;
                error = "No se encontró la dirección";
            }            
        }
    }
}
