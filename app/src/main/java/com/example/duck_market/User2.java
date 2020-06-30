package com.example.duck_market;

public class User2 {

    private String id;//아이디
    private String nickname;//닉네임
    private String shopname;//샵이름



    public User2(String id, String nickname, String shopname) {
       this.id=id;
       this.nickname=nickname;
       this.shopname=shopname;
    }

    public User2() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }
    @Override
    public String toString() {
        String str = id+" ("+nickname+") "+shopname;
        return  str;
    }

    public static class NullOnEmptyConverterFactory {
    }
}
