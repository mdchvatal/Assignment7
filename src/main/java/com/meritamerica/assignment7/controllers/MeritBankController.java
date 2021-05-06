package com.meritamerica.assignment7.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.models.AccountHolder;
import com.meritamerica.assignment7.models.AuthenticationRequest;
import com.meritamerica.assignment7.models.AuthenticationResponse;
import com.meritamerica.assignment7.models.CDAccount;
import com.meritamerica.assignment7.models.CDAccountCreator;
import com.meritamerica.assignment7.models.CheckingAccount;
import com.meritamerica.assignment7.models.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.models.NegativeAmountException;
import com.meritamerica.assignment7.models.NoSuchResourceFoundException;
import com.meritamerica.assignment7.models.SavingsAccount;
import com.meritamerica.assignment7.models.MeritBankUser;
import com.meritamerica.assignment7.services.AccountHolderServiceImpl;
import com.meritamerica.assignment7.services.MeritBankServiceImpl;
import com.meritamerica.assignment7.services.MeritUserDetailsService;
import com.meritamerica.assignment7.util.JwtUtil;

@CrossOrigin
@RestController
public class MeritBankController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	MeritBankServiceImpl meritBankSvc;
	
	@Autowired
	AccountHolderServiceImpl accountHolderSvc;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	MeritUserDetailsService meritUserDetailService;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String welcomeMessage() {
		return "Welcome to Merit Bank!";
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		final MeritBankUser userDetails = (MeritBankUser) meritUserDetailService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);
		 
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping("/authenticate/create-user")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public MeritBankUser postNewMeritBankUser(@RequestBody @Valid MeritBankUser mbUser) {
		meritUserDetailService.addMeritBankUser(mbUser);
		return mbUser;
	}
	
	@PostMapping("/account-holders")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public AccountHolder postAccountHolder(@RequestBody @Valid AccountHolder accHolder ) {
		MeritBankUser user = meritUserDetailService.loadUserByUsername(accHolder.getMbUser().getUsername());
		accHolder.setMbUser(user);
		meritBankSvc.addAccountHolder(accHolder);
		return accHolder;
	}
	
	@GetMapping(value = "/account-holders")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<AccountHolder> getAccountHolders() {
		return meritBankSvc.getAccountHolders();
	}
	
	@GetMapping("/account-holders/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public AccountHolder getAccountHolderById(@PathVariable int id) throws NoSuchResourceFoundException{
		return meritBankSvc.getAccountHolderById(id);
	}
	
	@PostMapping("/account-holders/{id}/checking-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public CheckingAccount postCheckingAccount(
			@PathVariable int id, @RequestBody @Valid CheckingAccount checkingAccount) 
					throws NoSuchResourceFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		checkingAccount.setAccountHolder(accHolder);
		System.out.println(checkingAccount.getAccountHolder());
		accountHolderSvc.addCheckingAccount(checkingAccount, accHolder);
		return checkingAccount;
	}

	@GetMapping("/account-holders/{id}/checking-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<CheckingAccount> getCheckingAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException {
		return accountHolderSvc.getCheckingAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}
	
	@PostMapping("/account-holders/{id}/savings-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public SavingsAccount postSavingsAccount(
			@PathVariable int id, @RequestBody @Valid SavingsAccount savingsAccount ) 
					throws NoSuchResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		AccountHolder accHolder = meritBankSvc.getAccountHolderById(id);
		savingsAccount.setAccountHolder(accHolder);
		accountHolderSvc.addSavingsAccount(savingsAccount, accHolder);
		return savingsAccount;
	}
	
	@GetMapping("/account-holders/{id}/savings-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<SavingsAccount> getSavingsAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		return accountHolderSvc.getSavingsAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}
	
	@PostMapping("/account-holders/{id}/cd-accounts")
	@ResponseStatus(HttpStatus.CREATED)
	@Secured("ROLE_ADMIN")
	public CDAccount postCDAccount(
			@PathVariable int id, @RequestBody @Valid CDAccountCreator cdAccountCreator)
					throws NoSuchResourceFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
		CDAccount newAccount = new CDAccount(cdAccountCreator.getCdOffering(),
				cdAccountCreator.getBalance());
		accountHolderSvc.addCDAccount(newAccount, getAccountHolderById(id));
		return newAccount;
	}
	
	@GetMapping("/account-holders/{id}/cd-accounts")
	@ResponseStatus(HttpStatus.OK)
	@Secured("ROLE_ADMIN")
	public List<CDAccount> getCDAccountsById(
			@PathVariable int id) throws NoSuchResourceFoundException{
		return accountHolderSvc.getCDAccountsByAccountHolder(
				meritBankSvc.getAccountHolderById(id));
	}

}
