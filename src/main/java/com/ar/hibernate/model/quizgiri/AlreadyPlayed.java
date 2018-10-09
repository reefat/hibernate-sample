package com.ar.hibernate.model.quizgiri;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "already_played", uniqueConstraints = {@UniqueConstraint(columnNames = {"playerId", "topicId", "questionId"})})
public class AlreadyPlayed  implements Serializable{


	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -8888414478501374852L;
	
	private long playerId;
	private int topicId;
	private long questionId;

	public AlreadyPlayed() {
	}

	@Id
	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	@Id
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@Id
	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

}