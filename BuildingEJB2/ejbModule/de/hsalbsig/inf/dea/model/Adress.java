package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "app_adresse")
public class Adress implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private int plz;
    private String city;
    private String street;
    private String number;

    public Adress() {
    }

    public Adress(long id, int plz, String city, String street, String number) {
        this.id = id;
        this.plz = plz;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "plz")
    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }


    @Column(name = "wohnort")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "strasse")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "hausnummer")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void copyData(Adress copy) {
        this.id = copy.id;
        this.plz = copy.plz;
        this.city = copy.city;
        this.street = copy.street;
        this.number = copy.number;
    }

    @Override
    public String toString() {
        return "Adress [id=" + id + ", plz=" + plz + ", city=" + city + ", street=" + street + ", number=" + number + "]";
    }
}