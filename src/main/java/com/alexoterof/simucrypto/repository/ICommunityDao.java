package com.alexoterof.simucrypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.Community;

@Repository
public interface ICommunityDao extends JpaRepository<Community, Long>{

}
