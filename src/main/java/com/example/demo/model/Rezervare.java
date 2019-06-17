package com.example.demo.model;

import com.example.demo.model.Zbor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Rezervare")
public class Rezervare implements Serializable {
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
