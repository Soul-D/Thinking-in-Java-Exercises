//: typeinfo/pets/LiteralPetCreator.java
// Using class literals.
package typeinfo.pets;

import typeinfo.factory.Factory;

import java.util.*;

public class Ex15PetCreator extends PetCreator {
  static List<Factory<? extends Pet>> partFactories =
          new ArrayList<Factory<? extends Pet>>();

  static {
    partFactories.add(new Cymric.Factory());
    partFactories.add(new EgyptianMau.Factory());
    partFactories.add(new Gerbil.Factory());
    partFactories.add(new Hamster.Factory());
    partFactories.add(new Manx.Factory());
    partFactories.add(new Mouse.Factory());
    partFactories.add(new Mutt.Factory());
    partFactories.add(new Pug.Factory());
    partFactories.add(new Rat.Factory());
  }

  private static Random rand = new Random(47);
  public Pet randomPet() {
    int n = rand.nextInt(partFactories.size());
    return partFactories.get(n).create();
  }

  public List<Class<? extends Pet>> types() {
    return null;
  }

  public Pet[] createArray(int size) {
    Pet[] result = new Pet[size];
    for(int i = 0; i < size; i++)
      result[i] = randomPet();
    return result;
  }
  public ArrayList<Pet> arrayList(int size) {
    ArrayList<Pet> result = new ArrayList<Pet>();
    Collections.addAll(result, createArray(size));
    return result;
  }
}
