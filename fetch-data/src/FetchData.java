package src;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FetchData {
    public Document fetch(String xmlUrl) {
        Document doc = null;
        try {
            URL url = new URL(xmlUrl);
            URLConnection conn = url.openConnection();
            conn.connect();

            InputStream inputStream = conn.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(inputStream);

            inputStream.close();
        } catch(IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return doc;
    }
}