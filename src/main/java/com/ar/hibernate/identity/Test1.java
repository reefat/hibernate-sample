package com.ar.hibernate.identity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Test1 {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="haha_generator")
	@SequenceGenerator(name="haha_generator", sequenceName = "haha_seq", allocationSize=50, initialValue=1000)
	private int id;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
