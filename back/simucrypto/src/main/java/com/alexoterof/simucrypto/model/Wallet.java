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
public class Wallet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5522391881622201739L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double cash;
	
	public Double getCash() {
		return this.cash == null ? 0 : this.cash;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codCoin")
    private Coin coin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUser")
	private User user;
	
	@Transient
	private Long codCoin;
	
	public Long getCodCoin() {
		return this.coin != null ? this.coin.getId() : null;
	}
	
	@Transient
	private String coinname;
	
	public String getCoinname() {
		return this.coin != null ? this.coin.getName() : "";
	}
	
	@Transient
	private Long idUser;
	
	public Long getIdUser() {
		return this.user != null ? this.user.getId() : null;
	}
	
	@Transient
	private String username;
	
	public String getUsername() {
		return this.user != null ? this.user.getUsername() : "";
	}

}
