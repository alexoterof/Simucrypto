package com.alexoterof.simucrypto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexoterof.simucrypto.model.Coin;
import com.alexoterof.simucrypto.repository.ICoinDao;

@SpringBootApplication
public class SimucryptoApplication implements CommandLineRunner{
	@Autowired
	ICoinDao dao;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SimucryptoApplication.class, args);
	}
	
	@Override
	public void run(String... args){
		List<Coin> defaultCoins = new ArrayList<Coin>();
		defaultCoins.add(new Coin(1L, "eur", "eur"));
		defaultCoins.add(new Coin(2L, "bitcoin", "btc"));
		defaultCoins.add(new Coin(3L, "ethereum", "eth"));
		defaultCoins.add(new Coin(4L, "litecoin", "ltc"));
		defaultCoins.add(new Coin(5L, "bitcoin-cash", "bch"));
		defaultCoins.add(new Coin(6L, "binancecoin", "bnb"));
		defaultCoins.add(new Coin(7L, "eos", "eos"));
		defaultCoins.add(new Coin(8L, "ripple", "xrp"));
		defaultCoins.add(new Coin(9L, "stellar", "xlm"));
		defaultCoins.add(new Coin(10L, "chainlink", "link"));
		defaultCoins.add(new Coin(11L, "polkadot", "dot"));
		defaultCoins.add(new Coin(12L, "yearn-finance", "yfi"));
		defaultCoins.add(new Coin(13L, "blackdragon-token", "bdt"));
		defaultCoins.add(new Coin(14L, "bitcoin-hd", "bmd"));
		defaultCoins.add(new Coin(15L, "candy-protocol", "cad"));
		defaultCoins.add(new Coin(16L, "lkr-coin", "lkr"));
		defaultCoins.add(new Coin(17L, "saren", "sar"));
		defaultCoins.add(new Coin(18L, "try-finance", "try"));
		defaultCoins.add(new Coin(19L, "xrpalike-gene", "xng"));
		defaultCoins.add(new Coin(20L, "bitcoinus", "bits"));
		dao.saveAll(defaultCoins);
	}

}
