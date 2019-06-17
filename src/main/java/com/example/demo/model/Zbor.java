package com.example.demo.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@RedisHash("Zbor")
public class Zbor implements Serializable {

    int id;

    String sursa;

    String destinatie;

    Integer oraPlecare;

    Integer ziuaPlecare;
    Integer durata;
    Integer locDisp;

    public Zbor(int id, String sursa, String destinatie) {
        this.id = id;
        this.sursa = sursa;
        this.destinatie = destinatie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSursa() {
        return sursa;
    }

    public void setSursa(String sursa) {
        this.sursa = sursa;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Integer getOraPlecare() {
        return oraPlecare;
    }

    public void setOraPlecare(Integer oraPlecare) {
        this.oraPlecare = oraPlecare;
    }

    public Integer getZiuaPlecare() {
        return ziuaPlecare;
    }

    public void setZiuaPlecare(Integer ziuaPlecare) {
        this.ziuaPlecare = ziuaPlecare;
    }

    public Integer getDurata() {
        return durata;
    }

    public void setDurata(Integer durata) {
        this.durata = durata;
    }

    public Integer getLocDisp() {
        return locDisp;
    }

    public void setLocDisp(Integer locDisp) {
        this.locDisp = locDisp;
    }


    @Override
    public String toString() {
        return "Zbor{" +
                "id=" + id +
                ", sursa='" + sursa + '\'' +
                ", destinatie='" + destinatie + '\'' +
                ", oraPlecare=" + oraPlecare +
                ", ziuaPlecare=" + ziuaPlecare +
                ", durata=" + durata +
                ", locDisp=" + locDisp +
                '}';
    }
}
