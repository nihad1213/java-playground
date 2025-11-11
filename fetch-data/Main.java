import java.io.StringWriter;
import java.util.Scanner;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import src.FetchData;
import src.WriteFile;

class Main {

    private static Document document;

    public static String documentToString(Document doc) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void searchCurrency(String searchTerm) {
        if (document == null) {
            System.out.println("No data loaded!");
            return;
        }

        boolean found = false;
        NodeList valTypes = document.getElementsByTagName("ValType");

        for (int i = 0; i < valTypes.getLength(); i++) {
            Element valType = (Element) valTypes.item(i);
            String type = valType.getAttribute("Type");
            NodeList valutes = valType.getElementsByTagName("Valute");

            for (int j = 0; j < valutes.getLength(); j++) {
                Element valute = (Element) valutes.item(j);
                String code = valute.getAttribute("Code");
                String name = getElementText(valute, "Name");

                // Search by code or name (case-insensitive)
                if (code.equalsIgnoreCase(searchTerm) || 
                    name.toLowerCase().contains(searchTerm.toLowerCase())) {
                    
                    if (!found) {
                        System.out.println("\n" + "=".repeat(70));
                        System.out.println("Search Results for: " + searchTerm);
                        System.out.println("=".repeat(70));
                        found = true;
                    }

                    String nominal = getElementText(valute, "Nominal");
                    String value = getElementText(valute, "Value");

                    System.out.println("\nType: " + type);
                    System.out.println("Code: " + code);
                    System.out.println("Name: " + name);
                    System.out.println("Nominal: " + nominal);
                    System.out.println("Rate: " + value + " AZN");
                    System.out.println("-".repeat(70));
                }
            }
        }

        if (!found) {
            System.out.println("\nNo currency found matching: " + searchTerm);
        }
    }

    public static void listAllCurrencies() {
        if (document == null) {
            System.out.println("No data loaded!");
            return;
        }

        NodeList valTypes = document.getElementsByTagName("ValType");
        System.out.println("\n" + "=".repeat(70));
        System.out.println("All Available Currencies");
        System.out.println("=".repeat(70));

        for (int i = 0; i < valTypes.getLength(); i++) {
            Element valType = (Element) valTypes.item(i);
            String type = valType.getAttribute("Type");
            System.out.println("\n[" + type + "]");
            
            NodeList valutes = valType.getElementsByTagName("Valute");

            for (int j = 0; j < valutes.getLength(); j++) {
                Element valute = (Element) valutes.item(j);
                String code = valute.getAttribute("Code");
                String name = getElementText(valute, "Name");
                String value = getElementText(valute, "Value");

                System.out.printf("  %-6s - %-40s: %s AZN\n", code, name, value);
            }
        }
        System.out.println("=".repeat(70));
    }

    private static String getElementText(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent().trim();
        }
        return "";
    }

    public static void main(String[] args) {
        FetchData data = new FetchData();
        System.out.println("Fetching currency data from CBAR...");
        document = data.fetch("https://www.cbar.az/currencies/07.11.2025.xml");
        
        if (document == null) {
            System.out.println("Failed to fetch data!");
            return;
        }

        System.out.println("Data fetched successfully!");

        WriteFile writer = new WriteFile();
        writer.writeCSV(document, "currencies.csv");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n" + "=".repeat(70));
        System.out.println("Currency Exchange Rate Lookup System");
        System.out.println("=".repeat(70));

        while (running) {
            System.out.println("\nCommands:");
            System.out.println("  search <currency> - Search for a currency (by code or name)");
            System.out.println("  list              - List all currencies");
            System.out.println("  xml               - Print XML data");
            System.out.println("  exit              - Exit program");
            System.out.print("\nEnter command: ");

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "search":
                    if (parts.length > 1) {
                        searchCurrency(parts[1]);
                    } else {
                        System.out.println("Usage: search <currency>");
                    }
                    break;

                case "list":
                    listAllCurrencies();
                    break;

                case "xml":
                    System.out.println("\n" + documentToString(document));
                    break;

                case "exit":
                case "quit":
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    if (!command.equals("help")) {
                        searchCurrency(input);
                    } else {
                        System.out.println("Unknown command. Type 'exit' to quit.");
                    }
            }
        }

        scanner.close();
    }
}