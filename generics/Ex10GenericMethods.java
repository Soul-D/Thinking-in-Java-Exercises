package generics;

public class Ex10GenericMethods {
  public <T, K> void f(T t, K k, Double v) {
    System.out.println(t.getClass().getName() + " " + k.getClass().getName() + " " + v.getClass().getName());
  }
  public static void main(String[] args) {
    Ex10GenericMethods gm = new Ex10GenericMethods();
    gm.f("",1,1.0);
    //gm.f("",1,"2");
  }
}
