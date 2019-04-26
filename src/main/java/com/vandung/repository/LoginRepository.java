package com.vandung.repository;

import com.vandung.entity.Login;

public interface LoginRepository {

	public void add(Login db);
	public Boolean login(String username, String password);
}
