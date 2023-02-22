package com.fdmgroup.clander.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MatchQueue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User queueOwner;
	
	@ManyToOne
	private User likedUser;
	
	public MatchQueue() {}

	public MatchQueue(User queueOwner, User likedUser) {
		super();
		this.queueOwner = queueOwner;
		this.likedUser = likedUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getQueueOwner() {
		return queueOwner;
	}

	public void setQueueOwner(User queueOwner) {
		this.queueOwner = queueOwner;
	}

	public User getLikedUser() {
		return likedUser;
	}

	public void setLikedUser(User likedUser) {
		this.likedUser = likedUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, likedUser, queueOwner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchQueue other = (MatchQueue) obj;
		return Objects.equals(id, other.id) && Objects.equals(likedUser, other.likedUser)
				&& Objects.equals(queueOwner, other.queueOwner);
	}

	@Override
	public String toString() {
		return "MatchQueue [id=" + id + ", queueOwner=" + queueOwner + ", likedUser=" + likedUser + "]";
	}
}
