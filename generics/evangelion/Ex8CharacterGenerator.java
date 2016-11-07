//: generics/coffee/CoffeeGenerator.java
// Generate different types of Coffee:
package generics.evangelion;

import net.mindview.util.Generator;

import java.util.Iterator;
import java.util.Random;

public class Ex8CharacterGenerator implements Generator<StoryCharacter>, Iterable<StoryCharacter> {
  private Class[] types = { AsukaLangleySoryu.class, Ramiel.class,
    ReiAyanami.class, Sachiel.class, Shamshel.class, ShinjiIkari.class};
  private static Random rand = new Random(47);
  public Ex8CharacterGenerator() {}
  // For iteration:
  private int size = 0;
  public Ex8CharacterGenerator(int sz) { size = sz; }
  public StoryCharacter next() {
    try {
      return (StoryCharacter)
        types[rand.nextInt(types.length)].newInstance();
      // Report programmer errors at run time:
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
  class CharacterIterator implements Iterator<StoryCharacter> {
    int count = size;
    public boolean hasNext() { return count > 0; }
    public StoryCharacter next() {
      count--;
      return Ex8CharacterGenerator.this.next();
    }
    public void remove() { // Not implemented
      throw new UnsupportedOperationException();
    }
  };	
  public Iterator<StoryCharacter> iterator() {
    return new CharacterIterator();
  }
  public static void main(String[] args) {
    Ex8CharacterGenerator gen = new Ex8CharacterGenerator();
    for(int i = 0; i < 5; i++)
      System.out.println(gen.next());
    for(StoryCharacter c : new Ex8CharacterGenerator(5))
      System.out.println(c);
  }
} /* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
Mocha 4
Breve 5
Americano 6
Latte 7
Cappuccino 8
Cappuccino 9
*///:~
