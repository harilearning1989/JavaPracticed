package com;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class ThreadExample {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6};
        Thread incrementThread = new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                System.out.println("First Thread Name::" + Thread.currentThread().getName() + "===Value::" + a[i]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread decreamentThread = new Thread(() -> {
            for (int i = a.length - 1; i >= 0; i--) {
                System.out.println("Second Thread Name::" + Thread.currentThread().getName() + "===Value::" + a[i]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        incrementThread.start();
        decreamentThread.start();
    }
}


