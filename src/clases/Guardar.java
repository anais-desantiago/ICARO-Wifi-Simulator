package clases;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Guardar extends javax.swing.JDialog
{
    /*Esta clase permite extraer los métodos getter de la clase Router, AccessPoint y STA.
     Luego de extraerlos, se invoca a cada uno de los métodos y se guardan los valores de 
     * c/u en la ruta especificada por el usuario*/
    
    JFileChooser guardarDialog = new JFileChooser();
    
    String prueba = "", tipoRed = "";
    int text;
    
    HashMap router = new HashMap();
    HashMap ap = new HashMap();
    HashMap sta = new HashMap();
    
    ArrayList <Method> getters = new ArrayList<>();
    ArrayList <Method> gettersAP = new ArrayList<>();
    ArrayList <Method> gettersSTA = new ArrayList<>();
    
    boolean cancel = false;
    
    public Guardar(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }
    
    public void routerFunctions(Class clase)
    {
        try {
            getters.add(clase.getDeclaredMethod("getNombre"));
            getters.add(clase.getDeclaredMethod("getIP"));
            getters.add(clase.getDeclaredMethod("getMask"));
            getters.add(clase.getDeclaredMethod("getActDHCP"));
            getters.add(clase.getDeclaredMethod("getCantDHCP"));
            getters.add(clase.getDeclaredMethod("getSSID"));
            getters.add(clase.getDeclaredMethod("getModo"));
            getters.add(clase.getDeclaredMethod("getBanda"));
            getters.add(clase.getDeclaredMethod("getRegion"));
            getters.add(clase.getDeclaredMethod("getCanal"));
            getters.add(clase.getDeclaredMethod("getRegion5G"));
            getters.add(clase.getDeclaredMethod("getCanal5G"));
            getters.add(clase.getDeclaredMethod("getSeguridad"));
            getters.add(clase.getDeclaredMethod("getPassword"));
            getters.add(clase.getDeclaredMethod("getConexion"));
            getters.add(clase.getDeclaredMethod("getIPWAN"));
            getters.add(clase.getDeclaredMethod("getMaskWAN"));
            getters.add(clase.getDeclaredMethod("getGateway"));
            getters.add(clase.getDeclaredMethod("getPrimDNS"));
            getters.add(clase.getDeclaredMethod("getSecDNS"));
            getters.add(clase.getDeclaredMethod("getEstacion"));
            getters.add(clase.getDeclaredMethod("getPosx"));
            getters.add(clase.getDeclaredMethod("getPosy"));
            getters.add(clase.getDeclaredMethod("getRx"));
            getters.add(clase.getDeclaredMethod("getRy"));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void apFunctions(Class clase)
    {
        try {
            gettersAP.add(clase.getDeclaredMethod("getNombre"));
            gettersAP.add(clase.getDeclaredMethod("getIP"));
            gettersAP.add(clase.getDeclaredMethod("getMask"));
            gettersAP.add(clase.getDeclaredMethod("getActDHCP"));
            gettersAP.add(clase.getDeclaredMethod("getCantDHCP"));
            gettersAP.add(clase.getDeclaredMethod("getSSID"));
            gettersAP.add(clase.getDeclaredMethod("getModo"));
            gettersAP.add(clase.getDeclaredMethod("getBanda"));
            gettersAP.add(clase.getDeclaredMethod("getRegion"));
            gettersAP.add(clase.getDeclaredMethod("getCanal"));
            gettersAP.add(clase.getDeclaredMethod("getRegion5G"));
            gettersAP.add(clase.getDeclaredMethod("getCanal5G"));
            gettersAP.add(clase.getDeclaredMethod("getSeguridad"));
            gettersAP.add(clase.getDeclaredMethod("getPassword"));
            gettersAP.add(clase.getDeclaredMethod("getEstacion"));
            gettersAP.add(clase.getDeclaredMethod("getPosx"));
            gettersAP.add(clase.getDeclaredMethod("getPosy"));
            gettersAP.add(clase.getDeclaredMethod("getRx"));
            gettersAP.add(clase.getDeclaredMethod("getRy"));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void staFunctions(Class clase)
    {
        try {
            gettersSTA.add(clase.getDeclaredMethod("getID"));
            gettersSTA.add(clase.getDeclaredMethod("getIP"));
            gettersSTA.add(clase.getDeclaredMethod("getMask"));
            gettersSTA.add(clase.getDeclaredMethod("getSSID"));
            gettersSTA.add(clase.getDeclaredMethod("getOrigenSSID"));
            gettersSTA.add(clase.getDeclaredMethod("getDHCP"));
            if (tipoRed.equals("AdHoc")) gettersSTA.add(clase.getDeclaredMethod("isNodoPadre"));
            gettersSTA.add(clase.getDeclaredMethod("getSeguridad"));
            gettersSTA.add(clase.getDeclaredMethod("getPassword"));
            gettersSTA.add(clase.getDeclaredMethod("getPosx"));
            gettersSTA.add(clase.getDeclaredMethod("getPosy"));
            gettersSTA.add(clase.getDeclaredMethod("getRx"));
            gettersSTA.add(clase.getDeclaredMethod("getRy"));
        } catch (NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void llenarArchivo(String tipo, File archivo, boolean append) throws IOException, IllegalAccessException, InvocationTargetException
    {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(archivo,append))) {
            prueba = tipo;
            outFile.write(prueba);
            outFile.newLine();
            prueba = "";
            
            STA auxSTA = new STA();
            AccessPoint auxAP = new AccessPoint();
            Router auxR = new Router();

            if (tipo.equals("Router"))
            {
                for (Object key : router.keySet())
                {
                    auxR = (Router) router.get(key);
                    for (int i = 0; i < getters.size(); i++)
                    {
                        try
                        {
                            if (getters.get(i).getReturnType().equals(Integer.TYPE))
                            {
                                text = (int)getters.get(i).invoke(auxR);

                                if(!getters.get(i).getName().equals("getRy")) prueba = prueba + Integer.toString(text)+"-";
                                else prueba = prueba + Integer.toString(text)+"#";
                            }

                            if (getters.get(i).getReturnType().equals(String.class)) prueba = prueba +(String)getters.get(i).invoke(auxR)+"-";

                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    outFile.write(prueba);
                    outFile.newLine();
                    prueba = "";
                }
            }
            if (tipo.equals("AP"))
            {
                for (Object key : ap.keySet())
                {
                    auxAP = (AccessPoint) ap.get(key);
                    for (int i = 0; i < gettersAP.size(); i++)
                    {
                        try
                        {
                            if (gettersAP.get(i).getReturnType().equals(Integer.TYPE))
                            {
                                text = (int)gettersAP.get(i).invoke(auxAP);

                                if(!gettersAP.get(i).getName().equals("getRy")) prueba = prueba + Integer.toString(text)+"-";
                                else prueba = prueba + Integer.toString(text)+"#";
                            }

                            if (gettersAP.get(i).getReturnType().equals(String.class)) prueba = prueba +(String)gettersAP.get(i).invoke(auxAP)+"-";

                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    outFile.write(prueba);
                    outFile.newLine();
                    prueba = "";
                }
            }
            if (tipo.equals("STA"))
            {
                for (Object key : sta.keySet())
                {
                    auxSTA = (STA) sta.get(key);
                    for (int i = 0; i < gettersSTA.size(); i++)
                    {
                        try
                        {
                            if (gettersSTA.get(i).getReturnType().equals(Integer.TYPE))
                            {
                                text = (int)gettersSTA.get(i).invoke(auxSTA);
                                
                                if(!gettersSTA.get(i).getName().equals("getRy")) prueba = prueba + Integer.toString(text)+"-";
                                else prueba = prueba + Integer.toString(text)+"#";
                            }
                            
                            if (gettersSTA.get(i).getReturnType().equals(String.class)) prueba = prueba +(String)gettersSTA.get(i).invoke(auxSTA)+"-";
                            if (gettersSTA.get(i).getReturnType().equals(Boolean.TYPE)) prueba = prueba + Boolean.toString((boolean)gettersSTA.get(i).invoke(auxSTA))+"-";
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(Guardar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    outFile.write(prueba);
                    outFile.newLine();
                    prueba = "";
                }
            }
            prueba = tipo + "~~";
            outFile.write(prueba);
            outFile.newLine();
            prueba = "";
        }
    }
    
    public void guardarArchivo() throws IOException, IllegalAccessException, InvocationTargetException
    {
        guardarDialog.setDialogTitle("Guardar como ...");
        guardarDialog.setApproveButtonText("Guardar");
        
        int status = guardarDialog.showSaveDialog(null);
        File archivo = guardarDialog.getSelectedFile();
        
        if (status == JFileChooser.APPROVE_OPTION) checkArchivo(archivo);
        else 
        {
            cancel = true;
            guardarDialog.hide();
        }
    }
   
    public void checkArchivo(File archivo) throws IOException, IllegalAccessException, InvocationTargetException
    {
        if (archivo.exists())
        {
            String [] opciones = {"Si", "No","Cancelar"};
            int i = JOptionPane.showOptionDialog(this, "El archivo ya existe, ¿Desea sobreescribirlo?", "Confirmar", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (i == JOptionPane.NO_OPTION) guardarArchivo();
        }
        escribir(archivo);
    }
    
    public void escribir (File archivo) throws IOException, IllegalAccessException, InvocationTargetException
    {
        if (tipoRed.equals("Infraestructura"))
        {
            llenarArchivo("Router", archivo,false);
            llenarArchivo("AP", archivo,true);
            llenarArchivo("STA", archivo,true);
        }
        else llenarArchivo("STA", archivo,false);

        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
