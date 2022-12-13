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

public class PersonasDAL
{
    /*Clase que almacena las personas en el XML*/
    private static String obtenerNodoPersonaValor(String strTag, Element ePersona)
    {
        Node nValor = (Node)ePersona.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    public ArrayList <Personas> obtenerPersonas()
    {
        ArrayList<Personas> listaPersonas = new ArrayList<>();
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/xmlsrc/basededatos.xml"));
            doc.getDocumentElement().normalize();
            NodeList nodosPersonas = doc.getElementsByTagName("personas");
            for (int i=0; i<nodosPersonas.getLength();i++)
            {
                Node persona = nodosPersonas.item(i);
                if (persona.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element unElemento = (Element) persona;
                    Personas objPersona = new Personas();
                    objPersona.setNombre(obtenerNodoPersonaValor("nombre", unElemento));
                    listaPersonas.add(objPersona);
                    objPersona.setApellido(obtenerNodoPersonaValor("apellido", unElemento));
                    listaPersonas.add(objPersona);
                    objPersona.setCedula(Integer.parseInt(obtenerNodoPersonaValor("cedula", unElemento)));
                    listaPersonas.add(objPersona);
                    objPersona.setMateria(Integer.parseInt(obtenerNodoPersonaValor("materia", unElemento)));
                    listaPersonas.add(objPersona);
                }        
            }
          }catch ( ParserConfigurationException | SAXException | IOException parseE) {
              JOptionPane.showMessageDialog(null, parseE.getMessage(), "" + "Error", JOptionPane.ERROR_MESSAGE); 
          }
        return listaPersonas;
    }
    public void agregarPersona(Personas person)
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("src/xmlsrc/basededatos.xml"));
            doc.getDocumentElement().normalize();
            
            Node nodoRaiz = doc.getDocumentElement();
            
            Element nuevaPersona = doc.createElement("personas");
            Element nuevoNombre = doc.createElement("nombre");
            nuevoNombre.setTextContent(person.getNombre());
            Element nuevoApellido = doc.createElement("apellido");
            nuevoApellido.setTextContent(person.getApellido());
            Element nuevaMat = doc.createElement("materia");
            nuevaMat.setTextContent("" +person.getMateria());
            Element nuevaCedula = doc.createElement("cedula");
            nuevaCedula.setTextContent("" +person.getCedula()); //para valores enteros
            Element nuevoTipo = doc.createElement("tipo");
            nuevoTipo.setTextContent(person.getTipo());
            
            /*Relacion etiquetas*/
            nuevaPersona.appendChild(nuevoNombre);
            nuevaPersona.appendChild(nuevoApellido);
            nuevaPersona.appendChild(nuevaCedula);
            nuevaPersona.appendChild(nuevaMat);
            nuevaPersona.appendChild(nuevoTipo);
            nodoRaiz.appendChild(nuevaPersona);
            
            /*Transformación a una estructura XML*/
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/xmlsrc/basededatos.xml"));
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
