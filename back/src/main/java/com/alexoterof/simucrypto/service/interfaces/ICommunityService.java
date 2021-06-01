package com.alexoterof.simucrypto.service.interfaces;

import java.util.List;

import com.alexoterof.simucrypto.dto.community.CommunityDto;
import com.alexoterof.simucrypto.dto.community.CommunityMinDto;

public interface ICommunityService {

	public List<CommunityMinDto> findAll();

	public List<CommunityMinDto> findByUsername(String username);

	public void create(CommunityDto input, String creatorUsername);
	
}
