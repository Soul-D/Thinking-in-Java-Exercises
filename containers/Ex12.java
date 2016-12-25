package containers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static net.mindview.util.Print.print;

public class Ex12 {
    static void testMap(Map<String, String> map){
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object"); // Past the end
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        print(map);
        print(map.get("ocean"));
    }

    public static void main(String[] args) {
        testMap(new HashMap<String, String>());
        testMap(new TreeMap<String, String>());
        testMap(new LinkedHashMap<String, String>());
    }
}
