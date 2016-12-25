package containers;

import net.mindview.util.TextFile;

import java.util.HashMap;
import java.util.Map;

public class Ex13 {
    static AssociativeArray<String,Integer> countWords(String text){
        String[] words = text.split("\\W+");
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (String s : words){
            if (map.get(s) == null)
                map.put(s,1);
            else
                map.put(s,map.get(s)+1);
        }
        AssociativeArray<String,Integer> result = new AssociativeArray<String, Integer>(words.length);
        for (Map.Entry<String,Integer> pair : map.entrySet()){
            result.put(pair.getKey(),pair.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        AssociativeArray<String,Integer> countedOccurrences = countWords(TextFile.read("src\\main\\java\\containers\\Ex13.java"));
        System.out.println(countedOccurrences);
    }
}
