package com.meritamerica.assignment7.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.AccountHolderContact;
import com.meritamerica.assignment7.models.AuthenticationRequest;
import com.meritamerica.assignment7.repositories.*;

@RestController
public class AccountHolderContactsController {
	
	@Autowired
	private ContactRepo contactRepo;

	
	@GetMapping(value = "/account-holder-contacts/all")
	@ResponseStatus(HttpStatus.OK)
	public List<AccountHolderContact> getContacts() {
		return contactRepo.findAll();
	}
	
	@PostMapping(value = "/account-holder-contacts/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolderContact postAccountHolderContact(@PathVariable Integer id, 
			@RequestBody @Valid AccountHolderContact accHolderContact) {
		contactRepo.save(accHolderContact);
		return accHolderContact;
	}
	
}
