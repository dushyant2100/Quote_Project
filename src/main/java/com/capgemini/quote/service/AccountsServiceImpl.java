package com.capgemini.quote.service;

import java.util.List;
import java.util.regex.Pattern;

import com.capgemini.quote.bean.Accounts;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.dao.AccountsDao;
import com.capgemini.quote.dao.AccountsDaoImpl;

//Class contains the functionalities of Service Accounts
public class AccountsServiceImpl implements AccountsService {

	AccountsDao aDao;

	public AccountsServiceImpl() {
		aDao = new AccountsDaoImpl();
	}

	// Validate and Create Account
	@Override
	public Accounts accountCreation(Accounts ac) throws CustomException {
		if ((Pattern.matches("^[A-Z][a-zA-Z]{1,15}$", ac.getInsuredName())
				&& Pattern.matches("^[a-zA-Z]{3,20}$", ac.getInsuredStreet())
				&& Pattern.matches("^[a-zA-Z]{2,20}$", ac.getInsuredCity())
				&& Pattern.matches("^[a-zA-Z]{2,20}$", ac.getInsuredState())
				&& Pattern.matches("^[1-9][0-9]{4}$", String.valueOf(ac.getInsuredZip()))
				&& Pattern.matches("^[(B)(R)(A)(G)]{1}$", ac.getBusinessSegment()))) 
		{
		Accounts accfind = aDao.findAccount(ac); //Find Account exist or not

		if(accfind==null) {
			Accounts acc = aDao.accountCreation(ac);  //Create Account
			if(acc!=null)
				return acc;
			else
				throw new CustomException("Profile does not exist by given username.");			
		}
		else 
			throw new CustomException("Values not inserted as instructed.");
		}else 
			throw new CustomException("Values not inserted as instructed.");
		
	}

	//Get Account by username from Dao Layer
	@Override
	public Accounts getAccountsByUsername(String userName) throws CustomException{
		if(Pattern.matches("^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$", userName)) 
		{
			Accounts acc = aDao.getAccountsByUsername(userName);
			if(acc != null)
				return acc;
			else 
				throw new CustomException("Account does not exist by given username.");
	}else
		throw new CustomException("Values not inserted as instructed.");
	}
	
	//GetGet Account by username from Dao Layer
	@Override
	public Accounts getAccountsByUsername(String userName, String userRole) {
		Accounts account = aDao.getAccountsByUsername(userName,userRole);
		if(account != null)
			return account;
		else 
			return null;
	}

	//Get Account List by Created By from Dao Layer
	@Override
	public List<Accounts> getAccountListByCreatedBy(String createdBy) {
		
		List<Accounts> accountList = aDao.getAccountListByCreatedBy(createdBy);
		return accountList;
		
	}

	//GEt All Account List from Dao Layer
	@Override
	public List<Accounts> getAllAccounts() {
		List<Accounts> allAccountList = aDao.getAllAccounts();
		return allAccountList;
	}

	//Get Account by Account Number
	@Override
	public Accounts getAccountbyAccNo(long accountNumber) throws CustomException{
		
		Accounts accByNo = aDao.getAccountbyAccNo(accountNumber);
		if(accByNo!=null)
			return accByNo;
		else
			throw new CustomException("Account number does not exist for given input.");
	}

	

}
