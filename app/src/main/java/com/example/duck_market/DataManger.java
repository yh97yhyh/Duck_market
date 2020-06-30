package com.example.duck_market;

import com.example.duck_market.Entity.Merchandise;
import com.example.duck_market.Entity.User;

public class DataManger {
    private String majorcate;
    private String subcate;
    private Merchandise merchandise;
    private User user;
    private String serarch_word;

    private  DataManger(){};


    private  static  class LazyHolder{
        public static final DataManger INSTANCE = new DataManger();
    }

    public static DataManger getInstance()
    {
        return  LazyHolder.INSTANCE;
    }

    public String getMajorcate() {
        return majorcate;
    }

    public void setMajorcate(String majorcate) {
        this.majorcate = majorcate;
    }

    public String getSubcate() {
        return subcate;
    }

    public void setSubcate(String subcate) {
        this.subcate = subcate;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSerarch_word() {
        return serarch_word;
    }

    public void setSerarch_word(String serarch_word) {
        this.serarch_word = serarch_word;
    }
}
