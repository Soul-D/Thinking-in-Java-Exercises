//: typeinfo/pets/Mutt.java
package generics.pets;

public class Mutt extends Dog {
  public Mutt(String name) { super(name); }
  public Mutt() { super(); }
  public static class Factory implements typeinfo.factory.Factory<Mutt> {
    public Mutt create() {
      return new Mutt();
    }
  }
  public void speak() {
    System.out.println("Mutt woof");
  }
} ///:~
