package com.fdmgroup.clander.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Boolean isMatched;
	
	@ManyToOne
	private User user1;
	
	@ManyToOne
	private User user2;
	
	public Match() {}

	public Match(User user1, User user2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
		this.isMatched = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	

	public Boolean getIsMatched() {
		return isMatched;
	}

	public void setIsMatched(Boolean isMatched) {
		this.isMatched = isMatched;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, isMatched, user1, user2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return Objects.equals(id, other.id) && Objects.equals(isMatched, other.isMatched)
				&& Objects.equals(user1, other.user1) && Objects.equals(user2, other.user2);
	}

	@Override
	public String toString() {
		return "Match [id=" + id + ", isMatched=" + isMatched + ", user1=" + user1 + ", user2=" + user2 + "]";
	}
	
}
