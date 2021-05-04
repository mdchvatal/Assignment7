package com.meritamerica.assignment7.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeritBankController {
	
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String welcomeMessage() {
		return "Welcome to Merit Bank!";
	}
	
}
