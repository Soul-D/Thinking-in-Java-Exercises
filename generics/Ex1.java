package generics;

import typeinfo.pets.Gerbil;
import typeinfo.pets.Pet;
import typeinfo.pets.Rat;

public class Ex1 {
    public static void main(String[] args) {
        Holder3<Pet> petHolder = new Holder3<Pet>(new Pet());
        Pet p = petHolder.get();
        System.out.println(petHolder.get());
        petHolder.set(new Rat());
        Rat rat = (Rat)petHolder.get();
        System.out.println(petHolder.get());
        petHolder.set(new Gerbil());
        Pet gebril = petHolder.get();
        System.out.println(petHolder.get());
    }
}
