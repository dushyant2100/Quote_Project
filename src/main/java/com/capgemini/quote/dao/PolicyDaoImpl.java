package com.capgemini.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.capgemini.quote.bean.BusinessSegment;
import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.util.JPAUtil;

//Class contains the functionalities of Dao Policy
public class PolicyDaoImpl implements PolicyDao{
	
	Logger myLogger =  Logger.getLogger(PolicyDaoImpl.class.getName());
	
	private EntityManager em;
	
	public PolicyDaoImpl() {
		em = JPAUtil.getEntityManager();
	}

    //Get Policy Question by Business Segment Id
	@Override
	public List<PolicyQuestion> getPolicyQuestionBySegId(String busSegId) {		
		String qStr = "SELECT pQ FROM PolicyQuestion pQ WHERE pQ.busSegId=:pbussegid";
		TypedQuery<PolicyQuestion> query = em.createQuery(qStr, PolicyQuestion.class);
		query.setParameter("pbussegid", busSegId);
		List<PolicyQuestion> polQuesList = query.getResultList();
		myLogger.info("Policy Question Fetched By BusSegId");
		return polQuesList;
	}

    //Set Policy Details in Database
	@Override
	public String setPolicyDetails(PolicyDetails polDetails) {
		em.getTransaction().begin();
		em.persist(polDetails);
		em.getTransaction().commit();
		myLogger.info("Policy Details been set");
        return "";
    }

	//Get All Business Segment List
	@Override
	public List<BusinessSegment> getBsList() {
		Query query = em.createNamedQuery("getAllBusinessSegment");
		@SuppressWarnings("unchecked")
		List<BusinessSegment> bsList = query.getResultList();
		myLogger.info("All Business Segment fetched");
		return bsList;
	}

    //Create New Policy
	@Override
	public Policy createPolicy(Policy policy) {
		em.getTransaction().begin();
		em.persist(policy);
		em.getTransaction().commit();
		myLogger.info("New Policy created");	
		return policy;
	}

	//Calculate Premium for Policy
	@Override
	public int calculatePremium(long policyNo) {
		String qStr = "SELECT pQ FROM PolicyDetails pQ WHERE pQ.policyNumber=:pPolicyNo";
		TypedQuery<PolicyDetails> query = em.createQuery(qStr, PolicyDetails.class);
		query.setParameter("pPolicyNo", policyNo);
		List<PolicyDetails> polList = query.getResultList();
		
		int sum=0;
		for(PolicyDetails p:polList) {
			sum= sum+p.getAnswer();
		}	
		myLogger.info("Policy Premium calculated and set to Policy Table");
		return sum;
	}

	//Get Policy by Account Number
	@Override
    public List<Policy> getPolicyByAccNo(long accountNo) {
        String qStr = "SELECT policy FROM Policy policy WHERE policy.accountNumber=:pAccountNo";
        TypedQuery<Policy> query = em.createQuery(qStr, Policy.class);
        query.setParameter("pAccountNo", accountNo);
        List<Policy> policyList = query.getResultList();
        myLogger.info("Policy List fetched by Account Number");
        return policyList;
    }	

}
