package com.alexoterof.simucrypto.dto.cypto.gecko;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDetailDto {

	@JMap
	private String id;
	
	@JMap
	private String name;
	
	@JMap
	private String image;
	
	@JMap
	private Double current_price;
	
	@JMap
	private Double price_change_24h;
	
	@JMap
	private Double price_change_percentage_24h;
	
	@JMap
	private Double market_cap;
	
	
}
