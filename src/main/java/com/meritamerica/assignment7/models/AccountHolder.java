package com.meritamerica.assignment7.models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class AccountHolder implements Comparable<AccountHolder>{
	private static int nextId = 1;
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Max(250000)
	@Column(name = "combined_balance")
	private double combinedBalance;
	
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "id")
	private AccountHolderContact accHolderCont;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accountHolder")
	private List<CheckingAccount> checkingAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
	private List<SavingsAccount> savingsAccounts;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder")
	private List<CDAccount> cdAccounts;

	@NotBlank(message="First name field must not be blank")
	@NotNull(message="First name field must not be blank")
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	@NotBlank(message="Last name field must not be blank")
	@NotNull(message="Last name field must not be blank")
	private String lastName;
	
	@NotBlank(message="SSN field must not be blank")
	@NotNull(message="SSN field must not be blank")
	@Column(name = "ssn")
	private String ssn;
	
	private String userName;
	
	private String password;
	
	private boolean active;
	
	private String role;
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public boolean isActive() {
		return active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountHolder() {
		this.id = nextId++;
	}
	
	public AccountHolderContact getAccHolderCont() {
		return accHolderCont;
	}

	public void setAccHolderCont(AccountHolderContact accHolderCont) {
		this.accHolderCont = accHolderCont;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getSSN() {
		return ssn;
	}
	
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static int getNextId() {
		return nextId;
	}

	public static void setNextId(int nextId) {
		AccountHolder.nextId = nextId;
	}

	public double getCombinedBalance() {
		return combinedBalance;
	}

	public void setCombinedBalance(double combinedBalance) {
		this.combinedBalance = combinedBalance;
	}

	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void setCheckingAccounts(List<CheckingAccount> checkingAccounts) {
		this.checkingAccounts = checkingAccounts;
	}

	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}

	public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
		this.savingsAccounts = savingsAccounts;
	}

	public List<CDAccount> getCdAccounts() {
		return cdAccounts;
	}

	public void setCdAccounts(List<CDAccount> cdAccounts) {
		this.cdAccounts = cdAccounts;
	}

	
	/*
	public CheckingAccount addCheckingAccount(double openingBalance) {
		CheckingAccount account = new CheckingAccount(openingBalance);
		account.setInterestRate(MeritBankService.getCheckingInterest());
		if (((this.getCheckingBalance() + this.getSavingsBalance()) + openingBalance < 250000)) {
			checkingAccounts.add(account);
			numberOfCheckingAccounts++;
			combinedBalance += openingBalance;
			return account;
		} else {
			return account;
		}
	}
	
	public CheckingAccount addCheckingAccount(BankAccount checkingAccount) {
		checkingAccounts.add((CheckingAccount) checkingAccount);
		numberOfCheckingAccounts++;
		combinedBalance += checkingAccount.getBalance();
		return (CheckingAccount) checkingAccount;
	}
		
	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;
	}
		
	public int getNumberOfCheckingAccounts() {
		return numberOfCheckingAccounts;
	}
		
	public double getCheckingBalance() {
		double tempBalance = 0;
		for (CheckingAccount checkingAccount : checkingAccounts) {
			tempBalance += checkingAccount.getBalance();
		}
		return tempBalance;
	}
		
	public SavingsAccount addSavingsAccount(double openingBalance) {
		SavingsAccount account = new SavingsAccount(openingBalance);
		savingsAccounts.add(account);
		numberOfSavingsAccounts++;
		return account;
	}
	
	public SavingsAccount addSavingsAccount(BankAccount savingsAccount) {
		savingsAccounts.add((SavingsAccount) savingsAccount);
		numberOfSavingsAccounts++;
		return (SavingsAccount) savingsAccount;
	}
	
	public List<SavingsAccount> getSavingsAccounts() {
		return savingsAccounts;
	}
	
	public int getNumberOfSavingsAccounts() {
		return numberOfSavingsAccounts;
	}
		
	public double getSavingsBalance() {
		double tempBalance = 0;
		for (SavingsAccount savingsAccount : savingsAccounts) {
			tempBalance += savingsAccount.getBalance();
		}
		return tempBalance;
	}
		
	public CDAccount addCDAccount(CDOfferingRepository offering, double openingBalance) {
		CDAccount cdAccount = new CDAccount(offering, (int) openingBalance);
		cdAccounts.add(cdAccount);
		numberOfCDAccounts++;
		return cdAccount;
	}
		
	public CDAccount addCDAccount(BankAccount cdAccount) {
		cdAccounts.add((CDAccount) cdAccount);
		numberOfCDAccounts++;
		return (CDAccount) cdAccount;
	}
		
	public List<CDAccount> getCDAccounts() {
		return cdAccounts;
	}
		
	public int getNumberOfCDAccounts() {
		return numberOfCDAccounts;
	}
		
	public double getCDBalance() {
		double tempBalance = 0;
		for (CDAccount cdAccount : cdAccounts) {
			tempBalance += cdAccount.getBalance();
		}
		return tempBalance;
	}
		
	public double getCombinedBalance() {
		combinedBalance = getCheckingBalance() + getSavingsBalance() + getCDBalance();
		return combinedBalance;
	}*/
/*
	@Override
	public int compareTo(AccountHolder accHold) {
		if (this.getCombinedBalance() > accHold.getCombinedBalance()) {
			return 1;
		} else if (this.getCombinedBalance() < accHold.getCombinedBalance()) {
			return -1;
		} else {
		return 0;
		}
	}
	
	static AccountHolder readFromString(String accountHolderData) throws ParseException{
		AccountHolder fromStringAccount = new AccountHolder();
		try {
			String[] accountDataFormatter = accountHolderData.split(",", -1);
				fromStringAccount.lastName = accountDataFormatter[0];
				fromStringAccount.middleName = accountDataFormatter[1];
				fromStringAccount.firstName = accountDataFormatter[2];
				fromStringAccount.ssn = accountDataFormatter[3];	
		} catch (NumberFormatException e) {
			System.out.println("That's not valid data input");
		}
		return fromStringAccount;
	}
*/
	@Override
	public String toString() {
		return lastName + "," + middleName + "," + firstName + "," + ssn;
	}

	@Override
	public int compareTo(AccountHolder o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
