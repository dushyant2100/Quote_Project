package com.capgemini.quote.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.capgemini.quote.bean.BusinessSegment;
import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.dao.PolicyDao;
import com.capgemini.quote.dao.PolicyDaoImpl;
import com.capgemini.quote.util.JPAUtil;


public class PolicyTest {
	
	@Test
	public void testBusinessSegment() {
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("getAllBusinessSegment");
		@SuppressWarnings("unchecked")
		List<BusinessSegment> bsList = query.getResultList();
		
		assertTrue(em.contains(bsList.get(0)));
	}

	@Test
	public void testCreatePolicy() {
		Policy pc = new Policy(3200,1000000002);
		PolicyDao pdao = new PolicyDaoImpl(); 
		pdao.createPolicy(pc);
		EntityManager em = JPAUtil.getEntityManager();
		
		assertTrue(em.contains(pc));	
	}

	@Test
	public void testPolicyQuestions() {
		EntityManager em = JPAUtil.getEntityManager();
		String busSegId = "B"; // UserInput
		String qStr = "SELECT pQ FROM PolicyQuestion pQ WHERE pQ.busSegId=:pbussegid";
		TypedQuery<PolicyQuestion> query = em.createQuery(qStr, PolicyQuestion.class);
		query.setParameter("pbussegid", busSegId);
		List<PolicyQuestion> pQL = query.getResultList();
		
		assertTrue(em.contains(pQL.get(0)));
	}
	
	@Test
	public void testCalculatePremium() {
		PolicyDao pdao = new PolicyDaoImpl();
		long policyNumber = 100002; // Change Policy number here(User Input)
		
		double policypremium = pdao.calculatePremium(policyNumber);
		
		assertTrue(3200.0 == policypremium);
	}

}
