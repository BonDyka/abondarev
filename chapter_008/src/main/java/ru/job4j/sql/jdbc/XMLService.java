package ru.job4j.sql.jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for work with xml.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 02.01.2018.
 */
public class XMLService {

    private final static Logger LOG = LoggerFactory.getLogger(XMLService.class);

    public void createDOM(List<Entry> entries, String fileName) {
        Element root = new Element("entries");
        Document doc = new Document(root);

        for (Entry e : entries) {
            root.addContent(new Element("entry")
                    .addContent(new Element("field").addContent(String.valueOf(e.getField()))));
        }

        writeXMLFile(doc, fileName);
    }

    public void domToXSLT(String fromXml, String toXml) {
        SAXBuilder parser = new SAXBuilder();
        File fromFile = new File(fromXml);

        try {
            Document fDoc = parser.build(fromFile);
            Element root = fDoc.getRootElement();
            List<Element> children = root.getChildren();

            root = new Element("entries");
            Document tDoc = new Document(root);
            for (Element e : children) {
                root.addContent(new Element("entry").setAttribute("field", e.getChildText("field")));
            }
            writeXMLFile(tDoc, toXml);
        } catch (JDOMException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public int sumOfAttributeValues(String fileName, String attributeName) {
        SAXBuilder parser = new SAXBuilder();
        File file = new File(fileName);
        int result = 0;
        try {
            Document doc = parser.build(file);
            List<Element> elements = doc.getRootElement().getChildren();
            for (Element e : elements) {
                result += Integer.valueOf(e.getAttributeValue(attributeName));
            }
        } catch (JDOMException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private void writeXMLFile(Document doc, String fileName) {
        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(fileName);
            outputter.output(doc, fw);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
