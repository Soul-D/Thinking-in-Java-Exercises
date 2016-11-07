package generics;

import java.util.HashMap;
import java.util.Map;

public class Ex24ClassTypeCapture {
    private Map<String, FactoryI> map = new HashMap<String, FactoryI>();

    public void addType(String typename, FactoryI factory) {
        map.put(typename,factory);
    }

    public Object createNew(String typename) throws Exception {
        FactoryI factory = map.get(typename);
        if (factory == null)
            throw new Exception("Wrong class");
        return factory.create();
    }

    public static void main(String[] args) throws Exception {
        Ex24ClassTypeCapture ctc = new Ex24ClassTypeCapture();
        ctc.addType("Integer",new IntegerFactory());
        ctc.addType("Widget",new Widget.Factory());
        Integer integer = (Integer)ctc.createNew("Integer");
        System.out.println(integer);
        Widget widget = (Widget)ctc.createNew("Widget");
        System.out.println(widget);
    }
}
