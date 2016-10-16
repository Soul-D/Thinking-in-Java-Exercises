//: holding/UniqueWords.java
package holding; /* Added by Eclipse.py */

import net.mindview.util.TextFile;

import java.util.*;

class Ex22Word implements Comparable<Ex22Word>{
  String word;
  int count;

  public Ex22Word(String word) {
    this.word = word;
    this.count = 1;
  }

  @Override
  public String toString() {
    return "word='" + word + '\'' +
            ", count=" + count +
            '}';
  }

  public int compareTo(Ex22Word o) {
    return this.word.toLowerCase().compareTo(o.word.toLowerCase());
  }
}

public class Ex22 {
  public static void main(String[] args) {
    Set<Ex22Word> occurence = new TreeSet<Ex22Word>();
    for(String word : new TextFile("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\holding\\Ex22.java", "\\W+")){
      for(Ex22Word wordClass : occurence){
        if (wordClass.word.equals(word)) {
          wordClass.count = wordClass.count + 1;
          continue;
        }
      }
      occurence.add(new Ex22Word(word));
    }
    System.out.println(occurence);
  }
} /* Output:
[A, B, C, Collections, D, E, F, G, H, HashSet, I, J, K, L, M, N, Output, Print, Set, SetOperations, String, X, Y, Z, add, addAll, added, args, class, contains, containsAll, false, from, holding, import, in, java, main, mindview, net, new, print, public, remove, removeAll, removed, set1, set2, split, static, to, true, util, void]
*///:~
