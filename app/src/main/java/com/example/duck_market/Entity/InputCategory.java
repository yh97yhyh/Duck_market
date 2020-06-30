package com.example.duck_market.Entity;

public class InputCategory {
    private String major;
    private String sub;

    public InputCategory(String major, String sub) {
        this.major = major;
        this.sub = sub;
    }

    public InputCategory(){

    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
