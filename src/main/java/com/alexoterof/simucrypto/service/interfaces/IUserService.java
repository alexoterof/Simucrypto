package com.alexoterof.simucrypto.service.interfaces;

import com.alexoterof.simucrypto.dto.JwtDto;
import com.alexoterof.simucrypto.dto.user.UserDetailDto;
import com.alexoterof.simucrypto.dto.user.UserMinDto;

public interface IUserService{

	public UserDetailDto getDetailOf(String username);
	public void register(UserMinDto input);
	public JwtDto login(UserMinDto input);
	public void delete(String username);
}
