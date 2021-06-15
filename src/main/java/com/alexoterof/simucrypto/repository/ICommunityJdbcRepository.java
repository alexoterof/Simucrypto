package com.alexoterof.simucrypto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.dto.community.CommunityMinDto;

@Repository
public class ICommunityJdbcRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<CommunityMinDto> findAllByUsername(String username) {		
		NamedParameterJdbcTemplate nameJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
		StringBuilder qry = new StringBuilder();
		qry.append(" SELECT c.id , c.name"
				 + " FROM Community c "
				 + " LEFT JOIN user_community ON user_community.cod_community = c.id "
				 + " LEFT JOIN user ON user.id = user_community.cod_user "
				 + " WHERE user.username = :username");		
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("username", username);		
		return nameJdbc.query(qry.toString(),
							  param,
							  new BeanPropertyRowMapper<CommunityMinDto>(CommunityMinDto.class));
	}
}
