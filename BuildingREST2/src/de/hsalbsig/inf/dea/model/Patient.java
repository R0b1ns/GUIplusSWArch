package de.hsalbsig.inf.dea.model;

import java.io.Serializable;



import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "app_patient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private int ssn;
	
	private int person_id;
	
	private int health_insurance_id;

	public Patient() {
	}

	public Patient(long id, int ssn, int person_id, int health_insurance_id) {
		this.id = id;
		this.ssn = ssn;
		this.person_id = person_id;
		this.health_insurance_id = health_insurance_id;
	}

	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Column(name = "versicherungsnr")
	public int getSSN() {
	    return ssn;
	}

	public void setSSN(int ssn) {
	    this.ssn = ssn;
	}
	
	
	@Column(name = "personid")
    public int getPerson() {
        return person_id;
    }

    public void setPerson(int person_id) {
        this.person_id = person_id;
    }
    
    @Column(name = "krankenkasseid")
    public int getHealthInsurance() {
        return health_insurance_id;
    }

    public void setHealthInsurance(int health_insurance_id) {
        this.health_insurance_id = health_insurance_id;
    }
		
	
	public void copyData(Patient copy) {
	  this.id = copy.id;
      this.ssn = copy.ssn;
      this.person_id = copy.person_id;
      this.health_insurance_id = copy.health_insurance_id;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", SSN=" + ssn + ", person=" + person_id + ", healthInsurance=" + health_insurance_id + "}";
	}

}
