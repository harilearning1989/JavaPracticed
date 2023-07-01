package com.demo;

import java.util.ArrayList;
import java.util.List;

public class CustomStringBuffer {
    List<String> stringList = new ArrayList<>();
    public void append(String str) {
        stringList.add(str);
    }

}
