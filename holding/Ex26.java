package holding;

import net.mindview.util.TextFile;

import java.util.*;

public class Ex26 {
    public static void main(String[] args) {
        Map<String,ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        int count = 0;
        for (String word : new TextFile("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\holding\\Ex26.java", "\\W+")){
            ArrayList<Integer> list = map.get(word);
            if (list == null) {
                list = new ArrayList<Integer>(Arrays.asList(++count));
                map.put(word,list);
            }
            else
                list.add(++count);
        }
        Map<Integer,String> map2 = new TreeMap<Integer, String>();
        for(Map.Entry<String,ArrayList<Integer>> pair : map.entrySet()){
            for(Integer i : pair.getValue()){
                map2.put(i,pair.getKey());
            }
        }
        System.out.println(map2);
    }
}
