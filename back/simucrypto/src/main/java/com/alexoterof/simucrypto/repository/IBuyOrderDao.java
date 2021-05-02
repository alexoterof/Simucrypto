package com.alexoterof.simucrypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.BuyOrder;

@Repository
public interface IBuyOrderDao extends JpaRepository<BuyOrder, Long>{

}
