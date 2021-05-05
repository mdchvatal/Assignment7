package com.meritamerica.assignment7.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import com.meritamerica.assignment7.models.CDOffering;
import com.meritamerica.assignment7.repositories.AccountHolderRepository;

public abstract class MeritBankService {
	
	private static int nextAccountNumber = 1;
	private static final double CHECKING_INTEREST = 0.0001;
	private static final double SAVINGS_INTEREST = 0.01;
	private static int ahArrayCounter = 0;
	private static List<CDOffering> cdOfferings = new ArrayList<CDOffering>();
	private static double totalBalances;

	//private static FraudQueue fraudQueue = new FraudQueue();
	//private static ArrayList<Transaction> transQueue = new ArrayList<Transaction>();
	
	public static double getCheckingInterest() {
		return CHECKING_INTEREST;
	}
	
	public static double getSavingsInterest() {
		return SAVINGS_INTEREST;
	}

	public static List<CDOffering> getCDOfferings() {
			return cdOfferings;
	}
	
	public static CDOffering getBestCDOffering(double depositAmount) {
		Collections.sort(cdOfferings);
		return cdOfferings.get(0);
	}
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		Collections.sort(cdOfferings);
		return cdOfferings.get(1);
	}
	
	public static void clearCDOfferings() {
		cdOfferings = null;
	}
	
	public static void setCDOfferings(CDOffering[] offerings) {
		for (int i = 0; i < offerings.length; i++) {
			cdOfferings.add(offerings[i]);
		}	 	
	}
	
	public static void addCDOffering(CDOffering cdOffering) {
			cdOfferings.add(cdOffering);	 	
	}
	
	/*
	public static double getTotalBalances(AccountHolder[] holders) {
		MeritBankService.totalBalances = 0;
		for (AccountHolder accountHolder : accountHolders) {
			MeritBankService.totalBalances += accountHolder.getCombinedBalance();
		}
		return MeritBankService.totalBalances;
	}*/
	
	public static double futureValue(double presentValue, double interestRate, int term) {
		double total = (presentValue * Math.pow((1+interestRate), term));
		return total;
	}
	
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		return power(amount, (1+(years * interestRate)));
	}
	
	public static double power(double base, double powerRaised) {
        if (powerRaised != 0)
            return (base * power(base, powerRaised - 1));
        else
            return 1;
	}
	
	public static int getNextAccountNumber() {
		return nextAccountNumber++;
	}

	public static void setNextAccountNumber(int nextAccountNumber) {
		MeritBankService.nextAccountNumber = nextAccountNumber;
	}
}