package com.alexoterof.simucrypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.MessageDto;
import com.alexoterof.simucrypto.service.SocketService;


@RestController
public class ChatController {
  @Autowired
  private SocketService socketService;

  @Autowired
  private SimpMessagingTemplate template;
  
  @MessageMapping("/send/message")
  public void sendMessage(String message) throws Exception{
	  this.template.convertAndSend("/message",  message);
    //socketService.saveSession(sessionId, message.getName());
  }

}