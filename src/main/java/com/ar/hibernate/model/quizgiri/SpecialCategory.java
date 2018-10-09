package com.ar.hibernate.model.quizgiri;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIAL_CATEGORY")
public class SpecialCategory {

	private int id;
	private String name;
	private Set<Topic> topics = new HashSet<>(0);

	public SpecialCategory() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name = "CAT_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "specialCategory", cascade=CascadeType.ALL)*/
//	@ManyToMany(mappedBy = "specialCategories")
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SpecialCategoryTopicIdMapping", joinColumns = { @JoinColumn(name = "CAT_ID") }, 
	inverseJoinColumns = {@JoinColumn(name = "topicId") })
	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
}