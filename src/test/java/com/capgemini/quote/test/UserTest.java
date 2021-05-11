package com.capgemini.quote.test;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.capgemini.quote.bean.UserRole;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.dao.UserDao;
import com.capgemini.quote.dao.UserDaoImpl;
import com.capgemini.quote.util.JPAUtil;


public class UserTest {

	@Test
	public void testLoginCheck() throws CustomException {
		UserDao udao = new UserDaoImpl();
		UserRole u = new UserRole();
		u = udao.loginCheck("Himanshu", "Himanshu123");
		EntityManager em = JPAUtil.getEntityManager();
		assertTrue(em.contains(u));	
	}
	
	@Test
	public void testLoginCheck2() throws CustomException  {
		UserDao udao = new UserDaoImpl();
		UserRole y = new UserRole();
		y = udao.loginCheck("Diya", "Diya123");
		EntityManager em = JPAUtil.getEntityManager();
		assertTrue(em.contains(y));	
	}
	
	@Test
	public void testCreateProfile() throws CustomException {
		
		UserRole u= new UserRole("Diya","Diya123","insurer");
		UserDao udao = new UserDaoImpl();
		udao.createProfile(u);
		EntityManager em = JPAUtil.getEntityManager();
		assertTrue(em.contains(u));	
	}
	@Test
	public void testCreateProfile2() throws CustomException {
		
		UserRole u= new UserRole("Riya","Riya123","insurer");
		UserDao udao = new UserDaoImpl();
		udao.createProfile(u);
		EntityManager em = JPAUtil.getEntityManager();
		assertTrue(em.contains(u));	
	}


}
