package com.emp;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private List<Languages> languages;

    public Employee() {}
    public Employee(int id, String name, List<Languages> languages) {
        this.id = id;
        this.name = name;
        this.languages = languages;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }
}
