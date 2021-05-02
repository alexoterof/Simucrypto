package com.alexoterof.simucrypto.dto.cypto.gecko;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricCryptoDto {
	private Double[] month;
	private Double[] day;
}
