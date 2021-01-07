package de.hsalbsig.inf.dea.model;

import java.io.Serializable;
import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "raum")
public class Room implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "rid")
	private long id;

	@Column(name = "art")
	private String kind;

	@Column(name = "nummer")
	private String number;

	public Room() {
	}

	public Room(int id, String kind, String number) {
		this.id = id;
		this.kind = kind;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", kind=" + kind + ", number=" + number + "]";
	}

}
