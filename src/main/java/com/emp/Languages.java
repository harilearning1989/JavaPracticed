package com.emp;

public class Languages {

    private String language;
    private String proficiency;

    public Languages() {}
    public Languages(String language, String proficiency) {
        this.language = language;
        this.proficiency = proficiency;
    }
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
