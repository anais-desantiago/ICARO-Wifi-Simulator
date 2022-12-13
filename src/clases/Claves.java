package clases;

public class Claves
{
    /*Clase que permitirá guardar las claves que introduzcan los profesores*/
    
    private int cedula, materia;
    private String claveoriginal, clavenueva;

    public int getMateria()
    {
        return materia;
    }

    public void setMateria(int materia)
    {
        this.materia = materia;
    }

    public int getCedula()
    {
        return cedula;
    }

    public void setCedula(int cedula)
    {
        this.cedula = cedula;
    }

    public String getClaveoriginal()
    {
        return claveoriginal;
    }

    public void setClaveoriginal(String claveoriginal)
    {
        this.claveoriginal = claveoriginal;
    }

    public String getClavenueva()
    {
        return clavenueva;
    }

    public void setClavenueva(String clavenueva)
    {
        this.clavenueva = clavenueva;
    }
}