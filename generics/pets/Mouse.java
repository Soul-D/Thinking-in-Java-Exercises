//: typeinfo/pets/Mouse.java
package generics.pets;

public class Mouse extends Rodent {
  public Mouse(String name) { super(name); }
  public Mouse() { super(); }
  public static class Factory implements typeinfo.factory.Factory<Mouse> {
    public Mouse create() {
      return new Mouse();
    }
  }
  public void speak() {
    System.out.println("Mouse crunch");
  }
} ///:~
