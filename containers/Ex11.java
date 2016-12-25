package containers;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Ex11 implements Comparable<Ex11>{
    private static final Random random = new Random();
    public final Integer integer = random.nextInt(101);

    public int compareTo(Ex11 o) {
        return integer > o.integer ? +1 : integer == o.integer ? 0 : -1;
    }

    @Override
    public String toString() {
        return integer.toString();
    }
}

class Ex11Test{
    public static void main(String[] args) {
        Queue<Ex11> queue = new PriorityQueue<Ex11>();
        for (int i = 0; i < 20; i++){
            queue.offer(new Ex11());
        }
        while (!queue.isEmpty())
            System.out.println(queue.poll());
    }
}
