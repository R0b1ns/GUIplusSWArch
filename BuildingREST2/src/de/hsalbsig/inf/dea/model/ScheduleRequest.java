package de.hsalbsig.inf.dea.model;

import java.io.Serializable;


import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "app_terminanfragen")
public class ScheduleRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String priority;
    private String note;
    private int patient_id;
    private int employee_id;

    public ScheduleRequest() {
    }

    public ScheduleRequest(long id, String priority, String note, int patient_id, int employee_id) {
        this.id = id;
        this.priority = priority;
        this.note = note;
        this.patient_id = patient_id;
        this.employee_id = employee_id;
    }

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "prioritaet")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Column(name = "notiz")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public void copyData(ScheduleRequest copy) {
        this.id = copy.id;
        this.priority = copy.priority;
        this.note = copy.note;
        this.patient_id = copy.patient_id;
        this.employee_id = copy.employee_id;
    }

    @Override
    public String toString() {
        return "Building [id=" + id + ", number=" + priority + ", street=" + note + "]";
    }

}
