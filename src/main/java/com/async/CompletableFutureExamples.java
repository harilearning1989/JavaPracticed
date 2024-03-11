package com.async;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompletableFutureExamples {

    /*static CompletableFuture<Void> runAsync(Runnable runnable)
    static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
    static <U> CompletableFuture<U>	supplyAsync(Supplier<U> supplier)
    static <U> CompletableFuture<U>	supplyAsync(Supplier<U> supplier, Executor executor)*/

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<CompletableFuture> list = new ArrayList<>();
        List<Integer> finalResult = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(supplyAsync(() ->
                    add(RandomUtils.nextInt(), RandomUtils.nextInt()), executorService)
                    .thenApplyAsync(result -> multiply(result), executorService));
        }
        for (CompletableFuture<Integer> future : list) {
            finalResult.add(future.get()); //4
        }
        System.out.println(finalResult);
    }

    public static Integer add(int a, int b) {
        return a + b;
    }

    public static Integer multiply(int result) {
        return result * 15;
    }

    public void runAsyncDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> runAsyncRunnable = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // Simulate a long-running Job
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("I'll run in a separate thread than the main thread.");
            }
        });
        runAsyncRunnable.get();

        CompletableFuture<Void> runAsyncLambda = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });
        runAsyncLambda.get();
    }

    public void supplyAsyncDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Result of the asynchronous computation";
            }
        });
        String result = supplyAsync.get();
        System.out.println(result);

        CompletableFuture<String> supplyAsyncLambda = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });
    }
}
