package com.capgemini.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.capgemini.quote.bean.Accounts;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.util.JPAUtil;

//Class contains the functionalities of Dao Accounts
public class AccountsDaoImpl implements AccountsDao {
	
	Logger myLogger =  Logger.getLogger(AccountsDaoImpl.class.getName());

	private EntityManager em;

	public AccountsDaoImpl() {
		em = JPAUtil.getEntityManager();
	}
	
    //Create new Account
	@Override
	public Accounts accountCreation(Accounts account) throws CustomException {
		
			em.getTransaction().begin();
			em.persist(account);
			em.getTransaction().commit();
			System.out.println("Account generated");
			myLogger.info("Account Created");
			return account;
		
	}
	
	//Get Account details by UserName
	@Override
	public Accounts getAccountsByUsername(String userName, String userRole) {
		try {
		String qstr = "select accounts from Accounts accounts where accounts.userName=:pUserName";
		TypedQuery<Accounts> query = em.createQuery(qstr, Accounts.class);
		query.setParameter("pUserName", userName);
		Accounts account = query.getSingleResult();
		myLogger.info("Account Information fetched by Username");
		return account;}
		catch(Exception e) {
			return null;
		}
	}

	//Get Account by UserName
	@Override
	public Accounts getAccountsByUsername(String userName) throws CustomException {
		try {
		String qstr = "select accounts from Accounts accounts where accounts.userName=:pUsername";
		TypedQuery<Accounts> query = em.createQuery(qstr, Accounts.class);
		query.setParameter("pUsername", userName);
		Accounts account = query.getSingleResult();
		myLogger.info("Account Information fetched by Username");
		return account;}
		catch(Exception e) {
			return null;
		}
	}
	
    //Get List of Account By created By
	@Override
	public List<Accounts> getAccountListByCreatedBy(String createdBy) {
		String qstr = "select accounts from Accounts accounts where accounts.createdBy=:pCreatedBy";
		TypedQuery<Accounts> query = em.createQuery(qstr, Accounts.class);
		query.setParameter("pCreatedBy", createdBy);
		List<Accounts> accountList = query.getResultList();
		myLogger.info("Account List fetched by CreatedBy");
		return accountList;
	}

	//Get List of All Accounts
	@Override
	public List<Accounts> getAllAccounts() {
		Query query = em.createNamedQuery("getAllAccounts");
		@SuppressWarnings("unchecked")
		List<Accounts> accountList = query.getResultList();
		myLogger.info("All Account Information fetched");
		return accountList;
	}

	//Get Account by Account Number
	@Override
	public Accounts getAccountbyAccNo(long accountNumber) throws CustomException {
		Accounts account = em.find(Accounts.class, accountNumber);
		myLogger.info("Account Information fetched by Account Number");
		return account;
	}

	//Find Account exist or not in database
	@Override
	public Accounts findAccount(Accounts acc) {
		try {
		String qstr = "select accounts from Accounts accounts where accounts.userName=:pUsername";
        TypedQuery<Accounts> query = em.createQuery(qstr, Accounts.class);
        query.setParameter("pUsername", acc.getUsername());
        Accounts account = query.getSingleResult();
        myLogger.info("Account Checked exist");
        return account;}
		catch(Exception e)
		{
			myLogger.warn("Account Checked Not found");
			return null;
		}  
	}
}
