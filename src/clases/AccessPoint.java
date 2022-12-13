package clases;

import java.util.ArrayList;
import java.util.HashMap;

public class AccessPoint
{
    HashMap estaciones = new HashMap();
    ArrayList <String> direcciones = new ArrayList<>();
    ArrayList <String> usadas = new ArrayList<>();
    
    int  cantDirDHCP, canal, canal5G, posx, posy, rx, ry;
    String id, ipLAN, mask, ssid="", region5G = "",region="", selectModo="",actDHCP="",tipoSeguridad="", passSeguridad="",banda="";
    boolean  configurado = false;
    
    AccessPoint()
    {
        this.setNombre("");
        this.setSSID("");
        this.setIP("0.0.0.0");
        this.setMask("0.0.0.0");
        this.setActDHCP("");
        this.setRegion("");
        this.setSeguridad("Ninguna");
        this.setBanda("");
        this.setConfigurado(configurado);
    }
    
        /*Setters*/
    public void setCanal5G(int canal5G)
    {
        this.canal5G = canal5G;
    }
    public void setRegion5G(String region5G) 
    {
        this.region5G = region5G;
    }
    public void setRx(int rx)
    {
        this.rx = rx;
    }
    public void setRy(int ry)
    {
        this.ry = ry;
    }
    public void setPosx(int posx)
    {
        this.posx = posx;
    }
    public void setPosy(int posy)
    {
        this.posy = posy;
    }
    public void setBanda(String banda)
    {
        this.banda = banda;
    }
    public void setDirecciones(ArrayList <String> direcciones, ArrayList <String> usadas)
    {
        for (int i=0; i<direcciones.size();i++)
        {
            this.direcciones.add(direcciones.get(i));
            this.usadas.add(usadas.get(i));
        }
    }
    public void setConfigurado(boolean uso)
    {
        //set si ya fue configurado;
        this.configurado = uso;
    }
    public void setNombre(String id)
    {
        this.id = id;
    }
    public void setIP(String ipLan)
    {
        this.ipLAN = ipLan;
    }
    public void setMask(String mask)
    {
        this.mask = mask;
    }
    public void setSSID(String ssid)
    {
        this.ssid = ssid;
    }
    public void setRegion(String region)
    {
        this.region = region;
    }
    public void setModo(String selectModo)
    {
        this.selectModo = selectModo;
    }
    public void setSeguridad(String tipoSeguridad)
    {
        this.tipoSeguridad = tipoSeguridad;
    }
    public void setPassword(String passSeguridad)
    {
        this.passSeguridad = passSeguridad;
    }
    public void setActDHCP(String actDHCP)
    {
        this.actDHCP = actDHCP;
    }
    public void setCantDHCP(int cantDirDHCP)
    {
        this.cantDirDHCP = cantDirDHCP;
    }
    public void setCanal(int canal)
    {
        this.canal = canal;
    }
    public void setUsadas(String usada, int i)
    {
        this.usadas.add(i, usada);
    }
    public void setEstacion (String nombre,STA sta)
    {
        this.estaciones.put(nombre, sta);
    }
        
        /*Getters*/
    public String getRegion5G()
    {
        return region5G;
    }
    public int getCanal5G()
    {
        return canal5G;
    }
    public int getRx()
    {
        return rx;
    }
    public int getRy()
    {
        return ry;
    }
    public String getBanda()
    {
        return banda;
    }
    public boolean getConfigurado()
    {
        return configurado;
    }
    public String getDireccion(int i)
    {
        return direcciones.get(i);
    }
    public String getUsadas(int i)
    {
        return usadas.get(i);
    }     
     public HashMap getEstacion()
    {
        return estaciones;
    }
    public String getNombre()
    {
        return id;
    }
    public String getIP()
    {
        return ipLAN;
    }
    public String getMask()
    {
        return mask;
    }
    public String getActDHCP()
    {
        return actDHCP;
    }
    public int getCantDHCP()
    {
        return cantDirDHCP;
    }
    public String getSSID()
    {
        return ssid;
    }
    public String getSeguridad()
    {
        return tipoSeguridad;
    }
    public String getPassword()
    {
        return passSeguridad;
    }
    public String getRegion()
    {
        return region;
    }
    public int getCanal()
    {
        return canal;
    }
    public String getModo()
    {
        return selectModo;
    }
    public int getPosx()
    {
        return posx;
    }
    public int getPosy()
    {
        return posy;
    }
}