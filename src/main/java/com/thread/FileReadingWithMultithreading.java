package com.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FileReadingWithMultithreading {

    public static void main(String[] args) {
        String[] files = {"file1.txt", "file2.txt", "file3.txt"};
        ExecutorService executor = Executors.newFixedThreadPool(files.length);
        List<Future<List<String>>> futures = new ArrayList<>();
        for (String file : files) {
            Future<List<String>> future = executor.submit(new FileReaderTask(file));
            futures.add(future);
        }
        for (Future<List<String>> future : futures) {
            try {
                List<String> lines = future.get(); // Get the result
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }

    static class FileReaderTask implements Callable<List<String>> {
        private String filename;
        public FileReaderTask(String filename) {
            this.filename = filename;
        }

        @Override
        public List<String> call() throws IOException {
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
            return lines;
        }
    }
}

