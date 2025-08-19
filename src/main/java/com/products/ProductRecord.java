package com.products;

// Product.java
public record ProductRecord(
        int productId,
        String company,
        String model,
        int quantity,
        double price
) {}

