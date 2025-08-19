package com.product;

import java.util.*;

public class ProductMerger {
    public static void main(String[] args) {
        List<Product> list1 = Arrays.asList(
                new Product(101, "GROCERY", 12, 10),
                new Product(102, "GROCERY", 15, 10),
                new Product(103, "VEGETABLE", 12, 7)
        );

        List<Product> list2 = Arrays.asList(
                new Product(104, "GROCERY", 12, 8),
                new Product(105, "GROCERY", 10, 15),
                new Product(108, "VEGETABLE", 12, 6)
        );

        Map<String, Product> mergedMap = new LinkedHashMap<>();

        // Add all products from list1
        for (Product p : list1) {
            mergedMap.put(p.getKey(), p);
        }

        // Merge list2
        for (Product p : list2) {
            String key = p.getKey();
            if (mergedMap.containsKey(key)) {
                Product existing = mergedMap.get(key);
                existing.quantity += p.quantity;
            } else {
                mergedMap.put(key, p);
            }
        }

        // Output the final merged list
        List<Product> finalList = new ArrayList<>(mergedMap.values());
        for (Product p : finalList) {
            System.out.println(p);
        }
    }
}

class Product {
    int id;
    String name;
    int subCategory;
    int quantity;

    public Product(int id, String name, int subCategory, int quantity) {
        this.id = id;
        this.name = name;
        this.subCategory = subCategory;
        this.quantity = quantity;
    }

    // Key for merging
    public String getKey() {
        return name + "-" + subCategory;
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ", " + subCategory + ", " + quantity + ")";
    }
}
