package com.alexoterof.simucrypto.dto.user;

import com.googlecode.jmapper.annotations.JGlobalMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JGlobalMap
public class UserMinDto {
	
	private String username;
	
	private String mail;
	
	private String password;

}
