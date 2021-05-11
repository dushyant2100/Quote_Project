package com.capgemini.quote.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.capgemini.quote.bean.Accounts;
import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.dao.AccountsDao;
import com.capgemini.quote.dao.AccountsDaoImpl;
import com.capgemini.quote.dao.ViewDao;
import com.capgemini.quote.dao.ViewDaoImpl;
import com.capgemini.quote.util.JPAUtil;

public class ViewTest {
	@Test
	public void testViewPolicy() throws CustomException{
		ViewDao vDao = new ViewDaoImpl();
		long polNo = 100008; // User Input
		Policy pol = new Policy();
		pol = vDao.getPolicyByPolicyNo(polNo);
		EntityManager em = JPAUtil.getEntityManager();
		assertTrue(em.contains(pol));
	}
	
	@Test
	public void testGenerateReport() throws CustomException{
		EntityManager em = JPAUtil.getEntityManager();
		
		ViewDao vDao = new ViewDaoImpl();
		long polNo = 100008;
		Policy pol = new Policy();
		pol = vDao.getPolicyByPolicyNo(polNo);
		
		AccountsDao aDao = new AccountsDaoImpl();
		Accounts accD = aDao.getAccountbyAccNo(pol.getAccountNumber());
		
		List<PolicyDetails> pDetails = vDao
				.getPolicyDetailsByPolNo((int) pol.getPolicyNumber());
		assertTrue(em.contains(accD));
		assertTrue(em.contains(pDetails.get(0)));
	}
}
