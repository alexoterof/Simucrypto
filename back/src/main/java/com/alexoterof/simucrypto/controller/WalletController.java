package com.alexoterof.simucrypto.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.wallet.WalletDto;
import com.alexoterof.simucrypto.service.IWalletService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/wallet")
public class WalletController {
	@Autowired
	IWalletService walletService;
	
	@GetMapping("mywallets")
	public ResponseEntity<List<WalletDto>> findWallets(Principal principal){
		return ResponseEntity.ok(walletService.findAllByUsername(principal.getName()));
	}
}
