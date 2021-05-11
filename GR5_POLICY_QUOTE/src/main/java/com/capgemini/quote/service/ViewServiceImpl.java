package com.capgemini.quote.service;

import java.util.List;
import java.util.regex.Pattern;

import com.capgemini.quote.bean.Policy;
import com.capgemini.quote.bean.PolicyDetails;
import com.capgemini.quote.bean.PolicyQuestion;
import com.capgemini.quote.customexp.CustomException;
import com.capgemini.quote.dao.ViewDao;
import com.capgemini.quote.dao.ViewDaoImpl;

//Class contains the functionalities of Service View
public class ViewServiceImpl implements ViewService{
	
	ViewDao vDao;
	
	public ViewServiceImpl() {
		vDao = new ViewDaoImpl();
	}

	
	//Get List of Policy by Account Number
	@Override
	public List<Policy> getPolicybyAccNo(long accNo) {
		List<Policy> getPolicyByAcc = vDao.getPolicybyAccNo(accNo);
		return getPolicyByAcc;
	}

	//Get Policy by Policy Number
	@Override
	public Policy getPolicyByPolicyNo(long polNo) throws CustomException {
		if(Pattern.matches("^[0-9]{6}$", String.valueOf(polNo))) {
		Policy getPolicyByPol = vDao.getPolicyByPolicyNo(polNo);
		if(getPolicyByPol!=null)
			return getPolicyByPol;
		else
			throw new CustomException("Policy Number does not exist.");
		} else
			throw new CustomException("Policy Number not inserted as instructed.");
	}

	//Get List of Policy Details by Policy Number
	@Override
	public List<PolicyDetails> getPolicyDetailsByPolNo(long polNo) {
		List<PolicyDetails> getPolicyListByPol = vDao.getPolicyDetailsByPolNo(polNo);
		return getPolicyListByPol;
	}

	//Get Policy Question by Question Id
	@Override
	public PolicyQuestion getPolQuesByQId(String qId) {
		PolicyQuestion polQues = vDao.getPolQuesByQId(qId);
		return polQues;
	}

}
