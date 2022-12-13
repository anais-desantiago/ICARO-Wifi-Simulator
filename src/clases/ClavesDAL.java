package clases;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.swing.JOptionPane;

public class ClavesDAL
{
    /*Clase que permite guardar las claves en el XML*/
    private static String obtenerNodoValorClave(String strTag, Element eClave)
    {
        Node nValor=(Node)eClave.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    public ArrayList <Claves> obtenerClaveOriginal()
    {
        ArrayList<Claves> listaClaves = new ArrayList<>();
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc= docBuilder.parse(new File("src/xmlsrc/claves.xml"));
            doc.getDocumentElement().normalize();
            NodeList nodosClaves = doc.getElementsByTagName("claves");
            for (int i=0; i<nodosClaves.getLength();i++)
            {
                Node clave = nodosClaves.item(i);
                if (clave.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element unElemento = (Element) clave;
                    Claves objClave = new Claves();
                    objClave.setClaveoriginal(obtenerNodoValorClave("claveoriginal", unElemento));
                    listaClaves.add(objClave);
                }        
            }
          }catch ( ParserConfigurationException | SAXException | IOException parseE) {
              JOptionPane.showMessageDialog(null, parseE.getMessage(), "" + "Error", JOptionPane.ERROR_MESSAGE); 
          }
        return listaClaves;
    }
    
    public ArrayList <Claves> obtenerClaveNueva()
    {
        ArrayList<Claves> listaClavesNuevas = new ArrayList<>();
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc= docBuilder.parse(new File("src/xmlsrc/claves.xml"));
            doc.getDocumentElement().normalize();
            NodeList nodosClaves = doc.getElementsByTagName("clavesPersonas");
            for (int i=0; i<nodosClaves.getLength();i++)
            {
                Node clave = nodosClaves.item(i);
                if (clave.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element unElemento = (Element) clave;
                    Claves objClave = new Claves();
                    objClave.setClavenueva(obtenerNodoValorClave("clavenueva", unElemento));
                    listaClavesNuevas.add(objClave);
                    objClave.setMateria(Integer.parseInt(obtenerNodoValorClave("materia", unElemento)));
                    listaClavesNuevas.add(objClave);
                    objClave.setCedula(Integer.parseInt(obtenerNodoValorClave("cedula",unElemento)));
                    listaClavesNuevas.add(objClave);
                }        
            }
          }catch ( ParserConfigurationException | SAXException | IOException parseE) {
              JOptionPane.showMessageDialog(null, parseE.getMessage(), "" + "Error", JOptionPane.ERROR_MESSAGE); 
          }
        return listaClavesNuevas;
    }
    
     public void agregarClave(Claves clave)
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/xmlsrc/claves.xml"));
            doc.getDocumentElement().normalize();
            
            Node nodoRaiz= doc.getDocumentElement();
            
            Element nuevaClave = doc.createElement("clavesPersonas");
            Element nuevaCedula = doc.createElement("cedula");
            nuevaCedula.setTextContent(""+clave.getCedula());
            Element nuevaClaveNueva=doc.createElement("clavenueva");
            nuevaClaveNueva.setTextContent(clave.getClavenueva());
            Element nuevaMat=doc.createElement("materia");
            nuevaMat.setTextContent(""+clave.getMateria());
            
            /*Relacion etiquetas*/
            nuevaClave.appendChild(nuevaCedula);
            nuevaClave.appendChild(nuevaClaveNueva);
            nuevaClave.appendChild(nuevaMat);
            
            nodoRaiz.appendChild(nuevaClave);
            
            /*Transformación a una estructura XML*/
            TransformerFactory transFactory=TransformerFactory.newInstance();
            Transformer transformer=transFactory.newTransformer();
            DOMSource source =new DOMSource(doc);
            StreamResult result= new StreamResult(new File("src/xmlsrc/claves.xml"));
            transformer.transform(source,result);
            
        }catch ( ParserConfigurationException | SAXException | IOException parseE) {
              System.out.println(parseE.getMessage());
        }catch(TransformerConfigurationException transE)
        {
            System.out.println(transE.getMessage());
        }catch (TransformerException transformE)
        {
            System.out.println(transformE.getMessage());
        }
    }
}
