package com.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReadingWithMultithread {

    public static void main(String[] args) {
        //readJsonFile("PatientDetails.json");
        readDileMultiThread("PatientDetails.json");
    }

    private static void readDileMultiThread(String filePath) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        String content = readFile(filePath);
                        System.out.println("File content:\n" + content);
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
                }, executor).
                exceptionally(ex -> {
                    System.err.println("Task failed with exception: " + ex.getMessage());
                    return null;
                });
        System.out.println("Before ShutDown");
        executor.shutdown();
    }

    private static void readJsonFile(String filePath) {
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            ObjectMapper objectMapper = new ObjectMapper();
            List<Patient> patientList = objectMapper.readValue(jsonContent,
                    new TypeReference<>() {
                    });
            for (Patient obj : patientList) {
                System.out.println("Read JSON object: " + obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}