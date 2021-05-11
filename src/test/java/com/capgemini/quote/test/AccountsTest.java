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
   Accounts a = new Accounts("Uday","HomeStreet","Faridabad","Haryana",12100,"R","Uday","Uday");
	AccountsDao adao =new AccountsDaoImpl();
	adao.accountCreation(a);
	
	EntityManager em = JPAUtil.getEntityManager();
	assertTrue(em.contains(a));
   
}
@Test
public void testaddAccountByInsurer() throws CustomException{
	Accounts a1 = new Accounts("Sultan","NewGali","Gurgaon","Haryana",12150,"A","Sultan","Sultan");
	AccountsDao adao =new AccountsDaoImpl();
	adao.accountCreation(a1);
	
	EntityManager em = JPAUtil.getEntityManager();
	assertTrue(em.contains(a1));
}
@Test
public void testaddAccountByAdmin() throws CustomException{
	Accounts a2 = new Accounts("Himanshu","NewDrive","Queens","NewYork",11150,"A","Himanshu","Drake");
	AccountsDao adao =new AccountsDaoImpl();
	adao.accountCreation(a2);
	
	EntityManager em = JPAUtil.getEntityManager();
	assertTrue(em.contains(a2));
}
}
