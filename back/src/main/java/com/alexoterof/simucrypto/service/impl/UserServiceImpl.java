package com.alexoterof.simucrypto.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alexoterof.simucrypto.configuration.JwtHelper;
import com.alexoterof.simucrypto.dto.JwtDto;
import com.alexoterof.simucrypto.dto.user.UserDetailDto;
import com.alexoterof.simucrypto.dto.user.UserMinDto;
import com.alexoterof.simucrypto.model.User;
import com.alexoterof.simucrypto.repository.IUserDao;
import com.alexoterof.simucrypto.service.IUserService;
import com.googlecode.jmapper.JMapper;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;
	
	@Autowired
	JwtHelper jwtHelper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public UserDetailDto getDetailOf(String username) {
		return convertToDetailDto(userDao.findByUsername(username).get(0));
	}

	@Override
	public void register(UserMinDto input) {
		if(userDao.findByUsername(input.getUsername()).size() != 0) {
			throw new RuntimeException("User already exists");
		}
		User user = new User();
		BeanUtils.copyProperties(input, user);
		user.setPassword(passwordEncoder.encode(input.getPassword()));
		userDao.save(user);
	}
	
	@Override
	public JwtDto login(UserMinDto input) {
		if(input == null || 
		   input.getUsername() == null || 
		   input.getPassword() == null)
			throw new RuntimeException("Null input");
		User user = userDao.findByUsername(input.getUsername()).get(0);
		if(user == null)
			throw new RuntimeException("User not found");
		if(!passwordEncoder.matches(input.getPassword(), user.getPassword()))
			throw new RuntimeException("Incorrect password");
			
		Map<String, String> claims = new HashMap<>();
		claims.put("username", user.getUsername());
		claims.put("userId", user.getId().toString());
		
		String jwt = jwtHelper.createJwtForClaims(user.getUsername(), claims);
		return new JwtDto(jwt);		
	}
	
	@Override
	@Transactional
	public void delete(String username) {
		userDao.deleteByUsername(username);			
	}
	
	private UserDetailDto convertToDetailDto(User entity) {
		JMapper<UserDetailDto, User> mapper = new JMapper<>
				(UserDetailDto.class, User.class);
		return mapper.getDestination(entity);
	}
	
}
