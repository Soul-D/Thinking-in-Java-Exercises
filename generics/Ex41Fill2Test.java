package generics;

import generics.pets.*;
import generics.pets.Hamster;

import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;

class Ex41Fill2Test {
  public static void main(String[] args) {
    // Adapt a Collection:
    List<Pet> pets = new ArrayList<Pet>();
    Fill2.fill(
      new AddableCollectionAdapter<Pet>(pets),
            Pet.class, 3);
    // Helper method captures the type:
    Fill2.fill(Adapter.collectionAdapter(pets),
      Pug.class, 2);
    for(Pet c: pets)
      print(c);
    print("----------------------");
    // Use an adapted class:
    AddableSimpleQueue<Pet> petQueue =
      new AddableSimpleQueue<Pet>();
    Fill2.fill(petQueue, Hamster.class, 4);
    Fill2.fill(petQueue, Cymric.class, 1);
    for(Pet c: petQueue)
      print(c);
  }
}
