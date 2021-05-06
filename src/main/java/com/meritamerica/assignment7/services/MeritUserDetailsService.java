package com.meritamerica.assignment7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.meritamerica.assignment7.models.MeritBankUser;
import com.meritamerica.assignment7.repositories.MeritBankUserRepository;


@Service
public class MeritUserDetailsService implements UserDetailsService{

	@Autowired
	private MeritBankUserRepository mbUserRepository;
	
	@Override
	public MeritBankUser loadUserByUsername(String userName) throws UsernameNotFoundException {
		if (!mbUserRepository.existsMeritBankUserByUsername(userName)) {
			throw new UsernameNotFoundException("Username " + userName + " not found.");
		} else {
			return mbUserRepository.findByUsername(userName);
		}
	}
	
	public void addMeritBankUser(MeritBankUser mbUser) {
		mbUserRepository.save(mbUser);
	}

}
