package com.alexoterof.simucrypto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.MessageDto;
import com.alexoterof.simucrypto.service.interfaces.ICommunityService;
import com.alexoterof.simucrypto.service.interfaces.IMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class ChatController {
	@Autowired
	private IMessageService messageService;

    @Autowired
    private SimpMessagingTemplate template;
  
    @MessageMapping("/send/message")
    public void sendMessage(String message) throws Exception{
    	System.out.println("Received");
    	System.out.println(message);
    	ObjectMapper mapper = new ObjectMapper();
    	MessageDto dto = mapper.readValue(message, MessageDto.class);
    	messageService.postMessage(dto);
    	//MessageDto dto = new MessageDto();
	    this.template.convertAndSend("/message",  dto.getText());
      //socketService.saveSession(sessionId, message.getName());
    }

}