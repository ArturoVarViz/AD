/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlwriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import serializacion2.*;
public class XMLwriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, XMLStreamException {
               XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("produtos.xml"));

        writer.writeStartDocument("1.0");
        writer.writeStartElement("produtos");
        Product product = new Product();
         FileInputStream fis = new FileInputStream("/home/arturo/NetBeansProjects/Serializacion2/products.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Leemos e imprimimos los objetos Product del archivo hasta que llegamos al null
        Product obj;
        while ((obj = (Product) ois.readObject()) != null) {
                     
         String cod = obj.getCod();
         
         writer.writeStartElement("product");
         writer.writeAttribute("cod", obj.getCod());
         writer.writeAttribute("desc", obj.getDesc());
         writer.writeAttribute("prezo", Integer.toString(obj.getPrezo()));
         // Engade aquí outros atributos do produto...
         writer.writeEndElement(); 
         
         
         
        }
        writer.writeEndElement(); // produtos
        writer.writeEndDocument();
        writer.flush();
        writer.close();

        // Cerramos el flujo de entrada
        ois.close();
    }
    
}
