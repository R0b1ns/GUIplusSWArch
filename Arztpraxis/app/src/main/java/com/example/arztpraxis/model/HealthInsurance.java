package com.example.arztpraxis.model;

import java.io.Serializable;

public class HealthInsurance implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private int adress;

    public HealthInsurance() {
    }

    public HealthInsurance(long id, String name, int adress) {
        this.id = id;
        this.name = name;
        this.adress = adress;
    }

    //@Id
    //@Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //@Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@Column(name = "adresseid")
    public int getAdress() {
        return adress;
    }

    public void setAdress(int adress) {
        this.adress = adress;
    }

    public void copyData(HealthInsurance copy) {
        this.id = copy.id;
        this.name = copy.name;
        this.adress = copy.adress;
    }

    @Override
    public String toString() {
        return "HealthInsurance [id=" + id + ", name=" + name + ", adress=" + adress + "]";
    }
}
