package xml;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static xml.Person.format;

public class Ex31Person {
    private String first, last, address;

    public Ex31Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        Element addressNode = new Element("address");
        addressNode.appendChild(address);
        person.appendChild(firstName);
        person.appendChild(lastName);
        person.appendChild(addressNode);
        return person;
    }

    public Ex31Person(Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
        address = person.getFirstChildElement("address").getValue();
    }

    public String toString() {
        return first + " " + last + " " + address;
    }

    public static void main(String[] args) throws Exception {
        List<Ex31Person> people = Arrays.asList(
                new Ex31Person("Dr. Bunsen", "Honeydew", "Holy street"),
                new Ex31Person("Gonzo", "The Great", "Vile street"),
                new Ex31Person("Phillip J.", "Fry", "Honey street"));
        System.out.println(people);
        Element root = new Element("people");
        for (Ex31Person p : people)
            root.appendChild(p.getXML());
        Document doc = new Document(root);
        format(System.out, doc);
        format(new BufferedOutputStream(new FileOutputStream("./src/main/java/xml/People.xml")), doc);
    }
}

class Ex31People extends ArrayList<Ex31Person> {
    public Ex31People(String fileName) throws Exception {
        Document doc = new Builder().build(new File(fileName));
        Elements elements =
                doc.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++)
            add(new Ex31Person(elements.get(i)));
    }

    public static void main(String[] args) throws Exception {
        Ex31People p = new Ex31People("./src/main/java/xml/People.xml");
        System.out.println(p);
    }
}

