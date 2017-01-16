package enumerated;

import java.util.Random;

enum Ex2CartoonCharacter {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private static Random rand = new Random(47);

    public static Ex2CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}

public class Ex2 {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.print(Ex2CartoonCharacter.next() + " ");
    }
}
