package com.alexoterof.simucrypto.service;

import com.alexoterof.simucrypto.dto.response.JwtDto;
import com.alexoterof.simucrypto.dto.user.UserDetailDto;
import com.alexoterof.simucrypto.dto.user.UserMinDto;

public interface IUserService{

	public UserDetailDto getDetailOf(String username);
	public void register(UserMinDto input);
	public JwtDto login(UserMinDto input);
}
