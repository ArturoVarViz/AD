/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package xmlproba0;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;

public class XMLproba0 {
    public static void main(String[] args) throws Exception {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("autores.xml"));

        writer.writeStartDocument("1.0");
        writer.writeStartElement("autores");

        writer.writeStartElement("autor");
        writer.writeAttribute("codigo", "a1");

        writer.writeStartElement("nome");
        writer.writeCharacters("Alexandre Dumas");
        writer.writeEndElement();

        writer.writeStartElement("titulo");
        writer.writeCharacters("El conde de montecristo");
        writer.writeEndElement();

        writer.writeStartElement("titulo");
        writer.writeCharacters("Los miserables");
        writer.writeEndElement();

        writer.writeEndElement(); // peche de 'autor'

        writer.writeStartElement("autor");
        writer.writeAttribute("codigo", "a2");

        writer.writeStartElement("nome");
        writer.writeCharacters("Fiodor Dostoyevski");
        writer.writeEndElement();

        writer.writeStartElement("titulo");
        writer.writeCharacters("El idiota");
        writer.writeEndElement();

        writer.writeStartElement("titulo");
        writer.writeCharacters("Noches blancas");
        writer.writeEndElement();

        writer.writeEndElement(); // peche de 'autor'

        writer.writeEndElement(); // peche de 'autores'
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
}
