package com.capgemini.quote.dao;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import com.capgemini.quote.bean.UserRole;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.util.JPAUtil;

//Class contains the functionalities of Dao User
public class UserDaoImpl implements UserDao {
	
	Logger myLogger =  Logger.getLogger(UserDaoImpl.class.getName());
	
	private EntityManager em;

	public UserDaoImpl() {
		em = JPAUtil.getEntityManager();
	}	

	//Login Validation
	@Override
	public UserRole loginCheck(String userName, String password) throws CustomException  {
		UserRole userRole = em.find(UserRole.class, userName);
		myLogger.info("Login Checked ");
		return userRole;
	}

	//Create New Profile
	@Override
	public String createProfile(UserRole userRole) throws CustomException 
	{
		if (userRole.getRoleCode().equals("admin") || userRole.getRoleCode().equals("agent")
				|| userRole.getRoleCode().equals("insurer")) 
		{
			em.getTransaction().begin();
			em.persist(userRole);
			em.getTransaction().commit();
			myLogger.info("New Profile Created");
		return userRole.getUserName()+"'s Profile Created.";
		}
		else {
			myLogger.warn("Profile Not created");
			return "RoleCode not valid.";
		}
	}

	//Find Profile exist in Database
	@Override
	public boolean findProfile(UserRole userRole) {
		UserRole answer = em.find(UserRole.class, userRole.getUserName());
		if(answer != null) {
			myLogger.info("Profile found by userrole");
			return true;
		}
		else {
			myLogger.warn("Profile not found by userrole");
			return false;
		}
	}	

}
