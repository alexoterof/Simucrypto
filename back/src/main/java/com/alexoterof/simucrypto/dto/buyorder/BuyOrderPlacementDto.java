package com.alexoterof.simucrypto.dto.buyorder;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyOrderPlacementDto {
	@JMap
	private String coinname;
	
	@JMap
	private String coincode;
	
	@JMap
	private Double ammount;
	
	@JMap
	private String username;
	
	@JMap
	private String paymentMethod;
}
