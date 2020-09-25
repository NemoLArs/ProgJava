package xmlconverter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

public class XmlDocumentBuilder {

    private XmlDocumentBuilder() {
        throw new IllegalStateException("Util class, not to be initialized");
    }

    //Setting up the new XML document - Naming the root "Xmlconverter-root"
    static Document generateDocument(String filePath) throws ParserConfigurationException, IOException {
        Document document;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            docFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
            document = docFactory.newDocumentBuilder().newDocument();

            Element root = document.createElement("Xmlconverter-root");
            document.appendChild(root);

            appendNodes(document, new CSVData(reader), root);
        }
        return document;
    }

    //Creating the rows and split the lines on "," to make categories on every item
    static void appendNodes(Document document, CSVData csvData, Element root) {
        final String[] categories = csvData.getCategories();
        final List<String> lines = csvData.getLines();
        // Start on 1 to avoid category line
        for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
            // One tag named row for each line in CSV file
            Element row = document.createElement("row");
            final String[] values = lines.get(lineIndex).split(",");
            for (int i = 0; i < categories.length; i++) {
                final String category = categories[i];
                if (category.isEmpty()) {
                    continue;
                }
                row.appendChild(createCategoryElement(document, category, Utils.getValue(values, i)));
                root.appendChild(row);
            }
        }
    }

    //here we create the actual categories for all data in the CSV
    static Element createCategoryElement(Document doc, String category, String value) {
        final Element categoryElement = doc.createElement(category);
        categoryElement.appendChild(doc.createTextNode(value));
        return categoryElement;
    }
}
