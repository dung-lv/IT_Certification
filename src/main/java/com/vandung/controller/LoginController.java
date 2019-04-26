package com.vandung.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vandung.entity.Login;
import com.vandung.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<Object> loginAdmin(@RequestParam String username, @RequestParam String password) {
		String passwordSC = md5(password);
		Boolean ok = loginService.login(username, passwordSC);
		if(ok) {
			HomeController.statusLogin = true;
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(new Login(), HttpStatus.BAD_REQUEST);
		}
	}
	
	public String md5(String str){
		String result = "";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(str.getBytes());
			BigInteger bigInteger = new BigInteger(1,digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
