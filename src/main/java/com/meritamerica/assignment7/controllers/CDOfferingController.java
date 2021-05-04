package com.meritamerica.assignment7.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.CDOffering;
import com.meritamerica.assignment7.repositories.CDOfferingRepository;
import com.meritamerica.assignment7.services.MeritBankService;

@RestController
public class CDOfferingController {
	
	@Autowired
	private CDOfferingRepository cdOfferingRepo;
	
	
	@PostMapping("/cd-offerings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering postCDOffering(@RequestBody @Valid CDOffering cdOffering) { 
			cdOfferingRepo.save(cdOffering);
			return cdOffering;
	}
	
	@GetMapping("/cd-offerings")
	@ResponseStatus(HttpStatus.OK)
	public List<CDOffering> getCDOfferings() {
		return cdOfferingRepo.findAll();
	}
	
}
