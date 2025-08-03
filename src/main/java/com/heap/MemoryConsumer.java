package com.heap;

import java.io.IOException;
import java.util.*;

public class MemoryConsumer {

    private static final List<String> stringList = new ArrayList<>();
    private static final Map<String, byte[]> cacheMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting memory consumption...");

        // Simulate large number of Strings
        for (int i = 0; i < 100_000; i++) {
            String s = "String number " + i + " at " + System.nanoTime();
            stringList.add(s);
        }

        // Simulate large byte arrays (e.g., like image or file data)
        for (int i = 0; i < 1_000; i++) {
            String key = "data_" + i;
            byte[] bigData = new byte[1024 * 1024]; // 1 MB
            Arrays.fill(bigData, (byte) i); // Fill with dummy data
            cacheMap.put(key, bigData);
        }
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Prevent GC
        System.out.println("Memory loaded. Use VisualVM to inspect the heap.");
        Thread.sleep(10 * 60 * 1000); // Sleep for 10 minutes
    }
}

