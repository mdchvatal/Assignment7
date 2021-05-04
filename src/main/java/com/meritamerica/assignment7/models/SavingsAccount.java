package com.meritamerica.assignment7.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment7.services.MeritBankService;

@Entity
public class SavingsAccount extends BankAccount {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountHolder_id", referencedColumnName = "id")
	@JsonIgnore
	private AccountHolder accountHolder;
	
	public SavingsAccount() {
		this.interestRate = MeritBankService.getSavingsInterest();
	}
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	
}

