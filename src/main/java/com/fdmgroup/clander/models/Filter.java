package com.fdmgroup.clander.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Filter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	private User user;
	
	private String game;
	private int characterLvlFrom;
	private int characterLvlTo;
	private String characterRace;
	private String characterClass;
	private String characterRole;
	

	
	public Filter(){};
	
	public Filter(User user,String game, int characterLvlFrom, int characterLvlTo, String characterRace, String characterClass, String characterRole) {
		super();
		this.user = user;
		this.game = game;
		this.characterLvlFrom = characterLvlFrom;
		this.characterLvlTo = characterLvlTo;
		this.characterRace = characterRace;
		this.characterClass = characterClass;
		this.characterRole = characterRole;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getCharacterLvlFrom() {
		return characterLvlFrom;
	}

	public void setCharacterLvlFrom(int characterLvlFrom) {
		this.characterLvlFrom = characterLvlFrom;
	}

	public int getCharacterLvlTo() {
		return characterLvlTo;
	}

	public void setCharacterLvlTo(int characterLvlTo) {
		this.characterLvlTo = characterLvlTo;
	}

	public String getCharacterRace() {
		return characterRace;
	}

	public void setCharacterRace(String characterRace) {
		this.characterRace = characterRace;
	}

	public String getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}

	public String getCharacterRole() {
		return characterRole;
	}

	public void setCharacterRole(String characterRole) {
		this.characterRole = characterRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(characterClass, characterLvlFrom, characterLvlTo, characterRace, characterRole, game, id,
				user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		return Objects.equals(characterClass, other.characterClass) && characterLvlFrom == other.characterLvlFrom
				&& characterLvlTo == other.characterLvlTo && Objects.equals(characterRace, other.characterRace)
				&& Objects.equals(characterRole, other.characterRole) && Objects.equals(game, other.game)
				&& id == other.id && Objects.equals(user, other.user);
	}



}
