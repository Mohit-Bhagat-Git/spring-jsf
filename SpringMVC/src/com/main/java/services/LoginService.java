package com.main.java.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class LoginService {
	
	public String authenticate() {
		System.out.println("login service called");
		return "home";
	}

}
