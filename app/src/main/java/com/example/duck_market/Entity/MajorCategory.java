package com.example.duck_market.Entity;

import java.util.ArrayList;
import java.util.List;

public class MajorCategory {
    private long id;
    private String major;
    private List<SubCategory> subs=new ArrayList<SubCategory>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<SubCategory> getSubs() {
        return subs;
    }

    public void setSubs(List<SubCategory> subs) {
        this.subs = subs;
    }

    public void addSubs(SubCategory s){
        this.subs.add(s);
        if(s.getMajor()!=this){
            s.setMajor(this);
        }
    }

    public MajorCategory(long id, String major, List<SubCategory> subs) {
        this.id = id;
        this.major = major;
        this.subs = subs;
    }

    public MajorCategory(){

    }
}
