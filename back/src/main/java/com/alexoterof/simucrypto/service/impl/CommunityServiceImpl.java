package com.alexoterof.simucrypto.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexoterof.simucrypto.dto.community.CommunityDto;
import com.alexoterof.simucrypto.dto.community.CommunityMinDto;
import com.alexoterof.simucrypto.model.Community;
import com.alexoterof.simucrypto.model.User;
import com.alexoterof.simucrypto.repository.ICommunityDao;
import com.alexoterof.simucrypto.repository.ICommunityJdbcRepository;
import com.alexoterof.simucrypto.repository.IUserDao;
import com.alexoterof.simucrypto.service.interfaces.ICommunityService;
import com.alexoterof.simucrypto.service.interfaces.IUserCommunityService;
import com.googlecode.jmapper.JMapper;

@Service
public class CommunityServiceImpl implements ICommunityService {
	@Autowired
	ICommunityDao comJpaDao;
	
	@Autowired
	IUserDao userDao;
	
	@Autowired
	ICommunityJdbcRepository comJdbcDao;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	IUserCommunityService userCommunityService;
	
	@Override
	public List<CommunityMinDto> findAll() {
		return comJpaDao.findAll()
							.stream()
							.map(this::convertToDto)
							.collect(Collectors.toList());
	}
	
	@Override
	public List<CommunityMinDto> findByUsername(String username) {
		return comJdbcDao.findAllByUsername(username);
	}
	
	@Override
	@Transactional
	public void create(CommunityDto input, String creatorUsername) {
		Community community = new Community();
		User creator = userDao.findByUsername(creatorUsername);
		BeanUtils.copyProperties(input, community);
		community.setCreator(creator);
		comJpaDao.save(community);
		userCommunityService.addUser(community.getId(), creator.getId());
	}
	
	private CommunityMinDto convertToDto(Community entity) {
		JMapper<CommunityMinDto, Community> mapper = new JMapper<>
		(CommunityMinDto.class, Community.class);
		return mapper.getDestination(entity);
	}
}
