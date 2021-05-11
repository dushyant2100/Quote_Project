package com.capgemini.quote.dao;

import com.capgemini.quote.bean.UserRole;
import com.capgemini.quote.customexp.CustomException;

public interface UserDao {

	public UserRole loginCheck(String userName, String password) throws CustomException;

	public String createProfile(UserRole userRole) throws CustomException;
	
	public boolean findProfile(UserRole userRole);

}
