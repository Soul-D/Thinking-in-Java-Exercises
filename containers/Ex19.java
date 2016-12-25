package containers;

import net.mindview.util.TextFile;

import java.util.HashMap;
import java.util.Map;

public class Ex19 {
    static Map<String,Integer> countWords(String text){
        String[] words = text.split("\\W+");
        Map<String,Integer> map = new SimpleHashMap<String, Integer>();
        for (String s : words){
            if (map.get(s) == null)
                map.put(s,1);
            else
                map.put(s,map.get(s)+1);
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String,Integer> countedOccurrences = countWords(TextFile.read("src\\main\\java\\containers\\Ex19.java"));
        System.out.println(countedOccurrences);
    }
}
