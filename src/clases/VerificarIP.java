package clases;

import javax.swing.JOptionPane;

public class VerificarIP
{
    /*Esta clase servirá para verificar las IPs según las máscaras obtenidas*/

    public  String ip="",ipR="", ipS="", clase="", ipFrom="", mask ="", maskSTA="", gt="", dns="", tipoRed= "";
    public  int[] octetosNum = new int [4], octetosR, octetosSTA, octetosGateway;
    
    public void checkConflicto()
    {
        
    }

    public void dividirIP(String ip, String tipoIP)
    {
        String [] octetos = new String [4];
        octetos = ip.split("\\.");
        octetosR = new int [octetos.length];
        octetosSTA = new int [octetos.length];
        octetosNum = new int [octetos.length];
        
        if (tipoIP.equals("Router"))
        {
            ipR = ip;
            for (int i = 0; i<octetos.length;i++)
            {
                octetosR[i] = Integer.parseInt(octetos[i]);
            }
        }
        if (tipoIP.equals("STA"))
        {
            ipS = ip;
            for (int i = 0; i<octetos.length;i++)
            {
                octetosSTA[i] = Integer.parseInt(octetos[i]);
            }
        }
        if (tipoIP.equals("Máscara")||tipoIP.equals("MáscaraSTA"))
        {
            mask = ip;
            for (int i = 0; i<octetos.length;i++)
            {
                octetosNum[i] = Integer.parseInt(octetos[i]);
            }
        }
         if (tipoIP.equals("WAN"))
        {
            for (int i=0; i< octetos.length;i++)
            {
                octetosNum[i] = Integer.parseInt(octetos[i]);
            }
            formarMask();
        }
    }
    
    public  boolean checkIP(String ipFrom)
    {
        /*Este procedimiendo separa el string de ip introducidas por el usuario tanto la del router como la de la estación.
         Separa cada vez que encuentre un punto y lo coloca en un arreglo de enteros. Después de haberlo separado va a guardar
         lo que obtuvo en la variable octetosR u octetosSTA según sea el caso*/
        if (ipFrom.equals("Máscara") ||(ipFrom.equals("MáscaraSTA")) )
        {
            formarIP();
            if (!mask.equals("255.0.0.0")&&(!maskSTA.equals("255.0.0.0")&&(!mask.equals("255.255.0.0")) && (!maskSTA.equals("255.255.0.0"))&&(!mask.equals("255.255.255.0"))&&(!maskSTA.equals("255.255.255.0"))))
            {
                JOptionPane.showMessageDialog(null, "La máscara no es válida");
                return false;
            }
        }
        else
        {
            if ((octetosNum[0]>223)||(octetosNum[0]==0)||(octetosNum[3]==0)||(octetosNum[3]>254) || (octetosNum[1]>255)||(octetosNum[2]>255))
            {
                JOptionPane.showMessageDialog(null, "Dirección IP no es válida");
                return false;
            }
        }
        if (ipFrom.equals("Router"))
        {
            octetosR = new int [octetosNum.length];
            System.arraycopy(octetosNum, 0, octetosR, 0, octetosR.length);
            
            if(claseIP()) formarIP();
            else return false;
        }
        if (ipFrom.equals("STA"))
        {
            octetosSTA = new int [octetosNum.length];
            System.arraycopy(octetosNum, 0, octetosSTA, 0, octetosSTA.length);
            
            formarIP();
        }
        if (ipFrom.equals("Gateway"))
        {
            octetosGateway = new int [octetosNum.length];
            System.arraycopy(octetosNum, 0, octetosGateway, 0, octetosGateway.length);
            
            if (checkGateway()) formarIP();
            else return false;
        }
        else formarIP();
        return true;
    }
    
    public boolean checkGateway()
    {
        for (int i=0; i<octetosGateway.length;i++)
        {
            if (i<3)
            {
                if (octetosGateway[i]!= octetosR[i])
                {
                    JOptionPane.showMessageDialog(null, "Error: la dirección del Gateway debe ser del rango de la dirección WAN");
                    return false;
                }
            }
            else
            {
                if (octetosGateway[i]!=254)
                {
                    JOptionPane.showMessageDialog(null, "Error: la dirección del Gateway debe terminar en 254");
                    return false;
                }
                else
                {    
                    for (int j=0; j<octetosGateway.length; j++)
                    {
                        if (j<3) gt = gt + Integer.toString(octetosGateway[i])+".";
                        else gt = gt + Integer.toString(octetosGateway[i]);
                    }
                }
            }
        }
        return true;
    }
    
    public  void obtenerClase()
    {
        /*Este procedimiento obtiene la clase de la IP del router según la máscar, de tal forma de obtener los octetos
          correspondientes según la máscara y posteriormente concatenarlos con la dirección DHCP que tendrá la estación*/
        //solo se invocará cuando tanto el router como la estación tengan DHCP activo
        if (mask.equals("255.0.0.0")||(maskSTA.equals("255.0.0.0")))
        {
            clase = Integer.toString(octetosR[0])+".";
        }
        if (mask.equals("255.255.0.0")||(maskSTA.equals("255.255.0.0")))
        {
            clase = Integer.toString(octetosR[0]) +"."+Integer.toString(octetosR[1])+".";
        }
        if (mask.equals("255.255.255.0")||(maskSTA.equals("255.255.255.0")))
        {
            clase = Integer.toString(octetosR[0])+ "."+ Integer.toString(octetosR[1]) + "." + Integer.toString(octetosR[2]) + ".";
        }
    }
    
    public boolean verificarIPEstatica()
    {
        /*Esta función verifica que la IP de la STA que fue introducida por el usuario esté dentro del rango
         * de las IP del router y lo hará según la máscara que tenga el router*/
        //solo se invocará cuando no haya dhcp dinámico en la sta
        if (mask.equals("255.0.0.0")&&(maskSTA.equals("255.0.0.0")))
        {
            if(octetosSTA[0]!=octetosR[0])
            {
                JOptionPane.showMessageDialog(null, "Error el rango de IPs no coinciden, no se podrá establecer comunicación");
                return false;
            }
            return true;
        }
        if (mask.equals("255.255.0.0")&&(maskSTA.equals("255.255.0.0")))
        {
            if ((octetosSTA[0]!=octetosR[0]) || (octetosSTA[1]!=octetosR[1]))
            {
                JOptionPane.showMessageDialog(null, "Error el rango de IPs no coinciden, no se podrá establecer comunicación");
                return false;
            }
            return true;
        }
        if (mask.equals("255.255.255.0")&&(maskSTA.equals("255.255.255.0")))
        {
            if (((octetosSTA[0]!=octetosR[0]) || (octetosSTA[1]!=octetosR[1]) ||(octetosSTA[2]!=octetosR[2])))
            {
                JOptionPane.showMessageDialog(null, "Error el rango de IPs no coinciden, no se podrá establecer comunicación");
                return false;
            }
            return true;
        }
        return true;
    }
    
    public boolean verificarIPRouterSTA()
    {
        /*Esta función verifica que las ips del router y de las estaciones sean distintas y esta verificación se hace
         después de haber verificado que la ip esté en el rango de ip del router*/
        //se invocará cuando no haya DHCP dinámico en la estación
        if (ipS.equals(ipR))
        {
            JOptionPane.showMessageDialog(null, "La dirección IP de la estación no puede ser igual a la dirección IP del router");
            return false;
        }
        return true;
    }
    
    public boolean verificarMaskRouterSta()
    {
        if (!maskSTA.equals(mask))
        {
            JOptionPane.showMessageDialog(null, "La máscara de la estación debe ser igual a la máscara del router");
            return false;
        }
        return true;
    }
    
    public void formarMask()
    {
         if ((octetosNum[0]>=0) &&(octetosNum[0]<=126)) mask = "255.0.0.0";
         if ((octetosNum[0]>=127)&&(octetosNum[0]<=191)) mask = "255.255.0.0";
         if ((octetosNum[0]>=192)&&(octetosNum[0]<=223)) mask = "255.255.255.0";
    }
    
    public boolean claseIP()
    {
        /* Este procedimiento verifica que la IP del router corresponda con la máscara introducida 
         y que no se excedan los valores permitidos por la máscara*/
        if (mask.equals("255.0.0.0"))
        {
            if (octetosR[0]>126)
            {
                JOptionPane.showMessageDialog(null, "Error esa IP no es válida para la máscara especificada");
                return false;
            }
            else
            {
                if (octetosR[0]==0)
                {
                    JOptionPane.showMessageDialog(null, "IP inválida");
                    return false;
                }
            }
        }
        if (mask.equals("255.255.0.0"))
        {
            if ((octetosR[0]<127)&&(octetosR[0]>191))
            {
                JOptionPane.showMessageDialog(null, "Error esa IP no es válida para la máscara especificada");
                return false;
            }
        }
        if (mask.equals("255.255.255.0"))
        {
            if ((octetosR[0]<192)&&(octetosR[0]>223))
            {
                JOptionPane.showMessageDialog(null, "Error esa IP no es válida para la máscara especificada");
                return false;
            }
        }
        return true;
    }
    
    public void formarIP()
    {
        if (ipFrom.equals("Router"))
        {
            ipR = Integer.toString(octetosR[0])+"."+Integer.toString(octetosR[1])+"."+Integer.toString(octetosR[2])+"."+Integer.toString(octetosR[3]);
        }
        if (ipFrom.equals("STA"))
        {
            ipS = Integer.toString(octetosSTA[0])+"."+Integer.toString(octetosSTA[1])+"."+Integer.toString(octetosSTA[2])+"."+Integer.toString(octetosSTA[3]);
        }
        if ((ipFrom.equals("Máscara")||(ipFrom.equals("MáscaraSTA"))))
        {
            if (ipFrom.equals("Máscara"))
            {
                mask = Integer.toString(octetosNum[0])+"."+Integer.toString(octetosNum[1])+"."+Integer.toString(octetosNum[2])+"."+Integer.toString(octetosNum[3]);
            }
            if (ipFrom.equals("MáscaraSTA"))
            {
                maskSTA = Integer.toString(octetosNum[0])+"."+Integer.toString(octetosNum[1])+"."+Integer.toString(octetosNum[2])+"."+Integer.toString(octetosNum[3]);
            }
            ip = Integer.toString(octetosNum[0])+"."+Integer.toString(octetosNum[1])+"."+Integer.toString(octetosNum[2])+"."+Integer.toString(octetosNum[3]);
        }
        else
        {
            dns = Integer.toString(octetosNum[0])+"."+Integer.toString(octetosNum[1])+"."+Integer.toString(octetosNum[2])+"."+Integer.toString(octetosNum[3]);
            ip = dns;
        }
    }
}
