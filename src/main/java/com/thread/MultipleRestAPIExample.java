package com.thread;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleRestAPIExample {

    public static void main(String[] args) {
        // List of URLs to call
        List<String> urls = List.of(
                "https://api.example.com/endpoint1",
                "https://api.example.com/endpoint2",
                "https://api.example.com/endpoint3");

        ExecutorService executorService = Executors.newFixedThreadPool(Math.min(urls.size(), 10));
        List<CompletableFuture<Void>> apiFutures = List.of();
        for (String url : urls) {
            CompletableFuture<Void> apiFuture = CompletableFuture.runAsync(() -> callAPI(url), executorService)
                    .exceptionally(ex -> {
                        System.err.println("Error occurred while calling API: " + url);
                        ex.printStackTrace();
                        return null;
                    });
            apiFutures.add(apiFuture);
        }

        CompletableFuture<Void> allFutures =
                CompletableFuture.allOf(apiFutures.toArray(new CompletableFuture[apiFutures.size()]));

        allFutures.thenRun(() -> {
            System.out.println("All API calls completed successfully");
            executorService.shutdown(); // Shut down executor service
        });
    }

    private static void callAPI(String url) {
        long startTime = System.currentTimeMillis();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            Map<String, String> parameters = new HashMap<>();
            request.headers().map().forEach((key, value) -> parameters.put(key, String.join(",", value)));

            Map<String, List<String>> responseHeaders = response.headers().map();

            System.out.println("API Call to " + url + " successful");
            System.out.println("Response Body: " + response.body());
            System.out.println("Duration: " + duration + " milliseconds");
            System.out.println("Request Parameters: " + parameters);
            System.out.println("Response Headers: " + responseHeaders);
        } catch (Exception e) {
            throw new RuntimeException("Failed to call API: " + url, e);
        }
    }
}

