//: holding/NonCollectionSequence.java
package holding; /* Added by Eclipse.py */

import typeinfo.pets.Pet;

import java.util.*;

public class Ex32 extends PetSequence implements Iterable<Pet> {
  public Iterator<Pet> iterator() {
    return new Iterator<Pet>() {
      private int index = 0;
      public boolean hasNext() {
        return index < pets.length;
      }
      public Pet next() { return pets[index++]; }
      public void remove() { // Not implemented
        throw new UnsupportedOperationException();
      }
    };
  }
  public Iterable<Pet> reversed() {
    return new Iterable<Pet>() {
      public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
          private int index = pets.length - 1;

          public boolean hasNext() {
            return index > -1;
          }

          public Pet next() {
            return pets[index--];
          }

          public void remove() { // Not implemented
            throw new UnsupportedOperationException();
          }
        };
      }
    };
  }
  public Iterable<Pet> randomized(){
    return new Iterable<Pet>() {
      public Iterator<Pet> iterator() {
        List<Pet> list = new ArrayList<Pet>(Arrays.asList(pets));
        Collections.shuffle(list);
        return list.iterator();
      }
    };
  }
  public static void main(String[] args) {
    Ex32 nc = new Ex32();
    InterfaceVsIterator.display(nc.iterator());
    for(Pet pet : nc)
      System.out.print(pet + " ");
    System.out.println();
    for(Pet pet : nc.reversed())
      System.out.print(pet + " ");
    System.out.println();
    for(Pet pet : nc.randomized())
      System.out.print(pet + " ");
    System.out.println();
  }
} /* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
*///:~
