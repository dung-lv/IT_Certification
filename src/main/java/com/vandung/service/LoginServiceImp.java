package com.vandung.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vandung.entity.Login;
import com.vandung.repository.LoginRepository;

@Service
public class LoginServiceImp implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public void add(Login db) {
		loginRepository.add(db);	
	}

	@Override
	public Boolean login(String username, String password) {
		return loginRepository.login(username, password);
	}
	
	

}
