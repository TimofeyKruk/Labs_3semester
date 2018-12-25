package Support;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class SupportXML implements Serializable {
    ProjectValidator projectValidator;

    public SupportXML(ProjectValidator projectValidator) {
        this.projectValidator = projectValidator;
    }


    public ProjectValidator getProjectValidator() {
        return projectValidator;
    }

    public void setProjectValidator(ProjectValidator projectValidator) {
        this.projectValidator = projectValidator;
    }

    //+++++++++++++++ MAKE XML ++++++++++++++++
    public String makeXMLFile(Object message) throws Exception {
        Field[] fields = message.getClass().getFields();
        int quantityOfFields = fields.length;

        String fileName;
        fileName = message.getClass().getName();
        fileName = fileName + "_" + (String) fields[0].getName();

        for (int i = 1; i < quantityOfFields; ++i)
            fileName = fileName + '_' + fields[i].getName();

        fileName += ".xml";


        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter streamWriter = outputFactory.createXMLStreamWriter(new FileWriter(fileName));

        streamWriter.writeStartDocument("1.0");
        streamWriter.writeCharacters("\n");

        streamWriter.writeStartElement(message.getClass().getName());

        for (Field field : fields) {
            streamWriter.writeStartElement(field.getName());
            streamWriter.writeCharacters((String) field.get(message));
            streamWriter.writeEndElement();
            // streamWriter.writeCharacters("\n");
        }

        streamWriter.writeEndElement();
        streamWriter.writeCharacters("\n");


        streamWriter.writeEndDocument();
        streamWriter.flush();

        return fileName;
    }

    //+++++++++++++++ READ from XML ++++++++++++++++
    public Object readFromXMLFile(String fileXMLName) throws Exception {

        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = docBuilder.parse(new File(fileXMLName));

        this.projectValidator.validateXMLBySchema(fileXMLName);

        Node root = document.getDocumentElement();

        String className = root.getNodeName();

        //creating object for specified class
        Class<?> myClass = Class.forName(className);


        ArrayList<String> nodes = new ArrayList<>();
        Node child = root.getFirstChild();
        nodes.add(child.getTextContent());

        Node newSibling = child;
        while ((newSibling = newSibling.getNextSibling()) != null)
            nodes.add(newSibling.getTextContent());

        // Finding constructor
        int n = nodes.size();
        Constructor<?> myConstructor;
        Object myObject;

        // ????????????????????????? HOW TO MAKE It BETTER????????????????????????????
        switch (n) {
            case 1:
                myConstructor = myClass.getConstructor(String.class);
                myObject = myConstructor.newInstance(nodes.get(0));
                break;
            case 2:
                myConstructor = myClass.getConstructor(String.class, String.class);
                myObject = myConstructor.newInstance(nodes.get(0), nodes.get(1));
                break;
            case 3:
                myConstructor = myClass.getConstructor(String.class, String.class, String.class);
                myObject = myConstructor.newInstance(nodes.get(0), nodes.get(1), nodes.get(2));
                break;
            default:
                throw new Exception("Not existing quantity if parameters in constructor");
        }

        return myObject;
    }


}
