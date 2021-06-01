package com.alexoterof.simucrypto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	public void deleteByUsername(String username);

}
