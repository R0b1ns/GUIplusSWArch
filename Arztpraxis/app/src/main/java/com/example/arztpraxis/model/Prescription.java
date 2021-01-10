package com.example.arztpraxis.model;

import java.io.Serializable;
import java.util.Date;

public class Prescription implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private int patientId;
    private int employeeId;
    private int drugId;
    private int diseaseId;
    private Date prescriptionDate;

    public Prescription() {
    }

    public Prescription(long id, int patient_id, int employee_id, int drug_id, int disease_id, Date prescription_date) {
        this.id = id;
        this.patientId = patient_id;
        this.employeeId = employee_id;
        this.drugId = drug_id;
        this.diseaseId = disease_id;
        this.prescriptionDate = prescription_date;
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

    //@Column(name = "medikamentid")
    public int getDrugId() {
        return drugId;
    }

    public void setDrugId(int drug_id) {
        this.drugId = drug_id;
    }

    //@Column(name = "krankheitid")
    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int disease_id) {
        this.diseaseId = disease_id;
    }

    //@Column(name= "ausstelldatum")
    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescription_date) {
        this.prescriptionDate = prescription_date;
    }

    public void copyData(Prescription copy) {
        this.id = copy.id;
        this.patientId = copy.patientId;
        this.employeeId = copy.employeeId;
        this.drugId = copy.drugId;
        this.diseaseId = copy.diseaseId;
        this.prescriptionDate = copy.prescriptionDate;
    }

    @Override
    public String toString() {
        return "Prescription [id=" + id + "patient_id=" + patientId + ", employee_id=" + employeeId + ", drug_id=" + drugId + ", disease_id=" + diseaseId + ", prescription_date=" + prescriptionDate + "]";
    }

}
