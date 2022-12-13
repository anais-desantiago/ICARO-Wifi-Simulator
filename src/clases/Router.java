package clases;

import java.util.ArrayList;
import java.util.HashMap;

public class Router
{
    HashMap estaciones = new HashMap();
    ArrayList <String> direcciones = new ArrayList<>();
    ArrayList <String> usadas = new ArrayList<>();
    
    final String dns = "8.8.8.8";
    int  cantDirDHCP = 0, canal = 0, canal5G = 0, posx = 0, posy = 0, rx = 0, ry = 0;
    String id, ipLAN, mask, ssid = "", region5G = "",region = "", selectModo = "", actDHCP = "", tipoSeguridad = "", passSeguridad = "", ipWAN = "", 
            maskWAN = "", gtIP, primDNS, secDNS, conexion, banda = "";
    boolean  configurado = false;
    
    Router()
    {
        this.setNombre("");
        this.setSSID("");
        this.setIP("0.0.0.0");
        this.setMask("0.0.0.0");
        this.setActDHCP("");
        this.setRegion("");
        this.setSeguridad("Ninguna");
        this.setIPWAN("0.0.0.0");
        this.setMaskWAN("0.0.0.0");
        this.setBanda("");
        this.setConfigurado(configurado);
    }
     
        /*Setters*/
    public void setNombre(String id)
    {
        this.id = id;
    }
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
    public void setConexion(String conexion)
    {
        this.conexion = conexion;
    }
    public void setIPWAN(String ipWAN)
    {
        this.ipWAN = ipWAN;
    }
    public void setMaskWAN(String maskWAN)
    {
        this.maskWAN = maskWAN;
    }
    public void setGateway(String gtIP)
    {
        this.gtIP = gtIP;
    }
    public void setPrimDNS(String primDNS)
    {
        this.primDNS = primDNS;
    }
    public void setSecDNS(String secDNS)
    {
        this.secDNS = secDNS;
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
        //si el router ya fue configurado;
        this.configurado = uso;
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
    public String getModo()
    {
        return selectModo;
    }
    public String getBanda()
    {
        return banda;
    }
    public String getRegion()
    {
        return region;
    }
    public int getCanal()
    {
        return canal;
    }
    public String getRegion5G()
    {
        return region5G;
    }
    public int getCanal5G()
    {
        return canal5G;
    }
    public String getSeguridad()
    {
        return tipoSeguridad;
    }
    public String getPassword()
    {
        return passSeguridad;
    }
    
    public String getConexion()
    {
        return conexion;
    }
    public String getIPWAN()
    {
        return ipWAN;
    }
    public String getMaskWAN()
    {
        return maskWAN;
    }
    public String getGateway()
    {
        return gtIP;
    }
    public String getPrimDNS()
    {
        return primDNS;
    }
    public String getSecDNS()
    {
        return secDNS;
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
    public int getPosx()
    {
        return posx;
    }
    public int getPosy()
    {
        return posy;
    }
    public int getRx()
    {
        return rx;
    }
    public int getRy()
    {
        return ry;
    }
}
