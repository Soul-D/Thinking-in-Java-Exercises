package concurrency;

import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Ex10Fibonacci implements Generator<Integer>, Callable<Integer> {
    private int count = 0;
    private int n = 0;
    private ExecutorService executorService;

    public Ex10Fibonacci(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public Integer call() {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += next();
        return sum;
    }

    public Future<Integer> runTask(int n){
        this.n = n;
        return executorService.submit(this);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            futures.add(new Ex10Fibonacci(exec).runTask(i));
        }
        exec.shutdown();
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
    }
}