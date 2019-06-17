package com.example.demo.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash("Cumparare")
public class Cumparare implements Serializable {
    String id;
    Zbor zbor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Zbor getZbor() {
        return zbor;
    }

    public void setZbor(Zbor zbor) {
        this.zbor = zbor;
    }
}
