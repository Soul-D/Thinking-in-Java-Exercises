package generics;

import net.mindview.util.Generator;

import java.util.*;

public class Ex18Ocean {
    public static void eat(BigFish t, LittleFish c) {
        System.out.println(t + " eats " + c);
    }
    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<LittleFish> littleFishes = new LinkedList<LittleFish>();
        Generators.fill(littleFishes, LittleFish.generator, 15);
        List<BigFish> bigFishes = new ArrayList<BigFish>();
        Generators.fill(bigFishes, BigFish.generator(), 4);
        for(LittleFish c : littleFishes)
            eat(bigFishes.get(rand.nextInt(bigFishes.size())), c);
    }
}

class BigFish{
    private static long counter = 1;
    private final long id = counter++;
    private BigFish() {}
    public String toString() { return "BigFish " + id; }
    // A method to produce Generator objects:
    public static Generator<BigFish> generator() {
        return new Generator<BigFish>() {
            public BigFish next() { return new BigFish(); }
        };
    }
}

class LittleFish {
    private static long counter = 1;
    private final long id = counter++;
    private LittleFish() {}
    public String toString() { return "LittleFish " + id; }
    // A single Generator object:
    public static Generator<LittleFish> generator =
            new Generator<LittleFish>() {
                public LittleFish next() { return new LittleFish(); }
            };
}
