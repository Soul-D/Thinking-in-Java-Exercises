package concurrency;

import net.mindview.util.Generator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex4Fibonacci implements Generator<Integer>, Runnable {
    private int count = 0;
    private int n;

    public Ex4Fibonacci(int n) {
        this.n = n;
    }

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public void run() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(next()).append(" ");
        System.out.println(n + ":" + sb.toString());
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            exec.execute(new Ex4Fibonacci(i));
        }
        exec.shutdown();
        exec = Executors.newFixedThreadPool(13);
        for (int i = 0; i < 5; i++){
            exec.execute(new Ex4Fibonacci(i));
        }
        exec.shutdown();
        exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++){
            exec.execute(new Ex4Fibonacci(i));
        }
        exec.shutdown();
    }
}