package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import java.sql.Date;


import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "app_person")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String firstName;

	private String lastName;

	private Date birthday;
	
	private int adress_id;
	
	private String gender;

	public Person() {
	}

	public Person(long id, String firstName, String lastName, Date birthday, int adress_id, String gender) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.adress_id = adress_id;
		this.gender = gender;
	}

	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "vorname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "name")
	public String getLastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="geburtstag")
	public Date getBirthday() {
	    return birthday;
	}
	
	public void setBirthday(Date birthday) {
	    this.birthday=birthday;
	}
	
	@Column(name = "adresseid")
	public int getAdress() {
	    return adress_id;
	}
	
	public void setAdress(int adress_id) {
	    this.adress_id=adress_id;
	}
	
	@Column(name = "geschlecht")
	public String getGender() {
	    return gender;
	}
	
	public void setGender(String gender) {
	    this.gender=gender;
	}

	public void copyData(Person copy) {
	  this.id = copy.id;
      this.firstName = copy.firstName;
      this.lastName = copy.lastName;
      this.birthday = copy.birthday;
      this.adress_id = copy.adress_id;
      this.gender = copy.gender;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + ", adress_id=" + adress_id + ", gender=" + gender + "]";
	}

}
