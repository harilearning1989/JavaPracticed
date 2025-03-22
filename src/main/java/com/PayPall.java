package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PayPall {

    public static void main(String[] args) {
        Map<String,Object> payload = new HashMap<>();


        Map<String,Object> wtx = new HashMap<>();
        Map<String,Object> dpgs = getDpgsData();
        Map<String,Object> zoot = getZootData();
        Map<String,Object> efx = getEFXData();
        Map<String,Object> ln = getLNData();

        wtx.put("ZOOT",zoot);
        wtx.put("DPGS_ZOOT",dpgs);
        //wtx.put("EFX",efx);
       // wtx.put("LN",ln);
        //wtx.put("EFX",efx);

        Map<String,Object> vendors = (Map<String, Object>) wtx.get("ZOOT");
        vendors.forEach((k,v) -> {
            payload.put(k,v);
        });
        Map<String,Object> dpgs_zoot = (Map<String, Object>) wtx.get("DPGS_ZOOT");
        dpgs_zoot.forEach((k,v) -> {
            payload.put(k,v);
        });
        System.out.println(payload);
    }

    private static Map<String, Object> getZootData() {
        Map<String, Object> randomData = new HashMap<>();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            String key = "key" + i;//key1
            Object value;

            switch (random.nextInt(4)) { // Randomly assign different types
                case 0 -> value = random.nextInt(100); // Random integer (0-99)
                case 1 -> value = random.nextDouble(); // Random double (0.0 - 1.0)
                case 2 -> value = "value" + random.nextInt(100); // Random string
                default -> value = random.nextBoolean(); // Random boolean
            }

            randomData.put(key, value);
        }
        randomData.put("name","Hari");
        randomData.put("age","22");
        return randomData;
    }

    private static Map<String, Object> getDpgsData() {
        Map<String, Object> randomData = new HashMap<>();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            String key = "DPGS_key" + i;//key1
            Object value;

            switch (random.nextInt(4)) { // Randomly assign different types
                case 0 -> value = random.nextInt(100); // Random integer (0-99)
                case 1 -> value = random.nextDouble(); // Random double (0.0 - 1.0)
                case 2 -> value = "value" + random.nextInt(100); // Random string
                default -> value = random.nextBoolean(); // Random boolean
            }

            randomData.put(key, value);
        }
        return randomData;
    }

    private static Map<String, Object> getLNData() {
        Map<String, Object> randomData = new HashMap<>();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            String key = "key" + i;
            Object value;

            switch (random.nextInt(4)) { // Randomly assign different types
                case 0 -> value = random.nextInt(100); // Random integer (0-99)
                case 1 -> value = random.nextDouble(); // Random double (0.0 - 1.0)
                case 2 -> value = "value" + random.nextInt(100); // Random string
                default -> value = random.nextBoolean(); // Random boolean
            }

            randomData.put(key, value);
        }
        return randomData;

    }

    private static Map<String, Object> getEFXData() {
        Map<String, Object> randomData = new HashMap<>();
        Random random = new Random();

        for (int i = 1; i <= 20; i++) {
            String key = "key" + i;
            Object value;

            switch (random.nextInt(4)) { // Randomly assign different types
                case 0 -> value = random.nextInt(100); // Random integer (0-99)
                case 1 -> value = random.nextDouble(); // Random double (0.0 - 1.0)
                case 2 -> value = "value" + random.nextInt(100); // Random string
                default -> value = random.nextBoolean(); // Random boolean
            }

            randomData.put(key, value);
        }
        return randomData;
    }


}
