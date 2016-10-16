//: holding/UniqueWords.java
package holding; /* Added by Eclipse.py */

import net.mindview.util.TextFile;

import java.util.*;

public class Ex21 {
  public static void main(String[] args) {
    List<String> words = new ArrayList<String>(
      new TextFile("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\holding\\Ex21.java", "\\W+"));
    Map<String,Integer> occurrence = new HashMap<String, Integer>();
    for(String word : words){
      Integer count = occurrence.get(word);
      occurrence.put(word,count == null ? 1 : count+1);
    }
    ArrayList<String> listWords = new ArrayList<String>(occurrence.keySet());
    Collections.sort(listWords,String.CASE_INSENSITIVE_ORDER);
    for(String s : listWords){
      System.out.print(s + " " + occurrence.get(s));
      System.out.println();
    }
  }
} /* Output:
[A, B, C, Collections, D, E, F, G, H, HashSet, I, J, K, L, M, N, Output, Print, Set, SetOperations, String, X, Y, Z, add, addAll, added, args, class, contains, containsAll, false, from, holding, import, in, java, main, mindview, net, new, print, public, remove, removeAll, removed, set1, set2, split, static, to, true, util, void]
*///:~
