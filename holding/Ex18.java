package holding;

import java.util.*;

public class Ex18 {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        map.put(5,5);
        map.put(4,5);
        map.put(1,5);
        map.put(7,5);
        map.put(3,5);
        System.out.println(map);
        Integer[] keys = map.keySet().toArray(new Integer[0]);
        Arrays.sort(keys);
        Map<Integer,Integer> lmap = new LinkedHashMap<Integer, Integer>();
        for(int key : keys){
            lmap.put(key,map.get(key));
        }
        System.out.println(lmap);
        lmap.clear();
        for (int i = keys.length-1;i>=0;i--){
            lmap.put(keys[i],map.get(keys[i]));
        }
        System.out.println(lmap);
    }
}
