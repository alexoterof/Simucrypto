package com.alexoterof.simucrypto.dto.community;

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
public class CommunityDto {
	private Long id;
	private String name;	
	private String description;	
	private String pathProfileImg;
	private Long creatorId;	
}
