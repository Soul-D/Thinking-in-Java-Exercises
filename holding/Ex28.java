package holding;

import java.util.PriorityQueue;
import java.util.Random;

public class Ex28 {
    public static void main(String[] args) {
        PriorityQueue<Double> queue = new PriorityQueue<Double>();
        Random random = new Random();
        for (int i = 0; i < 13; i++){
            queue.offer(random.nextDouble());
        }
        while(!queue.isEmpty())
            System.out.println(queue.poll());
    }
}
