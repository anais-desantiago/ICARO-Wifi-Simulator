package clases;
import java.util.*;

public class AsigDHCP
{
    ArrayList <String> direcciones = new ArrayList <>();
    ArrayList <String> usadas = new ArrayList<>();
    VerificarIP check = new VerificarIP();
    LANPanel lan = new LANPanel();
    int [] claseA, claseB, wan;
    int claseC, cantdhcp;
    boolean actDHCP;
    String ipR = "", ip = "", mask = "", clase = "", usada = "", ipWAN = "", maskWAN = "", gt = "";
    
    public void generarIPWAN()
    {
        /*Procedimiento para generar una IP aleatoriamente siempre y cuando la configuracion WAN
        indique que la dirección IP será por el ISP*/
        wan = new int [4];
        ipWAN = "";
        maskWAN = "";
        gt = "";
        for (int i = 0; i<4; i++)
        {
            if (i==0) wan[i] = (int)(Math.random()*223 + 1);
            else wan[i] = (int)(Math.random()*254 + 1);

            if (i<3)
            {
                ipWAN = ipWAN + Integer.toString(wan[i])+".";
                gt = gt +Integer.toString(wan[i])+".";
            }
            else
            {
                ipWAN = ipWAN + Integer.toString((wan[i]));
                gt = gt + "254";
            }
        }
        check.dividirIP(ipWAN, "WAN");
        maskWAN = check.mask;
    } 
    
    public void generarAleatorio(int cant)
    {
        /*Este procedimiento genera una IP aleatoria según sea la clase, es decir si es clase A se generarán 3 números aleatorios
         que luego serán concatenados con el primer octeto de la IP del router*/
        check.ipR = ipR;
        
        check.dividirIP(check.ipR, "Router");
        check.mask = mask;
        check.obtenerClase();
        
        clase = check.clase;
        
        claseA = new int [3];
        claseB = new int [2];
        
        for (int j = 0; j<cant; j++)
        {
            if (mask.equals("255.0.0.0"))
            {
                for (int i = 0; i<3; i++)
                {
                    claseA[i] = (int)(Math.random()*255 + 1);
                }
            }
            if (mask.equals("255.255.0.0"))
            {
                for (int i =0;i<2;i++)
                {
                    claseB[i] = (int)(Math.random()*255 + 1);
                }
            }
            if (mask.equals("255.255.255.0")) claseC = (int)(Math.random()*255 + 1);
            ip = setIP();
            direcciones.add(j, ip);
            usada = "No";
            usadas.add(j, usada);
        }
    }
    
    public String setIP()
    {
        /*Esta función forma la dirección IP que tendrá la estación después de que se hayan generado aleatoriamente.
         Retorna una IP que será concatenada con los octetos específicos obtenidos de la IP del router.
         Después de haber formado los 4 octetos se le asignará la IP a la estación*/
        if (mask.equals("255.0.0.0")) ip = clase + Integer.toString(claseA[0])+"."+Integer.toString(claseA[1])+"."+Integer.toString(claseA[2]);
        if (mask.equals("255.255.0.0")) ip = clase + Integer.toString(claseB[0])+"."+Integer.toString(claseB[1]);
        if (mask.equals("255.255.255.0")) ip = clase + Integer.toString(claseC);
        return ip;
    }
}
