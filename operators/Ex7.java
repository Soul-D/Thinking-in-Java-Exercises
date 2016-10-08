package operators;

import java.util.Random;

public class Ex7 {
    public static void main(String[] args) {
        System.out.println(coinFlip());
    }

    static boolean coinFlip()
    {
        Random random = new Random();
        return random.nextBoolean();
    }
}
