package com.alexoterof.simucrypto.controller;

import java.security.Principal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alexoterof.simucrypto.dto.JwtDto;
import com.alexoterof.simucrypto.dto.user.UserDetailDto;
import com.alexoterof.simucrypto.dto.user.UserMinDto;
import com.alexoterof.simucrypto.service.interfaces.IUserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserService userService;
	
	@PostMapping("register")
	@Transactional
	public ResponseEntity<Void> register(@RequestBody UserMinDto input){
		userService.register(input);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("login")
	public ResponseEntity<JwtDto> login(@RequestBody UserMinDto input){
		return ResponseEntity.ok(userService.login(input));
	}
	
	@GetMapping("delete")
	public ResponseEntity<Void> delete(Principal principal){
		userService.delete(principal.getName());
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("detail")
	public ResponseEntity<UserDetailDto> getDetail(Principal principal){
		System.out.println(principal.getName());
		return ResponseEntity.ok(userService.getDetailOf(principal.getName()));
	}	
}
