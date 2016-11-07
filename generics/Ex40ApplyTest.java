package generics;

import generics.pets.*;
import generics.pets.Hamster;

import java.util.ArrayList;
import java.util.List;

class Ex40ApplyTest {
  public static void main(String[] args) throws Exception {
    List<Pet> pets = new ArrayList<Pet>();
    pets.add(new Cymric());
    pets.add(new EgyptianMau());
    pets.add(new Gerbil());
    pets.add(new Hamster());
    pets.add(new Manx());
    pets.add(new Mouse());
    pets.add(new Mutt());
    pets.add(new Pug());
    pets.add(new Rat());
    Apply.apply(pets, Pet.class.getMethod("speak"));
  }
}
