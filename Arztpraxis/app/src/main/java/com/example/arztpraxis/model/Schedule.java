package com.example.arztpraxis.model;

import java.io.Serializable;
import java.sql.Date;

public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private int patientId;
    private int employeeId;
    private Date date;
    private int treatmentId;

    public Schedule() {
    }

    public Schedule(long id, int patient_id, int employee_id, Date date, int treatment_id) {
        this.id = id;
        this.patientId = patient_id;
        this.employeeId = employee_id;
        this.date = date;
        this.treatmentId = treatment_id;
    }

    //@Id
    //@Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //@Column(name = "patientid")
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patient_id) {
        this.patientId = patient_id;
    }

    //@Column(name = "mitarbeiterid")
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employee_id) {
        this.employeeId = employee_id;
    }

    //@Column(name = "datum")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //@Column(name = "behandlungid")
    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatment_id) {
        this.treatmentId = treatment_id;
    }

    public void copyData(Schedule copy) {
        this.patientId = copy.patientId;
        this.employeeId = copy.employeeId;
        this.date = copy.date;
        this.treatmentId = copy.treatmentId;
    }

    @Override
    public String toString() {
        return "Schedule [patient_id=" + patientId + ", employee_id=" + employeeId + ", date=" + date + ", treatment_id=" + treatmentId + "]";
    }

}
