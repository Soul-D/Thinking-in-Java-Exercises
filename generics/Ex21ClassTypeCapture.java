package generics;

import java.util.HashMap;
import java.util.Map;

public class Ex21ClassTypeCapture {
    private Map<String, Class<?>> map = new HashMap<String, Class<?>>();

    public void addType(String typename, Class<?> kind) {
        map.put(typename,kind);
    }

    public Object createNew(String typename) throws Exception {
        Class clazz = map.get(typename);
        if (clazz == null)
            throw new Exception("Wrong class");
        return clazz.newInstance();
    }

    public static void main(String[] args) throws Exception {
        Ex21ClassTypeCapture ctc = new Ex21ClassTypeCapture();
        ctc.addType("Building",Building.class);
        ctc.addType("House",House.class);
        Building building = (Building) ctc.createNew("Building");
        House house = (House) ctc.createNew("House");
        //ctc.createNew("Lalala");
    }
}
