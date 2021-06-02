package com.alexoterof.simucrypto.service.interfaces;

public interface IUserCommunityService{
	public void addUser(Long codCommunity, Long codUser);
	public void addUser(String username, Long codCommunity);
}
