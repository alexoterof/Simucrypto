package com.alexoterof.simucrypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.UserCommunity;

@Repository
public interface IUserCommunityDao extends JpaRepository<UserCommunity, Long>{

}
