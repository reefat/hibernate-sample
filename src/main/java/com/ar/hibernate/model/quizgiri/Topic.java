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
@Table(name = "TOPIC")
public class Topic {

	private int topicId;
	private String name;
	private String url;
//	private Set<SpecialCategory> specialCategories = new HashSet<>();

	public Topic() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "topicId")
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "SpecialCategoryTopicIdMapping", joinColumns = { @JoinColumn(name = "topicId") }, 
//	inverseJoinColumns = {@JoinColumn(name = "CAT_ID") })
//	public Set<SpecialCategory> getSpecialCategories() {
//		return specialCategories;
//	}
//
//	public void setSpecialCategories(Set<SpecialCategory> specialCategories) {
//		this.specialCategories = specialCategories;
//	}

	/*
	 * @OneToMany
	 * 
	 * @JoinColumn(name = "CAT_ID") public SpecialCategory getSpecialCategory() {
	 * return specialCategory; }
	 * 
	 * public void setSpecialCategory(SpecialCategory specialCategory) {
	 * this.specialCategory = specialCategory; }
	 */

}