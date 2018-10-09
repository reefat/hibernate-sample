package com.ar.hibernate.model.seconddb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")

// Using @NamedQuery for single JPQL or HQL
@NamedQuery(name = "get_total_dept", query = "select count(1) from Department")

// Using @NamedQueries for multiple JPQL or HQL
@NamedQueries({ @NamedQuery(name = "get_dept_name_by_id", query = "select name from Department where id=:id"),
		@NamedQuery(name = "get_all_dept", query = "from Department") })
public class Department {
	@Id
	@Column(name = "DPT_ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees = new ArrayList<>();

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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}