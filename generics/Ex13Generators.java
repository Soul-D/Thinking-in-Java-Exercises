package generics;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.Generator;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Ex13Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        System.out.println("!collection!");
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
        System.out.println("!list!");
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> Queue<T> fill(Queue<T> coll, Generator<T> gen, int n) {
        System.out.println("!queue!");
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> Set<T> fill(Set<T> coll, Generator<T> gen, int n) {
        System.out.println("!set!");
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> LinkedList<T> fill(LinkedList<T> coll, Generator<T> gen, int n) {
        System.out.println("!LinkedList!");
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffee)
            System.out.println(c);
        Set<Integer> fnumbers = fill(new HashSet<Integer>(), new Fibonacci(), 12);
        for (int i : fnumbers)
            System.out.print(i + ", ");
        Queue<Coffee> coffee2 = fill(new LinkedBlockingQueue<Coffee>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffee2)
            System.out.println(c);
        LinkedList<Integer> fnumbers2 = fill(new LinkedList<Integer>(), new Fibonacci(), 12);
        for (int i : fnumbers2)
            System.out.print(i + ", ");
    }
}