/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.ArrayList;

/**
 *
 * @author Any
 */
public class Debug {
    //public Debug();
    
    public void debugsta(ArrayList <STA> stations)
    {
        for (int i=0 ; i<stations.size(); i++)
        {
            System.out.println("Object: "+stations.get(i).getID()+"Posicion " + i);
        }
    }
    
    public void debugAnimacion (ArrayList <Animacion> animaciones)
    {
        for (int i=0 ; i<animaciones.size(); i++)
        {
            System.out.println("Animacion: "+animaciones.get(i).getNombre()+" Posicion " + i);
        }
    }
}
