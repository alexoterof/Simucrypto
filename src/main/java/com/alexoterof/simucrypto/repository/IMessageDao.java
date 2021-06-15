package com.alexoterof.simucrypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexoterof.simucrypto.model.Message;

@Repository
public interface IMessageDao extends JpaRepository<Message, Long>{

}
