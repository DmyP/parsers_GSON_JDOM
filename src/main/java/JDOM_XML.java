import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entityXML.Catalog;
import entityXML.Notebook;
import entityXML.Person;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class JDOM_XML {
    private static final String xmlFilePath = "created.xml";

    public static void main(String[] args) {
        //Writing to XML
        writeToXMLFile();
        //Get collection
        Catalog catalog = parseXMLFileToJava();
        //Printing
        System.out.println(catalog);
    }

    private static void writeToXMLFile() {
        System.out.println("Writing new file");
        try {
            Element catalog = new Element("catalog");
            Document doc = new Document(catalog);
            doc.setRootElement(catalog);
            Element notebook = new Element("notebook");

            Element person = new Element("person");
            person.setAttribute(new Attribute("id", "1"));
            person.addContent(new Element("name").setText("John"));
            person.addContent(new Element("address").setText("sadasdas"));
            person.addContent(new Element("cash").setText("99999"));
            person.addContent(new Element("education").setText("sa"));

            Element person2 = new Element("person");
            person2.setAttribute(new Attribute("id", "2"));
            person2.addContent(new Element("name").setText("AAaa"));
            person2.addContent(new Element("address").setText("sadasdas"));
            person2.addContent(new Element("cash").setText("99999"));
            person2.addContent(new Element("education").setText("sa"));


            Element person3 = new Element("person");
            person3.setAttribute(new Attribute("id", "3"));
            person3.addContent(new Element("name").setText("Bbb"));
            person3.addContent(new Element("address").setText("sadasdas"));
            person3.addContent(new Element("cash").setText("2222"));
            person3.addContent(new Element("education").setText("sa"));

            notebook.addContent(person);
            notebook.addContent(person2);
            notebook.addContent(person3);
            catalog.addContent(notebook);

            XMLOutputter xmlOutput = new XMLOutputter();

            // display nice nice
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(xmlFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File Saved!");
    }

    private static Catalog parseXMLFileToJava(){
        System.out.println("Parsing file");
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(xmlFilePath);
        List<Person> personList = new ArrayList<>();
        Notebook notebook = new Notebook();
        Catalog catalog = new Catalog();

        try {
            Document document = builder.build(xmlFile);
            Element root = document.getRootElement();
            Element nb = root.getChild("notebook");
            List<Element> persons = nb.getChildren("person");

            for (int i = 0; i < persons.size(); i++) {
                Element element = persons.get(i);
                Person parsedPerson = new Person(
                        Long.parseLong(element.getAttribute("id").getValue()),
                        element.getChildText("name"),
                        element.getChildText("address"),
                        Double.parseDouble(element.getChildText("cash")),
                        element.getChildText("education"));
                personList.add(parsedPerson);
            }
            notebook.setPersons(personList);
            catalog.setNotebook(notebook);

        } catch (IOException | JDOMException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("File succesfully parsed");
        return catalog;
    }
}