package com.muscle.web.dao;

public class LoginDAO {

	public static boolean validate(String user, String password) {
		//TODO dummy method 
		return (user.equals("admin") && password.equals("secret"));
		
	}
}
