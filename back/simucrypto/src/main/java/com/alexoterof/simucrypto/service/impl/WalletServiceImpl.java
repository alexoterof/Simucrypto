package com.alexoterof.simucrypto.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexoterof.simucrypto.dto.buyorder.BuyOrderPlacementDto;
import com.alexoterof.simucrypto.dto.wallet.WalletDto;
import com.alexoterof.simucrypto.model.Coin;
import com.alexoterof.simucrypto.model.Wallet;
import com.alexoterof.simucrypto.repository.ICoinDao;
import com.alexoterof.simucrypto.repository.IUserDao;
import com.alexoterof.simucrypto.repository.IWalletDao;
import com.alexoterof.simucrypto.service.ICoinService;
import com.alexoterof.simucrypto.service.IWalletService;
import com.googlecode.jmapper.JMapper;

@Service
public class WalletServiceImpl implements IWalletService {
	@Autowired
	IWalletDao walletDao;

	@Autowired
	IUserDao userDao;
	
	@Autowired
	ICoinDao coinDao;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	ICoinService coinService;
	
	@Override
	public List<WalletDto> findAllByUsername(String username) {
		if(username == null)
			throw new RuntimeException("Username not valid");
		List<Wallet> wallets = walletDao.findByUsername(username);
		return wallets.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	
	
	public void refill(String username, Double ammount) {
		Wallet targetWallet = getWallet(username, "eur");
		targetWallet.setCash(targetWallet.getCash() + ammount);
		walletDao.save(targetWallet);
	}
	
	@Override
	public void place(BuyOrderPlacementDto input) {	
		Coin payCoin = coinDao.findByName(input.getPaymentMethod()).get(0);
		Coin targetCoin = coinDao.findByName(input.getCoinname()).get(0);
		String username = input.getUsername();
		Double ammount = input.getAmmount();		
		
		Double conversionRate = coinService.getConversionRate(payCoin, targetCoin);		
		Wallet paymentWallet= walletDao.findByUsernameAndCoinname(username, payCoin.getName()).get();	
		Wallet targetWallet = getWallet(username, targetCoin.getName());
		if(paymentWallet.getCash() < ammount / conversionRate)
			throw new RuntimeException("Not enough money to perform this transaction");				
		paymentWallet.setCash(paymentWallet.getCash() - (ammount / conversionRate));
		targetWallet.setCash(targetWallet.getCash() + ammount);		
		walletDao.save(targetWallet);
	}	
	
	private Wallet getWallet(String username, String coinname) {
		Optional<Wallet> opWallet = walletDao.findByUsernameAndCoinname(username,coinname);
		Wallet obtainingWallet;		
		if(opWallet.isPresent())
			obtainingWallet = opWallet.get();
		else 
			obtainingWallet = createWallet(username, coinname);
		return obtainingWallet;
	}
	
	private Wallet createWallet(String username, String coinname) {
		Wallet creatingWallet = new Wallet();
		creatingWallet.setUser(userDao.findByUsername(username).get(0));
		creatingWallet.setCoin(coinDao.findByName(coinname).get(0));
		return creatingWallet;
	}
	
	private WalletDto convertToDto(Wallet entity) {
		JMapper<WalletDto, Wallet> mapper = new JMapper<>
				(WalletDto.class, Wallet.class);
		return mapper.getDestination(entity);
	}
}
