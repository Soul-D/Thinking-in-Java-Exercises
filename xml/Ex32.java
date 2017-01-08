package xml;

import net.mindview.util.TextFile;
import nu.xom.Document;
import nu.xom.Element;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import static xml.Person.format;

public class Ex32 {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> res = new HashMap<String, Integer>();
        for (String word : new TextFile("./src/main/java/xml/Ex32.java", "\\W+"))
            res.put(word, res.get(word) == null ? 1 : res.get(word) + 1);
        saveMap(res);
    }

    public static <K, V> void saveMap(Map<K, V> map) throws Exception {
        Element mapElem = new Element("map");
        for (Map.Entry<K, V> pair : map.entrySet()) {
            Element entry = new Element("entry");
            Element key = new Element("key");
            key.appendChild(pair.getKey().toString());
            Element value = new Element("value");
            value.appendChild(pair.getValue().toString());
            entry.appendChild(key);
            entry.appendChild(value);
            mapElem.appendChild(entry);
        }
        Document doc = new Document(mapElem);
        format(new BufferedOutputStream(new FileOutputStream("./src/main/java/xml/Map.xml")), doc);
    }
}
