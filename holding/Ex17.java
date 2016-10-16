package holding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Ex17 {
    public static void main(String[] args) {
        Map<String, Ex1Gerbil> gebrils = new HashMap();
        gebrils.put("Fuzzy",new Ex1Gerbil(1));
        gebrils.put("Spot",new Ex1Gerbil(2));
        gebrils.put("Kawai",new Ex1Gerbil(3));
        Iterator<String> iterator = gebrils.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            System.out.print(key + " ");
            gebrils.get(key).hop();
        }
    }
}
