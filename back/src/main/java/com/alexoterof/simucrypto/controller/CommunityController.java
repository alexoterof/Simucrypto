package com.alexoterof.simucrypto.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.community.CommunityDto;
import com.alexoterof.simucrypto.dto.community.CommunityMinDto;
import com.alexoterof.simucrypto.service.interfaces.ICommunityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/community")
public class CommunityController {
	@Autowired
	ICommunityService communityService;
	
	@GetMapping("all")
	public ResponseEntity<List<CommunityMinDto>> findAll(){
		return ResponseEntity.ok(communityService.findAll());
	}
	
	@GetMapping("mine")
	public ResponseEntity<List<CommunityMinDto>> findByUser(Principal principal){
		return ResponseEntity.ok(communityService.findByUsername(principal.getName()));
	}
	
	@PostMapping("create")
	public ResponseEntity<Void> create(Principal principal, @RequestBody CommunityDto input){
		communityService.create(input, principal.getName());
		return ResponseEntity.ok(null);
	}
}
