package com.example.duck_market.Entity;

import com.example.duck_market.Entity.MajorCategory;

public class SubCategory {
    private long id;
    private String sub;
    private MajorCategory major;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SubCategory(){}

    public SubCategory(long id, String sub, MajorCategory major) {
        this.id = id;
        this.sub = sub;
        this.major = major;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public MajorCategory getMajor() {
        return major;
    }

    public void setMajor(MajorCategory major) {
        this.major = major;
        if(!major.getSubs().contains(this)){
            major.getSubs().add(this);
        }
    }
}
