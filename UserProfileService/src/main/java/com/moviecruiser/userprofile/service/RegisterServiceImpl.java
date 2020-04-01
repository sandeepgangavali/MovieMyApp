package com.moviecruiser.userprofile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviecruiser.userprofile.model.Register;
import com.moviecruiser.userprofile.repository.RegisteruserRepo;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	RegisteruserRepo registeruserRepo;
	
	/*
	 * @Autowired UserLoginProxy userLoginProxy;
	 */

	@Override
	public Register registerUser(Register register) {
		Register user=registeruserRepo.save(register);
		//userLoginProxy.postMovie(register);
		return user;
	}

}
