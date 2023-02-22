package com.fdmgroup.clander.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String content;
	private Date date;
	private String type;
	
	@OneToOne
	private User sender;
	
	@OneToOne
	private User receiver;
	
	@Transient
	private String[] types = {"MESSAGE","INVITE","REPORT"};
	
	
	public Message() {
		this.date = new Date();
		this.type = this.types[0];
	}
	
	public Message(User sender, String content, User receiver) {
		super();
		this.sender = sender;
		this.content = content;
		this.receiver = receiver;
		this.date = new Date();
		this.type = this.types[0];
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void markAsInvite() {
		this.type = this.types[1];
	}
	
	public void markAsReport() {
		this.type = this.types[2];
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, date, id, receiver, sender, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(content, other.content) && Objects.equals(date, other.date)
				&& Objects.equals(id, other.id) && Objects.equals(receiver, other.receiver)
				&& Objects.equals(sender, other.sender) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", date=" + date + ", type=" + type + ", sender=" + sender
				+ ", receiver=" + receiver + "]";
	}

}
