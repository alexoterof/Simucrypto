package com.alexoterof.simucrypto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.cypto.gecko.CryptoDetailDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.CryptoMinDto;
import com.alexoterof.simucrypto.dto.cypto.gecko.HistoricCryptoDto;
import com.alexoterof.simucrypto.service.interfaces.ICoinService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/coin")
public class CoinController {
	@Autowired
	ICoinService coinService;
	
	@GetMapping("all")
	public ResponseEntity<List<CryptoMinDto>> findAllMin() {
		List<CryptoMinDto> results = coinService.findAllMin();
		return ResponseEntity.ok(results);		
	}
	
	@GetMapping("detail/{id}")
	public ResponseEntity<CryptoDetailDto> findDetail(@PathVariable String id){
		return ResponseEntity.ok(coinService.findDetail(id));
	}
	
	@GetMapping("historic/{coinname}")
	public ResponseEntity<HistoricCryptoDto> findHistoric(@PathVariable String coinname){
		return ResponseEntity.ok(coinService.getHistoric(coinname));
	}
}
