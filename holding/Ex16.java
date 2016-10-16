package holding;

import net.mindview.util.TextFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Ex16 {
    public static void main(String[] args) {
        Set<Character> vowels = new HashSet<Character>(Arrays.asList('a','e','i','o','u','A','E','I','O','U'));
        Set<String> words = new TreeSet<String>(
                new TextFile("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\holding\\SetOperations.java", "\\W+"));
        int numberOfVowels = 0;
        for(String word : words){
            int count = 0;
            for(Character ch : word.toCharArray()){
                if (vowels.contains(ch))
                    count++;
            }
            System.out.println(word + " contains " + count + " vowels");
            numberOfVowels+=count;
        }
        System.out.println("Number of vowels: " + numberOfVowels);
    }
}
