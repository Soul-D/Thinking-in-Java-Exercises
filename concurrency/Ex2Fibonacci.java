package concurrency;

import net.mindview.util.Generator;

public class Ex2Fibonacci implements Generator<Integer>, Runnable {
    private int count = 0;
    private int n;

    public Ex2Fibonacci(int n) {
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
        for (int i = 1; i < 10; i++) {
            new Thread(new Ex2Fibonacci(i)).start();
        }
    }
}