package com.ar.hibernate.model.quizgiri;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "game")
public class Game {

	// @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

	private UUID id;
	private long playerId;
	private int topicId;
	private int experienceGathered;
	private long startTime;
	private long endTime;

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

	@Column(name = "player_id")
	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	@Column(name = "topic_id", updatable = false)
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@Column(name = "xp")
	public int getExperienceGathered() {
		return experienceGathered;
	}

	public void setExperienceGathered(int experienceGathered) {
		this.experienceGathered = experienceGathered;
	}

	@Column(name = "start_time")
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time")
	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

}
