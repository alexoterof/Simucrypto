package com.alexoterof.simucrypto.service;

import java.util.List;

import com.alexoterof.simucrypto.dto.buyorder.BuyOrderPlacementDto;
import com.alexoterof.simucrypto.dto.wallet.WalletDto;

public interface IWalletService{
	public List<WalletDto> findAllByUsername(String username);
	public void place(BuyOrderPlacementDto input);
	public void refill(String username, Double ammount);
	
}
