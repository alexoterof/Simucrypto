package com.alexoterof.simucrypto.service.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexoterof.simucrypto.model.Community;
import com.alexoterof.simucrypto.model.User;
import com.alexoterof.simucrypto.model.UserCommunity;
import com.alexoterof.simucrypto.repository.IUserCommunityDao;
import com.alexoterof.simucrypto.repository.IUserDao;
import com.alexoterof.simucrypto.service.interfaces.IUserCommunityService;

@Service
public class UserCommunityServiceImpl implements IUserCommunityService {
	@Autowired
	IUserCommunityDao userCommunityDao;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public void addUser(String username, Long codCommunity) {
		addUser(codCommunity, userDao.findByUsername(username).getId());
	}
	
	@Override
	public void addUser(Long codCommunity, Long codUser) {
		UserCommunity userCommunity = new UserCommunity();
		userCommunity.setCommunity(entityManager.getReference(Community.class, codCommunity));
		userCommunity.setUser(entityManager.getReference(User.class, codUser));
		userCommunityDao.save(userCommunity);		
	}
}
