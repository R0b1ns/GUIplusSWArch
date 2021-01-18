package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "app_terminplan")
public class Schedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private int patient_id;
    private int employee_id;
    private Date date;
    private int treatment_id;

    public Schedule() {
    }

    public Schedule(long id, int patient_id, int employee_id, Date date, int treatment_id) {
        this.id = id;
        this.patient_id = patient_id;
        this.employee_id = employee_id;
        this.date = date;
        this.treatment_id = treatment_id;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "patientid")
    public int getPatientId() {
        return patient_id;
    }

    public void setPatientId(int patient_id) {
        this.patient_id = patient_id;
    }

    @Column(name = "mitarbeiterid")
    public int getEmployeeId() {
        return employee_id;
    }

    public void setEmployeeId(int employee_id) {
        this.employee_id = employee_id;
    }

    @Column(name = "datum")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "behandlungid")
    public int getTreatmentId() {
        return treatment_id;
    }

    public void setTreatmentId(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public void copyData(Schedule copy) {
        this.patient_id = copy.patient_id;
        this.employee_id = copy.employee_id;
        this.date = copy.date;
        this.treatment_id = copy.treatment_id;
    }

    @Override
    public String toString() {
        return "Schedule [patient_id=" + patient_id + ", employee_id=" + employee_id + ", date=" + date + ", treatment_id=" + treatment_id + "]";
    }

}
