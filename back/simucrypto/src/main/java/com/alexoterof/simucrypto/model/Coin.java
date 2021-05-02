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
public class Coin implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4220483554711089625L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String symbol;
	
	private String pathIcon;
	
	private Double lastReading;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="coin", orphanRemoval=true)
    private Set<Wallet> wallet;
	
	public void setCarteras(Set<Wallet> newWallets) {
		if(this.wallet == null)
			this.wallet = new HashSet<Wallet>();
		this.wallet.clear();
		if(newWallets != null)
			this.wallet.addAll(newWallets);
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="coin", orphanRemoval=true)
	private Set<BuyOrder> orders;
	
	public void addOrder(BuyOrder newOrder) {
		if(this.orders == null)
			this.orders = new HashSet<BuyOrder>();
		this.orders.add(newOrder);
	}
	
	public void setOrders(Set<BuyOrder> newOrders) {
		if(this.orders == null)
			this.orders = new HashSet<BuyOrder>();
		this.orders.clear();
		if(newOrders != null)
			this.orders.addAll(newOrders);
	}	
}
