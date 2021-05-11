package com.capgemini.quote.service;

import java.util.List;

import com.capgemini.quote.bean.BusinessSegment;
import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.dao.PolicyDao;
import com.capgemini.quote.dao.PolicyDaoImpl;

//Class contains the functionalities of Service Policy
public class PolicyServiceImpl implements PolicyService{
	
	PolicyDao pDao;
	
	public PolicyServiceImpl() {
		pDao = new PolicyDaoImpl();
	}

	//Get List of Policy Question by Business Segment Id
	@Override
	public List<PolicyQuestion> getPolicyQuestionBySegId(String busSegId) {
		List<PolicyQuestion> polQuesList = pDao.getPolicyQuestionBySegId(busSegId);
		return polQuesList;
	}

	//Set Policy Details to Policy Details Table
	@Override
	public String setPolicyDetails(PolicyDetails pDetails) {
		String setPolDet = pDao.setPolicyDetails(pDetails);
		return setPolDet;
	}

	//Get List of All Business Segment from Dao Layer
	@Override
	public List<BusinessSegment> getBsList() {
		List<BusinessSegment> bsList = pDao.getBsList();
		return bsList;
	}

	//Create New Policy
	@Override
	public Policy createPolicy(Policy pol) {
		Policy cPolicy = pDao.createPolicy(pol);
		return cPolicy;
	}

	//Calculate Premium
	@Override
	public int calculatePremium(long policyNo) {
		int calPremium = pDao.calculatePremium(policyNo);
		return calPremium;
	}

	//Get Policy List by Account Number
	@Override
	public List<Policy> getPolicyByAccNo(long accountNo) {
		List<Policy> getPolByAcc = pDao.getPolicyByAccNo(accountNo);
		return getPolByAcc;
	}

}
