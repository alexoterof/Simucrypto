package com.alexoterof.simucrypto.controller;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.buyorder.BuyOrderPlacementDto;
import com.alexoterof.simucrypto.service.interfaces.IBuyOrderService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	IBuyOrderService orderService;
	
	@PostMapping("refill/{ammount}")
	public ResponseEntity<Void> refill(Principal principal, @PathVariable Double ammount){
		orderService.refill(principal.getName(), ammount);;
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("place")
	public ResponseEntity<Void> placeOrder(Principal principal, @RequestBody BuyOrderPlacementDto input){
		input.setUsername(principal.getName());
		orderService.place(input);
		return ResponseEntity.ok(null);
	}
}
