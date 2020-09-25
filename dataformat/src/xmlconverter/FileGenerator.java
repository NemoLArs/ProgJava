package xmlconverter;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class FileGenerator {

    private FileGenerator() {
        throw new IllegalStateException("Util class, not to be initialized");
    }
    
    //Method that creates the actual new XML file to your drive
    static void generateXMlFile(Document document) throws TransformerException, IOException {
        File file = new File(UIFrame.savepath);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(getXmlFile(document));
            UIFrame.displaySuccess();
        }
    }
    
    
    //Method that organize the XML file so we can creating it
    static String getXmlFile(Document document) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));

        return writer.getBuffer().toString();
    }

}
