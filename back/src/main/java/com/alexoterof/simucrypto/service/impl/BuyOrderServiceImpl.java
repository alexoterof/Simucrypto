package com.alexoterof.simucrypto.service.impl;

import java.time.Instant;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexoterof.simucrypto.dto.buyorder.BuyOrderPlacementDto;
import com.alexoterof.simucrypto.model.BuyOrder;
import com.alexoterof.simucrypto.model.Coin;
import com.alexoterof.simucrypto.model.User;
import com.alexoterof.simucrypto.repository.IBuyOrderDao;
import com.alexoterof.simucrypto.repository.ICoinDao;
import com.alexoterof.simucrypto.repository.IUserDao;
import com.alexoterof.simucrypto.service.interfaces.IBuyOrderService;
import com.alexoterof.simucrypto.service.interfaces.ICoinService;
import com.alexoterof.simucrypto.service.interfaces.IWalletService;

@Service
public class BuyOrderServiceImpl implements IBuyOrderService{
	@Autowired
	IBuyOrderDao orderDao;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	ICoinDao cryptoDao;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	IWalletService walletService;
	
	@Autowired
	ICoinService cryptoService;
	
	@Override
	public void refill(String username, Double ammount) {
		User user = userDao.findByUsername(username);
		BuyOrder buyOrder = getBuyOrder(user, entityManager.getReference(Coin.class, 1L), ammount);
		orderDao.save(buyOrder);
		walletService.refill(username, ammount);
	}
	
	@Override
	public void place(BuyOrderPlacementDto input) {
		User user = userDao.findByUsername(input.getUsername());
		
		Coin coin = cryptoDao.findByName(input.getCoinname()).get(0);
		BuyOrder buyOrder = getBuyOrder(user, coin, input.getAmmount());
		orderDao.save(buyOrder);
		walletService.place(input);
	}
	
	private BuyOrder getBuyOrder(User user, Coin coin, Double ammount) {
		BuyOrder buyOrder = new BuyOrder();		
		buyOrder.setUser(user);
		buyOrder.setCoin(coin);
		buyOrder.setTimestamp(Instant.now().toEpochMilli());
		buyOrder.setAmount(ammount);	
		return buyOrder;
	}
	
}
