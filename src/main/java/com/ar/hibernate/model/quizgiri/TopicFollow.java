package com.ar.hibernate.model.quizgiri;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topicFollow")
public class TopicFollow implements Serializable {
	private long followerId;
	private int topicId;

	@Id
	@Column(name = "follower_id")
	public long getFollowerId() {
		return followerId;
	}

	public void setFollowerId(long followerId) {
		this.followerId = followerId;
	}

	@Id
	@Column(name = "topic_id")
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TopicFollow))
			return false;
		TopicFollow that = (TopicFollow) o;
		return getFollowerId() == that.getFollowerId() && getTopicId() == that.getTopicId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFollowerId(), getTopicId());
	}

}
