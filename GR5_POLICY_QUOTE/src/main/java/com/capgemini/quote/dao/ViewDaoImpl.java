package com.capgemini.quote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.util.JPAUtil;

//Class contains the functionalities of Dao View
public class ViewDaoImpl implements ViewDao{
	
	Logger myLogger =  Logger.getLogger(ViewDaoImpl.class.getName());
	
	private EntityManager em;

	public ViewDaoImpl() {
		em = JPAUtil.getEntityManager();
	}

	//Get Policy List by Account Number
	@Override
	public List<Policy> getPolicybyAccNo(long accountNo) {
		String qstr = "select policy from Policy policy where policy.accountNumber=:pAccNo";
		TypedQuery<Policy> query = em.createQuery(qstr,Policy.class);
		query.setParameter("pAccNo", accountNo);
		List<Policy> polList = query.getResultList();
		myLogger.info("Policy List fetched by Account Number ");
		return polList;
	}
	
	//Get Policy by Policy Number
	@Override
    public Policy getPolicyByPolicyNo(long policyNumber) throws CustomException {
        Policy policy = em.find(Policy.class, policyNumber);
        myLogger.info("Policy checked by Policy Number");
        return policy;
    }

	//Get Policy Details by Policy Number
	@Override
    public List<PolicyDetails> getPolicyDetailsByPolNo(long polNo) {
        String qStr = "SELECT policyDetails FROM PolicyDetails policyDetails WHERE policyDetails.policyNumber=:pPolNo";
        TypedQuery<PolicyDetails> query = em.createQuery(qStr, PolicyDetails.class);
        query.setParameter("pPolNo", polNo);
        List<PolicyDetails> polDetList = query.getResultList();
        myLogger.info("Policy Details List fetched");
        return polDetList;
    }

    //Get Policy Question by Question ID
    @Override
    public PolicyQuestion getPolQuesByQId(String qId) {
        PolicyQuestion polQues = em.find(PolicyQuestion.class, qId);
        myLogger.info("Policy Question fetched by question ID");
        return polQues;
    }


}
