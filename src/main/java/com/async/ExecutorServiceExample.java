package com.async;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorServiceExample {

    //ExecutorService es = Executors.newSingleThreadExecutor();
    //ExecutorService es = Executors.newFixedThreadPool(2);
    //ExecutorService es = Executors.newScheduledThreadPool(10);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //invokeAnyExample();
        //invokeAllExample();
        //runExecutorExample();
        //runMultipleExecutorExample();
        //scheduleTaskExample();
        executeInterval();
    }

    private static void executeInterval() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Executing Task At " + System.nanoTime());
        };

        System.out.println("scheduling task to be executed every 2 seconds with an initial delay of 0 seconds");
        scheduledExecutorService.scheduleAtFixedRate(task, 0,2, TimeUnit.SECONDS);
    }

    private static void scheduleTaskExample() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            System.out.println("Executing Task At " + System.nanoTime());
        };

        System.out.println("Submitting task at " + System.nanoTime() + " to be executed after 5 seconds.");
        scheduledExecutorService.schedule(task, 5, TimeUnit.SECONDS);

        scheduledExecutorService.shutdown();
    }

    private static void runMultipleExecutorExample() {
        System.out.println("Inside : " + Thread.currentThread().getName());
        System.out.println("Creating Executor Service with a thread pool of Size 2");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Runnable task1 = () -> {
            System.out.println("Executing Task1 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task2 = () -> {
            System.out.println("Executing Task2 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };

        Runnable task3 = () -> {
            System.out.println("Executing Task3 inside : " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ex) {
                throw new IllegalStateException(ex);
            }
        };
        System.out.println("Submitting the tasks for execution...");
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);

        executorService.shutdown();
    }

    private static void runExecutorExample() {
        System.out.println("Inside : " + Thread.currentThread().getName());

        System.out.println("Creating Executor Service...");
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("Creating a Runnable...");
        Runnable runnable = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
        };

        System.out.println("Submit the task specified by the runnable to the executor service.");
        executorService.submit(runnable);
    }

    private static void invokeAllExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callableSet = getCallables();
        List<Future<String>> futures = executorService.invokeAll(callableSet);

        for(Future<String> future : futures){
            System.out.println("future.get = " + future.get());
        }
        executorService.shutdown();
    }
    private static void invokeAnyExample() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Set<Callable<String>> callableSet = getCallables();
        String result = executorService.invokeAny(callableSet);
        System.out.println("result = " + result);
        executorService.shutdown();
    }
    private static Set<Callable<String>> getCallables() {
        Set<Callable<String>> callables = new HashSet<>();
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });
        return callables;
    }
}