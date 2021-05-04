package com.meritamerica.assignment7.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meritamerica.assignment7.services.MeritBankService;

@Entity
public class CDOffering implements Comparable<CDOffering>{
	
	@NotNull
	@PositiveOrZero
	@Column(name = "term")
	private int term;
	
	@NotNull
	@Max(1)
	@PositiveOrZero
	@Column(name = "interest_rate")
	private double interestRate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cdOffering")
	@JsonIgnore
	private List<CDAccount> cdAccount;

	public List<CDAccount> getCdAccount() {
		return cdAccount;
	}

	public void setCdAccount(List<CDAccount> cdAccount) {
		this.cdAccount = cdAccount;
	}

	public CDOffering() {
	}
	
	public CDOffering(int id) {
		this.interestRate = MeritBankService.getCDOfferings().get(id-1).getInterestRate();
		this.term = MeritBankService.getCDOfferings().get(id-1).getTerm();
		this.id = id;
	}
	
	public int getTerm() {
		return term;
	}
	
	public void setTerm(int term) {
		this.term = term;
	}

	public double getInterestRate() {
		return interestRate;
	}
	
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(CDOffering o) {
		if ((Math.pow((1+this.getInterestRate()), this.getTerm())) > (Math.pow((1+o.getInterestRate()), o.getTerm()))) {
			return 1;
		} else if ((Math.pow((1+this.getInterestRate()), this.getTerm())) == (Math.pow((1+o.getInterestRate()), o.getTerm()))) {
			return 0;
		} else {
			return -1;
		}
	}
	
}
