package object;

public class Ex8 {
    static int x;

    public static void main(String[] args) {
        Ex8 ex8 = new Ex8();
        ex8.x = 6;
        new Ex8().x = 100;
        System.out.println(ex8.x);
    }
}
