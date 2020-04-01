package com.moviecruiser.AuthenticationService.models;


import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
@Component

public class Register{

	@Id
	private String id;

	private String username;

	private String password;

	private String rptPassword;

	private String email;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRptPassword() {
		return rptPassword;
	}

	public void setRptPassword(String rptpassword) {
		this.rptPassword = rptpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Register(String id, String username, String password, String rptpassword, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.rptPassword = rptpassword;
		this.email = email;
	}

	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Register [id=" + id + ", username=" + username + ", password=" + password + ", rptPassword="
				+ rptPassword + ", email=" + email + "]";
	}
	

	
	
}
