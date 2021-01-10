package com.example.arztpraxis.model;

import java.io.Serializable;

public class Drug implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String description;

    public Drug() {
    }

    public Drug(long id, String description) {
        this.id = id;
        this.description = description;
    }

    //@Id
    //@Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //@Column(name = "bezeichnung")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void copyData(Drug copy) {
        this.id = copy.id;
        this.description = copy.description;
    }

    @Override
    public String toString() {
        return "Drug [id=" + id + ", description=" + description + "]";
    }

}
