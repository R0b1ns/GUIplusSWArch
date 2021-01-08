package com.example.arztpraxis.model;

import java.io.Serializable;


//import javax.persistence.*;

// @SuppressWarnings("serial")
//@Entity
//@Table(name = "app_mitarbeiter")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private int position;
	
	private int personnalnumber;
	
	private int personId;

	public Employee() {
	}

	public Employee(long id, int position_id, int personnalnumber, int person_id) {
		this.id = id;
		this.position = position_id;
		this.personnalnumber = personnalnumber;
		this.personId = person_id;
	}

	//@Id
	//@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	//@Column(name = "pos_id")
	public int getPosition(){
	    return position;
	}
	
	public void setPosition(int position_id) {
	    this.position = position_id;
	}
	
	//@Column(name = "personalnummer")
    public int getPersonnalnumber(){
        return personnalnumber;
    }
    
    public void setPersonnalnumber(int personnalnumber) {
        this.personnalnumber = personnalnumber;
    }
    
    //@Column(name = "personid")
    public int getPersonId(){
        return personId;
    }
    
    public void setPersonId(int person_id) {
        this.personId = person_id;
    }

	public void copyData(Employee copy) {
	  this.id = copy.id;
      this.position = copy.position;
      this.personnalnumber = copy.personnalnumber;
      this.personId = copy.personId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", position_id=" + position + ", personnalnumber=" + personnalnumber + ", person_id=" + personId + "]";
	}

}
