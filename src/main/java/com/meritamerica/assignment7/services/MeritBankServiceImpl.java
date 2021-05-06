package com.meritamerica.assignment7.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.CDOffering;
import com.meritamerica.assignment7.models.NoSuchResourceFoundException;
import com.meritamerica.assignment7.repositories.AccountHolderRepository;
import com.meritamerica.assignment7.repositories.CDOfferingRepository;



@Service
public class MeritBankServiceImpl extends MeritBankService {
	
	@Autowired
	private AccountHolderRepository accHolderRepo;
	
	@Autowired
	private CDOfferingRepository cdOfferingRepo;
	
	public AccountHolderRepository getAccHolderRepo() {
		return accHolderRepo;
	}

	public void setAccHolderRepo(AccountHolderRepository accHolderRepo) {
		this.accHolderRepo = accHolderRepo;
	}
	
	public List<AccountHolder> getAccountHolders() {
		return accHolderRepo.findAll();
	}
	
	public AccountHolder getAccountHolderById(int id) throws NoSuchResourceFoundException {
		return accHolderRepo.findById(id).orElseThrow(() -> new NoSuchResourceFoundException("Account Holder not found by given id"));
	}
	
	public void addAccountHolder(AccountHolder accountHolder) {
		accHolderRepo.save(accountHolder);
	}

	public CDOfferingRepository getCdOfferingRepo() {
		return cdOfferingRepo;
	}

	public void setCdOfferingRepo(CDOfferingRepository cdOfferingRepo) {
		this.cdOfferingRepo = cdOfferingRepo;
	}
	
	public List<CDOffering> getAllCDOfferings() {
		return cdOfferingRepo.findAll();
	}
	
	public CDOffering getBestCDOffering() {
		ArrayList<CDOffering> cdoList = (ArrayList<CDOffering>) cdOfferingRepo.findAll();
		Collections.sort(cdoList);
		return cdoList.get(0);
	}

}
