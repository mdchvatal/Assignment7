package com.meritamerica.assignment7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.models.AccountHolder;


public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
	
	public AccountHolder findByUserName(String userName);

	public boolean existsAccountHolderByUserName(String userName);
}
