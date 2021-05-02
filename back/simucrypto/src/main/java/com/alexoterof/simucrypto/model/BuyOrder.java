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
public class BuyOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5721942213073574114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double amount;
	
	private Long timestamp;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codCoin")
    private Coin coin;
	
	@Transient
	public Long codCoin;
	
	public Long getIdCoin() {
		return this.coin != null ? this.coin.getId() : null;
	}
	
	@Transient
	public String coinname;
	
	public String getCoinname() {
		return this.coin != null ? this.coin.getName() : "";
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codUser")
	private User user;
	
	@Transient
	public Long idUser;
	
	public Long getIdUser() {
		return this.user != null ? this.user.getId() : null;
	}
}
