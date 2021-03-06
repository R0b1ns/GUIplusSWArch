package de.hsalbsig.inf.dea.model;

import java.io.Serializable;


import javax.persistence.*;

// @SuppressWarnings("serial")
@Entity
@Table(name = "app_position")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;

	private String description;

	public Position() {
	}

	public Position(long id, String description) {
		this.id = id;
		this.description = description;
	}

	@Id
	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "bezeichnung")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void copyData(Position copy) {
        this.id = copy.id;
        this.description = copy.description;
    }

	@Override
	public String toString() {
		return "Position [id=" + id + ", description=" + description + "]";
	}

}
