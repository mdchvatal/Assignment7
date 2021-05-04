package com.meritamerica.assignment7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.BankAccount;
import com.meritamerica.assignment7.models.SavingsAccount;

@Repository
public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer>{

	List<SavingsAccount> findBankAccountByAccountHolder(AccountHolder accountHolder);
	
}
