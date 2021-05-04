package com.meritamerica.assignment7.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.AccountHolderContact;

public interface ContactRepo extends JpaRepository<AccountHolderContact, Integer>{

	
}
