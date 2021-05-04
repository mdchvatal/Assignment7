package com.meritamerica.assignment7.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CDAccount extends BankAccount {
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountHolder_id")
	@JsonIgnore
	private AccountHolder accountHolder;
	
	@JsonIgnore
	protected double futureBalance;
	
	@Column(name = "term")
	private int term;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cdOffering_id")
	private CDOffering cdOffering;
	
	public CDAccount() {
	}

	public CDAccount(CDOffering cdOffering, int balance) {
		this.cdOffering = cdOffering;
		this.balance = balance;
	}

	public int getTerm() {
		return term;
	}
	
	public void setTerm(int i) {
		this.term = i;
	}
	
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	@Override
	public boolean withdraw(double amount) {
			return false;
	}
	
	@Override
	public boolean deposit(double amount) {
			return false;
	}
	/*
	public double futureValue() {
	    if (tempTerm == 0) {
	    	double tempBalance;
	    	tempBalance = futureBalance;
	    	resetFutureBalance();
	        return tempBalance;
	    } else {
	        futureBalance = futureBalance * (1 + getInterestRate());
	        --tempTerm;
	        return futureValue();
	    }
	  }*/
	
	public double getFutureBalance() {
		this.futureBalance = this.balance;
		return futureBalance;
	}
	
	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}

	public void resetFutureBalance() {
		this.futureBalance = getBalance();
	}
	
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate() {
		this.interestRate = cdOffering.getInterestRate();
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public void setTerm() {
		this.term = cdOffering.getTerm();
	}
	
	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = dateFormatter.format(getOpenedOn());
		return super.getId() + "," + getBalance() + "," + getInterestRate()
					+ "," + dateString + "," + term;
	}
	
}