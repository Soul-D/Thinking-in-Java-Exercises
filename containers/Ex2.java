package containers;

import net.mindview.util.Countries;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Ex2 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>();
        Set<String> set = new HashSet<String>();
        for (Map.Entry<String,String> entry : Countries.capitals().entrySet()){
            if (entry.getKey().charAt(0) == 'A'){
                map.put(entry.getKey(),entry.getValue());
                set.add(entry.getKey());
            }
        }
        System.out.println(map);
        System.out.println(set);
    }
}
