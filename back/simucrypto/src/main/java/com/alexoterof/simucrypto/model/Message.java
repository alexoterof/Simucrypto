package com.alexoterof.simucrypto.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Message implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 8592718474314621282L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long timestamp;
	
	private String msg;
	
	private Boolean isDeleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUser")
	private User user;
	
	@Transient
	public Long codUser;
	
	public Long getCodUser() {
		return this.user != null ? this.user.getId() : null;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codCommunity")
	private Community community;
	
	@Transient
	public Long codCommunity;
	
	public Long getCodCommunity() {
		return this.community != null ? this.community.getId() : null;
	}
}
