package com.ar.hibernate.relationship.case1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "soldier")
public class Soldier {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String name;

	@ManyToOne
	@JoinColumn(name = "troop_fk")
	private Troop troop;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Troop getTroop() {
		return troop;
	}

	public void setTroop(Troop troop) {
		this.troop = troop;
	}
}
