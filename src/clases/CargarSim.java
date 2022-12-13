package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CargarSim
{
    Router r;
    AccessPoint ap;
    STA sta;
    
    HashMap routers = new HashMap();
    HashMap aps = new HashMap();
    HashMap stas = new HashMap();
    
    ArrayList <Method> settersRouter = new ArrayList<>();
    ArrayList <Method> settersAP = new ArrayList<>();
    ArrayList <Method> settersSTA = new ArrayList<>();
    
    String linea = "", tipoRed = "";
    
    public void setFunctionsRouter(Class clase)
    {
        try {
            settersRouter.add(clase.getDeclaredMethod("setNombre",String.class));
            settersRouter.add(clase.getDeclaredMethod("setIP",String.class));
            settersRouter.add(clase.getDeclaredMethod("setMask",String.class));
            settersRouter.add(clase.getDeclaredMethod("setActDHCP",String.class));
            settersRouter.add(clase.getDeclaredMethod("setCantDHCP",int.class));
            settersRouter.add(clase.getDeclaredMethod("setSSID",String.class));
            settersRouter.add(clase.getDeclaredMethod("setModo",String.class));
            settersRouter.add(clase.getDeclaredMethod("setBanda",String.class));
            settersRouter.add(clase.getDeclaredMethod("setRegion",String.class));
            settersRouter.add(clase.getDeclaredMethod("setCanal",int.class));
            settersRouter.add(clase.getDeclaredMethod("setRegion5G",String.class));
            settersRouter.add(clase.getDeclaredMethod("setCanal5G",int.class));
            settersRouter.add(clase.getDeclaredMethod("setSeguridad",String.class));
            settersRouter.add(clase.getDeclaredMethod("setPassword",String.class));
            settersRouter.add(clase.getDeclaredMethod("setConexion",String.class));
            settersRouter.add(clase.getDeclaredMethod("setIPWAN",String.class));
            settersRouter.add(clase.getDeclaredMethod("setMaskWAN",String.class));
            settersRouter.add(clase.getDeclaredMethod("setGateway",String.class));
            settersRouter.add(clase.getDeclaredMethod("setPrimDNS",String.class));
            settersRouter.add(clase.getDeclaredMethod("setSecDNS",String.class));
            settersRouter.add(clase.getDeclaredMethod("setPosx",int.class));
            settersRouter.add(clase.getDeclaredMethod("setPosy",int.class));
            settersRouter.add(clase.getDeclaredMethod("setRx",int.class));
            settersRouter.add(clase.getDeclaredMethod("setRy",int.class));
            settersRouter.add(clase.getDeclaredMethod("setConfigurado",boolean.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFunctionsAP(Class clase)
    {
        try {
            settersAP.add(clase.getDeclaredMethod("setNombre",String.class));
            settersAP.add(clase.getDeclaredMethod("setIP",String.class));
            settersAP.add(clase.getDeclaredMethod("setMask",String.class));
            settersAP.add(clase.getDeclaredMethod("setActDHCP",String.class));
            settersAP.add(clase.getDeclaredMethod("setCantDHCP",int.class));
            settersAP.add(clase.getDeclaredMethod("setSSID",String.class));
            settersAP.add(clase.getDeclaredMethod("setModo",String.class));
            settersAP.add(clase.getDeclaredMethod("setBanda",String.class));
            settersAP.add(clase.getDeclaredMethod("setRegion",String.class));
            settersAP.add(clase.getDeclaredMethod("setCanal",int.class));
            settersAP.add(clase.getDeclaredMethod("setRegion5G",String.class));
            settersAP.add(clase.getDeclaredMethod("setCanal5G",int.class));
            settersAP.add(clase.getDeclaredMethod("setSeguridad",String.class));
            settersAP.add(clase.getDeclaredMethod("setPassword",String.class));
            settersAP.add(clase.getDeclaredMethod("setPosx",int.class));
            settersAP.add(clase.getDeclaredMethod("setPosy",int.class));
            settersAP.add(clase.getDeclaredMethod("setRx",int.class));
            settersAP.add(clase.getDeclaredMethod("setRy",int.class));
            settersAP.add(clase.getDeclaredMethod("setConfigurado",boolean.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFunctionsSTA(Class clase)
    {
        try {
            settersSTA.add(clase.getDeclaredMethod("setID",String.class));
            settersSTA.add(clase.getDeclaredMethod("setIP",String.class));
            settersSTA.add(clase.getDeclaredMethod("setMask",String.class));
            settersSTA.add(clase.getDeclaredMethod("setSSID",String.class));
            settersSTA.add(clase.getDeclaredMethod("setOrigenSSID",String.class));
            settersSTA.add(clase.getDeclaredMethod("setDHCP",String.class));
            if (tipoRed.equals("AdHoc")) settersSTA.add(clase.getDeclaredMethod("setNodoPadre",boolean.class));
            settersSTA.add(clase.getDeclaredMethod("setSeguridad",String.class));
            settersSTA.add(clase.getDeclaredMethod("setPassword",String.class));
            settersSTA.add(clase.getDeclaredMethod("setPosx",int.class));
            settersSTA.add(clase.getDeclaredMethod("setPosy",int.class));
            settersSTA.add(clase.getDeclaredMethod("setRx",int.class));
            settersSTA.add(clase.getDeclaredMethod("setRy",int.class));
            settersSTA.add(clase.getDeclaredMethod("setConfigurado",boolean.class));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void leerArchivo (String archivo)
    {
        try
        {
            try (FileReader fr = new FileReader(new File(archivo)); BufferedReader br = new BufferedReader(fr)) {
                
                while ((linea = br.readLine()) != null)
                {
                    if (linea.endsWith("#")) llenarArreglos(linea.length());
                }
            }
            
        } catch (IOException | IllegalAccessException | InvocationTargetException e){}
    }
    
    public void llenarArreglos (int cant) throws IllegalAccessException, InvocationTargetException
    {
        String [] texto = new String [cant];
        String [] aux = new String [1];
        Type [] tipo;
        texto = linea.split("\\-");
        String nodo = texto[0];
        
        if (nodo.startsWith("Router"))
        {
            r = new Router();
            for (int i = 0; i<texto.length; i++)
            {
                tipo = settersRouter.get(i).getGenericParameterTypes();
                try
                {
                    if (tipo[0].equals(int.class))
                    {
                        if (settersRouter.get(i).getName().equals("setRy"))
                        {
                            aux = texto[i].split("\\#");
                            settersRouter.get(i).invoke(r, Integer.parseInt(aux[0]));
                        }
                        else settersRouter.get(i).invoke(r, Integer.parseInt(texto[i]));
                    }
                    
                    if (tipo[0].equals(String.class)) settersRouter.get(i).invoke(r, texto[i]);
                    
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            settersRouter.get(settersRouter.size()-1).invoke(r, true);
            routers.put(r.getNombre(),r);
        }
        if (nodo.startsWith("AP"))
        {
            ap = new AccessPoint();
            for (int i = 0; i<texto.length; i++)
            {
                tipo = settersAP.get(i).getGenericParameterTypes();
                try
                {
                    if (tipo[0].equals(int.class))
                    {
                        if (settersAP.get(i).getName().equals("setRy"))
                        {
                            aux = texto[i].split("\\#");
                            settersAP.get(i).invoke(ap, Integer.parseInt(aux[0]));
                        }
                        else settersAP.get(i).invoke(ap, Integer.parseInt(texto[i]));
                    }
                    
                    if (tipo[0].equals(String.class)) settersAP.get(i).invoke(ap, texto[i]);
                    
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            settersAP.get(settersAP.size()-1).invoke(ap, true);
            aps.put(ap.getNombre(),ap);
        }
        if (nodo.startsWith("STA"))
        {
            sta = new STA();
            for (int i = 0; i<texto.length; i++)
            {
                tipo = settersSTA.get(i).getGenericParameterTypes();
                try
                {
                    if (tipo[0].equals(int.class))
                    {
                        if (settersSTA.get(i).getName().equals("setRy"))
                        {
                            aux = texto[i].split("\\#");
                            settersSTA.get(i).invoke(sta, Integer.parseInt(aux[0]));
                        }
                        else settersSTA.get(i).invoke(sta, Integer.parseInt(texto[i]));
                    }
                    if (tipo[0].equals(String.class)) settersSTA.get(i).invoke(sta, texto[i]);
                    
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            settersSTA.get(settersSTA.size()-1).invoke(sta, true);
            stas.put(sta.getID(),sta);
        }
    }
}
