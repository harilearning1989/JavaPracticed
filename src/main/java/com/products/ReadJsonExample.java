package com.products;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReadJsonExample {
    public static void main(String[] args) throws Exception {
        MobileShopData data = getMobileShopData();
        List<MobileShop> mobileShops = data.mobileShops();
        List<ProductRecord> productRecordList = getProductsList(mobileShops);
        System.out.printf("%d products\n", productRecordList.size());
        List<ProductRecord> allProducts = getAllProductsList(mobileShops);
        System.out.println("========Total Products=========");
        System.out.printf("%d products\n", allProducts.size());
        allProducts = allProducts.stream().sorted(Comparator.comparing(ProductRecord::company)).toList();

        allProducts.forEach(productRecord -> {
            System.out.println("Company::" + productRecord.company() + "===Model:: "
                    + productRecord.model() + "===quantity===" + productRecord.quantity() + "===Price:: " + productRecord.price());
        });
    }

    private static List<ProductRecord> getAllProductsList(List<MobileShop> mobileShops) {
        /*List<ProductRecord> productRecordList = mobileShops.stream()
                .flatMap(f -> f.products().stream())
                .toList();
        Map<String, ProductRecord> map = new HashMap<>();

        for (ProductRecord productRecord : productRecordList) {
            String companyModel = productRecord.company() + productRecord.model();
            if (map.get(companyModel) != null) {
                ProductRecord record = map.get(companyModel);
                record = new ProductRecord(record.productId(), record.company(), record.model(),
                        record.quantity() + productRecord.quantity(), record.price() + productRecord.price());
                map.put(companyModel, record);
            } else {
                map.put(companyModel, productRecord);
            }
        }
        return new ArrayList<>(map.values());*/
        return mobileShops.stream()
                .flatMap(f -> f.products().stream())
                .collect(Collectors.toMap(
                        p -> p.company() + p.model(), // key: company + model
                        Function.identity(),          // value: the product record
                        (p1, p2) -> new ProductRecord( // merge duplicates
                                p1.productId(),
                                p1.company(),
                                p1.model(),
                                p1.quantity() + p2.quantity(),
                                p1.price() + p2.price()
                        )
                ))
                .values()
                .stream()
                .toList();

    }


    private static List<ProductRecord> getProductsList(List<MobileShop> mobileShops) {
        return mobileShops.stream()
                .flatMap(f -> f.products().stream())
                .toList();
    }

    private static MobileShopData getMobileShopData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        // Read the JSON into the MobileShopData record
        MobileShopData data = mapper.readValue(
                new File("products.json"),
                MobileShopData.class
        );

        // Example: print first shop's details
        data.mobileShops().forEach(shop -> {
            System.out.println("Shop: " + shop.shopName());
            shop.products().forEach(product ->
                    System.out.println(" - " + product.company() + " " + product.model() +
                            " | Qty: " + product.quantity() +
                            " | Price: " + product.price())
            );
        });

        return data;
    }
}

