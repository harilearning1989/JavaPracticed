package com.thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReadingExample {

    public static void main(String[] args) {
        //readFilesWithAllOf();
        readFilesWithAllOfWithEachTime();
    }

    private static void readFilesWithAllOfWithEachTime() {
        List<String> filePaths = List.of("PatientDetails.json", "PatientDetails.json", "PatientDetails.json");
        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(filePaths.size(), 10));
        List<CompletableFuture<FileReadResult>> fileFutures = new ArrayList<>();
        for (String filePath : filePaths) {
            CompletableFuture<FileReadResult> fileFuture = CompletableFuture.supplyAsync(() -> readFileWithTime(filePath), executorService);
            fileFutures.add(fileFuture);
        }

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(fileFutures.toArray(new CompletableFuture[fileFutures.size()]));
        allFutures.thenRun(() -> {
            for (CompletableFuture<FileReadResult> fileFuture : fileFutures) {
                try {
                    FileReadResult result = fileFuture.get(); // Get the result of CompletableFuture
                    System.out.println("File: " + result.filePath + ", Read Time: " + result.readTime + " ms");
                    System.out.println("Content:");
                    System.out.println(result.content); // Process file content here
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown(); // Shut down executor service
        });
    }

    private static FileReadResult readFileWithTime(String filePath) {
        long startTime = System.currentTimeMillis();
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long readTime = endTime - startTime;
        return new FileReadResult(filePath, content.toString(), readTime);
    }

    private static void readFilesWithAllOf() {
        List<String> filePaths = List.of("PatientDetails.json", "PatientDetails.json", "PatientDetails.json");
        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(filePaths.size(), 10));
        List<CompletableFuture<String>> fileFutures = new ArrayList<>();

        for (String filePath : filePaths) {
            CompletableFuture<String> fileFuture = CompletableFuture.supplyAsync(() -> readFile(filePath), executorService);
            fileFutures.add(fileFuture);
        }

        CompletableFuture<Void> allFutures =
                CompletableFuture.allOf(fileFutures.toArray(new CompletableFuture[fileFutures.size()]));
        allFutures.thenRun(() -> {
            for (CompletableFuture<String> fileFuture : fileFutures) {
                try {
                    String fileContent = fileFuture.get(); // Get the result of CompletableFuture
                    System.out.println(fileContent); // Process file content here
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown(); // Shut down executor service
        });
    }

    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}

class FileReadResult {
    String filePath;
    String content;
    long readTime;

    FileReadResult(String filePath, String content, long readTime) {
        this.filePath = filePath;
        this.content = content;
        this.readTime = readTime;
    }
}