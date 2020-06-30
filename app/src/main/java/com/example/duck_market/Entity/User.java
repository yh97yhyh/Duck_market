package com.example.duck_market.Entity;

public class User {
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String shopname;
    private String shopdes;
    private String phone;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopdes() {
        return shopdes;
    }

    public void setShopdes(String shopdes) {
        this.shopdes = shopdes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User() {

    }

    public User(long id, String username, String password, String nickname, String shopname, String shopdes, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.shopname = shopname;
        this.shopdes = shopdes;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
