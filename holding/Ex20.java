package holding;

import net.mindview.util.TextFile;

import java.util.*;

public class Ex20 {
    public static void main(String[] args) {
        Set<Character> vowels = new HashSet<Character>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        Map<Character,Integer> vowelsCountMap = new HashMap<Character, Integer>();
        Set<String> words = new TreeSet<String>(
                new TextFile("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\holding\\SetOperations.java", "\\W+"));
        int numberOfVowels = 0;
        for(String word : words){
            int count = 0;
            for(Character ch : word.toCharArray()){
                if (vowels.contains(ch)) {
                    Integer i = vowelsCountMap.get(ch);
                    vowelsCountMap.put(ch,i == null ? 1 : i+1);
                    count++;
                }
            }
            System.out.println(word + " contains " + count + " vowels");
            numberOfVowels+=count;
        }
        System.out.println("Number of vowels: " + numberOfVowels);
        System.out.println(vowelsCountMap);
    }
}
