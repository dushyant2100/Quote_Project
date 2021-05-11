package com.capgemini.quote.service;

import java.util.List;

import com.capgemini.quote.bean.Accounts;
import com.capgemini.quote.customexp.CustomException;

public interface AccountsService {
	
	public Accounts accountCreation(Accounts account) throws CustomException;

	public Accounts getAccountsByUsername(String userName) throws CustomException;
	
	public Accounts getAccountsByUsername(String userName, String userRole);

	public List<Accounts> getAccountListByCreatedBy(String createdBy);

	public List<Accounts> getAllAccounts();

	public Accounts getAccountbyAccNo(long accountNumber) throws CustomException;

}
