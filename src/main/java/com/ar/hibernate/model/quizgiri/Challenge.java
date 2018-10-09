package com.ar.hibernate.model.quizgiri;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Challenge {

	private UUID id;
	private int topic;
	private long challenger;
	private int challengerPoint;
	private long challengedUser;
	private int challengedUserPoint;
	private int state;
	private long startTime;
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "gameId", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	@Column(name="topic", nullable=false, updatable=false)
	public int getTopic() {
		return topic;
	}

	public void setTopic(int topic) {
		this.topic = topic;
	}

	@Column(name="challenger")
	public long getChallenger() {
		return challenger;
	}

	public void setChallenger(long challenger) {
		this.challenger = challenger;
	}

	@Column(name="challenger_point")
	public int getChallengerPoint() {
		return challengerPoint;
	}

	public void setChallengerPoint(int challengerPoint) {
		this.challengerPoint = challengerPoint;
	}

	@Column(name="challenged_user")
	public long getChallengedUser() {
		return challengedUser;
	}

	public void setChallengedUser(long challengedUser) {
		this.challengedUser = challengedUser;
	}

	@Column(name="challenged_user_point")
	public int getChallengedUserPoint() {
		return challengedUserPoint;
	}

	public void setChallengedUserPoint(int challengedUserPoint) {
		this.challengedUserPoint = challengedUserPoint;
	}

	@Column(name="state", nullable=false)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name="create_time")
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
}
