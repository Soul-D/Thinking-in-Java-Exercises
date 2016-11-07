//: typeinfo/pets/EgyptianMau.java
package generics.pets;

public class EgyptianMau extends Cat {
  public EgyptianMau(String name) { super(name); }
  public EgyptianMau() { super(); }
  public static class Factory implements typeinfo.factory.Factory<EgyptianMau> {
    public EgyptianMau create() {
      return new EgyptianMau();
    }
  }

  public void speak() {
    System.out.println("EgyptianMau nya");
  }
} ///:~
