package src;

import java.io.FileWriter;
import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WriteFile {
    
    public void writeCSV(Document doc, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Type,Code,Nominal,Name,Value\n");
            
            NodeList valTypes = doc.getElementsByTagName("ValType");
            
            for (int i = 0; i < valTypes.getLength(); i++) {
                Element valType = (Element) valTypes.item(i);
                String type = valType.getAttribute("Type");
                
                NodeList valutes = valType.getElementsByTagName("Valute");
                
                for (int j = 0; j < valutes.getLength(); j++) {
                    Element valute = (Element) valutes.item(j);
                    String code = valute.getAttribute("Code");
                    
                    String nominal = getElementText(valute, "Nominal");
                    String name = getElementText(valute, "Name");
                    String value = getElementText(valute, "Value");
                    
                    writer.append(String.format("%s,%s,%s,\"%s\",%s\n", 
                        type, code, nominal, name, value));
                }
            }
            
            System.out.println("CSV file created successfully: " + filename);
            
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }
}