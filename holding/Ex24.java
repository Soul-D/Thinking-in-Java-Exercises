package holding;

import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;

public class Ex24 {
    public static void main(String[] args) {
        LinkedHashMap<String,Integer> lhm = new LinkedHashMap<String, Integer>();
        lhm.put("1",1);
        lhm.put("3",1);
        lhm.put("2",1);
        lhm.put("11",1);
        lhm.put("10",1);
        System.out.println(lhm);
        Set<String> keys = new TreeSet<String>(lhm.keySet());
        for(String key : keys){
            lhm.put(key,lhm.get(key));
        }
        System.out.println(lhm);
    }
}
