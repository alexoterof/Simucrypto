package com.alexoterof.simucrypto.service.interfaces;

import com.alexoterof.simucrypto.dto.MessageDto;

public interface IMessageService {
	
	public void postMessage(MessageDto message);
}
