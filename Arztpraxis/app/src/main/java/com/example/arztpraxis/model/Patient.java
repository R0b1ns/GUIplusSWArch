package com.example.arztpraxis.model;

import java.io.Serializable;


//import javax.persistence.*;

// @SuppressWarnings("serial")
//@Entity
//@Table(name = "app_patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private int SSN;
    private int person;
    private int healthInsurance;

    public Patient() {
    }

    public Patient(long id, int SSN, int person, int healthInsurance) {
        this.id = id;
        this.SSN = SSN;
        this.person = person;
        this.healthInsurance = healthInsurance;
    }

    //@Id
    //@Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    //@Column(name = "versicherungsnr")
    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    //@Column(name = "personid")
    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    //@Column(name = "krankenkasseid")
    public int getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(int healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public void copyData(Patient copy) {
        this.id = copy.id;
        this.SSN = copy.SSN;
        this.person = copy.person;
        this.healthInsurance = copy.healthInsurance;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", ssn=" + SSN + ", person_id=" + person + ", health_insurance_id=" + healthInsurance + "]";
    }
}
