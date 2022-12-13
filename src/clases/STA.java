package clases;

import java.util.HashMap;

public class STA
{
    boolean configurado = false, nodoPadre = false;

    HashMap nombres = new HashMap();
    String ip = "", passwd="", ssid ="", id ="", dhcp="", seguridad="", mask="", origenSSID="";
    int posx=0, posy=0, rx=0, ry=0;
    
    public STA()
    {
        setID("");
        setIP("0.0.0.0");
        setMask("0.0.0.0");
        setConfigurado(false);
        setNodoPadre(false);
        setPassword(passwd);
        setDHCP(dhcp);
        setSSID(ssid);
        setSeguridad(seguridad);
        setOrigenSSID(origenSSID);
        setPosx(posx);
        setPosy(posy);
        setRx(rx);
        setRy(ry);
    }
    
        /*Getters*/
    public HashMap getNombres()
    {
        return nombres;
    }

    public boolean isNodoPadre()
    {
        return nodoPadre;
    }
    public int getRx()
    {
        return rx;
    }
    public int getRy()
    {
        return ry;
    }
    public int getPosx()
    {
        return posx;
    }
    public int getPosy()
    {
        return posy;
    }
    public String getOrigenSSID()
    {
        return origenSSID;
    }
    public String getID()
    {
        return id;
    }
    public String getSSID()
    {
        return ssid;
    }
    public String getIP()
    {
        return ip;
    }
    public String getMask()
    {
        return mask;
    }
    public String getPassword()
    {
        return passwd;
    }
    public String getDHCP()
    {
        return dhcp;
    }
    public String getSeguridad()
    {
        return seguridad;
    }
    public boolean getConfigurado()
    {
        return configurado;
    }
    
        /*Setters*/
    public void setNombres(String nombre)
    {
        this.nombres.put(nombre,nombre);
    }

    public void setNodoPadre(boolean nodoPadre)
    {
        this.nodoPadre = nodoPadre;
    }
    public void setRx(int rx)
    {
        this.rx = rx;
    }
    public void setRy(int ry)
    {
        this.ry = ry;
    }
    public void setPosy(int posy)
    {
        this.posy = posy;
    }
    public void setPosx(int posx)
    {
        this.posx = posx;
    }
    public void setOrigenSSID(String origenSSID)
    {
        this.origenSSID = origenSSID;
    }        
    public void setID(String id)
    {
        this.id = id;
    }
    public void setIP(String ip)
    {
        this.ip = ip;
    }
    public void setMask(String mask)
    {
        this.mask = mask;
    }
    public void setSSID(String ssid)
    {
        this.ssid = ssid;
    }
    public void setPassword(String password)
    {
        this.passwd = password;
    }
    public void setDHCP(String dhcp)
    {
        this.dhcp = dhcp;
    }
    public void setSeguridad(String seguridad)
    {
        this.seguridad = seguridad;
    }
    public void setConfigurado (boolean configurado)
    {
        this.configurado = configurado;
    }
}
