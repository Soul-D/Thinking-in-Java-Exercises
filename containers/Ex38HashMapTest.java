package containers;

import java.util.HashMap;
import java.util.Map;

public class Ex38HashMapTest {
    static void fillMap(Map<Integer,Integer> map, int size){
        for (int i = 0; i < size; i++){
            map.put(i,i*100);
        }
    }

    static double testMap(Map<Integer,Integer> map){
        double startTime = System.nanoTime();
        for (int i = 0; i < map.size(); i++){
            map.get(i);
        }
        return (System.nanoTime() - startTime)/map.size();
    }

    public static void main(String[] args) {
        Map<Integer,Integer> map1 = new HashMap<Integer, Integer>(200);
        fillMap(map1,140); //LoadFactor = 140/200 = 0.7
        System.out.println(testMap(map1));
        Map<Integer,Integer> map2 = new HashMap<Integer, Integer>(400);
        map2.putAll(map1); //LoadFactor = 140/400 = 0.35
        System.out.println(testMap(map2));
    }
}
