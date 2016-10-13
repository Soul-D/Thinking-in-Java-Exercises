package reusing;

import java.util.Random;

public class Ex18 {
    private static Random rand = new Random();
    static final int CONST = rand.nextInt();
    final int random = rand.nextInt();

    @Override
    public String toString() {
        return "Ex18{" +
                "random=" + random +
                " CONST=" + CONST +
                '}';
    }

    public static void main(String[] args) {
        Ex18 e1 = new Ex18();
        Ex18 e2 = new Ex18();
        System.out.println(e1);
        System.out.println(e2);
    }
}
