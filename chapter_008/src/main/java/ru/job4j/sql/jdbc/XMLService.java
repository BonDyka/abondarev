package ru.job4j.sql.jdbc;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;

/**
 * Class for work with xml.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 02.01.2018.
 */
public class XMLService {

    private static final Logger LOG = LoggerFactory.getLogger(XMLService.class);

    private static final XMLOutputFactory oFactory = XMLOutputFactory.newInstance();
    private static final XMLInputFactory iFactory = XMLInputFactory.newInstance();

    /**
     * Creates xml document from {@link List<Entry>} specified as parameter and
     * write it to 1.xml file.
     *
     * @param entries the list of entry.
     * @param fileName the output file.
     */
    public void createXML(List<Entry> entries, String fileName) {

        XMLStreamWriter writer;
        try (FileWriter fw = new FileWriter(fileName)) {
            writer = oFactory.createXMLStreamWriter(fw);
            writer.writeStartDocument();
            writer.writeStartElement("entries");
            for (Entry e : entries) {
                writer.writeStartElement("entry");
                writer.writeStartElement("field");
                writer.writeCharacters(String.valueOf(e.getField()));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void xmlToXSLT(String fromXml, String toXml) {
        List<String> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fromXml);
             FileWriter fw = new FileWriter(toXml)) {
            XMLStreamReader reader = iFactory.createXMLStreamReader(fis);
            int event;
            while (reader.hasNext()) {
                event = reader.next();
                if (event == XMLEvent.START_ELEMENT && "field".equals(reader.getLocalName())) {
                    data.add(reader.getElementText());
                }
            }
            reader.close();
            XMLStreamWriter writer = oFactory.createXMLStreamWriter(fw);
            writer.writeStartDocument();
            writer.writeStartElement("entries");
            for (String str : data) {
                writer.writeStartElement("entry");
                writer.writeAttribute("field", str);
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (XMLStreamException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public int sumOfAttributeValues(String fileName) {
        int result = 0;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            XMLStreamReader reader = iFactory.createXMLStreamReader(fis);
            int event;
            while (reader.hasNext()) {
                event = reader.next();
                if (event == XMLEvent.START_ELEMENT && "entry".equals(reader.getLocalName())) {
                    result += Integer.valueOf(reader.getAttributeValue(0));
                }
            }
        } catch (XMLStreamException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
