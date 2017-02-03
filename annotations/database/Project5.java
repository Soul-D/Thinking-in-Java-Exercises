package annotations.database;

import nu.xom.*;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static xml.Person.format;

public class Project5 {
    private static Map<Class, XMLInfo> mapXML = new HashMap<Class, XMLInfo>();

    public static <T> void saveToXML(String filename, List<T> objects) throws Exception {
        if (objects == null || objects.size() < 1) {
            System.out.println("List of objects is needed");
        }
        Class<?> clazz = objects.get(0).getClass();
        XMLInfo xmlInfo = getXMLInfo(clazz);
        if (xmlInfo == null) {
            System.out.println("Class " + clazz.getSimpleName() + " doesn't have XMLElement annotation");
            return;
        }
        Element root = new Element(xmlInfo.name + "List");
        for (T t : objects) {
            Element element = new Element(xmlInfo.name);
            for (FieldInfo fieldInfo : xmlInfo.fieldInfoList) {
                Element field = new Element(fieldInfo.name);
                field.appendChild(fieldInfo.field.get(t).toString());
                element.appendChild(field);
            }
            root.appendChild(element);
        }
        Document doc = new Document(root);
        format(new BufferedOutputStream(new FileOutputStream(filename)), doc);
    }

    public static <T> List<T> loadFromXML(String filename, Class<T> clazz) throws ParsingException, IOException, IllegalAccessException, InstantiationException {
        XMLInfo xmlInfo = getXMLInfo(clazz);
        if (xmlInfo == null) {
            System.out.println("Class " + clazz.getSimpleName() + " doesn't have XMLElement annotation");
            return null;
        }
        List<T> res = new ArrayList<T>();
        Document doc = new Builder().build(new File(filename));
        Elements elements = doc.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++){
            T obj = clazz.newInstance();
            Element el = elements.get(i);
            for (FieldInfo f : xmlInfo.fieldInfoList){

                f.field.set(obj,convert(f.field.getType(),el.getFirstChildElement(f.name).getValue()));
            }
            res.add(obj);
        }
        return res;
    }

    private static Object convert(Class<?> targetType, String text) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(text);
        return editor.getValue();
    }

    private static <T> XMLInfo getXMLInfo(Class<T> clazz) {
        XMLInfo res = mapXML.get(clazz);
        if (res != null)
            return res;
        XMLElement xmlElement = clazz.getAnnotation(XMLElement.class);
        if (xmlElement == null)
            return null;
        res = new XMLInfo(xmlElement.value());
        for (Field field : clazz.getDeclaredFields()) {
            xmlElement = field.getAnnotation(XMLElement.class);
            if (xmlElement != null)
                res.fieldInfoList.add(new FieldInfo(field, xmlElement.value()));
        }
        mapXML.put(clazz, res);
        return res;
    }

    private static class FieldInfo {
        public Field field;
        public String name;

        public FieldInfo(Field field, String name) {
            this.field = field;
            this.name = name;
        }
    }

    private static class XMLInfo {
        public String name;
        public List<FieldInfo> fieldInfoList;

        public XMLInfo(String name) {
            this.name = name;
            fieldInfoList = new ArrayList<FieldInfo>();
        }
    }

    public static void main(String[] args) throws Exception {
        /*List<P5Hero> heroes = new ArrayList<P5Hero>();
        heroes.add(new P5Hero("Golgo 13", 35, true, "Our universe"));
        heroes.add(new P5Hero("Iron man", 35, false, "Marvel"));
        saveToXML("./src/main/java/annotations/database/P5XML", heroes);*/
        System.out.println(loadFromXML("./src/main/java/annotations/database/P5XML",P5Hero.class));
    }
}

@XMLElement("Hero")
class P5Hero {
    @XMLElement("Name")
    String name;
    @XMLElement("Age")
    Integer age;
    @XMLElement("Cool")
    Boolean isCool;
    @XMLElement("Universe")
    String universe;

    public P5Hero(String name, Integer age, Boolean isCool, String universe) {
        this.name = name;
        this.age = age;
        this.isCool = isCool;
        this.universe = universe;
    }

    public P5Hero() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("P5Hero{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", isCool=").append(isCool);
        sb.append(", universe='").append(universe).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface XMLElement {
    String value();
}
