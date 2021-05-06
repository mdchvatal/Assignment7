package com.meritamerica.assignment7.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CheckingAccount;
import com.meritamerica.assignment7.models.MeritBankUser;
import com.meritamerica.assignment7.services.AccountHolderServiceImpl;
import com.meritamerica.assignment7.services.MeritBankServiceImpl;

@RestController
public class MeEndpointController {
	
	@Autowired
	AccountHolderServiceImpl accountHolderSvc;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	private MeritBankUser user;
	
	private AccountHolder accHolder;
	
	@GetMapping("/me")
	public MeritBankUser getAccountHolderByUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MeritBankUser) auth.getPrincipal();
		return user;
	}
	
	@PostMapping("/me/checking-accounts")
	public CheckingAccount postCheckingAccount(@RequestBody @Valid CheckingAccount checkAccount) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = (MeritBankUser) auth.getPrincipal();
		user = (MeritBankUser) meritBankSvc.loadUserByUsername(user.getUsername());
		accHolder = meritBankSvc.getAccHolder();
		
	}
}
