package com.meritamerica.assignment7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.MeritBankUser;

public interface MeritBankUserRepository extends JpaRepository<MeritBankUser, Integer>{

	public MeritBankUser findByUsername(String userName);

	public boolean existsMeritBankUserByUsername(String userName);
	
}
