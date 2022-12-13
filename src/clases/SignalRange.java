package clases;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;

public class SignalRange
{
    /*Esta clase solamente será utilizada por las STAs para conectarse entre sí (ad-hoc) o con un router y/o ap (infraestructura)
    Permite determinar si existen routers/AP o STAs en el rango
    Además permite añadir el ruido*/
   
    int ruido;// se escogera un valor aleatorio de ruido y en un enlace aleatorio
    int x1,y1,x2,y2,r1_x,r1_y,r2_x,r2_y, dx_sum, dy_sum, p1, p2;
    final int [] dosGhz = {2412,2417,2422,2427,2432,2437,2442,2447,2452,2457,2462,2467,2472,2484};
    final int [] cincoGhz = {5170,5180,5190,5200,5210,5220,5230,5240,5260,5280,5300,5320};
    double signal,alcance,onda, snr,d;
    boolean noise = false, errorTrans = false, junto = false;
    
    HashMap routers = new HashMap();
    HashMap aps = new HashMap();
    HashMap stas = new HashMap();
    HashMap redes = new HashMap();
    
    ArrayList <String> indicesR = new ArrayList<>();
    ArrayList <String> indicesAP = new ArrayList<>();
    ArrayList <String> indicesSTA = new ArrayList<>();
    ArrayList <String> nombres = new ArrayList<>();
    
    ArrayList <AddBotones.DrawCanvas> arrayCirculoRouter = new ArrayList<>();
    ArrayList <AddBotones.DrawCanvas> arrayCirculoAP = new ArrayList<>();
    ArrayList <AddBotones.DrawCanvas> arrayCirculoSTA = new ArrayList<>();
    
    ArrayList <JButton> botonesRouter = new ArrayList<>();
    ArrayList <JButton> botonesAP = new ArrayList<>();
    ArrayList <JButton> botonesSTA = new ArrayList<>();
    
    String ssid;
    STA sta = new STA();
    Router router = new Router();
    AccessPoint ap = new AccessPoint();
    
    public boolean checkAlcance(int indice, String tipoRed)
    {
        indicesR.clear();
        indicesAP.clear();
        indicesSTA.clear();
        junto = false;
        if (tipoRed.equals("Infraestructura"))
        {
            x1 = botonesSTA.get(indice).getLocation().x+botonesSTA.get(indice).getPreferredSize().width/2;
            y1 = botonesSTA.get(indice).getLocation().y+botonesSTA.get(indice).getPreferredSize().height/2;
            r1_x = arrayCirculoSTA.get(indice).r1;
            r1_y = arrayCirculoSTA.get(indice).r2;
            
            for (int i = 0; i<botonesAP.size(); i++)
            {
                if (aps.containsKey(botonesAP.get(i).getName()))
                {
                    x2 = botonesAP.get(i).getLocation().x+botonesAP.get(i).getPreferredSize().width/2;
                    y2 = botonesAP.get(i).getLocation().y+botonesAP.get(i).getPreferredSize().height/2;
                    r2_x = arrayCirculoAP.get(i).r1;
                    r2_y = arrayCirculoAP.get(i).r2;
                    detAlcance("AP",botonesAP.get(i).getName());
                }
            }
            for (int i = 0; i<botonesRouter.size(); i++)
            {
                if (routers.containsKey(botonesRouter.get(i).getName()))
                {
                    x2 = botonesRouter.get(i).getLocation().x+botonesRouter.get(i).getPreferredSize().width/2;
                    y2 = botonesRouter.get(i).getLocation().y+botonesRouter.get(i).getPreferredSize().height/2;
                    r2_x = arrayCirculoRouter.get(i).r1;
                    r2_y = arrayCirculoRouter.get(i).r2;
                    detAlcance("Router",botonesRouter.get(i).getName());
                }
            }
            if(indicesR.isEmpty() && indicesAP.isEmpty()) return false; //no se encontraron redes en el alcance
        }
        else
        {
            x1 = botonesSTA.get(indice).getLocation().x;
            y1 = botonesSTA.get(indice).getLocation().y;
            r1_x = arrayCirculoSTA.get(indice).r1;
            r1_y = arrayCirculoSTA.get(indice).r2;
            
            for(int i = 0;i<botonesSTA.size();i++)
            {
                if (redes.containsKey(botonesSTA.get(i).getName()))
                {
                    x2 = botonesSTA.get(i).getLocation().x;
                    y2 = botonesSTA.get(i).getLocation().y;
                    r2_x = arrayCirculoSTA.get(i).r1;
                    r2_y = arrayCirculoSTA.get(i).r2;
                    detAlcance("STA", botonesSTA.get(i).getName());
                }
            }
            if (indicesSTA.isEmpty()) return false; //no se encontraron stas en el alcance
        }
        //se encontró al menos una red o una sta en el alcance
        return true;
    }
    
    public boolean checkRouter(int indice, String tipoBoton)
    {
        junto = false;
        indicesSTA.clear();
        nombres.clear();
        HashMap estaciones = new HashMap();
        
        STA auxSTA = new STA();
        
        if (tipoBoton.equals("Router"))
        {
            x1 = botonesRouter.get(indice).getLocation().x+botonesRouter.get(indice).getPreferredSize().width/2;
            y1 = botonesRouter.get(indice).getLocation().y+botonesRouter.get(indice).getPreferredSize().height/2;
            r1_x = arrayCirculoRouter.get(indice).r1;
            r1_y = arrayCirculoRouter.get(indice).r2;
            
            if (routers.containsKey(botonesRouter.get(indice).getName()))
            {
                router = (Router) routers.get(botonesRouter.get(indice).getName());
                estaciones = router.getEstacion();

                for (int j = 0;j<botonesSTA.size();j++)
                {
                    if (estaciones.containsKey(botonesSTA.get(j).getName()))
                    {
                        nombres.add(botonesSTA.get(j).getName());
                        x2 = botonesSTA.get(j).getLocation().x+botonesSTA.get(j).getPreferredSize().width/2;
                        y2 = botonesSTA.get(j).getLocation().y+botonesSTA.get(j).getPreferredSize().height/2;
                        r2_x = arrayCirculoSTA.get(j).r1;
                        r2_y = arrayCirculoSTA.get(j).r2;
                        detAlcance("STA",botonesSTA.get(j).getName());
                    }
                }
            }
            
            if(indicesSTA.isEmpty()) return false; //no se encontraron redes en el alcance
        }
        if (tipoBoton.equals("AP"))
        {
            x1 = botonesAP.get(indice).getLocation().x+botonesAP.get(indice).getPreferredSize().width/2;
            y1 = botonesAP.get(indice).getLocation().y+botonesAP.get(indice).getPreferredSize().height/2;
            r1_x = arrayCirculoAP.get(indice).r1;
            r1_y = arrayCirculoAP.get(indice).r2;
            
            if (aps.containsKey(botonesAP.get(indice).getName()))
            {
                ap = (AccessPoint) aps.get(botonesAP.get(indice).getName());
                estaciones = ap.getEstacion();

                for (int j = 0;j<botonesSTA.size();j++)
                {
                    if (estaciones.containsKey(botonesSTA.get(j).getName()))
                    {
                        nombres.add(botonesSTA.get(j).getName());
                        x2 = botonesSTA.get(j).getLocation().x+botonesSTA.get(j).getPreferredSize().width/2;
                        y2 = botonesSTA.get(j).getLocation().y+botonesSTA.get(j).getPreferredSize().height/2;
                        r2_x = arrayCirculoSTA.get(j).r1;
                        r2_y = arrayCirculoSTA.get(j).r2;
                        detAlcance("STA",botonesSTA.get(j).getName());
                    }
                }
            }
            
            if(indicesSTA.isEmpty()) return false; //no se encontraron redes en el alcance
        }
        if (tipoBoton.equals("STA"))
        {
            x1 = botonesSTA.get(indice).getLocation().x+botonesSTA.get(indice).getPreferredSize().width/2;
            y1 = botonesSTA.get(indice).getLocation().y+botonesSTA.get(indice).getPreferredSize().height/2;
            r1_x = arrayCirculoSTA.get(indice).r1;
            r1_y = arrayCirculoSTA.get(indice).r2;
            
            if (redes.containsKey(botonesSTA.get(indice).getName()))
            {
                auxSTA = (STA) redes.get(botonesSTA.get(indice).getName());

                for (int j = 0;j<botonesSTA.size();j++)
                {
                    if (stas.containsKey(botonesSTA.get(j).getName()))
                    {
                        sta = (STA) stas.get(botonesSTA.get(j).getName());
                        if (auxSTA.getSSID().equals(sta.getSSID())&&(!sta.getID().equals(auxSTA.getID())))
                        {
                            nombres.add(botonesSTA.get(j).getName());
                            x2 = botonesSTA.get(j).getLocation().x+botonesSTA.get(j).getPreferredSize().width/2;
                            y2 = botonesSTA.get(j).getLocation().y+botonesSTA.get(j).getPreferredSize().height/2;
                            r2_x = arrayCirculoSTA.get(j).r1;
                            r2_y = arrayCirculoSTA.get(j).r2;
                            detAlcance("STA",sta.getID());
                        }
                    }
                }
            }
             
            if(indicesSTA.isEmpty()) return false; //no se encontraron redes en el alcance
        }
        //se encontró al menos una red o una sta en el alcance
        
        return true;
    }
    
    public void calcAlcance()
    {
        dx_sum = (r1_x+r2_x)/2;
        dy_sum = (r1_y+r2_y)/2;
        p1 = (int) Math.pow((x2-x1), 2);
        p2 =(int) Math.pow((y2-y1), 2);
        d = Math.sqrt((p1+p2));
    }
    
    public void detAlcance(String tipo, String i)
    {
        calcAlcance();
        if ((d>dx_sum)&&(d>dy_sum)) junto = false; //circulos disjuntos
        else
        {
            //en rango
            junto =  true;
            if (tipo.equals("Router")) indicesR.add(i);
            if (tipo.equals("AP")) indicesAP.add(i);
            if (tipo.equals("STA")) indicesSTA.add(i);
        }
    }
}
