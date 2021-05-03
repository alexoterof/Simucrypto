package com.alexoterof.simucrypto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.Wallet;

@Repository
public interface IWalletDao extends JpaRepository<Wallet, Long>{
	
	@Query(value = "SELECT *"
					+ "FROM Wallet as w "
					+ "LEFT JOIN user AS u ON w.cod_user = u.id "
					+ "LEFT JOIN coin AS c ON w.cod_coin = c.id "
					+ "WHERE u.username = :username "
					+ "  AND c.name = :coinname ;",
					nativeQuery = true)
	public Optional<Wallet> findByUsernameAndCoinname(@Param("username")String username, @Param("coinname")String coinname);

	@Query(value = "SELECT *"
					+ "FROM Wallet as w "
					+ "LEFT JOIN user AS u ON w.cod_user = u.id "
					+ "LEFT JOIN coin AS c ON w.cod_coin = c.id "
					+ "WHERE u.username = :username ;",
					nativeQuery = true)
	public List<Wallet> findByUsername(@Param("username") String username);
}
