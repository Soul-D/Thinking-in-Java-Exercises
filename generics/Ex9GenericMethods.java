package generics;

public class Ex9GenericMethods {
  public <T, K, V> void f(T t, K k, V v) {
    System.out.println(t.getClass().getName() + " " + k.getClass().getName() + " " + v.getClass().getName());
  }
  public static void main(String[] args) {
    Ex9GenericMethods gm = new Ex9GenericMethods();
    gm.f("",1,1.0);
  }
}
