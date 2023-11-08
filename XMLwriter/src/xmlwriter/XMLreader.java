package xmlwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import javax.xml.stream.XMLStreamConstants;
import serializacion2.*;

public class XMLreader {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, XMLStreamException {
         FileInputStream obxFile = new FileInputStream("produtos.xml");
        XMLInputFactory obxFactory = XMLInputFactory.newInstance();
        XMLStreamReader obxReader = obxFactory.createXMLStreamReader(obxFile);
        
        int numero;
        String element = "";
        
        
        while(obxReader.hasNext()){
            obxReader.next();
            numero = obxReader.getEventType();
            
            if(numero == XMLStreamConstants.START_ELEMENT){
                element = obxReader.getLocalName();
                if (element.equals("autor")){
                    System.out.println(obxReader.getAttributeValue(0));
                }else if(element.equals("nome") || element.equals("titulo")){
                    System.out.println("\t" + obxReader.getElementText());
                }
            }
            
        }
        
}
