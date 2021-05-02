package com.alexoterof.simucrypto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.Coin;

@Repository
public interface ICoinDao extends JpaRepository<Coin, Long>{
	public List<Coin> findByName(String name);
}
