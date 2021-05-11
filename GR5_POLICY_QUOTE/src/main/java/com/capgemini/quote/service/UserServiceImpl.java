package com.capgemini.quote.service;

import java.util.regex.Pattern;

import com.capgemini.quote.bean.UserRole;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.dao.UserDao;
import com.capgemini.quote.dao.UserDaoImpl;

//Class contains the functionalities of Service User
public class UserServiceImpl implements UserService {

	UserDao uDao;

	public UserServiceImpl() {
		uDao = new UserDaoImpl();
	}

	//Login Validation and check
	@Override
	public UserRole loginCheck(String userName, String password) throws CustomException {
		UserRole userRole;
		userRole = uDao.loginCheck(userName, password); // ## Exception Handling
		if (userRole == null)
			throw new CustomException("User Does not Exist");

		else if (userRole.getUserName().equals(userName) && userRole.getPassword().equals(password))
				return userRole;
		else 
			throw new CustomException("Invalid Inputs");

	}

	//Check Validation and Create New Profile
	@Override
	public String createProfile(UserRole userRole) throws CustomException{
		if (Pattern.matches("^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", userRole.getUserName())
				&& (Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,30}$",
						userRole.getPassword()))) {
			
		boolean answer = uDao.findProfile(userRole);
		if (answer==false)
		{
		String userProfile = uDao.createProfile(userRole);
		return userProfile;
		}
		else 
			throw new CustomException("User already exist.");
	}else 
		throw new CustomException("Input not inserted as instructed.");
	}

}
