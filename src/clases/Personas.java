package clases;

public class Personas
{
    /*Clase que permitirá el registro de los profesores y estudiantes*/
    private String nombre, apellido, tipo;
    private int cedula, materia;

    public int getMateria()
    {
        return materia;
    }

    public void setMateria(int materia)
    {
        this.materia = materia;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public int getCedula()
    {
        return cedula;
    }

    public void setCedula(int cedula)
    {
        this.cedula = cedula;
    }
    
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
}