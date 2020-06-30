package com.example.duck_market.Entity;

public class Recommend {
    long itemId;
    float value;

    public Recommend(){}
    public Recommend(long itemId, float value) {
        this.itemId = itemId;
        this.value = value;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
