/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlreader;

/**
 *
 * @author arturo
 */
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;

public class XMLreader {
    public static void main(String[] args) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileReader("/home/arturo/NetBeansProjects/AD/XMLwriter/produtos.xml"));

        while (reader.hasNext()) {
            int eventType = reader.next();

            if (eventType == XMLStreamReader.START_ELEMENT) {
                String elementName = reader.getLocalName();

                if (elementName.equals("product")) {
                    String codigo = reader.getAttributeValue(0);
                    System.out.println("Código do producto: " + codigo);
                } else if (elementName.equals("desc") || elementName.equals("prezo")) {
                    String text = reader.getElementText();
                    System.out.println(elementName + ": " + text);
                }
            }
        }

        reader.close();
    }
}