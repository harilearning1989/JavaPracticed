package com.products;

// MobileShop.java
import java.util.List;

public record MobileShop(
        int shopId,
        String shopName,
        List<ProductRecord> products
) {}

