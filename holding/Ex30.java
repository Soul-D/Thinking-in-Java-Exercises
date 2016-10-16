//: holding/CollectionSequence.java
package holding; /* Added by Eclipse.py */

import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.*;

public class Ex30
implements Collection<Pet> {
  private Pet[] pets = Pets.createArray(8);
  public int size() { return pets.length; }
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

  public boolean isEmpty() {
    return pets.length == 0;
  }

  public boolean contains(Object o) {
    for(Pet pet : pets){
      if (pet.equals(o))
        return true;
    }
    return false;
  }

  public Object[] toArray() {
    return pets.clone();
  }

  public <T> T[] toArray(T[] a) {
    return (T[])pets.clone();
  }

  public boolean add(Pet pet) {
    Pet[] newPet = new Pet[pets.length+1];
    System.arraycopy(pets,0,newPet,0,pets.length);
    newPet[newPet.length-1]=pet;
    pets = newPet;
    return true;
  }

  public boolean remove(Object o) {
    boolean flag = false;
    List<Object> list = new ArrayList<Object>(Arrays.asList(pets));
    Iterator<Object> iterator = list.iterator();
    while(iterator.hasNext()){
      Object next = iterator.next();
      if (o.equals(next)) {
        iterator.remove();
        flag = true;
        break;
      }
    }
    if (flag){
      pets = list.toArray(new Pet[0]);
      return true;
    }
    else
      return false;
  }

  public boolean containsAll(Collection<?> c) {
    Iterator<?> iterator = c.iterator();
    List<Pet> list = new ArrayList<Pet>(Arrays.asList(pets));
    while(iterator.hasNext()){
      Object next = iterator.next();
      if (!list.contains(next))
        return false;
      else list.remove(next);
    }
    return true;
  }

  public boolean addAll(Collection<? extends Pet> c) {
    Iterator<? extends Pet> iterator = c.iterator();
    while(iterator.hasNext())
      add(iterator.next());
    return true;
  }

  public boolean removeAll(Collection<?> c) {
    Iterator<?> iterator = c.iterator();
    boolean flag = false;
    while(iterator.hasNext()) {
      if (remove(iterator.next()))
        flag = true;
    }
    return flag;
  }

  public boolean retainAll(Collection<?> c) {
    boolean flag = false;
    List<Pet> list = new ArrayList<Pet>(Arrays.asList(pets));
    Iterator<Pet> iterator = list.iterator();
    while (iterator.hasNext()){
      Pet next = iterator.next();
      if(!c.contains(next)){
        iterator.remove();
        flag = true;
      }
    }
    pets = list.toArray(new Pet[0]);
    return flag;
  }

  public void clear() {
    pets = new Pet[0];
  }

  public static void main(String[] args) {
    Ex30 c = new Ex30();
    InterfaceVsIterator.display(c);
    InterfaceVsIterator.display(c.iterator());
  }
} /* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
*///:~
