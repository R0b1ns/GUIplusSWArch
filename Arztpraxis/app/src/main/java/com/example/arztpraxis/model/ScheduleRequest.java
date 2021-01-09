package com.example.arztpraxis.model;

import java.io.Serializable;


//import javax.persistence.*;

// @SuppressWarnings("serial")
//@Entity
//@Table(name = "app_terminanfragen")
public class ScheduleRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String priority;

	private String note;
	
	private int patientId;
	
	private int employeeId;

	public ScheduleRequest() {
	}

	public ScheduleRequest(long id, String priority, String note, int patient_id, int employee_id) {
		this.id = id;
		this.priority = priority;
		this.note = note;
		this.patientId = patient_id;
		this.employeeId = employee_id;
	}

	//@Id
	//@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//@Column(name = "prioritaet")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	//@Column(name = "notiz")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
    
    public void copyData(ScheduleRequest copy) {
      this.id = copy.id;
      this.priority = copy.priority;
      this.note = copy.note;
      this.patientId = copy.patientId;
      this.employeeId = copy.employeeId;
	}

	@Override
	public String toString() {
		return "ScheduleRequest [id=" + id + ", priority=" + priority + ", note=" + note + "]";
	}

}
