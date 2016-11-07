package generics;

public class Ex32GenericCast {
  public static final int SIZE = 10;
  public static void main(String[] args) {
    FixedSizeStack<String> strings =      new FixedSizeStack<String>(SIZE);
    /*for(String s : "A B C D E F G H I J K".split(" "))
      strings.push(s);*/
    /*for(int i = 0; i < SIZE + 1; i++) {
      String s = strings.pop();
      System.out.print(s + " ");
    }*/
  }
}
