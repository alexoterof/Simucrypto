package com.alexoterof.simucrypto.service.impl;

import java.time.Instant;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexoterof.simucrypto.dto.MessageDto;
import com.alexoterof.simucrypto.model.Community;
import com.alexoterof.simucrypto.model.Message;
import com.alexoterof.simucrypto.model.User;
import com.alexoterof.simucrypto.repository.IMessageDao;
import com.alexoterof.simucrypto.service.interfaces.IMessageService;

@Service
public class MessageServiceImpl implements IMessageService{

	@Autowired
	IMessageDao messageDao;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void postMessage(MessageDto newMessage) {
		Message message = new Message();
		message.setCommunity(entityManager.getReference(Community.class, newMessage.getCodCommunity()));
		message.setUser(entityManager.getReference(User.class, newMessage.getCodUser()));
		message.setTimestamp(Instant.now().toEpochMilli());
		message.setIsDeleted(false);
		message.setMsg(newMessage.getText());
		messageDao.save(message);
	}

}
