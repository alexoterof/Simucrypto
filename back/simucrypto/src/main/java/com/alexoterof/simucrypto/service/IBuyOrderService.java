package com.alexoterof.simucrypto.service;

import com.alexoterof.simucrypto.dto.buyorder.BuyOrderPlacementDto;

public interface IBuyOrderService {
	public void refill(String username, Double ammount);
	public void place(BuyOrderPlacementDto input);
}
