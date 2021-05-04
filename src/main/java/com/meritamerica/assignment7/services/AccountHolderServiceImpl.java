package com.meritamerica.assignment7.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.CheckingAccount;
import com.meritamerica.assignment7.models.NoSuchResourceFoundException;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.repositories.CDAccountRepository;
import com.meritamerica.assignment7.repositories.CheckingAccountRepository;
import com.meritamerica.assignment7.repositories.SavingsAccountRepository;

@Service
public class AccountHolderServiceImpl {
	
	@Autowired
	private CheckingAccountRepository checkAccRepo;
	
	@Autowired
	private SavingsAccountRepository savingAccRepo;
	
	@Autowired
	private CDAccountRepository cdAccRepo;
	
	public List<CheckingAccount> getCheckingAccounts() {
		 return checkAccRepo.findAll();
	}
	
	public void addCheckingAccount(CheckingAccount checkAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + checkAccount.getBalance());
		checkAccount.setAccountHolder(accHolder);
		checkAccRepo.save(checkAccount);
	}
	
	public CheckingAccount getCheckingAccountById(int Id) throws NoSuchResourceFoundException {
		return (CheckingAccount) checkAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public List<CheckingAccount> getCheckingAccountsByAccountHolder(AccountHolder accountHolder) {
		 return checkAccRepo.findBankAccountByAccountHolder(accountHolder);
	}
	
	public List<SavingsAccount> getSavingsAccounts() {
		 return savingAccRepo.findAll();
	}
	
	public List<SavingsAccount> getSavingsAccountsByAccountHolder(AccountHolder accountHolder) {
		 return savingAccRepo.findBankAccountByAccountHolder(accountHolder);
	}
	
	public void addSavingsAccount(SavingsAccount savingsAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + savingsAccount.getBalance());
		savingsAccount.setAccountHolder(accHolder);
		savingAccRepo.save(savingsAccount);
	}
	
	public SavingsAccount getSavingsAccountById(int Id) throws NoSuchResourceFoundException {
		return (SavingsAccount) savingAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public List<CDAccount> getCDAccounts() {
		 return cdAccRepo.findAll();
	}
	
	public void addCDAccount(CDAccount cdAccount, AccountHolder accHolder) {
		accHolder.setCombinedBalance(accHolder.getCombinedBalance() + cdAccount.getBalance());
		cdAccount.setAccountHolder(accHolder);
		cdAccRepo.save(cdAccount);
	}
	
	public CDAccount getCDAccountById(int Id) throws NoSuchResourceFoundException {
		return (CDAccount) cdAccRepo.findById(Id).orElseThrow(() -> new NoSuchResourceFoundException("Account Not Found"));
	}
	
	public List<CDAccount> getCDAccountsByAccountHolder(AccountHolder accountHolder) {
		 return cdAccRepo.findBankAccountByAccountHolder(accountHolder);
	}

	public CheckingAccountRepository getCheckAccRepo() {
		return checkAccRepo;
	}

	public void setCheckAccRepo(CheckingAccountRepository checkAccRepo) {
		this.checkAccRepo = checkAccRepo;
	}

	public SavingsAccountRepository getSavingAccRepo() {
		return savingAccRepo;
	}

	public void setSavingAccRepo(SavingsAccountRepository savingAccRepo) {
		this.savingAccRepo = savingAccRepo;
	}

	public CDAccountRepository getCdAccRepo() {
		return cdAccRepo;
	}

	public void setCdAccRepo(CDAccountRepository cdAccRepo) {
		this.cdAccRepo = cdAccRepo;
	}
}
