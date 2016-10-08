package operators;

public class Ex3 {
    float f;

    public static void main(String[] args) {
        Ex3 ex3 = new Ex3();
        ex3.printField();
        f(ex3);
        ex3.printField();
    }

    private static void f(Ex3 ex3) {
        ex3.f = 10.f;
    }

    private void printField() {
        System.out.println("f = " + this.f);
    }
}
