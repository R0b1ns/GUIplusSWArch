package com.example.arztpraxis.model;

import java.io.Serializable;
import java.sql.Date;
//import javax.persistence.*;

// @SuppressWarnings("serial")
//@Entity
//@Table(name = "app_rezept")
public class Prescription implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	private int patient_id;

	private int employee_id;
	
	private int drug_id;
	
	private int disease_id;
	
	private Date prescription_date;

	

	public Prescription() {
	}

	public Prescription(long id, int patient_id, int employee_id, int drug_id, int disease_id, Date prescription_date) {
		this.id = id;
	    this.patient_id = patient_id;
		this.employee_id = employee_id;
		this.drug_id = drug_id;
		this.disease_id =disease_id;
		this.prescription_date = prescription_date;
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
		return patient_id;
	}

	public void setPatientId(int patient_id) {
		this.patient_id = patient_id;
	}

	//@Column(name = "mitarbeiterid")
	public int getEmployeeId() {
		return employee_id;
	}

	public void setEmployeeId(int employee_id) {
		this.employee_id = employee_id;
	}

	//@Column(name = "medikamentid")
	public int getDrugId() {
	    return drug_id;
	}
	
	public void setDrugId( int drug_id) {
	    this.drug_id = drug_id;
	}
	
	//@Column(name = "krankheitid")
    public int getDiseaseId() {
        return disease_id;
    }
    
    public void setDiseaseId( int disease_id) {
        this.disease_id = disease_id;
    }
	
	//@Column(name= "ausstelldatum")
	public Date getPrescriptionDate() {
	    return prescription_date;
	}
	
	public void setPrescriptionDate(Date prescription_date) {
	    this.prescription_date = prescription_date;
	}
	
	public void copyData(Prescription copy) {
	  this.id = copy.id;
      this.patient_id = copy.patient_id;
      this.employee_id = copy.employee_id;
      this.drug_id = copy.drug_id;
      this.disease_id = copy.disease_id;
      this.prescription_date = copy.prescription_date;
    }

	@Override
	public String toString() {
		return "Prescription [id=" + id + "patient_id=" + patient_id + ", employee_id=" + employee_id + ", drug_id=" + drug_id + ", disease_id=" + disease_id + ", prescription_date=" + prescription_date + "]";
	}

}
