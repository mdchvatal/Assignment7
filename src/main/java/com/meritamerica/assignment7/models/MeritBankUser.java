package com.meritamerica.assignment7.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MeritBankUser implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@NotNull
	private String username;
	
	@NotBlank
	@NotNull
	private String password;
	
	@NotNull
	@NotBlank
	private String role;
	
	@Transient
	private List<GrantedAuthority> authorities;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "mbUser")
	private AccountHolder accHolder;
	
	
	public MeritBankUser() {
	}
	
	public MeritBankUser(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AccountHolder getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(AccountHolder accHolder) {
		this.accHolder = accHolder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean isActive() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}
	
	public void setAuthorities() {
		this.authorities.add(new SimpleGrantedAuthority(this.getRole()));
	}

	public void addAuthority(String role) {
		authorities.add(new SimpleGrantedAuthority(role));
	}
}
