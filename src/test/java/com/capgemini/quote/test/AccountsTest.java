package com.capgemini.quote.test;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.capgemini.quote.dao.AccountsDaoImpl;
import com.capgemini.quote.util.JPAUtil;
import com.capgemini.quote.bean.Accounts;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.dao.AccountsDao;
public class AccountsTest {


@Test
public void testaddAccountByAgent() throws CustomException{
   Accounts a = new Accounts("Uday","21","Faridabad","Haryana",12100,"R","Uday","Himanshu");
	AccountsDao adao =new AccountsDaoImpl();
	adao.accountCreation(a);
	
	EntityManager em = JPAUtil.getEntityManager();
	assertTrue(em.contains(a));
   
}
@Test
public void testaddAccountByInsurer() throws CustomException{
	Accounts a1 = new Accounts("Dwight","47","Gurgaon","Haryana",12150,"P","Dwight","Dwight");
	AccountsDao adao =new AccountsDaoImpl();
	adao.accountCreation(a1);
	
	EntityManager em = JPAUtil.getEntityManager();
	assertTrue(em.contains(a1));
}
@Test
public void testaddAccountByAdmin() throws CustomException{
	Accounts a2 = new Accounts("Barney","86","Queens","NewYork",11150,"A","Barney","Sultan");
	AccountsDao adao =new AccountsDaoImpl();
	adao.accountCreation(a2);
	
	EntityManager em = JPAUtil.getEntityManager();
	assertTrue(em.contains(a2));
}
}
