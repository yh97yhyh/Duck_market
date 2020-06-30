package com.example.duck_market.Entity;

public class Interest {
    private long id;
    private long merchandiseId;
    private long userId;
    private float prefer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public float getPrefer() {
        return prefer;
    }

    public void setPrefer(float prefer) {
        this.prefer = prefer;
    }

    public Interest(long id, long merchandiseId, long userId, float prefer) {
        this.id = id;
        this.merchandiseId = merchandiseId;
        this.userId = userId;
        this.prefer = prefer;
    }

    public Interest(){

    }
}
