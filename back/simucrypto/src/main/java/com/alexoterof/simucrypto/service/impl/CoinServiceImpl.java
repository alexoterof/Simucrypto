package com.alexoterof.simucrypto.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alexoterof.simucrypto.dto.cypto.gecko.CryptoDetailDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.CryptoMinDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.HistoricCryptoDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.TinyDto;
import com.alexoterof.simucrypto.model.Coin;
import com.alexoterof.simucrypto.service.ICoinService;



@Service
public class CoinServiceImpl implements ICoinService{
	@Autowired 
	RestTemplate restTemplate;
	
	String requestedCoins = "bitcoin,ethereum,litecoin,bitcoin-cash,binancecoin,eos,ripple,stellar,chainlink,polkadot,yearn-finance,blackdragon-token,bitcoin-hd,candy-protocol,lkr-coin,saren,try-finance,xrpalike-gene,bitcoinus";
	ArrayList<String> requestedProps = new ArrayList<String>(Arrays.asList("id","symbol","image","current_price","market_cap"));
	
	@Override
	public List<CryptoMinDto> findAllMin() {
		ResponseEntity<HashMap<String, TinyDto>> json = 
				restTemplate.exchange("https://api.coingecko.com/api/v3/simple/price?ids=" + 
										requestedCoins + 
										"&vs_currencies=eur", 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<HashMap<String, TinyDto>>() {});
		HashMap<String, TinyDto> response = json.getBody();		
		return response.keySet()
					   .stream()
					   .map(key -> 
						   new CryptoMinDto(key, response.get(key).getEur())
					   )
					   .collect(Collectors.toList());
	}
	
	@Override
	public CryptoDetailDto findDetail(String id) {
		if(!requestedCoins.contains(id))
			return null;
		ResponseEntity<List<CryptoDetailDto>> json = 
				restTemplate.exchange("https://api.coingecko.com/api/v3/coins/markets?vs_currency=eur&ids=" + id, 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<List<CryptoDetailDto>>() {});
		return json.getBody().get(0);
	}
	
	public HistoricCryptoDto getHistoric(String name) {
		HistoricCryptoDto response = new HistoricCryptoDto();
		ResponseEntity<HashMap<String, Double[][]>> dayResponse = 
				restTemplate.exchange("https://api.coingecko.com/api/v3/coins/" + name + "/market_chart?vs_currency=eur&days=1&interval=hourly", 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<HashMap<String, Double[][]>>() {});
		ResponseEntity<HashMap<String, Double[][]>> monthResponse = 
				restTemplate.exchange("https://api.coingecko.com/api/v3/coins/" + name + "/market_chart?vs_currency=eur&days=30&interval=daily", 
										HttpMethod.GET, 
										null, 
										new ParameterizedTypeReference<HashMap<String, Double[][]>>() {});
		List<Double> dayDataList = new ArrayList<Double>();
		for(Double[] couple : dayResponse.getBody().get("prices"))
			dayDataList.add(couple[1]);
		response.setDay(dayDataList.toArray(new Double[dayDataList.size()]));		
		
		List<Double> monthDataList = new ArrayList<Double>();
		for(Double[] couple : monthResponse.getBody().get("prices")) 
				monthDataList.add(couple[1]);
		response.setMonth(monthDataList.toArray(new Double[monthDataList.size()]));
		return response;
	}
	
	@Override
	public Double getConversionRate(Coin from, Coin to) {
		if(from.getName().equals("eur"))
			return 1 / getPrice(to.getName());
		String reqUrl = "https://api.coingecko.com/api/v3/simple/price?ids=" + from.getName() + "&vs_currencies=" + to.getSymbol();
		ResponseEntity<HashMap<String, HashMap<String, Double>>> json = 
				restTemplate.exchange(reqUrl, 
									HttpMethod.GET, 
									null, 
									new ParameterizedTypeReference<HashMap<String, HashMap<String, Double>>>() {});
		return json.getBody().get(from.getName()).get(to.getSymbol());
	}
	
	private Double getPrice(String name) {
		ResponseEntity<HashMap<String, TinyDto>> json = restTemplate.exchange("https://api.coingecko.com/api/v3/simple/price?ids=" + name + "&vs_currencies=eur", HttpMethod.GET, null, new ParameterizedTypeReference<HashMap<String, TinyDto>>() {});
		return json.getBody().get(name).eur;
	}
}
