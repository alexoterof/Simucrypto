package com.alexoterof.simucrypto.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1078419093935539128L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String mail;
	
	private String pathProfileImg;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user", orphanRemoval=true)
	private Set<BuyOrder> orders;
	
	public void setOrders(Set<BuyOrder> newOrders) {
		if(this.orders == null)
			this.orders = new HashSet<BuyOrder>();
		this.orders.clear();
		if(newOrders != null)
			this.orders.addAll(newOrders);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user", orphanRemoval=true)
    private Set<Wallet> wallets;
	
	public void setWallets(Set<Wallet> newWallets) {
		if(this.wallets == null)
			this.wallets = new HashSet<Wallet>();
		this.wallets.clear();
		if(newWallets != null)
			this.wallets.addAll(newWallets);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user", orphanRemoval=true)
    private Set<UserCommunity> userCommunity;
	
	public void setUserCommunity(Set<UserCommunity> newUserCommunity) {
		if(this.userCommunity == null)
			this.userCommunity = new HashSet<UserCommunity>();
		this.userCommunity.clear();
		if(newUserCommunity != null)
			this.userCommunity.addAll(newUserCommunity);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="user", orphanRemoval=true)
    private Set<Message> messages;
	
	public void addMessage(Message message) {
		if(this.messages == null)
			this.messages = new HashSet<Message>();
		this.messages.add(message);
	}
	
	public void setMessages(Set<Message> newMessages) {
		if(this.messages == null)
			this.messages = new HashSet<Message>();
		this.messages.clear();
		if(newMessages != null)
			this.messages.addAll(newMessages);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="creator", orphanRemoval=true)
    private Set<Community> createdCommunities;
	
	public void addComunidadCreada(Community createdCommunity) {
		if(this.createdCommunities == null)
			this.createdCommunities = new HashSet<Community>();
		this.createdCommunities.add(createdCommunity);
	}
}
