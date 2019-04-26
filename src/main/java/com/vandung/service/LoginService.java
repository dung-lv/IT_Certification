package com.vandung.service;

import com.vandung.entity.Login;

public interface LoginService {

	public void add(Login db);
	public Boolean login(String username, String password);
}
