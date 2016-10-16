package holding;

import net.mindview.util.TextFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ex25 {
    public static void main(String[] args) {
        Map<String,ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        int count = 0;
        for (String word : new TextFile("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\holding\\Ex25.java", "\\W+")){
            ArrayList<Integer> list = map.get(word);
            if (list == null) {
                list = new ArrayList<Integer>(Arrays.asList(++count));
                map.put(word,list);
            }
            else
                list.add(++count);

        }
        System.out.println(map);
    }
}
