package io;

import net.mindview.util.TextFile;

import java.util.Map;
import java.util.TreeMap;

public class Ex17 {
    static Map<Character,Integer> countChars(String filename){
        Map<Character,Integer> result = new TreeMap<Character,Integer>();
        String file = TextFile.read(filename);
        for (Character ch : file.toCharArray()){
            if (result.containsKey(ch))
                result.put(ch,result.get(ch)+1);
            else
                result.put(ch,1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(countChars("./src/main/java/io/Ex17.java"));
    }
}
