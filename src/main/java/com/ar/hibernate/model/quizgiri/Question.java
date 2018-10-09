package com.ar.hibernate.model.quizgiri;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Question")

@NamedQuery(name = "get_all_questions_under_a_topic", query = "from Question where topicId=:topicId")

public class Question {

	private long questionId;
	private String bangla;
	private String english;
	private int topicId;
	private Set<Option> options;
	private long correctAnswerId;

	public Question() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "questionId")
	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	@Column(name = "bangla")
	public String getBangla() {
		return bangla;
	}

	public void setBangla(String bangla) {
		this.bangla = bangla;
	}

	@Column(name = "english")
	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	@Column(name = "topicId")
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	@Column(name = "correctAnswerId")
	public long getCorrectAnswerId() {
		return correctAnswerId;
	}

	public void setCorrectAnswerId(long correctAnswerId) {
		this.correctAnswerId = correctAnswerId;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", bangla=" + bangla + ", english=" + english + ", topicId="
				+ topicId + ", options=" + options + ", correctAnswerId=" + correctAnswerId + "]";
	}
}