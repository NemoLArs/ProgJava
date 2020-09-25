package xmlconverter;

import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class XmlConverter {
	
	//Gets called when user press the Convert now button - Starts the program and getting the file from the selected filepath
    public static void convertCSV() throws IOException, ParserConfigurationException, TransformerException {
        Document document = XmlDocumentBuilder.generateDocument(UIFrame.filepath);
        FileGenerator.generateXMlFile(document);
    }

}
