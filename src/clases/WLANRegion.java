package clases;

public class WLANRegion
{
    /*Clase para determinar la región que se está utilizando*/
    int indice = 0;
    String region = "", region5G = "";
    
    public String getRegion(int indice)
    {
        if (indice == 1) region = "USA";
        if (indice == 2) region = "Europa";
        if (indice == 3) region = "Japón";
        return region;
    }
    
    public String getRegion5GHz(int indice, String modo)
    {
        if (modo.equals("802.11a"))
        {
            if (indice == 1) region5G = "América";
            if (indice == 2) region5G = "Australia";
            if (indice == 3) region5G = "China";
            if (indice == 4) region5G = "Europa";
            if (indice == 5) region5G = "Japón";
        }
        else
        {
            if (indice == 1) region5G = "América";
            if (indice == 2) region5G = "Europa";
            if (indice == 3) region5G = "China";
            if (indice == 4) region5G = "Israel";
            if (indice == 5) region5G = "Japón";
        }
        return region5G;
    }
}
