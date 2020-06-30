package com.example.duck_market.Entity;

import java.sql.Blob;

public class Merchandise {
    private long id;
    private String user;
    private String name;
    private String description;
    private int price;
    private String major;
    private String sub;


    public Merchandise(long id, String user, String name, String description, int price, String major, String sub) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.price = price;
        this.major = major;
        this.sub = sub;
    }

    public Merchandise() {

    }

    public String getMember() {
        return user;
    }

    public void setMember(String member) {
        this.user = member;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        String str =  name + "\n" + price + "원\n";
        return str;
    }
}
