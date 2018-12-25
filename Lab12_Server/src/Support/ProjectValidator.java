package Support;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ProjectValidator {
    protected String xmlSchemaName;
    protected String dtdName;

    private File schemaFile;
    private File dtdFile;

    public ProjectValidator() {
    }

    public ProjectValidator(String dtdName, String xmlSchemaName) {
        this.xmlSchemaName = xmlSchemaName;
        this.dtdName = dtdName;
        schemaFile = new File(xmlSchemaName);
        dtdFile = new File(dtdName);
    }

    public void validateXMLBySchema(String xmlNameFile) {
        try {

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlNameFile)));

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getXmlSchemaName() {
        return xmlSchemaName;
    }

    public void setXmlSchemaName(String xmlSchemaName) {
        this.xmlSchemaName = xmlSchemaName;
    }

    public String getDtdName() {
        return dtdName;
    }

    public void setDtdName(String dtdName) {
        this.dtdName = dtdName;
    }
}
