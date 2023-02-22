package com.fdmgroup.clander.models;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "single_users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	private String email;
	private String password;
	private String role;
	
	private String game;
	private String description;
	private int characterLvl;
	private String characterRace;
	private String characterClass;
	private String characterRole;
	
	private Date banTime = null;
	
	
	@Transient // Means that this field is ignored by database
	private String defaultRole = "ROLE_USER";
	
	public User(){};
	
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.game = null;
		this.description = null;
		this.characterLvl = 0;
		this.characterRace = null;
		this.characterClass = null;
		this.characterRole = null;
		setDefaultRole();
	}
	
	public User(String username, String email, String password, String game, String description, int characterLvl,
			String characterRace, String characterClass, String characterRole) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.game = game;
		this.description = description;
		this.characterLvl = characterLvl;
		this.characterRace = characterRace;
		this.characterClass = characterClass;
		this.characterRole = characterRole;
		setDefaultRole();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public void setDefaultRole() {
		this.role = this.defaultRole;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCharacterLvl() {
		return characterLvl;
	}

	public void setCharacterLvl(int characterLvl) {
		this.characterLvl = characterLvl;
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

	public String getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(String defaultRole) {
		this.defaultRole = defaultRole;
	}

	public Date getBanTime() {
		return banTime;
	}

	public void setBanTime(Date banTime) {
		this.banTime = banTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(banTime, characterClass, characterLvl, characterRace, characterRole, description, email,
				game, id, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(banTime, other.banTime) && Objects.equals(characterClass, other.characterClass)
				&& characterLvl == other.characterLvl && Objects.equals(characterRace, other.characterRace)
				&& Objects.equals(characterRole, other.characterRole) && Objects.equals(description, other.description)
				&& Objects.equals(email, other.email) && Objects.equals(game, other.game) && id == other.id
				&& Objects.equals(password, other.password) && Objects.equals(role, other.role)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", role="
				+ role + ", game=" + game + ", description=" + description + ", characterLvl=" + characterLvl
				+ ", characterRace=" + characterRace + ", characterClass=" + characterClass + ", characterRole="
				+ characterRole + ", banTime=" + banTime + "]";
	}

}
