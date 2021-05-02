package com.alexoterof.simucrypto.service;

import java.util.List;

import com.alexoterof.simucrypto.dto.cypto.gecko.CryptoDetailDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.CryptoMinDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.HistoricCryptoDto;
import com.alexoterof.simucrypto.model.Coin;

public interface ICoinService {
	public List<CryptoMinDto> findAllMin();
	public CryptoDetailDto findDetail(String id);
	public Double getConversionRate(Coin from, Coin to);
	public HistoricCryptoDto getHistoric(String name);
}
