package com.alexoterof.simucrypto.dto.user;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
	
	@JMap
	private Long id;
	
	@JMap
	private String username;
	
	@JMap
	private String mail;
	
	@JMap
	private String pathProfileImg;
	
//	@JMap
//	private Long cash;
}
