//: typeinfo/pets/Hamster.java
package generics.pets;

public class Hamster extends Rodent {
  public Hamster(String name) { super(name); }
  public Hamster() { super(); }
  public static class Factory implements typeinfo.factory.Factory<Hamster> {
    public Hamster create() {
      return new Hamster();
    }
  }
  public void speak() {
    System.out.println("Hamster crunch");
  }
} ///:~
