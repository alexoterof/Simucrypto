package com.alexoterof.simucrypto.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Community implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1690979023858784925L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String description;
	
	private String pathProfileImg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codCreator")
	private User creator;
	
	@Transient
	public Long codCreator;
	
	public Long getCodUser() {
		return this.creator != null ? this.creator.getId() : null;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="community", orphanRemoval=true)
    private Set<UserCommunity> userCommunity;
	
	public void setUserCommunity(Set<UserCommunity> newUserCommunity) {
		if(this.userCommunity == null)
			this.userCommunity = new HashSet<UserCommunity>();
		this.userCommunity.clear();
		if(newUserCommunity != null)
			this.userCommunity.addAll(newUserCommunity);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="community", orphanRemoval=true)
    private Set<Message> messages;
	
	public void addMessage(Message message) {
		if(this.messages == null)
			this.messages = new HashSet<Message>();
		if(message != null)
			this.messages.add(message);
	}
	
	public void setMessages(Set<Message> newMessages) { 
		if(this.messages == null)
			this.messages = new HashSet<Message>();
		this.messages.clear();
		if(newMessages != null)
			this.messages.addAll(newMessages);
	}
}
