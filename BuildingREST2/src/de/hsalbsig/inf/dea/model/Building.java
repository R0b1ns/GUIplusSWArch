package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "gebaeude")
public class Building implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String number;
	private String street;
	private Collection<Room> allRooms;

	public Building() {
	}

	public Building(long id, String number, String street) {
		this.id = id;
		this.number = number;
		this.street = street;
	}

	@Id
	@Column(name = "gid")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "nummer")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "strasse")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "gid_fk", referencedColumnName = "gid")
	public Collection<Room> getAllRooms() {
		return allRooms;
	}

	public void setAllRooms(Collection<Room> allRooms) {
		this.allRooms = allRooms;
	}

	public void addOneRoom(Room room) {
		if (allRooms == null)
			allRooms = new ArrayList<Room>();

		allRooms.add(room);
	}

	public void copyData(Building copy) {
		this.id = copy.id;
		this.number = copy.number;
		this.street = copy.street;
		this.allRooms = copy.allRooms;
	}

	@Override
	public String toString() {
		return "Building [id=" + id + ", number=" + number + ", street=" + street + "]";
	}

}
