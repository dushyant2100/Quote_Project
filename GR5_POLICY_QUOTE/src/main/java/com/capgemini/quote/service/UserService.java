package com.capgemini.quote.service;

import com.capgemini.quote.bean.UserRole;
import com.capgemini.quote.customexp.CustomException;

public interface UserService {
	
	public UserRole loginCheck(String userName, String password) throws CustomException;
	
	public String createProfile(UserRole userRole) throws CustomException;

}
